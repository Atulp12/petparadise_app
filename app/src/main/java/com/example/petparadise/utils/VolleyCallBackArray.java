package com.example.petparadise.utils;

import com.android.volley.VolleyError;

import org.json.JSONArray;

public interface VolleyCallBackArray {
    void onSuccess(JSONArray response);

    void onError(VolleyError error);
}
