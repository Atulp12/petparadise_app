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
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petparadise.R;
import com.example.petparadise.models.TrackListModel;
import com.example.petparadise.models.TrackTableData;
import com.example.petparadise.models.WaitingListResultModel;

import java.util.List;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.MyViewHolder>{
    Context context;
    private List<TrackTableData> getDetails;
   TrackListAdapter.OnItemClickListener listener;
    String demo;

    public TrackListAdapter(Context context, List<TrackTableData> getDetails, TrackListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.getDetails = getDetails;
        this.listener = listener;
    }

    @Override
    public TrackListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_tem, parent, false);
        return new TrackListAdapter.MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(TrackListAdapter.MyViewHolder holder, final int position) {
        final TrackTableData getData = getDetails.get(position);

//        holder.branchName.setText(getData.get);

        holder.startTime.setText(getData.getStart_time());
        holder.tableNo.setText(getData.getTable_assign_id());
        holder.branchName.setText(getData.getBranch_name());
//        Toast.makeText(context, getData.getBranch_name() + " ", Toast.LENGTH_SHORT).show();
        if(getData.getWashing_status().equals("1")){
            holder.view1.setBackgroundResource(R.drawable.baseline_check_circle_24);
        }
        if (getData.getDrying_status().equals("1")) {
            holder.lineView1.setBackgroundColor(R.color.color16);
            holder.view2.setBackgroundResource(R.drawable.baseline_check_circle_24);
        }
        if (getData.getHairStyling_status().equals("1")) {
            holder.lineView2.setBackgroundColor(R.color.color16);
            holder.view3.setBackgroundResource(R.drawable.baseline_check_circle_24);
        }
        if (getData.getFinal_status().equals("1")) {
            holder.lineView3.setBackgroundColor(R.color.color16);
            holder.view4.setBackgroundResource(R.drawable.baseline_check_circle_24);
        }
        holder.startBtn.setVisibility(View.GONE);
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
        void onItemClick(TrackTableData getData, TextView textViewName, String pos);

        void onClickButtonClick(TrackTableData getData, Button button, String pos);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTable, tvStartTime, tvEmail, tvBranch, tvGroup,titleTxt,subtitleTxt,petName,petDob,status,branchName,startTime,tableNo;
        CardView cardView;
        ImageButton btnView, btnEdit, btnDelete;
        Button btnStatus,btnStatus1, buttonClick,visitBtn,detailBtn,startBtn;
        LinearLayout linearLayoutTime;
        ImageView imageView1, imageView2, imageView3, imageView4;
        View view1, view2, view3, view4,lineView1,lineView2,lineView3;
//        ShimmerRibbonView ribbonView1, ribbonView2;

        public MyViewHolder(View itemView) {
            super(itemView);

            branchName = itemView.findViewById(R.id.trackBranch);
            tableNo = itemView.findViewById(R.id.tableNo);
            startTime = itemView.findViewById(R.id.trackTime);
            view1 = itemView.findViewById(R.id.view_state1);
            view2 = itemView.findViewById(R.id.view_state2);
            view3 = itemView.findViewById(R.id.view_state3);
            view4 = itemView.findViewById(R.id.view_state4);
            lineView1 = itemView.findViewById(R.id.line1);
            lineView2 = itemView.findViewById(R.id.line2);
            lineView3 = itemView.findViewById(R.id.line3);
            startBtn = itemView.findViewById(R.id.startTbBtn);
            cardView = itemView.findViewById(R.id.custTrackCD);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }

        public void bind(final TrackTableData getData, final TrackListAdapter.OnItemClickListener listener, final String s) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getData, tvTable, s);
                }
            });



//            visitBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onClickButtonClick(getData, visitBtn, s);
//                }
//            });



        }

    }
}
