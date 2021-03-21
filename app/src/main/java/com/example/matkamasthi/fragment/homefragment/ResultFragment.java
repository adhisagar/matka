package com.example.matkamasthi.fragment.homefragment;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.NoticeBoardRecyclerAdapter;
import com.example.matkamasthi.adapter.SattaResultAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.model.HomeJodiChartModel;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;
import com.example.matkamasthi.model.SattaResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    public ResultFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList<SattaResultModel> recyclerModels=new ArrayList<>();
    SattaResultAdapter adapter;
    ImageView showBtn;
    String gameId;
   // boolean isVisible=false;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_result, container, false);
        initialize(view);
        gettingmarketName(view);
 //       gettingResult(view);
        return view;
    }

    private void initialize(View view) {
        progressBar=view.findViewById(R.id.result_chart_progressbar);
        recyclerView = view.findViewById(R.id.sattka_game_result_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SattaResultAdapter(getContext(),recyclerModels);
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
                        String game_name=data.getString("game_type_name");
                        String game_timing=data.getString("open_result_time");
                        String game_end_time=data.getString("close_result_time");
//                        String game_result_time=data.getString("game_result_time");
                        String game_id=data.getString("game_categories_id");
//                        String type=data.getString("type");
                        gameId=game_id;
                        recyclerModels.add(new SattaResultModel(game_timing,game_end_time,game_name));
//                        gettingResult(view);
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
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        objectRequest.setShouldCache(false);
        requestQueue.add(objectRequest);
    }

    private void gettingResult(View view){
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest objectRequest=new JsonArrayRequest(Request.Method.GET, Constant.jodi_game_result, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                try {
//                    JSONArray categoryArry=response.getJSONArray("categories");
                    for (int i=0;i<response.length();i++){
                        JSONObject data=response.getJSONObject(i);
                        String gameResult=data.getString("jodi_number");

                        String game_id=data.getString("game_categories_id");
//                        String type=data.getString("type");
                        gameId=game_id;
                        recyclerModels.add(new SattaResultModel(gameResult));
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

}
/*
private void showAnimation(View view){
        showBtn=view.findViewById(R.id.result_jodi_chart_show_more_btn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isVisible==false){
                    isVisible=true;
                    LayoutAnimationController animationController=AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_down_to_up);
                    recyclerView.setLayoutAnimation(animationController);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.getAdapter().notifyDataSetChanged();
                    recyclerView.scheduleLayoutAnimation();

                    showBtn.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
                else {
                    isVisible=false;
                    Animation animFadeIn = AnimationUtils.loadAnimation(getContext().getApplicationContext(),R.anim.fade_out);
                    recyclerView.startAnimation(animFadeIn);
                    recyclerView.setVisibility(View.VISIBLE);
                    showBtn.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
    }


    //        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Sridevi"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Time Bazar"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Madhur Day"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Milan Day"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Rajdhani Day"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Supreme Day"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Kalyan"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Sridevi Night"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Supreme Night"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Milan Night"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Kalyan Night"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Rajdhani Night"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Main Ratan"));
//        recyclerModels.add(new SattaResultModel("10:00 AM","11:00 AM","Madhur Night"));


 */
