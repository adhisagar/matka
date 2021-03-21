package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.WalletHistoryRecyclerModel;

import java.util.ArrayList;

public class WalletHistoryRecyclerAdapter extends RecyclerView.Adapter<WalletHistoryRecyclerAdapter.Viewholder> {

    Context context;
    ArrayList<WalletHistoryRecyclerModel> arrayList;

    public WalletHistoryRecyclerAdapter(Context context, ArrayList<WalletHistoryRecyclerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public WalletHistoryRecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.wallet_history_recycler_list,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHistoryRecyclerAdapter.Viewholder holder, int position) {

        holder.gameType.setText(arrayList.get(position).getGameType());
        holder.gameStatus.setText(arrayList.get(position).getStatus());
        holder.gameMoney.setText(arrayList.get(position).getPlayAmount());
        holder.gamePlayedDate.setText(arrayList.get(position).getPlayedDate());
        holder.gameHistoryTotalAmount.setText(arrayList.get(position).getWalletPoint());
        holder.gameHistoryFieldWin.setText(arrayList.get(position).getWalletFieldWin());
        holder.gameHistoryComments.setText(arrayList.get(position).getGameComments());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView gameType,gameStatus,gameMoney,gamePlayedDate,gameHistoryTotalAmount,gameHistoryFieldWin,gameHistoryComments;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            gameType=itemView.findViewById(R.id.wallet_history_type);
            gameStatus=itemView.findViewById(R.id.wallet_history_status);
            gameMoney=itemView.findViewById(R.id.wallet_history_use_money);
            gamePlayedDate=itemView.findViewById(R.id.wallet_history_game_date);
            gameHistoryTotalAmount=itemView.findViewById(R.id.wallet_history_amount);
            gameHistoryFieldWin=itemView.findViewById(R.id.wallet_history_field_win);
            gameHistoryComments=itemView.findViewById(R.id.wallet_history_comments);
        }
    }
}
