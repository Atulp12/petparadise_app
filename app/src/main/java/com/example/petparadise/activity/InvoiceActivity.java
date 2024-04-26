package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.ADD_INVOICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUST_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.GENERATE_INVOICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.GROOM_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.INVOICE_PACKAGE;
import static com.example.petparadise.utils.AppConstantsAndUtils.MOBILE_NO;
import static com.example.petparadise.utils.AppConstantsAndUtils.START_TIME;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.activity.staff.GroomingActivity;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceActivity extends AppCompatActivity {
    TextView date1,date2,custName,mobileNo,srNo,serviceTxt,timeTxt,totalTxt;
    String currentDate,currentTime,amountTxt;
    EditText invoice,amount;
    Button generateBtn, cancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        initView();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm");
        currentTime = sdf1.format(new Date());

        date1.setText(currentDate);
        date2.setText(currentDate);
        timeTxt.setText(currentTime);


        generateInvoice();

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInvoice();
            }
        });


    }

    void initView(){
        date1 = findViewById(R.id.dateIN);
        date2 = findViewById(R.id.dateTxtIN);
        custName = findViewById(R.id.custNmIN);
        mobileNo = findViewById(R.id.mobileIN);
        srNo = findViewById(R.id.srNo);
        serviceTxt = findViewById(R.id.serviceTxt);
        timeTxt = findViewById(R.id.timeIN);
        totalTxt = findViewById(R.id.totalIN);
        invoice = findViewById(R.id.invoiceNo);
        amount = findViewById(R.id.amountIN);
        generateBtn = findViewById(R.id.generateBtn);
        cancel = findViewById(R.id.cancelBtn);

        custName.setText(MyApplication.getInstance().getFromSharedPreference(CUST_NAME));
        mobileNo.setText(MyApplication.getInstance().getFromSharedPreference(MOBILE_NO));
        serviceTxt.setText(MyApplication.getInstance().getFromSharedPreference(INVOICE_PACKAGE));

        srNo.setText("01");
        amountTxt = amount.getText().toString();

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                totalTxt.setText(amount.getText().toString());
            }
        });




    }

    void generateInvoice(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("invoice_date",currentDate);
            requestJson.put("amount",amount.getText().toString());
            requestJson.put("cust_id",MyApplication.getInstance().getFromSharedPreference(CUSTOMER_ID));
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));

            new NetworkRequestUtil(this).postData(GENERATE_INVOICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            invoice.setText(packageModel.getInvoice_no());


                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(InvoiceActivity.this, packageModel.getMessage() + " ", Toast.LENGTH_SHORT).show();
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

    void addInvoice(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("invoice_date",currentDate);
            requestJson.put("invoice_no",invoice.getText().toString());
            requestJson.put("amount",amount.getText().toString());
            requestJson.put("cust_id",MyApplication.getInstance().getFromSharedPreference(CUSTOMER_ID));
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));

            new NetworkRequestUtil(this).postData(ADD_INVOICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

//                            Toast.makeText(InvoiceActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(InvoiceActivity.this, LastActivity.class));


                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(InvoiceActivity.this, packageModel.getMessage() + " ", Toast.LENGTH_SHORT).show();
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