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
import com.example.petparadise.models.TrackTableData;

import java.util.List;

public class TableAssignListAdapter extends RecyclerView.Adapter<TableAssignListAdapter.MyViewHolder>{
    Context context;
    private List<TrackTableData> getDetails;
   TableAssignListAdapter.OnItemClickListener listener;
    String demo;

    public TableAssignListAdapter(Context context, List<TrackTableData> getDetails, TableAssignListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.getDetails = getDetails;
        this.listener = listener;
    }

    @Override
    public TableAssignListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_tem, parent, false);
        return new TableAssignListAdapter.MyViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(TableAssignListAdapter.MyViewHolder holder, final int position) {
        final TrackTableData getData = getDetails.get(position);

//        holder.branchName.setText(getData.get);

        holder.startTime.setText(getData.getStart_time());
        holder.tableNo.setText(getData.getTable_assign_id());
        if(getData.getWashing_status().equals("1")){
            holder.view1.setBackgroundResource(R.drawable.baseline_check_circle_24);
        } else if (getData.getDrying_status().equals("1")) {
            holder.lineView1.setBackgroundColor(R.color.black_light);
            holder.view2.setBackgroundResource(R.drawable.baseline_check_circle_24);
        } else if (getData.getHairStyling_status().equals("1")) {
            holder.lineView2.setBackgroundColor(R.color.black_light);
            holder.view3.setBackgroundResource(R.drawable.baseline_check_circle_24);
        } else if (getData.getFinal_status().equals("1")) {
            holder.lineView3.setBackgroundColor(R.color.black_light);
            holder.view4.setBackgroundResource(R.drawable.baseline_check_circle_24);
        }
        holder.linearProgress.setVisibility(View.GONE);
        holder.linearBranch.setVisibility(View.GONE);
        holder.linearBranch1.setVisibility(View.GONE);
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
        LinearLayout linearProgress,linearBranch,linearBranch1;
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
            cardView = itemView.findViewById(R.id.custTrackCD);
            linearProgress = itemView.findViewById(R.id.linearProgress);
            linearBranch = itemView.findViewById(R.id.branchLinear);
            linearBranch1 = itemView.findViewById(R.id.branchLinear1);
            startBtn = itemView.findViewById(R.id.startTbBtn);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }

        public void bind(final TrackTableData getData, final TableAssignListAdapter.OnItemClickListener listener, final String s) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getData, tvTable, s);
                }
            });



            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickButtonClick(getData, startBtn, s);
                }
            });



        }

    }
}
