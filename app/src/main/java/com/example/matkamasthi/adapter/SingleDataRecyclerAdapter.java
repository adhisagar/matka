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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleDataRecyclerAdapter extends RecyclerView.Adapter<SingleDataRecyclerAdapter.Viewholder> {

    Context context;
    ArrayList<SingleDataRecyclerModel> singleDataRecyclerModels;
    PassTotal passTotal;

    public SingleDataRecyclerAdapter(Context context, ArrayList<SingleDataRecyclerModel> singleDataRecyclerModels) {
        this.context = context;
        this.singleDataRecyclerModels = singleDataRecyclerModels;
      //  this.passTotal=passTotal;
    }

    @NonNull
    @Override
    public SingleDataRecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_data_recycler_list, parent, false);
        return new SingleDataRecyclerAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleDataRecyclerAdapter.Viewholder holder, int position) {
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
                    boolean isSubmitEnable=false;
                    ArrayList<String> amount=new ArrayList<>();
                    HashMap<String,String> hashMap=new HashMap<>();
                    for(int i = 0; i < singleDataRecyclerModels.size(); i++){
                        int value=0;
                        try{
                            if(!singleDataRecyclerModels.get(i).getEditedValueText().isEmpty() && Integer.parseInt(singleDataRecyclerModels.get(i).getEditedValueText())>9){
                                value=Integer.parseInt(singleDataRecyclerModels.get(i).getEditedValueText());
                                hashMap.put(singleDataRecyclerModels.get(i).getNumberText(),singleDataRecyclerModels.get(i).getEditedValueText());
                                isSubmitEnable=true;
                                //amount.add(triplePattiRecyclerModels.get(i).getEditedValueText());
                            }
                            else if(Integer.parseInt(singleDataRecyclerModels.get(i).getEditedValueText())<10){
                                Toast.makeText(context, "Amount should be greater than 10", Toast.LENGTH_SHORT).show();
                                sEditText.requestFocus();
                                isSubmitEnable=false;
                       }
                            else {
                                value=0;
                                isSubmitEnable=false;
                            }
                        }catch(NumberFormatException ex){ // handle your exception
                            Log.i("exception",ex.getMessage());
                        }

                        total = total + value;
                        Log.i("totla", String.valueOf(total));
                        passTotal.sendTotal(String.valueOf(total),hashMap,isSubmitEnable);
                    }
                }
            });
        }
    }

    public interface PassTotal{
        void sendTotal(String total, HashMap<String,String> hashMap,boolean isSubmitEnable);
    }
}
