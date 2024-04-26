package com.example.petparadise.activity.staff;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.TABLE_NO;
import static com.example.petparadise.utils.AppConstantsAndUtils.TRACK_LIST;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.adapter.TableAssignListAdapter;
import com.example.petparadise.adapter.TrackListAdapter;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.TrackListModel;
import com.example.petparadise.models.TrackTableData;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

public class AssignTable extends AppCompatActivity {
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_table);
        recyclerView = findViewById(R.id.tableAssignRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getTrackList();
    }

    public void getTrackList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
//            requestJson.put("status_wait","Waiting");
            new NetworkRequestUtil().postData(TRACK_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    TrackListModel trackListModel = new Gson().fromJson(response.toString(), TrackListModel.class);
                    if (trackListModel != null) {
                        if (trackListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            showList(trackListModel);
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(AssignTable.this, "An error occured", Toast.LENGTH_SHORT).show();
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
//            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            printLog(e.toString());
        }
    }

    public void showList(final TrackListModel trackListModel){
        final TableAssignListAdapter tableAssignListAdapter =  new TableAssignListAdapter(AssignTable.this, trackListModel.getResult().getTable_data(), new TableAssignListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TrackTableData getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(TrackTableData getData, Button button, String pos) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.getInstance().addToSharedPreference(TABLE_NO,getData.getTable_no());
                        startActivity(new Intent(AssignTable.this, GroomingActivity.class));
                    }
                });
            }



        });
        recyclerView.setAdapter(tableAssignListAdapter);
    }
}