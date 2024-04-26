package com.example.petparadise.activity;

import static com.example.petparadise.utils.AppConstantsAndUtils.ADD_INVOICE;
import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUSTOMER_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.GROOM_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.NEXT_REMINDER;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.ImageUtil.printLog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LastActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 123;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageButton camera_open_id;
    ImageView click_image_id;

    TextView dateTxt;
    TextInputEditText endTime,reminderDay;
    Button completeBtn;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        camera_open_id = findViewById(R.id.camera_button);
        click_image_id = findViewById(R.id.click_image);
        endTime = findViewById(R.id.timeLS);
        reminderDay = findViewById(R.id.reminderDay);
        completeBtn = findViewById(R.id.completeGroomBtn);
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm");
        currentTime = sdf1.format(new Date());

        endTime.setText(currentTime);

        camera_open_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(LastActivity.this)
                        .cameraOnly()
                        .start();
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeGroom();

            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        click_image_id.setImageURI(uri);
    }

    void completeGroom(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("next_day_reminder",reminderDay.getText().toString());
            requestJson.put("final_status","1");
            requestJson.put("grooming_id",MyApplication.getInstance().getFromSharedPreference(GROOM_ID));

            new NetworkRequestUtil(this).postData(NEXT_REMINDER, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            Toast.makeText(LastActivity.this, "Data added", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LastActivity.this, HistoryActivity.class));


                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LastActivity.this, packageModel.getMessage() + " ", Toast.LENGTH_SHORT).show();
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