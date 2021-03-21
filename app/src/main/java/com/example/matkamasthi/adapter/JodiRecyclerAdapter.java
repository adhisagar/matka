package com.example.matkamasthi.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

import java.util.ArrayList;

public class JodiRecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<SingleDataRecyclerModel> arrayList;

    public JodiRecyclerAdapter(Context context, ArrayList<SingleDataRecyclerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%10==0 || position==0){
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view;
        if(viewType==0){
            view=inflater.inflate(R.layout.single_data_another_list,parent,false);
            return new ViewholderTwo(view);
        }
        view=inflater.inflate(R.layout.single_data_recycler_list,parent,false);
        return new ViewholderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(position %10==0 ||position==0){
            ViewholderTwo viewholderTwo=(ViewholderTwo) holder;
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,10,10);
            layoutParams.setFullSpan(true);
            viewholderTwo.itemView.setLayoutParams(layoutParams);
            viewholderTwo.anotherTextView.setText(position+"-"+(position+10));

        } else {
            ViewholderOne viewholderOne=(ViewholderOne) holder;
            viewholderOne.sTextView.setText(arrayList.get(position).getNumberText());
            viewholderOne.sEditText.setText(arrayList.get(position).getEditedValueText());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class  ViewholderOne extends RecyclerView.ViewHolder {

        TextView sTextView;
        EditText sEditText;
        public ViewholderOne(@NonNull View itemView) {
            super(itemView);
            sTextView=itemView.findViewById(R.id.single_data_list_position);
            sEditText=itemView.findViewById(R.id.single_data_edittext);

            sEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                  //  singleDataRecyclerModels.get(getAdapterPosition()).setEditedValueText(sEditText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    class ViewholderTwo extends RecyclerView.ViewHolder{

        TextView anotherTextView;
        public ViewholderTwo(@NonNull View itemView) {
            super(itemView);
            anotherTextView=itemView.findViewById(R.id.jodi_count_text);
        }
    }


}
