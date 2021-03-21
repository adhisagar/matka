package com.example.matkamasthi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.ResultShowAdapter;
import com.example.matkamasthi.model.ResultShowModel;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

import java.util.ArrayList;

public class ResultShowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ResultShowModel> resultShowModelArrayList=new ArrayList<>();
    ResultShowAdapter adapter;

    TextView completeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

        intitialization();
    }

    private void intitialization(){
        completeDetails=findViewById(R.id.jodi_details_click_here);
        recyclerView=findViewById(R.id.result_show_recycler_view);
        resultShowModelArrayList=populateList();
        LinearLayoutManager layoutManager=new LinearLayoutManager(ResultShowActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ResultShowAdapter(ResultShowActivity.this,resultShowModelArrayList);
        recyclerView.setAdapter(adapter);

        completeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="https://matkamasthi.com/home";
                Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,query);
                startActivity(intent);
            }
        });
    }

    private ArrayList<ResultShowModel> populateList(){
        ArrayList<ResultShowModel> list=new ArrayList<>();
        for(int i=0;i<7;i++){
            ResultShowModel model=new ResultShowModel();
            model.setMondayResult("");
            model.setTuesdayResult("");
            model.setWednesdayResult("");
            model.setThursdayResult("");
            model.setFidayResult("");
            model.setSaturdayResult("");
            model.setSundayResult("");
            list.add(model);
        }
        return list;
    }
}
