package com.example.matkamasthi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.activity.ResultShowActivity;
import com.example.matkamasthi.model.HomeJodiChartModel;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;

import java.util.ArrayList;

public class HomeJodiChartAdapter extends RecyclerView.Adapter<HomeJodiChartAdapter.Viewholder> {

    Context context;
    ArrayList<HomeJodiChartModel> recyclerModels;
    private JodiChartAdapterListener listener;

    public HomeJodiChartAdapter(Context context, ArrayList<HomeJodiChartModel> recyclerModels,JodiChartAdapterListener listener) {
        this.context = context;
        this.recyclerModels = recyclerModels;
        this.listener=listener;
    }

    @NonNull
    @Override
    public HomeJodiChartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jodi_chart_record_recycler_list, parent, false);
        return new HomeJodiChartAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeJodiChartAdapter.Viewholder holder, final int position) {
        holder.nTextView.setText(recyclerModels.get(position).getJodiText());

    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nTextView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nTextView=itemView.findViewById(R.id.home_jodi_chart_recycler_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.sendPosition(recyclerModels.get(getAdapterPosition()));
                }
            });
        }

    }

    public interface JodiChartAdapterListener{
        void sendPosition(HomeJodiChartModel chartModel);
    }

}

/*
 holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, recyclerModels.get(position).getJodiText(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ResultShowActivity.class);
                context.startActivity(intent);
            }
        });
 */




