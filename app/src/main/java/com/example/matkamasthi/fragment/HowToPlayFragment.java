package com.example.matkamasthi.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.matkamasthi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToPlayFragment extends Fragment {

    public HowToPlayFragment() {
        // Required empty public constructor
    }


    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_how_to_play, container, false);
        textView=view.findViewById(R.id.marquee_text);
        Animation marquee = AnimationUtils.loadAnimation(getContext(), R.anim.marquee_text);
        textView.startAnimation(marquee);
//        textView.setSelected(true);
//        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        textView.setHorizontallyScrolling(true);
//        textView.setMarqueeRepeatLimit(-1);
//        textView.setFocusable(true);
//        textView.setFocusableInTouchMode(true);
        return view;
    }
}
