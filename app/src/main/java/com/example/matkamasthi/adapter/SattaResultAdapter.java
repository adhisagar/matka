package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;
import com.example.matkamasthi.model.SattaResultModel;

import java.util.ArrayList;

public class SattaResultAdapter extends RecyclerView.Adapter<SattaResultAdapter.Viewholder> {

    Context context;
    ArrayList<SattaResultModel> recyclerModels;

    public SattaResultAdapter(Context context, ArrayList<SattaResultModel> recyclerModels) {
        this.context = context;
        this.recyclerModels = recyclerModels;
    }

    @NonNull
    @Override
    public SattaResultAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.satka_result_recycler_list, parent, false);
        return new SattaResultAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SattaResultAdapter.Viewholder holder, int position) {
        holder.gName.setText(recyclerModels.get(position).getGameName());
        holder.gStartTime.setText(recyclerModels.get(position).getStartTime());
        holder.gEndTime.setText(recyclerModels.get(position).getEndTime());
        holder.gameResultText.setText(recyclerModels.get(position).getGameResult());
    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView gName,gStartTime,gEndTime,gameResultText;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            gName=itemView.findViewById(R.id.satka_result_game_name);
            gStartTime=itemView.findViewById(R.id.satka_result_start_time_txt);
            gEndTime=itemView.findViewById(R.id.satka_result_end_time_txt);
            gameResultText=itemView.findViewById(R.id.result_recycler_text);
        }
    }
}
