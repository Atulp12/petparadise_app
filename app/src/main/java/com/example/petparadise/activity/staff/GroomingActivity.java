package com.example.petparadise.activity.staff;

import static com.example.petparadise.utils.AppConstantsAndUtils.ADD_GROOMING1;
import static com.example.petparadise.utils.AppConstantsAndUtils.ADD_GROOMING_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.AUTO_INCREMENT;
import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.DRY_SERVICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.FETCH_PACKAGE;
import static com.example.petparadise.utils.AppConstantsAndUtils.GROOM_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.HAIR_SERVICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.LOGIN_URL;
import static com.example.petparadise.utils.AppConstantsAndUtils.PACKAGES;
import static com.example.petparadise.utils.AppConstantsAndUtils.PET_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.START_TIME;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_LOGIN;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.STOP_SERVICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.TABLE_NO;
import static com.example.petparadise.utils.AppConstantsAndUtils.USER_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.WASHING_SERVICE;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.activity.LoginActivity;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.FetchPackageModel;
import com.example.petparadise.models.LoginModel;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroomingActivity extends AppCompatActivity {
    TextInputEditText petNameGM,dateGM,timeGM,remarkGM,signatureGM;

     String packages;
    String currentTime;
    String currentDate;

    Button startBtn,stopBtn,saveBtn;
    TextView tableNoGm;

    LinearLayout washingLinear,dryingLinear,hairLinear;

    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10,chk11,chk12,chk13,chk14,opChk1,opChk2,opChk3,opChk4,opChk5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming);
        initView();
        fetchPackage();
        autoIncrementGroomingId();
