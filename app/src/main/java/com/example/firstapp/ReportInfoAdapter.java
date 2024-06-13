package com.example.firstapp;//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ReportInfoAdapter extends RecyclerView.Adapter<ReportInfoAdapter.MyViewHolder> {

    private ArrayList<ReportInfo> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView crime;
        TextView details;
        TextView time;
        TextView reportedby;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.crime = (TextView) itemView.findViewById(R.id.card_crime);
            this.details = (TextView) itemView.findViewById(R.id.card_details);
            this.time = (TextView) itemView.findViewById(R.id.card_time);
            this.reportedby = (TextView) itemView.findViewById(R.id.card_reportedby);
        }
    }

    public ReportInfoAdapter(ArrayList<ReportInfo> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView crime_1 = holder.crime;
        TextView details_1 = holder.details;
        TextView time_1 = holder.time;
        TextView reportedby_1 = holder.reportedby;


        crime_1.setText(dataSet.get(listPosition).getCrime());
        details_1.setText(dataSet.get(listPosition).getDetails());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(dataSet.get(listPosition).getTime());
        time_1.setText(formattedDate);
        if(dataSet.get(listPosition).isAnonymous()){
            reportedby_1.setText("Anonymous");
        }
        else{
            reportedby_1.setText(dataSet.get(listPosition).getUserId());
        }





    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}