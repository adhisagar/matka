package com.example.matkamasthi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.PanelResultShowAdapter;
import com.example.matkamasthi.adapter.ResultShowAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.model.HomePanelChartModel;
import com.example.matkamasthi.model.PanelResultShowModel;
import com.example.matkamasthi.model.ResultShowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PanelResultShow extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<PanelResultShowModel> resultShowModelArrayList=new ArrayList<>();
    PanelResultShowAdapter adapter;
    String gameId;
    TextView completeDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_result_show);

        intitialization();
    }

    private void intitialization(){
        gameId=getIntent().getStringExtra("gid");
        completeDetails=findViewById(R.id.panel_details_click_here);
        recyclerView=findViewById(R.id.panel_result_show_recycler_view);
        resultShowModelArrayList=populateList();
        LinearLayoutManager layoutManager=new LinearLayoutManager(PanelResultShow.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new PanelResultShowAdapter(PanelResultShow.this,resultShowModelArrayList);
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

    private void getPanelResult(){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.marketNameUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray categoryArry=response.getJSONArray("categories");
                    for (int i=0;i<categoryArry.length();i++){
                        JSONObject data=categoryArry.getJSONObject(i);
                        String game_name=data.getString("game_name");
                        String game_timing=data.getString("game_timing");
                        String game_end_time=data.getString("game_end_time");
                        String game_result_time=data.getString("game_result_time");
                        String game_id=data.getString("games_id");
                        String type=data.getString("type");
                        gameId=game_id;
                        //recyclerModels.add(new HomePanelChartModel(game_name));
                    }
                } catch (JSONException e) {
                    //                   progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //               progressBar.setVisibility(View.GONE);
                Log.i("volleyerror",error.toString());
                Toast.makeText(PanelResultShow.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(PanelResultShow.this);
        queue.add(objectRequest);
    }

    private ArrayList<PanelResultShowModel> populateList(){
        ArrayList<PanelResultShowModel> list=new ArrayList<>();
        for(int i=0;i<8;i++){
            PanelResultShowModel model=new PanelResultShowModel();
            model.setMondayFirstResult("");
            model.setTuesdayFirstResult("");
            model.setWednesdayFirstResult("");
            model.setThursdayFirstResult("");
            model.setFidayFirstResult("");
            model.setSaturdayFirstResult("");
            model.setSundayFirstResult("");

            model.setMondaySecondResult("");
            model.setTuesdaySecondResult("");
            model.setWednesdaySecondResult("");
            model.setThursdaySecondResult("");
            model.setFidaySecondResult("");
            model.setSaturdaySecondResult("");
            model.setSundaySecondResult("");

            model.setMondayThirdResult("");
            model.setTuesdayThirdResult("");
            model.setWednesdayThirdResult("");
            model.setThursdayThirdResult("");
            model.setFidayThirdResult("");
            model.setSaturdayThirdResult("");
            model.setSundayThirdResult("");
            list.add(model);
        }
        return list;
    }

}