package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.SIGNUP_URL;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

public class SignupActivity extends AppCompatActivity {

    Button submitBtn;
    Spinner spin;
    TextInputEditText userName,mobileNo,password,email;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spin = findViewById(R.id.branchSpin);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.select_branch, android.R.layout.simple_spinner_item);
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
                validate();
            }
        });

    }

    void signup(){
        final JSONObject requestJson;
        try{
            requestJson = new JSONObject();
            requestJson.put("username",userName.getText().toString());
            requestJson.put("mobile",mobileNo.getText().toString());
            requestJson.put("email",email.getText().toString());
            requestJson.put("branch_name",spin.getSelectedItem().toString());
            requestJson.put("password",password.getText().toString());
            requestJson.put("status","staff");

            new NetworkRequestUtil(this).postData(SIGNUP_URL, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) throws JSONException {
                    SignupModel signupModel = new Gson().fromJson(response.toString(), SignupModel.class);
                    if(signupModel != null){
                        if(signupModel.getSuccess().equals(STATUS_SUCCESS)){
                            Toast.makeText(SignupActivity.this, "Register Successfully! Please login with same credentials.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignupActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
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