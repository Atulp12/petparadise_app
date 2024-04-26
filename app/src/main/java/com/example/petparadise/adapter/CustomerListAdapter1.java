package com.example.petparadise.adapter;




import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.petparadise.R;
import com.example.petparadise.models.ResultModel;

import java.util.List;


public class CustomerListAdapter1 extends RecyclerView.Adapter<CustomerListAdapter1.MyViewHolder> {
    Context context;
    private List<ResultModel> getDetails;
    OnItemClickListener listener;
    String demo;

    public CustomerListAdapter1(Context context, List<ResultModel> getDetails, OnItemClickListener listener) {
        this.context = context;
        this.getDetails = getDetails;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_card, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ResultModel getData = getDetails.get(position);

        holder.titleTxt.setText(getData.getCustomer_Name());
        holder.subtitleTxt.setText(getData.getContact_No());
        holder.petName.setText(getData.getPets_name());
        holder.petDob.setText(getData.getDob());
        holder.branchName.setText(getData.getBranch_name());
        holder.bind(getData,listener,String.valueOf(position));

    }


    @Override
    public int getItemCount() {
        if (getDetails == null) {
            return 0;
        } else {
            return getDetails.size();
        }
    }


    public interface OnItemClickListener {
        void onItemClick(ResultModel getData, TextView textViewName, String pos);

        void onClickButtonClick(ResultModel getData, Button button, String pos);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTable, tvStartTime, tvEmail, tvBranch, tvGroup,titleTxt,subtitleTxt,petName,petDob,branchName;
        CardView cardView;
        ImageButton btnView, btnEdit, btnDelete;
        Button btnStatus,btnStatus1, buttonClick,visitBtn,detailBtn;
        LinearLayout linearLayoutTime;
        ImageView imageView1, imageView2, imageView3, imageView4;
        View view1, view2, view3, view4;
//        ShimmerRibbonView ribbonView1, ribbonView2;

        public MyViewHolder(View itemView) {
            super(itemView);


            titleTxt = itemView.findViewById(R.id.custName);
            subtitleTxt = itemView.findViewById(R.id.custMobile);
            petName = itemView.findViewById(R.id.petName);
            petDob = itemView.findViewById(R.id.petDob);
            branchName = itemView.findViewById(R.id.branchName);
            cardView = itemView.findViewById(R.id.custCard);
            visitBtn = itemView.findViewById(R.id.visitBtn);
            detailBtn = itemView.findViewById(R.id.detailBtn);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }

        public void bind(final ResultModel getData, final OnItemClickListener listener, final String s) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getData, tvTable, s);
                }
            });



            visitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickButtonClick(getData, visitBtn, s);
                }
            });



        }

    }


}
