//package com.example.pets.utils;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.core.content.ContextCompat;
//
//import com.google.android.material.snackbar.Snackbar;
//
//
//
//public class MessageUtil {
//
//    private Context context;
//
//    public MessageUtil(Context context) {
//        this.context = context;
//    }
//
//    public void showSnackBar(View container, String message) {
//        //TODO scack bar design
//        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_one));
//        TextView tv = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
//        tv.setTextColor(ContextCompat.getColor(context, R.color.white));
//        tv.setFontFeatureSettings("smcp");
//        snackbar.show();
//    }
//
//    public void showScreen(View container, String message) {
//        //TODO scack bar design
//        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG);
//        View sbView = snackbar.getView();
//        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_one));
//        TextView tv = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
//        tv.setTextColor(ContextCompat.getColor(context, R.color.white));
//        tv.setFontFeatureSettings("smcp");
//        snackbar.show();
//    }
//}
