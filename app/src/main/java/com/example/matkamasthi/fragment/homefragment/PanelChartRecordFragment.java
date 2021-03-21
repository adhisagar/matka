package com.example.matkamasthi.fragment.homefragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.activity.PanelResultShow;
import com.example.matkamasthi.adapter.HomeJodiChartAdapter;
import com.example.matkamasthi.adapter.HomePanelChartAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.model.HomeJodiChartModel;
import com.example.matkamasthi.model.HomePanelChartModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PanelChartRecordFragment extends Fragment implements HomePanelChartAdapter.HomePanleChartAdapterListener {

    public PanelChartRecordFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    ArrayList<HomePanelChartModel> recyclerModels=new ArrayList<>();
    HomePanelChartAdapter adapter;

    ImageView showBtn;
 //   boolean isVisible=false;
    String gameId;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_panel_chart_record, container, false);
        progressBar=view.findViewById(R.id.panel_chart_progressbar);
        initialize(view);
        gettingmarketName(view);
        return view;
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.panel_chart_record_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePanelChartAdapter(getContext(),recyclerModels,this);

        recyclerView.setAdapter(adapter);

    }

    private void gettingmarketName(final View view){
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.marketNameJodiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray categoryArry=response.getJSONArray("categories");
                    for (int i=0;i<categoryArry.length();i++){
                        JSONObject data=categoryArry.getJSONObject(i);
//                        String game_name=data.getString("game_name");
//                        String game_timing=data.getString("game_timing");
//                        String game_end_time=data.getString("game_end_time");
//                        String game_result_time=data.getString("game_result_time");
//                        String game_id=data.getString("games_id");
//                        String type=data.getString("type");

                        String game_name=data.getString("game_type_name");
                        String game_timing=data.getString("open_result_time");
                        String game_end_time=data.getString("close_result_time");
                       // String game_result_time=data.getString("game_result_time");
                        String game_id=data.getString("game_categories_id");
                        //String type=data.getString("type");
                        gameId=game_id;
                        recyclerModels.add(new HomePanelChartModel(game_name));
                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.i("volleyerror",error.toString());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(objectRequest);
    }

    @Override
    public void sendPosition(HomePanelChartModel model) {
        Toast.makeText(getContext(), model.getPanelText(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), PanelResultShow.class);
                intent.putExtra("gid",gameId);
                startActivity(intent);
    }
}

/*
 private void showAnimation(View view){
        showBtn=view.findViewById(R.id.panel_chart_show_more_btn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible==false){
                    isVisible=true;
//                    Animation animFadeIn = AnimationUtils.loadAnimation(getContext().getApplicationContext(),R.anim.fade_in);
//                    recyclerView.startAnimation(animFadeIn);

                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.getAdapter().notifyDataSetChanged();

                    showBtn.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
                else {
                    isVisible=false;
                    Animation animFadeIn = AnimationUtils.loadAnimation(getContext().getApplicationContext(),R.anim.fade_out);
                    recyclerView.startAnimation(animFadeIn);
                    recyclerView.setVisibility(View.GONE);
                    showBtn.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
    }


        //recyclerModels.add(new HomePanelChartModel("Sridevi Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Time Bazar Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Madhur Day Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Milan Day Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Rajdhani Day Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Supreme Day Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Kalyan Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Sridevi Night Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Supreme Night Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Milan Night Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Kalyan Night Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Rajdhani Night Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Main Ratan Jodi"));
//        recyclerModels.add(new HomePanelChartModel("Madhur Night Jodi"));

 */
