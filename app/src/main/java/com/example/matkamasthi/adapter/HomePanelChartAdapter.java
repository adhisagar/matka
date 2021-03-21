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
import com.example.matkamasthi.activity.PanelResultShow;
import com.example.matkamasthi.activity.ResultShowActivity;
import com.example.matkamasthi.model.HomePanelChartModel;

import java.util.ArrayList;

public class HomePanelChartAdapter extends RecyclerView.Adapter<HomePanelChartAdapter.Viewholder> {

    Context context;
    ArrayList<HomePanelChartModel> recyclerModels;
      private HomePanleChartAdapterListener listener;

    public HomePanelChartAdapter(Context context, ArrayList<HomePanelChartModel> recyclerModels,HomePanleChartAdapterListener listener) {
        this.context = context;
        this.recyclerModels = recyclerModels;
        this.listener=listener;
    }

    @NonNull
    @Override
    public HomePanelChartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.panel_chart_record_recycler_list, parent, false);
        return new HomePanelChartAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomePanelChartAdapter.Viewholder holder, final int position) {
        holder.nTextView.setText(recyclerModels.get(position).getPanelText());

    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nTextView;
        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            nTextView=itemView.findViewById(R.id.home_panel_chart_recycler_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.sendPosition(recyclerModels.get(getAdapterPosition()));
                }
            });
        }

    }

    public interface HomePanleChartAdapterListener{
        void sendPosition(HomePanelChartModel model);
    }

}


/*
 holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, recyclerModels.get(position).getPanelText(), Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(context, PanelResultShow.class);
//                context.startActivity(intent);
//            }
//        });
 */