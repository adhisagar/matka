package com.example.matkamasthi.fragment;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.GameHistoryRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.model.GameHistoryModel;
import com.example.matkamasthi.model.WalletHistoryRecyclerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GamesHistory extends Fragment {


    public GamesHistory() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList<GameHistoryModel> gameHistoryModelArrayList=new ArrayList<>();
    GameHistoryRecyclerAdapter adapter;
    String tokenValue;
    TextView completeDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_games_history, container, false);
        initialization(view);
        fetchingGameHistory();
        return view;
    }

    private void initialization(View view){
        tokenValue=new PreferenceManager(getContext()).getuserToken();
        completeDetails=view.findViewById(R.id.details_click_here);
        //tokenValue="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9tYXRrYS5vZGRob3Vyc3NvbHV0aW9ucy5pblwvYXBpXC9sb2dpbiIsImlhdCI6MTYxNTE0MTc0NiwiZXhwIjo5ODUzNDAxNDUwNiwibmJmIjoxNjE1MTQxNzQ2LCJqdGkiOiJONFBlQ1JYSWJaMWxMT3dkIiwic3ViIjo2LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.gKSi5Q-cJbF2s38d2de3J6za0FnEw_0Q4T79PbdegXY";
        recyclerView=view.findViewById(R.id.game_history_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter=new GameHistoryRecyclerAdapter(getContext(),gameHistoryModelArrayList);
        recyclerView.setAdapter(adapter);

        completeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query="https://matkamasthi.com/home";
                Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,query);
                getActivity().startActivity(intent);
            }
        });
    }

    private void fetchingGameHistory(){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.user_game_history, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   // JSONObject jresponse = response.getJSONObject("game_histories");
                    String success=response.getString("success");
                    if(success.equals("true")){
                        JSONArray jsonArray=response.getJSONArray("game_histories");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            String gameType=jsonObject.getString("type");

                            String gameUsedAmount=jsonObject.getString("use_points");
                            String gamePlayedDate=jsonObject.getString("game_played_date");
                            JSONArray gameArray=jsonObject.getJSONArray("games");
                            JSONObject gameObject=gameArray.getJSONObject(0);
                            String gameTypeName=gameObject.getString("game_name");

                            gameHistoryModelArrayList.add(new GameHistoryModel(gameType,gameTypeName,"Rs. "+gameUsedAmount,gamePlayedDate));
                            adapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(getContext(), "No History Found", Toast.LENGTH_SHORT).show();
                    }



                }catch (JSONException jsonException) {

                    jsonException.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (ErrorHelper.isNetworkProblem(error)) {
                    Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    String errorMesage = ErrorHelper.errorMessageFromData(new String(networkResponse.data));
                    if (errorMesage != null) {
                        Toast.makeText(getContext(), errorMesage, Toast.LENGTH_SHORT).show();
                    }

                }
                if (ErrorHelper.isServerProblem(error))
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                String token="Bearer "+tokenValue;
                headers.put("Authorization", token);

                return headers;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(objectRequest);
    }

}