package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_LIST;
import static com.example.petparadise.utils.AppConstantsAndUtils.PACKAGES;
import static com.example.petparadise.utils.AppConstantsAndUtils.SELECT_PACKAGE;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.adapter.CustomerListAdapter1;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.CustomerListModel;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.models.ResultModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        recyclerView = findViewById(R.id.custDetailRecyclerView);

        searchView = findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchCustomer();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void searchCustomer(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            //requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("keyword",searchView.getText().toString());

            new NetworkRequestUtil(this).postData(CUSTOMER_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    CustomerListModel customerListModel = new Gson().fromJson(response.toString(), CustomerListModel.class);
                    if (customerListModel != null) {
                        if (customerListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            showList(customerListModel);

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CustomerActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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


    public void getCustomerList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            //requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));

            new NetworkRequestUtil(this).postData(CUSTOMER_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    CustomerListModel customerListModel = new Gson().fromJson(response.toString(), CustomerListModel.class);
                    if (customerListModel != null) {
                        if (customerListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            showList(customerListModel);
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CustomerActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    public void showList(final CustomerListModel customerListModel){
        final CustomerListAdapter1 customerListAdapter1 =  new CustomerListAdapter1(this, customerListModel.getResult(), new CustomerListAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(ResultModel getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(ResultModel getData, Button button, String pos) {
                MyApplication.getInstance().addToSharedPreference(CUSTOMER_ID,getData.getCust_id());
//                Toast.makeText(MainActivity.this, getData.getCust_id()+ "", Toast.LENGTH_SHORT).show();
                MyApplication.getInstance().addToSharedPreference(BRANCH_ID,getData.getBranch_id());
//                Toast.makeText(MainActivity.this, getData.getBranch_id()+ "", Toast.LENGTH_SHORT).show();

                packageSelect();
                startActivity(new Intent(CustomerActivity.this, DetailAcitivity.class));
            }
        });
        recyclerView.setAdapter(customerListAdapter1);
    }

    void packageSelect(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("cust_id",MyApplication.getInstance().getFromSharedPreference(CUSTOMER_ID));
            requestJson.put("packages",MyApplication.getInstance().getFromSharedPreference(PACKAGES));
            new NetworkRequestUtil(this).postData(SELECT_PACKAGE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            MyApplication.getInstance().addToSharedPreference(PACKAGES,packageModel.packages);
//                            Toast.makeText(CustomerActivity.this, MyApplication.getInstance().getFromSharedPreference(PACKAGES) + " ", Toast.LENGTH_SHORT).show();


                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(CustomerActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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