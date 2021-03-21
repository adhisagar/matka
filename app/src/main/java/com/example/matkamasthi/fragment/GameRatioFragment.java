package com.example.matkamasthi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.NoticeBoardRecyclerAdapter;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;

import java.util.ArrayList;


public class GameRatioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameRatioFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    ArrayList<NoticeBoardRecyclerModel> recyclerModels=new ArrayList<>();
    NoticeBoardRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_game_ratio, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.notice_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoticeBoardRecyclerAdapter(getContext(),recyclerModels);
        recyclerModels.add(new NoticeBoardRecyclerModel("SINGLE 10 KA 100"));
        recyclerModels.add(new NoticeBoardRecyclerModel("JODI 10 KA 1000"));
        recyclerModels.add(new NoticeBoardRecyclerModel("SINGLE PATTI 10 KA 1500"));
        recyclerModels.add(new NoticeBoardRecyclerModel("DOUBLE PATTI 10 KA 3000"));
        recyclerModels.add(new NoticeBoardRecyclerModel("TRIPLE PATTI 10 KA 5000"));
        recyclerModels.add(new NoticeBoardRecyclerModel("HALF SANGAM 10 KA 10000"));
        recyclerModels.add(new NoticeBoardRecyclerModel("FULL SANGAM 10 KA 100000"));
        recyclerView.setAdapter(adapter);


    }
}