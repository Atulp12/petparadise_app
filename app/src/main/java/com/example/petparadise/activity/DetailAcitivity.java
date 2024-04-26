package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_FETCH;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_UPDATE;
import static com.example.petparadise.utils.AppConstantsAndUtils.LOGIN_URL;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.USER_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.WAIT;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.CustomerUpdateResult;
import com.example.petparadise.models.FetchCustomerModel;
import com.example.petparadise.models.LoginModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailAcitivity extends AppCompatActivity {

    TextInputEditText petNameTxt,breedTxt,birthdayTxt,custNameTxt,phoneTxt,addressTxt;
    TextInputEditText reminderTxt;
    Button startBtn;
    Spinner statusSpin;
    RadioGroup radioGroup;
    RadioButton radioButton,smallRadio,mediumRadio,largeRadio;
    String currentDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivity);

        fetchCustomer();

        petNameTxt = findViewById(R.id.petNameTxt);
        breedTxt = findViewById(R.id.breedTxt);
        birthdayTxt = findViewById(R.id.birthdayTxt);
        reminderTxt = findViewById(R.id.reminderEditText);
        custNameTxt = findViewById(R.id.custNameTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        addressTxt = findViewById(R.id.addressTxt);
        statusSpin = findViewById(R.id.statusSpin);
        radioGroup = findViewById(R.id.radioGrp);
        startBtn = findViewById(R.id.startBtn);
        smallRadio = findViewById(R.id.small);
        mediumRadio = findViewById(R.id.medium);
        largeRadio = findViewById(R.id.large);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.select_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpin.setAdapter(adapter);
        if(statusSpin.getItemAtPosition(1).equals(1)){
            Toast.makeText(this, "Available", Toast.LENGTH_SHORT).show();
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = sdf.format(new Date());

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void updateData() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("cust_id", MyApplication.getInstance().getFromSharedPreference(CUSTOMER_ID));
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("Customer_Name", custNameTxt.getText().toString().trim());
            requestJson.put("Contact_No", phoneTxt.getText().toString().trim());
            requestJson.put("Address", addressTxt.getText().toString().trim());
            requestJson.put("pets_name", petNameTxt.getText().toString().trim());
            requestJson.put("breed", breedTxt.getText().toString().trim());
            requestJson.put("dob", birthdayTxt.getText().toString().trim());
            requestJson.put("type", radioButton.getText().toString());
            requestJson.put("reminder",reminderTxt.getText().toString());;

            if(statusSpin.getSelectedItem().equals("Available")){
                requestJson.put("status_wait", "Available");

            }
            else{
                requestJson.put("status_wait", "Waiting");
            }

            requestJson.put("date", currentDate);
            new NetworkRequestUtil(DetailAcitivity.this).postData(CUSTOMER_UPDATE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    CustomerUpdateResult commonModel = new Gson().fromJson(response.toString(), CustomerUpdateResult.class);
                    if (commonModel != null) {
                        if (commonModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(DetailAcitivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
//                            gotoHome();

                            //gotoHome();
                            //getAllOutletList();
                        } else {
                            Toast.makeText(DetailAcitivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //
                        //showDialogWithOkButton(getString(R.string.error_someting_went_wrong));
                        Toast.makeText(DetailAcitivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
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

    void fetchCustomer(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("cust_id",MyApplication.getInstance().getFromSharedPreference(CUSTOMER_ID));
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            new NetworkRequestUtil(this).postData(CUSTOMER_FETCH, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    FetchCustomerModel fetchCustomerModel = new Gson().fromJson(response.toString(), FetchCustomerModel.class);
                    if (fetchCustomerModel != null) {
                        if (fetchCustomerModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            petNameTxt.setText(fetchCustomerModel.getResult().getPets_name());
                            breedTxt.setText(fetchCustomerModel.getResult().getBreed());
                            birthdayTxt.setText(fetchCustomerModel.getResult().getDob());
                            custNameTxt.setText(fetchCustomerModel.getResult().getCustomer_Name());
                            phoneTxt.setText(fetchCustomerModel.getResult().getContact_No());
                            addressTxt.setText(fetchCustomerModel.getResult().getAddress());
                            if(fetchCustomerModel.getResult().getType().equals("Small")){
                                smallRadio.setChecked(true);
                            } else if (fetchCustomerModel.getResult().getType().equals("Medium")) {
                                mediumRadio.setChecked(true);
                            }
                            else{
                                largeRadio.setChecked(true);
                            }
                            MyApplication.getInstance().addToSharedPreference(CUSTOMER_ID, fetchCustomerModel.getResult().getCust_id());


                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(DetailAcitivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
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