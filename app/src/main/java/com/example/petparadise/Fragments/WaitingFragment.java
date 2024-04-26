package com.example.petparadise.Fragments;

import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.WAITING_LIST;
import static com.example.petparadise.utils.ImageUtil.printLog;

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
import com.example.petparadise.adapter.WaitingListAdapter1;
import com.example.petparadise.models.WaitingListModel;
import com.example.petparadise.models.WaitingListResultModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;


public class WaitingFragment extends Fragment {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_waiting, container, false);

        recyclerView = view.findViewById(R.id.waitingListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Log.d("WaitingFragment", "Context: " + getActivity());

        getCustomerList();
        return view;
    }

    public void getCustomerList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
//            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
            requestJson.put("status_wait","Waiting");
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

    public void showList(final WaitingListModel waitingListModel){
        final WaitingListAdapter1 waitingListAdapter1 =  new WaitingListAdapter1(getContext(), waitingListModel.getResult(), new WaitingListAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(WaitingListResultModel getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(WaitingListResultModel getData, Button button, String pos) {
                
            }

            @Override
            public void onClickButtonClick1(WaitingListResultModel getData, Button button1, String pos) {

            }

            @Override
            public void onClickButtonClick2(WaitingListResultModel getData, ImageView imageView, String pos) {

            }


        });
        recyclerView.setAdapter(waitingListAdapter1);
    }
}