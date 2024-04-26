package com.example.petparadise.activity.staff;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.PET_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.WAITING_LIST;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.activity.HistoryActivity;
import com.example.petparadise.activity.LoginActivity;
import com.example.petparadise.activity.MainActivity;
import com.example.petparadise.activity.NotificationActivity;
import com.example.petparadise.adapter.WaitingListAdapter;
import com.example.petparadise.adapter.WaitingListStaffAdapter;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.WaitingListModel;
import com.example.petparadise.models.WaitingListResultModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);
        recyclerView = findViewById(R.id.waitingListSTRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getWaitingList();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    return true;
                }else if(item.getItemId() == R.id.notification){
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                    overridePendingTransition(0,0);

                    return true;
                }
                else if(item.getItemId() == R.id.customerHistory) {
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }else if(item.getItemId() == R.id.logout){
                    SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    public void getWaitingList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("status_wait","Waiting");
            new NetworkRequestUtil().postData(WAITING_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    WaitingListModel waitingListModel = new Gson().fromJson(response.toString(), WaitingListModel.class);
                    if (waitingListModel != null) {
                        if (waitingListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            showList(waitingListModel);
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(HomeActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //
                        //showDialogWithOkButton(getString(R.string.error_someting_went_wrong));
                    }

                }

                //@Override
                public void onError(VolleyError error) {
                    printLog("error Response:" + error);
                }
            });
        } catch (Exception e) {
            Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showList(final WaitingListModel waitingListModel){
        final WaitingListStaffAdapter waitingListStaffAdapter =  new WaitingListStaffAdapter(HomeActivity.this, waitingListModel.getResult(), new WaitingListStaffAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WaitingListResultModel getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(WaitingListResultModel getData, Button button, String pos) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getInstance().addToSharedPreference(PET_NAME,getData.getPets_name());
                        startActivity(new Intent(HomeActivity.this,AssignTable.class));

                    }
                });
            }



        });
        recyclerView.setAdapter(waitingListStaffAdapter);
    }
}