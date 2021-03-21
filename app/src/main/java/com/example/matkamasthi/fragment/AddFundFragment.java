package com.example.matkamasthi.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matkamasthi.R;
import com.example.matkamasthi.manager.PreferenceManager;

public class AddFundFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFundFragment() {
        // Required empty public constructor
    }

    private EditText enterAmount;
    private Button fiveBtn, thousandBtn, fifteenBtn, addFundBtn;
    String userMobileNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_fund, container, false);
        initialization(view);
        return view;
    }

    private void initialization(View view) {
        //userMobileNo=new PreferenceManager(getContext()).getuserMobile();
        enterAmount = view.findViewById(R.id.add_fund_amount);
        fiveBtn = view.findViewById(R.id.add_five_hundred);
        thousandBtn = view.findViewById(R.id.add_thousand);
        fifteenBtn = view.findViewById(R.id.add_fifteen_hundred);
        addFundBtn = view.findViewById(R.id.add_fund_btn);
        userMobileNo=new PreferenceManager(getContext()).getuserMobile();

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount.setText("500");
            }
        });
        thousandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount.setText("1000");
            }
        });
        fifteenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount.setText("1500");
            }
        });

        addFundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(enterAmount.getText().toString());
                if (enterAmount.getText().toString().isEmpty() || amount < 500) {
                    enterAmount.requestFocus();
                    enterAmount.setError("Amount should be more than 500");
                } else {
                    String strMessage="Want to Add Rs. "+amount+" for mobile Number "+"8287428834"+" in Matka Masti";
                    boolean installed = appInstalledOrNot("com.whatsapp");
                    if (installed){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "+91"+userMobileNo
                                + "&text=" + strMessage));
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "WhatsApp not installed on your Device", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean appInstalledOrNot(String uri){
        PackageManager packageManager = getContext().getPackageManager();
        boolean appInstalled;

        try {
            packageManager.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            appInstalled = true;
        }catch (PackageManager.NameNotFoundException e){
            appInstalled = false;
        }
        return appInstalled;
    }
}