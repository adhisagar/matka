package com.example.matkamasthi.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.DoubleDataRecyclerModel;

import java.util.ArrayList;

public class DoubleDataRecylerAdapter extends RecyclerView.Adapter<DoubleDataRecylerAdapter.Viewholder> {

    Context context;
    ArrayList<DoubleDataRecyclerModel> doubleDataRecyclerModels;

    public DoubleDataRecylerAdapter(Context context, ArrayList<DoubleDataRecyclerModel> doubleDataRecyclerModels) {
        this.context = context;
        this.doubleDataRecyclerModels = doubleDataRecyclerModels;
    }

    @NonNull
    @Override
    public DoubleDataRecylerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.double_data_recycler_list, parent, false);
        return new DoubleDataRecylerAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoubleDataRecylerAdapter.Viewholder holder, int position) {
        if(position %10==0 ||position==0){
            holder.jodi_design_linear.setVisibility(View.VISIBLE);
            holder.double_count_text.setText(position+"-"+(position+9));

        } else {
            holder.jodi_design_linear.setVisibility(View.GONE);
            holder.sTextView.setText(doubleDataRecyclerModels.get(position).getNumberText());
            holder.sEditText.setText(doubleDataRecyclerModels.get(position).getEditedValueText());
        }


    }

    @Override
    public int getItemCount() {
        return doubleDataRecyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView sTextView;
        EditText sEditText;
        LinearLayout jodi_design_linear;
        TextView double_count_text;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            sTextView=itemView.findViewById(R.id.double_data_list_position);
            sEditText=itemView.findViewById(R.id.double_data_edittext);
            double_count_text=itemView.findViewById(R.id.double_count_text);
            jodi_design_linear=itemView.findViewById(R.id.jodi_design_linear);

            sEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    doubleDataRecyclerModels.get(getAdapterPosition()).setEditedValueText(sEditText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}

