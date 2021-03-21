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

import java.util.ArrayList;

public class NoticeBoardRecyclerAdapter extends RecyclerView.Adapter<NoticeBoardRecyclerAdapter.Viewholder> {
    Context context;
    ArrayList<NoticeBoardRecyclerModel> recyclerModels;

    public NoticeBoardRecyclerAdapter(Context context, ArrayList<NoticeBoardRecyclerModel> recyclerModels) {
        this.context = context;
        this.recyclerModels = recyclerModels;
    }

    @NonNull
    @Override
    public NoticeBoardRecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_board_recycler_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeBoardRecyclerAdapter.Viewholder holder, int position) {
        holder.nTextView.setText(recyclerModels.get(position).getNoticeTxt());
    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nTextView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nTextView=itemView.findViewById(R.id.notice_text_view);
        }
    }
}