//        addGroomingId();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStartTime();
                startBtn.setText("Service Started");
            }
        });

        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWashingService();
            }
        });

        chk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDryService();
            }
        });

        chk13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHairService();
            }
        });


        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(GroomingActivity.this, packages+" ", Toast.LENGTH_SHORT).show();
                if(packages.equals("Hygiene Cut")){
                    hairValidate();
                    hygieneCutValidate();

                }

                if (packages.equals("Body Trim")) {
                    bodyTrimValidate();

                }
                if (packages.equals("Face Design")) {
                    faceDesignValidate();

                }
                if (packages.equals("Washing Service")) {
                   washingValidate();

                }
                if (packages.equals("Complete Package")) {
                    washingValidate();
                    hygieneCutValidate();

                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroomingId1Api();
            }
        });

    }

    void washingChecked(){
        if(!chk1.isChecked() || !chk2.isChecked() || !chk3.isChecked() || !chk4.isChecked() || !chk5.isChecked() || !chk6.isChecked() || !chk7.isChecked() || !chk8.isChecked() || !chk9.isChecked() || !chk10.isChecked() || !chk11.isChecked() || !chk12.isChecked()){
            Toast.makeText(this, "Please select all the items", Toast.LENGTH_SHORT).show();
        } else{
            stopServiceApi();
        }
    }

    void washingValidate(){

       washingChecked();

        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk1.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Dirt Cleaning Shampoo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk1.isChecked() && !chk2.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Dirt Cleaning Shampoo", Toast.LENGTH_SHORT).show();
                    Toast.makeText(GroomingActivity.this, "Select Inner Coat Refreshment Shampoo", Toast.LENGTH_SHORT).show();
                } else if ( !chk1.isChecked() && chk2.isChecked() ){
                    Toast.makeText(GroomingActivity.this, "Select Dirt Cleaning Shampoo", Toast.LENGTH_SHORT).show();
                } else if (chk1.isChecked() && !chk2.isChecked()) {
                    Toast.makeText(GroomingActivity.this, "Select Inner Coat Refreshment Shampoo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!chk1.isChecked() && !chk2.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Dirt Cleaning Shampoo", Toast.LENGTH_SHORT).show();
                    Toast.makeText(GroomingActivity.this, "Select Inner Coat Refreshment Shampoo", Toast.LENGTH_SHORT).show();
                } else if ( !chk1.isChecked() && chk2.isChecked() ){
                    Toast.makeText(GroomingActivity.this, "Select Dirt Cleaning Shampoo", Toast.LENGTH_SHORT).show();
                } else if (chk1.isChecked() && !chk2.isChecked()) {
                    Toast.makeText(GroomingActivity.this, "Select Inner Coat Refreshment Shampoo", Toast.LENGTH_SHORT).show();
                } else if (!chk3.isChecked()) {
                    Toast.makeText(GroomingActivity.this, "Select Conditioner", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk5.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Body", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk6.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Inner Area", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk7.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Nail Cut", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk8.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Eye cleaning", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk9.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Ear cleaning", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk10.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Mouth Spray", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chk12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk11.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Hair Serum", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void hairValidate(){
        if(!chk13.isChecked() || !chk14.isChecked()){
            Toast.makeText(GroomingActivity.this, "Select Hair Styling items", Toast.LENGTH_SHORT).show();
        }
        else{
            stopServiceApi();
        }

    }

    void hygieneCutValidate(){
        chk14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chk13.isChecked()){
                    Toast.makeText(GroomingActivity.this, "Select Hygiene Cut", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void bodyTrimValidate(){
        if(!opChk5.isChecked()){
            Toast.makeText(GroomingActivity.this, "Select Body Trim", Toast.LENGTH_SHORT).show();
        } else{
            stopServiceApi();
        }
    }

    void faceDesignValidate(){
        if(!chk14.isChecked()){
            Toast.makeText(GroomingActivity.this, "Select Face Design", Toast.LENGTH_SHORT).show();
        } else{
            stopServiceApi();
        }
    }

    void initView(){
        washingLinear = findViewById(R.id.washingLinear);
        dryingLinear = findViewById(R.id.dryingLinear);
        hairLinear = findViewById(R.id.hairLinear);
        tableNoGm = findViewById(R.id.tableNoGM);
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);
        chk6 = findViewById(R.id.chk6);
        chk7 = findViewById(R.id.chk7);
        chk8 = findViewById(R.id.chk8);
        chk9 = findViewById(R.id.chk9);
        chk10 = findViewById(R.id.chk10);
        chk11 = findViewById(R.id.chk11);
        chk12 = findViewById(R.id.chk12);
        chk13 = findViewById(R.id.chk13);
        chk14 = findViewById(R.id.chk14);
        opChk1 = findViewById(R.id.optChk1);
        opChk2 = findViewById(R.id.optChk2);
        opChk3 = findViewById(R.id.optChk3);
        opChk4 = findViewById(R.id.optChk4);
        opChk5 = findViewById(R.id.optChk21);
        petNameGM = findViewById(R.id.petNameGM);
        dateGM = findViewById(R.id.dateGM);
        timeGM = findViewById(R.id.timeGM);
        remarkGM = findViewById(R.id.remarkEditTxt);
        signatureGM = findViewById(R.id.signature);
        startBtn = findViewById(R.id.startServiceBtn);
        stopBtn = findViewById(R.id.stopService);
        saveBtn = findViewById(R.id.saveGMBtn);

        petNameGM.setText(MyApplication.getInstance().getFromSharedPreference(PET_NAME));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = sdf.format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm");
        currentTime = sdf1.format(new Date());

        dateGM.setText(currentDate);
        timeGM.setText(currentTime);
        tableNoGm.setText(MyApplication.getInstance().getFromSharedPreference(TABLE_NO));


    }

    void fetchPackage(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("pets_name",MyApplication.getInstance().getFromSharedPreference(PET_NAME));
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            new NetworkRequestUtil(this).postData(FETCH_PACKAGE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    FetchPackageModel fetchPackageModel = new Gson().fromJson(response.toString(), FetchPackageModel.class);
                    if (fetchPackageModel != null) {
                        if (fetchPackageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            Toast.makeText(GroomingActivity.this, fetchPackageModel.getPackages()+" ", Toast.LENGTH_SHORT).show();
                            packages = fetchPackageModel.getPackages();
                            if(fetchPackageModel.getPackages().equals("Complete Package")){

                            } else if (fetchPackageModel.getPackages().equals("Washing Service")) {
                                hairLinear.setVisibility(View.GONE);
                            } else if(fetchPackageModel.getPackages().equals("Hygiene Cut")){
                                washingLinear.setVisibility(View.GONE);
                                dryingLinear.setVisibility(View.GONE);
                            } else if (fetchPackageModel.getPackages().equals("Body Trim")) {
                                washingLinear.setVisibility(View.GONE);
                                dryingLinear.setVisibility(View.GONE);
                                chk13.setVisibility(View.GONE);
                                chk14.setVisibility(View.GONE);
                            } else if (fetchPackageModel.getPackages().equals("Face Design")) {
                                washingLinear.setVisibility(View.GONE);
                                dryingLinear.setVisibility(View.GONE);
                                opChk5.setVisibility(View.GONE);
                                chk13.setVisibility(View.GONE);
                            }

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void addGroomingId(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            requestJson.put("pets_name",petNameGM.getText().toString());
            requestJson.put("table_no",MyApplication.getInstance().getFromSharedPreference(TABLE_NO));
            new NetworkRequestUtil(this).postData(ADD_GROOMING_ID, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    FetchPackageModel fetchPackageModel = new Gson().fromJson(response.toString(), FetchPackageModel.class);
                    if (fetchPackageModel != null) {
                        if (fetchPackageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            Toast.makeText(GroomingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void autoIncrementGroomingId(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            new NetworkRequestUtil(this).postData(AUTO_INCREMENT, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    FetchPackageModel fetchPackageModel = new Gson().fromJson(response.toString(), FetchPackageModel.class);
                    if (fetchPackageModel != null) {
                        if (fetchPackageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            MyApplication.getInstance().addToSharedPreference(GROOM_ID,fetchPackageModel.getGrooming_id());
                            addGroomingId();
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void updateStartTime(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            Toast.makeText(GroomingActivity.this, MyApplication.getInstance().getFromSharedPreference(GROOM_ID)+ " ", Toast.LENGTH_SHORT).show();
            requestJson.put("start_time",currentTime);
            new NetworkRequestUtil(this).postData(START_TIME, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Service Started", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, packageModel.getMessage() + " ", Toast.LENGTH_SHORT).show();
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

    void updateWashingService(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("table_no",MyApplication.getInstance().getFromSharedPreference(TABLE_NO));
//            Toast.makeText(GroomingActivity.this, MyApplication.getInstance().getFromSharedPreference(GROOM_ID)+ " ", Toast.LENGTH_SHORT).show();
            requestJson.put("washing_service","1");
            new NetworkRequestUtil(this).postData(WASHING_SERVICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Washing Service Started", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void updateDryService(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            requestJson.put("table_no",MyApplication.getInstance().getFromSharedPreference(TABLE_NO));
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
//            Toast.makeText(GroomingActivity.this, MyApplication.getInstance().getFromSharedPreference(GROOM_ID)+ " ", Toast.LENGTH_SHORT).show();
            requestJson.put("drying_cleaning","1");
            new NetworkRequestUtil(this).postData(DRY_SERVICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Drying and Cleaning Service Started", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void updateHairService(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            requestJson.put("table_no",MyApplication.getInstance().getFromSharedPreference(TABLE_NO));
            requestJson.put("branch_id",MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
//            Toast.makeText(GroomingActivity.this, MyApplication.getInstance().getFromSharedPreference(GROOM_ID)+ " ", Toast.LENGTH_SHORT).show();
            requestJson.put("hair_styling","1");
            new NetworkRequestUtil(this).postData(HAIR_SERVICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Hair Styling Service Started", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void stopServiceApi(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("dirtCleanShampoo","1");
            requestJson.put("innerCoarRefShampoo","1");
            requestJson.put("antiDandruff","1");
            requestJson.put("antiItching","1");
            requestJson.put("antiFleas","1");
            requestJson.put("antiTicks","1");
            requestJson.put("conditioner","1");
            requestJson.put("hairSmoothShampoo","1");
            requestJson.put("body","1");
            requestJson.put("innerArea","1");
            requestJson.put("nailCut","1");
            requestJson.put("eyeCleaning","1");
            requestJson.put("mouthSpray","1");
            requestJson.put("hairSerum","1");
            requestJson.put("hairPerfume","1");
            requestJson.put("hygieneCut","1");
            requestJson.put("bodyTrim","1");
            requestJson.put("faceDesign","1");
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
            new NetworkRequestUtil(this).postData(STOP_SERVICE, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    FetchPackageModel fetchPackageModel = new Gson().fromJson(response.toString(), FetchPackageModel.class);
                    if (fetchPackageModel != null) {
                        if (fetchPackageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Service Stopped", Toast.LENGTH_SHORT).show();
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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

    void addGroomingId1Api(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));
//            Toast.makeText(GroomingActivity.this, MyApplication.getInstance().getFromSharedPreference(GROOM_ID)+ " ", Toast.LENGTH_SHORT).show();
            requestJson.put("grooming_date",currentDate);
            requestJson.put("remark",remarkGM.getText().toString());
            requestJson.put("signature",signatureGM.getText().toString());
            new NetworkRequestUtil(this).postData(ADD_GROOMING1, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(GroomingActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(GroomingActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
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