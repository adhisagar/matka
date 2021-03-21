package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.GameHistoryModel;

import java.util.ArrayList;

public class GameHistoryRecyclerAdapter extends RecyclerView.Adapter<GameHistoryRecyclerAdapter.Viewholder> {

    Context context;
    ArrayList<GameHistoryModel> gameHistoryModelArrayList;

    public GameHistoryRecyclerAdapter(Context context, ArrayList<GameHistoryModel> gameHistoryModelArrayList) {
        this.context = context;
        this.gameHistoryModelArrayList = gameHistoryModelArrayList;
    }

    @NonNull
    @Override
    public GameHistoryRecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.game_history_recycler_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameHistoryRecyclerAdapter.Viewholder holder, int position) {
        holder.gameType.setText(gameHistoryModelArrayList.get(position).getGameType());
        holder.gameTypeName.setText(gameHistoryModelArrayList.get(position).getGameTypename());
        holder.gameUsedMoney.setText(gameHistoryModelArrayList.get(position).getGameUsedAmount());
        holder.gamePlayedDate.setText(gameHistoryModelArrayList.get(position).getGamePlayedDate());
    }

    @Override
    public int getItemCount() {
        return gameHistoryModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView gameType,gameTypeName,gameUsedMoney,gamePlayedDate;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            gameType=itemView.findViewById(R.id.game_history_type);
            gameTypeName=itemView.findViewById(R.id.game_history_type_name);
            gameUsedMoney=itemView.findViewById(R.id.game_history_use_money);
            gamePlayedDate=itemView.findViewById(R.id.game_history_game_date);
        }
    }
}
