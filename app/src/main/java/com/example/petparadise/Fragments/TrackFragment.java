package com.example.petparadise.Fragments;

import static com.example.petparadise.utils.AppConstantsAndUtils.STATUS_SUCCESS;
import static com.example.petparadise.utils.AppConstantsAndUtils.TRACK_LIST;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.petparadise.R;
import com.example.petparadise.adapter.TrackListAdapter;
import com.example.petparadise.adapter.WaitingListAdapter;
import com.example.petparadise.models.TrackListModel;
import com.example.petparadise.models.TrackTableData;
import com.example.petparadise.models.WaitingListModel;
import com.example.petparadise.models.WaitingListResultModel;
import com.example.petparadise.utils.NetworkRequestUtil;
import com.example.petparadise.utils.VolleyCallback;
import com.google.gson.Gson;

import org.json.JSONObject;


public class TrackFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track, container, false);

        recyclerView = view.findViewById(R.id.trackListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        getTrackList();
        return view;
    }

    public void getTrackList() {
        final JSONObject requestJson;

        try {
            requestJson = new JSONObject();
//            requestJson.put("branch_id", MyApplication.getInstance().getFromSharedPreference(BRANCH_ID));
//            requestJson.put("status_wait","Waiting");
            new NetworkRequestUtil().postData(TRACK_LIST, requestJson, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject response) {
                    TrackListModel trackListModel = new Gson().fromJson(response.toString(), TrackListModel.class);
                    if (trackListModel != null) {
                        if (trackListModel.getSuccess().equalsIgnoreCase(STATUS_SUCCESS)) {

                            showList(trackListModel);
                        } else {
                            //Toast.makeText(AddCustomer.this, "Customer Not Added", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "An error occured", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //
                        //showDialogWithOkButton(getString(R.string.error_something_went_wrong));
                    }

                }

                //@Override
                public void onError(VolleyError error) {
                    printLog("error Response:" + error);
                }
            });
        } catch (Exception e) {
//            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            printLog(e.toString());
        }
    }

    public void showList(final TrackListModel trackListModel){
        final TrackListAdapter trackListAdapter =  new TrackListAdapter(getContext(), trackListModel.getResult().getTable_data(), new TrackListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TrackTableData getData, TextView textViewName, String pos) {

            }

            @Override
            public void onClickButtonClick(TrackTableData getData, Button button, String pos) {

            }



        });
        recyclerView.setAdapter(trackListAdapter);
    }
}