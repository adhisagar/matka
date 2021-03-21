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
import com.example.matkamasthi.model.AmountEnteres;
import com.example.matkamasthi.model.TriplePattiRecyclerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class TriplePattiRecyclerAdapter extends RecyclerView.Adapter<TriplePattiRecyclerAdapter.Viewholder> {

    Context context;
    ArrayList<TriplePattiRecyclerModel> triplePattiRecyclerModels;
   // PassTotal passTotal;


    public TriplePattiRecyclerAdapter(Context context, ArrayList<TriplePattiRecyclerModel> triplePattiRecyclerModels) {
        this.context = context;
        this.triplePattiRecyclerModels = triplePattiRecyclerModels;

    }

    @NonNull
    @Override
    public TriplePattiRecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.triple_patti_data_recycler_list, parent, false);
        return new TriplePattiRecyclerAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriplePattiRecyclerAdapter.Viewholder holder, int position) {
        holder.sTextView.setText(triplePattiRecyclerModels.get(position).getNumberText());
        holder.sEditText.setText(triplePattiRecyclerModels.get(position).getEditedValueText());

    }

    @Override
    public int getItemCount() {
        return triplePattiRecyclerModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView sTextView;
        EditText sEditText;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            sTextView=itemView.findViewById(R.id.triple_patti_data_list_position);
            sEditText=itemView.findViewById(R.id.triple_patti_data_edittext);

            sEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    triplePattiRecyclerModels.get(getAdapterPosition()).setEditedValueText(sEditText.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int total = 0;
                    ArrayList<String> amount=new ArrayList<>();
                    HashMap<String,String> hashMap=new HashMap<>();
                    for(int i = 0; i < triplePattiRecyclerModels.size(); i++){
                        int value=0;
                        if(!triplePattiRecyclerModels.get(i).getEditedValueText().isEmpty()){
                            value=Integer.parseInt(triplePattiRecyclerModels.get(i).getEditedValueText());
                            hashMap.put(triplePattiRecyclerModels.get(i).getNumberText(),triplePattiRecyclerModels.get(i).getEditedValueText());
                            //amount.add(triplePattiRecyclerModels.get(i).getEditedValueText());
                        }
//                        else if(Integer.parseInt(triplePattiRecyclerModels.get(i).getEditedValueText())<10){
//                            Toast.makeText(context,"Minimum Bet is Rs 10 for 1 Number",Toast.LENGTH_LONG).show();
//                            value=Integer.parseInt(triplePattiRecyclerModels.get(i).getEditedValueText());
//                            hashMap.put(triplePattiRecyclerModels.get(i).getNumberText(),triplePattiRecyclerModels.get(i).getEditedValueText());
//                        }
                        else {
                            value=0;
                        }
                        total = total + value;
                        Log.i("totla", String.valueOf(total));
                       // passTotal.sendTotal(String.valueOf(total),hashMap);
                    }
                }
            });
        }
    }

    public interface PassTotal{
        void sendTotal(String total,HashMap<String,String> hashMap);
    }
}