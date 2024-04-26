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
import com.example.petparadise.models.WaitingListResultModel;

import java.util.List;

public class WaitingListAdapter extends RecyclerView.Adapter<WaitingListAdapter.MyViewHolder>{
    Context context;
    private List<WaitingListResultModel> getDetails;
   WaitingListAdapter.OnItemClickListener listener;
    String demo;

    public WaitingListAdapter(Context context, List<WaitingListResultModel> getDetails, WaitingListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.getDetails = getDetails;
        this.listener = listener;
    }

    @Override
    public WaitingListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.waiting_list_item, parent, false);
        return new WaitingListAdapter.MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(WaitingListAdapter.MyViewHolder holder, final int position) {
        final WaitingListResultModel getData = getDetails.get(position);

        holder.titleTxt.setText(getData.getCustomer_Name());
        holder.subtitleTxt.setText(getData.getContact_No());
        holder.petName.setText(getData.getPets_name());
        holder.petDob.setText(getData.getDob());
        holder.packName.setText(getData.getPackages());
        holder.status.setText(getData.getStatus_wait());
        holder.bind(getData,listener,String.valueOf(position));



        if(getData.getStatus_wait().equals("Complete")){
            holder.finishBtn.setVisibility(View.VISIBLE);
        }
        else{
            holder.finishBtn.setVisibility(View.GONE);
        }

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
        void onItemClick(WaitingListResultModel getData, TextView textViewName, String pos);

        void onClickButtonClick(WaitingListResultModel getData, Button button, String pos);
        void onClickButtonClick1(WaitingListResultModel getData, Button button1, String pos);
        void onClickButtonClick2(WaitingListResultModel getData, ImageView imageView, String pos);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTable, tvStartTime, tvEmail, tvBranch, tvGroup,titleTxt,subtitleTxt,petName,petDob,status,packName;
        CardView cardView;
        ImageButton btnView, btnEdit, btnDelete;
        Button btnStatus,btnStatus1, buttonClick,visitBtn,detailBtn,finishBtn,doneBtn;
        LinearLayout linearLayoutTime;
        ImageView imageView1, imageView2, imageView3, imageView4;
        View view1, view2, view3, view4;
//        ShimmerRibbonView ribbonView1, ribbonView2;

        public MyViewHolder(View itemView) {
            super(itemView);


            titleTxt = itemView.findViewById(R.id.custNm);
            subtitleTxt = itemView.findViewById(R.id.custMb);
            petName = itemView.findViewById(R.id.petNm);
            packName = itemView.findViewById(R.id.packageName);
            petDob = itemView.findViewById(R.id.petDb);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.custCd);
            finishBtn = itemView.findViewById(R.id.finishBtn);
            doneBtn = itemView.findViewById(R.id.doneGroomBtn);
            imageView1 = itemView.findViewById(R.id.whatsappIcon);
            view1 = itemView.findViewById(R.id.view1);
//            visitBtn = itemView.findViewById(R.id.visitBtn);
//            detailBtn = itemView.findViewById(R.id.detailBtn);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }

        public void bind(final WaitingListResultModel getData, final WaitingListAdapter.OnItemClickListener listener, final String s) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getData, tvTable, s);
                }
            });



            finishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickButtonClick(getData, finishBtn, s);
                }
            });

            doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickButtonClick1(getData, doneBtn, s);
                }
            });

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickButtonClick2(getData,imageView1,s);
                }
            });




        }

    }
}
