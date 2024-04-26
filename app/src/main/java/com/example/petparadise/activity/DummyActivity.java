package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_UPDATE;
import static com.example.petparadise.utils.AppConstantsAndUtils.SIGNUP_URL;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.models.SignupModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class DummyActivity extends AppCompatActivity {
    Button submitBtn;
    Spinner spin;
    TextInputEditText userName,mobileNo,password,email;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        spin = findViewById(R.id.branchSpin);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.select_branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);

        submitBtn = findViewById(R.id.submitBtn);
        userName = findViewById(R.id.userName);
        mobileNo = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);
        email = findViewById(R.id.emailId);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    void signup(){
        final JSONObject requestJson;
        try{
            requestJson = new JSONObject();
            requestJson.put("cust_id", "4653");
            requestJson.put("branch_id", "1");
            requestJson.put("Customer_Name",userName.getText().toString());
            requestJson.put("Contact_No",mobileNo.getText().toString());
            requestJson.put("Address",email.getText().toString());
            requestJson.put("pets_name",spin.getSelectedItem().toString());
            requestJson.put("dob",password.getText().toString());
            requestJson.put("breed","breed");
            requestJson.put("type","Medium");
            requestJson.put("reminder","dfsdf");
            requestJson.put("status_wait","dsd");
            requestJson.put("date","555d");

            new NetworkRequestUtil(this).postData(CUSTOMER_UPDATE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    SignupModel signupModel = new Gson().fromJson(response.toString(), SignupModel.class);
                    if(signupModel != null){
                        if(signupModel.getSuccess().equals(STATUS_SUCCESS)){
                            Toast.makeText(DummyActivity.this, "Register Successfully! Please login with same credentials.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DummyActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onError(VolleyError error) {
                    printLog("error Response:" + error);
                }
            });

        } catch (Exception e){

        }
    }

    void validate(){
        if(userName.getText().toString().isEmpty() || mobileNo.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty() || spin.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else{
            signup();
        }
    }



}