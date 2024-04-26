package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.LOGIN_URL;
import static com.example.petparadise.utils.AppConstantsAndUtils.PREF_USER_LOGE_IN;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_LOGIN;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.USERNAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.USER_NAME;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;

import com.example.petparadise.activity.staff.HomeActivity;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.LoginModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.PrefManager;
import com.example.petparadise.utils.VolleyCallback;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ncorti.slidetoact.SlideToActView;

import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    TextInputEditText email,password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    SlideToActView slideBtn;
    CheckBox checkBox;

    TextView signupTxt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupTxt = findViewById(R.id.signupTxt);
        email = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        slideBtn = findViewById(R.id.slideLogin);
        checkBox = findViewById(R.id.checkBox);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean(KEY_REMEMBER, false))
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);

        email.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        password.setText(sharedPreferences.getString(KEY_PASS, ""));

        email.addTextChangedListener(this);
        password.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(this);



        if (!new PrefManager(this).isUserLogedOut()) {
            //user's email and password both are saved in preferences
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));

            }
        });

        slideBtn.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(@NonNull SlideToActView slideToActView) {
                validation();
            }
        });
    }

    public void login() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("username", MyApplication.getInstance().getFromSharedPreference(USER_NAME));
            requestJson.put("username",email.getText().toString());
            requestJson.put("password",password.getText().toString());
            new NetworkRequestUtil(this).postData(LOGIN_URL, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    LoginModel loginModel1 = new Gson().fromJson(response.toString(), LoginModel.class);
                    if (loginModel1 != null) {
                        if (loginModel1.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            MyApplication.getInstance().addToSharedPreference(BRANCH_ID,loginModel1.getResult().getBranch_id());
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            MyApplication.getInstance().addToSharedPreference(STATUS_LOGIN, loginModel1.getResult().getStatus());
                            MyApplication.getInstance().addToSharedPreference(STATUS, loginModel1.getResult().getStatus());


                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            if (loginModel1.getResult().getStatus().equalsIgnoreCase("admin")) {
                                gotoHome();
                            } else {
                                gotoHome1();
                            }
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
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

    private void gotoHome() {
//        showProgress(false);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void gotoHome1() {
//        showProgress(false);
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();
    }

    private void managePrefs() {
        if (checkBox.isChecked()) {
            editor.putString(KEY_USERNAME, email.getText().toString().trim());
            editor.putString(KEY_PASS, password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getInstance().clearAllPreferences();
        MyApplication.getInstance().addBooleanToSharedPreference(PREF_USER_LOGE_IN, false);
        /* Intent intent = new Intent(DashboardStaffActivity.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        finish();
    }

    @Override
    protected String getTag() {
        return null;
    }


    public void validation(){
        if(email.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if(password.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
        } else{
            login();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

    }
}