package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;

public class Half_sangam_RecyclerAdapter extends RecyclerView.Adapter<Half_sangam_RecyclerAdapter.Viewholder> {
    Context context;
    @NonNull
    @Override
    public Half_sangam_RecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.half_sangam_recycler_design, parent, false);
        return new Viewholder(view);

    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
