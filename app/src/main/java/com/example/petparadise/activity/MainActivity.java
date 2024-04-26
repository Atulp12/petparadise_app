package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_LIST;
import static com.example.petparadise.utils.AppConstantsAndUtils.PACKAGES;
import static com.example.petparadise.utils.AppConstantsAndUtils.SELECT_PACKAGE;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.CustomerListModel;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    CheckBox ch1,ch2,ch3,ch4,ch5;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);
        ch1 = findViewById(R.id.ck1);
        ch2 = findViewById(R.id.ck2);
        ch3 = findViewById(R.id.ck3);
        ch4 = findViewById(R.id.ck4);
        ch5 = findViewById(R.id.ck5);

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
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    return true;
                }
                return false;
            }
        });

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageSelect("Complete Package");

            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageSelect("Washing Service");
            }
        });

        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageSelect("Hygiene Cut");
            }
        });

        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageSelect("Body Trim");
            }
        });

        ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageSelect("Face Design");
            }
        });




    }

    void packageSelect(String packageName){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("cust_id","");
            requestJson.put("packages",packageName);
            new NetworkRequestUtil(this).postData(SELECT_PACKAGE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            MyApplication.getInstance().addToSharedPreference(PACKAGES,packageModel.packages);
                            startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

        }
    }



}

