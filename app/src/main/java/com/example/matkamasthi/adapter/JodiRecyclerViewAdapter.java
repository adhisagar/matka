package com.example.matkamasthi.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class JodiRecyclerViewAdapter extends RecyclerView.Adapter<JodiRecyclerViewAdapter.Viewholder> {

    Context context;
    ArrayList<SingleDataRecyclerModel> singleDataRecyclerModels;
    PassTotal passTotal;

    public JodiRecyclerViewAdapter(Context context, ArrayList<SingleDataRecyclerModel> singleDataRecyclerModels) {
        this.context = context;
        this.singleDataRecyclerModels = singleDataRecyclerModels;
        //this.passTotal=passTotal;
    }

    @NonNull
    @Override
    public JodiRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_data_recycler_list, parent, false);
        return new JodiRecyclerViewAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JodiRecyclerViewAdapter.Viewholder holder, int position) {
        holder.sTextView.setText(singleDataRecyclerModels.get(position).getNumberText());
        holder.sEditText.setText(singleDataRecyclerModels.get(position).getEditedValueText());
    }

    @Override
    public int getItemCount() {
        return singleDataRecyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView sTextView;
        EditText sEditText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            sTextView=itemView.findViewById(R.id.single_data_list_position);
            sEditText=itemView.findViewById(R.id.single_data_edittext);

            sEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    singleDataRecyclerModels.get(getAdapterPosition()).setEditedValueText(sEditText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    int total = 0;
                    ArrayList<String> amount=new ArrayList<>();
                    ArrayList<String> numText=new ArrayList<>();
                    ArrayList<String> valueText=new ArrayList<>();

                    HashMap<String,String> hashMap=new HashMap<>();
                    for(int i = 0; i < singleDataRecyclerModels.size(); i++){
                        int value=0;
                        if(!singleDataRecyclerModels.get(i).getEditedValueText().isEmpty()){
                            value=Integer.parseInt(singleDataRecyclerModels.get(i).getEditedValueText());
                            hashMap.put(singleDataRecyclerModels.get(i).getNumberText(),singleDataRecyclerModels.get(i).getEditedValueText());
                            //numText
                            //amount.add(triplePattiRecyclerModels.get(i).getEditedValueText());
                        } else {
                            value=0;
                        }
                        total = total + value;
                       // Log.i("totla", String.valueOf(total));
                        passTotal.sendTotal(String.valueOf(total),hashMap);
                    }
                }
            });
        }
    }

    public interface PassTotal{
        void sendTotal(String total, HashMap<String,String> hashMap);
    }
}
