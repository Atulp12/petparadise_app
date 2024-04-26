package com.example.petparadise.Fragments;

import static com.example.petparadise.utils.AppConstantsAndUtils.BRANCH_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.CUST_NAME;
import static com.example.petparadise.utils.AppConstantsAndUtils.DONE_GROOMING;
import static com.example.petparadise.utils.AppConstantsAndUtils.GROOM_ID;
import static com.example.petparadise.utils.AppConstantsAndUtils.INVOICE_NO;
import static com.example.petparadise.utils.AppConstantsAndUtils.INVOICE_PACKAGE;
import static com.example.petparadise.utils.AppConstantsAndUtils.MOBILE_NO;
import static com.example.petparadise.utils.AppConstantsAndUtils.NEXT_REMINDER;
import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.WAITING_LIST;
import static com.example.petparadise.utils.ImageUtil.printLog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.activity.HistoryActivity;
import com.example.petparadise.activity.InvoiceActivity;
import com.example.petparadise.activity.LastActivity;
import com.example.petparadise.adapter.WaitingListAdapter;
import com.example.petparadise.application.MyApplication;
import com.example.petparadise.models.PackageModel;
import com.example.petparadise.models.WaitingListModel;
import com.example.petparadise.models.WaitingListResultModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;


public class CompleteFragment extends Fragment {

    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete, container, false);

        recyclerView = view.findViewById(R.id.completeListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getCustomerList();
        return view;
    }

    public void getCustomerList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
//            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("status_wait","Complete");
            new NetworkRequestUtil().postData(WAITING_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    WaitingListModel waitingListModel = new Gson().fromJson(response.toString(), WaitingListModel.class);
                    if (waitingListModel != null) {
                        if (waitingListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            showList(waitingListModel);
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "An error occured", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            printLog(e.toString());
        }
    }

    void doneGrooming(){
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
            requestJson.put("invoice_no", MyApplication.getInstance().getFromSharedPreference(INVOICE_NO));


            new NetworkRequestUtil().postData(DONE_GROOMING, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    PackageModel packageModel = new Gson().fromJson(response.toString(), PackageModel.class);
                    if (packageModel != null) {
                        if (packageModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {




                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), packageModel.getMessage() + " ", Toast.LENGTH_SHORT).show();
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

    public void showList(final WaitingListModel waitingListModel){
        final WaitingListAdapter waitingListAdapter =  new WaitingListAdapter(getContext(), waitingListModel.getResult(), new WaitingListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WaitingListResultModel getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(WaitingListResultModel getData, Button button, String pos) {
                MyApplication.getInstance().addToSharedPreference(CUST_NAME,getData.getCustomer_Name());
                MyApplication.getInstance().addToSharedPreference(MOBILE_NO,getData.getContact_No());
                MyApplication.getInstance().addToSharedPreference(INVOICE_PACKAGE,getData.getPackages());
                startActivity(new Intent(getActivity(), InvoiceActivity.class));
            }

            @Override
            public void onClickButtonClick1(WaitingListResultModel getData, Button button1, String pos) {
                    MyApplication.getInstance().addToSharedPreference(INVOICE_NO,getData.getInvoice_no());
                    doneGrooming();
                    button1.setVisibility(View.GONE);
            }

            @Override
            public void onClickButtonClick2(WaitingListResultModel getData, ImageView imageView, String pos) {
                String contact = "+91 9370299452"; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact ;

                try {
                    String mobile = "+91 9370299452";
                    String msg = "Hello Pet app ki testing kr raha hu";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
                }catch (Exception e){
                    //whatsapp app not install
                }
            }
        });
        recyclerView.setAdapter(waitingListAdapter);
    }
}