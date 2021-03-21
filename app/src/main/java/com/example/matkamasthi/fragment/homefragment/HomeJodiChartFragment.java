package com.example.matkamasthi.fragment.homefragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.matkamasthi.activity.ResultShowActivity;
import com.example.matkamasthi.adapter.HomeJodiChartAdapter;
import com.example.matkamasthi.adapter.NoticeBoardRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.model.HomeJodiChartModel;
import com.example.matkamasthi.model.HomePanelChartModel;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;
import com.example.matkamasthi.model.SattaResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeJodiChartFragment extends Fragment implements HomeJodiChartAdapter.JodiChartAdapterListener {

    public HomeJodiChartFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList<HomeJodiChartModel> recyclerModels=new ArrayList<>();
    HomeJodiChartAdapter adapter;
    ImageView showBtn;
    ProgressBar progressBar;
    String gameId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_jodi_chart, container, false);
        initialize(view);
        gettingmarketName(view);
        return view;
    }

    private void initialize(View view) {
        progressBar=view.findViewById(R.id.jodi_chart_progressbar);
        recyclerView = view.findViewById(R.id.jodi_chart_record_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeJodiChartAdapter(getContext(),recyclerModels,this);
        recyclerView.setAdapter(adapter);

    }

    private void gettingmarketName(View view){
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.marketNameJodiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray categoryArry=response.getJSONArray("categories");
                    for (int i=0;i<categoryArry.length();i++){
                        JSONObject data=categoryArry.getJSONObject(i);
                        String game_name=data.getString("game_type_name");
                        String game_timing=data.getString("open_result_time");
                        String game_end_time=data.getString("close_result_time");
//                        String game_result_time=data.getString("game_result_time");
                        String game_id=data.getString("game_categories_id");
//                        String type=data.getString("type");
                        gameId=game_id;
                        recyclerModels.add(new HomeJodiChartModel(game_name));
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
    public void sendPosition(HomeJodiChartModel chartModel) {
        Toast.makeText(getContext(), chartModel.getJodiText(), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(), ResultShowActivity.class);
        intent.putExtra("gid",gameId);
        startActivity(intent);
    }
}

/*
LayoutAnimationController animationController=AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_down_to_up);
        recyclerView.setLayoutAnimation(animationController);

 private void showAnimation(View view){
        showBtn=view.findViewById(R.id.home_jodi_chart_show_more_btn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible==false){
                    isVisible=true;
                    recyclerView.setVisibility(View.VISIBLE);
                    LayoutAnimationController animationController=AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_down_to_up);
                    recyclerView.setLayoutAnimation(animationController);

                    recyclerView.getAdapter().notifyDataSetChanged();
                    recyclerView.scheduleLayoutAnimation();


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



         GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerModels.add(new HomeJodiChartModel("Sridevi Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Time Bazar Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Madhur Day Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Milan Day Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Rajdhani Day Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Supreme Day Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Kalyan Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Sridevi Night Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Supreme Night Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Milan Night Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Kalyan Night Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Rajdhani Night Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Main Ratan Jodi"));
        recyclerModels.add(new HomeJodiChartModel("Madhur Night Jodi"));


        /    @Override
//    public void onJodiChartSelected(HomeJodiChartModel chartModel) {
//        int position=recyclerView.getChildCount();
//
//        Toast.makeText(getContext(), "Postion is "+position, Toast.LENGTH_SHORT).show();
//
//    }

 */
