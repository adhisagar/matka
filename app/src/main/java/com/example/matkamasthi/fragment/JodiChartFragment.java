package com.example.matkamasthi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.matkamasthi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JodiChartFragment extends Fragment {

    public JodiChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jodi_chart, container, false);
        return view;
    }
}
