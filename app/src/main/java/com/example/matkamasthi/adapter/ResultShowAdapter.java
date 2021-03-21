package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.ResultShowModel;

import java.util.ArrayList;

public class ResultShowAdapter extends RecyclerView.Adapter<ResultShowAdapter.Viewholder> {

    Context context;
    ArrayList<ResultShowModel> resultShowModelArrayList;

    public ResultShowAdapter(Context context, ArrayList<ResultShowModel> resultShowModelArrayList) {
        this.context = context;
        this.resultShowModelArrayList = resultShowModelArrayList;
    }

    @NonNull
    @Override
    public ResultShowAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.result_show_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultShowAdapter.Viewholder holder, int position) {
        if(!resultShowModelArrayList.get(position).getMondayResult().isEmpty()){
            holder.monText.setText(resultShowModelArrayList.get(position).getMondayResult());
        }
        else {
            holder.monText.setText("**");
        }
        if(!resultShowModelArrayList.get(position).getTuesdayResult().isEmpty()){
            holder.tuesText.setText(resultShowModelArrayList.get(position).getTuesdayResult());
        }
        else {
            holder.tuesText.setText("**");
        }
        if(!resultShowModelArrayList.get(position).getWednesdayResult().isEmpty()){
            holder.wedText.setText(resultShowModelArrayList.get(position).getWednesdayResult());
        }
        else {
            holder.wedText.setText("**");
        }
        if(!resultShowModelArrayList.get(position).getThursdayResult().isEmpty()){
            holder.thuText.setText(resultShowModelArrayList.get(position).getThursdayResult());
        }
        else {
            holder.thuText.setText("**");
        }

        if(!resultShowModelArrayList.get(position).getFidayResult().isEmpty()){
            holder.friText.setText(resultShowModelArrayList.get(position).getFidayResult());
        }
        else {
            holder.friText.setText("**");
        }if(!resultShowModelArrayList.get(position).getSaturdayResult().isEmpty()){
            holder.satText.setText(resultShowModelArrayList.get(position).getSaturdayResult());
        }
        else {
            holder.satText.setText("**");
        }if(!resultShowModelArrayList.get(position).getSundayResult().isEmpty()){
            holder.sunText.setText(resultShowModelArrayList.get(position).getSundayResult());
        }
        else {
            holder.sunText.setText("**");
        }
    }

    @Override
    public int getItemCount() {
        return resultShowModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView monText,tuesText,wedText,thuText,friText,satText,sunText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            monText=itemView.findViewById(R.id.monday_result_text);
            tuesText=itemView.findViewById(R.id.tuesday_result_text);
            wedText=itemView.findViewById(R.id.wednesday_result_text);
            thuText=itemView.findViewById(R.id.thursday_result_text);
            friText=itemView.findViewById(R.id.friday_result_text);
            satText=itemView.findViewById(R.id.saturday_result_text);
            sunText=itemView.findViewById(R.id.sunday_result_text);
        }
    }
}
