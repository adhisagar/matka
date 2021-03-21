package com.example.matkamasthi.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.activity.HomeActivity;
import com.example.matkamasthi.activity.RegistrationActivity;
import com.example.matkamasthi.adapter.SattaResultAdapter;
import com.example.matkamasthi.adapter.SingleDataRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.model.SattaResultModel;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SingleFragment extends Fragment {

    public SingleFragment() {
        // Required empty public constructor
    }

    private Spinner spinner;
    TextView dateText;
    RecyclerView recyclerView;
    ArrayList<SingleDataRecyclerModel> recyclerModels;
    SingleDataRecyclerAdapter adapter;
    private Button singleSubmitBtn;
    boolean isTimeLies=false;
    boolean isSubmitEnable=false;

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> startTime=new ArrayList<>();
    ArrayList<String> endTime=new ArrayList<>();

    private TextView totalPoint;
    ArrayList<String> editedAmount=new ArrayList<String>();
    HashMap<String,String> enteredAmount=new HashMap<>();
    ProgressBar progressBar;

    String totalAmount;
    String currentDateandTime;
    String gameId;
    String tokenVal;
    int total=0;
    String walletAmount;
    int wallet;
    Button single_total_point_text;
    boolean istotalCalculate=true;

    TextView field1,field2,field3,field4,field5,field6,field7,field8,field9,field0;
    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et0;
    String firstNum,secondNum,thirdNum,forthNum,fifthNum,sixthNum,seventhNum,eightNum,ninthNum,zerothNum;
    int count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_single, container, false);
       // spinner=view.findViewById(R.id.single_date_spinner);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        dateText=view.findViewById(R.id.single_date_spinner);
        dateText.setText(currentDateandTime);
        progressBar=view.findViewById(R.id.market_name_progressbar);
        tokenVal=new PreferenceManager(getContext()).getuserToken();
        walletAmount=new PreferenceManager(getContext()).getWalletAmount();
        wallet=Integer.parseInt(walletAmount);
        initialize(view);
        initializeSubmitBtn(view);
        gettingmarketName(view);
  //      comparingEdittext();
 //       initializeSpinner(view);

        return view;
    }

    private void initialize(View view){
        totalPoint=view.findViewById(R.id.single_total_point);
        single_total_point_text=view.findViewById(R.id.single_total_point_text);

        singleSubmitBtn=view.findViewById(R.id.single_submit_btn);
        recyclerView = view.findViewById(R.id.single_data_recycler_view);
        recyclerModels=populateList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SingleDataRecyclerAdapter(getContext(),recyclerModels);
        recyclerView.setAdapter(adapter);
        single_total_point_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(istotalCalculate==true){
                    comparingEdittext();
//                    istotalCalculate=false;
//                }else {
//                    Toast.makeText(getContext(), "Not Calculatred", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        singleSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //    comparingEdittext();
                if(count==0){
                    firstNum=et1.getText().toString().trim();
                    secondNum=et2.getText().toString().trim();
                    thirdNum=et3.getText().toString().trim();
                    forthNum=et4.getText().toString().trim();
                    fifthNum=et5.getText().toString().trim();
                    sixthNum=et6.getText().toString().trim();
                    seventhNum=et7.getText().toString().trim();
                    eightNum=et8.getText().toString().trim();
                    ninthNum=et9.getText().toString().trim();
                    zerothNum=et0.getText().toString().trim();

                    if (!firstNum.isEmpty()||!secondNum.isEmpty()||!thirdNum.isEmpty()||!forthNum.isEmpty()||!fifthNum.isEmpty()
                            ||!sixthNum.isEmpty()||!seventhNum.isEmpty()||!eightNum.isEmpty()||!ninthNum.isEmpty()||!zerothNum.isEmpty()){
                        submitData(v);
                    }else {
                        Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        singleSubmitBtnListener();

    }

//    private void singleSubmitBtnListener(){
//
//        single_total_point_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(istotalCalculate==true){
//                    comparingEdittext();
//                    istotalCalculate=false;
//                }
//
//            }
//        });
//    }

    private void initializeSubmitBtn(View view){
        field1=view.findViewById(R.id.frag_single_text_number_1);
        field2=view.findViewById(R.id.frag_single_text_number_2);
        field3=view.findViewById(R.id.frag_single_text_number_3);
        field4=view.findViewById(R.id.frag_single_text_number_4);
        field5=view.findViewById(R.id.frag_single_text_number_5);
        field6=view.findViewById(R.id.frag_single_text_number_6);
        field7=view.findViewById(R.id.frag_single_text_number_7);
        field8=view.findViewById(R.id.frag_single_text_number_8);
        field9=view.findViewById(R.id.frag_single_text_number_9);
        field0=view.findViewById(R.id.frag_single_text_number_0);

        et1=view.findViewById(R.id.frag_single_data_edittext_1);
        et2=view.findViewById(R.id.frag_single_data_edittext_2);
        et3=view.findViewById(R.id.frag_single_data_edittext_3);
        et4=view.findViewById(R.id.frag_single_data_edittext_4);
        et5=view.findViewById(R.id.frag_single_data_edittext_5);
        et6=view.findViewById(R.id.frag_single_data_edittext_6);
        et7=view.findViewById(R.id.frag_single_data_edittext_7);
        et8=view.findViewById(R.id.frag_single_data_edittext_8);
        et9=view.findViewById(R.id.frag_single_data_edittext_9);
        et0=view.findViewById(R.id.frag_single_data_edittext_0);

    }

    private void comparingEdittext(){

        total=0;
        count=0;
        if(!et0.getText().toString().isEmpty() && Integer.parseInt(et0.getText().toString())>10){
            if(Integer.parseInt(et0.getText().toString())!=0 && Integer.parseInt(et0.getText().toString())>10 ){
                total=total+Integer.parseInt(et0.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(!et0.getText().toString().isEmpty() && Integer.parseInt(et0.getText().toString())<10){
            et0.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }



        if(!et1.getText().toString().isEmpty() && Integer.parseInt(et1.getText().toString())>10){
            if(Integer.parseInt(et1.getText().toString())!=0 || Integer.parseInt(et1.getText().toString())>10 ){
                total=total+Integer.parseInt(et1.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et1.getText().toString().isEmpty() && Integer.parseInt(et1.getText().toString())<10){
            et1.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }


        if(!et2.getText().toString().isEmpty() && Integer.parseInt(et2.getText().toString())>10){
            if(Integer.parseInt(et2.getText().toString())!=0 || Integer.parseInt(et2.getText().toString())>10 ){
                total=total+Integer.parseInt(et2.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et2.getText().toString().isEmpty() && Integer.parseInt(et2.getText().toString())<10){
            et2.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et3.getText().toString().isEmpty() && Integer.parseInt(et3.getText().toString())>10){
            if(Integer.parseInt(et3.getText().toString())!=0 && Integer.parseInt(et3.getText().toString())>10 ){
                total=total+Integer.parseInt(et3.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et3.getText().toString().isEmpty() && Integer.parseInt(et3.getText().toString())<10){
            et3.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et4.getText().toString().isEmpty() && Integer.parseInt(et4.getText().toString())>10){
            if(Integer.parseInt(et4.getText().toString())!=0 && Integer.parseInt(et4.getText().toString())>10 ){
                total=total+Integer.parseInt(et4.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et4.getText().toString().isEmpty() && Integer.parseInt(et4.getText().toString())<10){
            et4.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et5.getText().toString().isEmpty() && Integer.parseInt(et5.getText().toString())>10){
            if(Integer.parseInt(et5.getText().toString())!=0 && Integer.parseInt(et5.getText().toString())>10 ){
                total=total+Integer.parseInt(et5.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }
        else if(!et5.getText().toString().isEmpty() && Integer.parseInt(et5.getText().toString())<10){
            et5.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et6.getText().toString().isEmpty() && Integer.parseInt(et6.getText().toString())>10){
            if(Integer.parseInt(et6.getText().toString())!=0 && Integer.parseInt(et6.getText().toString())>10 ){
                total=total+Integer.parseInt(et6.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et6.getText().toString().isEmpty() && Integer.parseInt(et6.getText().toString())<10){
            et6.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et7.getText().toString().isEmpty() && Integer.parseInt(et7.getText().toString())>10){
            if(Integer.parseInt(et7.getText().toString())!=0 && Integer.parseInt(et7.getText().toString())>10 ){
                total=total+Integer.parseInt(et7.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et7.getText().toString().isEmpty() && Integer.parseInt(et7.getText().toString())<10){
            et7.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et8.getText().toString().isEmpty() && Integer.parseInt(et8.getText().toString())>10){
            if(Integer.parseInt(et8.getText().toString())!=0 && Integer.parseInt(et8.getText().toString())>10 ){
                total=total+Integer.parseInt(et8.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et8.getText().toString().isEmpty() && Integer.parseInt(et8.getText().toString())<10){
            et8.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et9.getText().toString().isEmpty() && Integer.parseInt(et9.getText().toString())>10){
            if(Integer.parseInt(et9.getText().toString())!=0 && Integer.parseInt(et9.getText().toString())>10 ){
                total=total+Integer.parseInt(et9.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(!et9.getText().toString().isEmpty() && Integer.parseInt(et9.getText().toString())<10){
            et9.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }
/*
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et1.removeTextChangedListener(this);
                if(Integer.parseInt(et1.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et1.requestFocus();
                }else {
                    total=total+Integer.parseInt(et1.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }

                et1.addTextChangedListener(this);
            }
            

        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et2.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et2.requestFocus();
                }else {
                    total=total+Integer.parseInt(et2.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et3.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et3.requestFocus();
                }else {
                    total=total+Integer.parseInt(et3.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et4.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et4.requestFocus();
                }else {
                    total=total+Integer.parseInt(et4.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et5.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et5.requestFocus();
                }else {
                    total=total+Integer.parseInt(et5.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et6.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et6.requestFocus();
                }else {
                    total=total+Integer.parseInt(et6.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et7.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et7.requestFocus();
                }else {
                    total=total+Integer.parseInt(et7.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et8.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et8.requestFocus();
                }else {
                    total=total+Integer.parseInt(et8.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et9.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et9.requestFocus();
                }else {
                    total=total+Integer.parseInt(et9.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });
        et0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et0.getText().toString().isEmpty()){
                    //Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    total=total-Integer.parseInt(et0.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et0.getText().toString())<10 || et0.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et0.requestFocus();
                }else {
                    total=total+Integer.parseInt(et0.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });
 */
    }

    private void gettingmarketName(final View view){
        progressBar.setVisibility(View.VISIBLE);

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

                        name.add(game_name);
                        startTime.add(game_timing);
                        endTime.add(game_end_time);
                        gameId=game_id;

                    }
                    initializeSpinner(view);
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


    private ArrayList<SingleDataRecyclerModel> populateList(){
        ArrayList<SingleDataRecyclerModel> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            SingleDataRecyclerModel model=new SingleDataRecyclerModel();
            model.setEditedValueText("");
            model.setNumberText(String.valueOf(i));
            list.add(model);
        }
        return list;
    }


    private void initializeSpinner(View view){

        ///Setting Array
        ArrayList<String> category=new ArrayList<>();
        for(int i=0;i<name.size();i++){
            category.add(name.get(i) +" "+ startTime.get(i) +"-"+ endTime.get(i));
        }
       // final List<String> categoryList = new ArrayList<>(Arrays.asList(category));

        ///Setting Array

        spinner=view.findViewById(R.id.single_Market_spinner);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm a");
        final String strDate = mdformat.format(calendar.getTime());
        Log.i("current",strDate);


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,category){
            @Override
            public boolean isEnabled(int position){

                try {
                    Date time1 = new SimpleDateFormat("h:mm a").parse(startTime.get(position));
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(time1);
                    calendar1.add(Calendar.DATE, 1);

                    Date time2 = new SimpleDateFormat("h:mm a").parse(endTime.get(position));
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(time2);
                    calendar2.add(Calendar.DATE, 1);

                    Date d = new SimpleDateFormat("h:mm a").parse(strDate);
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(d);
                    calendar3.add(Calendar.DATE, 1);

                    Date x = calendar3.getTime();
                    if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                        //checkes whether the current time is between 14:49:00 and 20:11:13.
                       // System.out.println(true);
                        isTimeLies=true;
                    } else {
                        isTimeLies=false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(isTimeLies==false)
                {
                    // Disable the second item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(isTimeLies==false) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                   // tv.setVisibility(View.GONE);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                 //   tv.setVisibility(View.VISIBLE);
                }
                return view;
            }


        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        progressBar.setVisibility(View.GONE);
    }



    private void submitData(View view) {
        if(total==0){
            Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
        } else if(total>wallet){
            Toast.makeText(getContext(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("edited List",enteredAmount.toString());
            submitNumber(view);
        }
    }

    private void submitNumber(final View view){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.submitSingleGame, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String message=jsonObject.getString("message");
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//                    String sucess=jsonObject.getString("single");
//                    if(sucess.isEmpty()){
//                        //progressBar.setVisibility(View.GONE);
//                        String message=jsonObject.getString("message");
//                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//
//                    }else{
//                        String message=jsonObject.getString("message");
//                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//                        showSuccessDialog(view);
//                        Intent intent = getActivity().getIntent();
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        getActivity().finish();
//                        startActivity(intent);
//
//                    }

                } catch (JSONException jsonException) {
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
        }) {

                @Override
                public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                String token="Bearer "+tokenVal;
                headers.put("Authorization", token);

                return headers;
            }

            ///name,username,mobile,password
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("gameid", gameId);
                params.put("game_play_date", currentDateandTime);
               // params.put(fieldNum, mobile);

                params.put("field11",field1.getText().toString());
                params.put("field22",field2.getText().toString());
                params.put("field33",field3.getText().toString());
                params.put("field44",field4.getText().toString());
                params.put("field55",field5.getText().toString());
                params.put("field66",field6.getText().toString());
                params.put("field77",field7.getText().toString());
                params.put("field88",field8.getText().toString());
                params.put("field99",field9.getText().toString());
                params.put("field100",field0.getText().toString());

                params.put("first_num11", firstNum);
                params.put("second_num22", secondNum);
                params.put("third_num33", thirdNum);
                params.put("forth_num44", forthNum);
                params.put("fifth_num55", fifthNum);
                params.put("sixth_num66", sixthNum);
                params.put("seventh_num77", seventhNum);
                params.put("eighth_num88", eightNum);
                params.put("ninth_num99", ninthNum);
                params.put("tenth_num100", zerothNum);

                params.put("gamespoint", String.valueOf(total));
                return params;
            }

        };


        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        objectRequest.setShouldCache(false);
        requestQueue.add(objectRequest);
    }

    private void showSuccessDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.submit_succesful_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button okBtn=alertDialog.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                clearEditText();
            }
        });
    }

    private void clearEditText(){
        et0.getText().clear();
        et1.getText().clear();
        et2.getText().clear();
        et3.getText().clear();
        et4.getText().clear();
        et5.getText().clear();
        et6.getText().clear();
        et7.getText().clear();
        et8.getText().clear();
        et9.getText().clear();
        total=0;

    }

}


//    String[] name=new String[]{
//            "Sridevi",
//            "Time Bazar",
//            "Madhur Day",
//            "Milan Day",
//            "Rajdhani Day",
//            "Supreme Day",
//            "Kalyan",
//            "Sridevi Night",
//            "Supreme Night",
//            "Milan Night",
//            "Kalyan Night",
//            "Rajdhani Night",
//            "Main Ratan",
//            "Madhur Night"
//    };
//    String [] startTime=new String[]{
//          "10:00 am",
//          "10:00 am",
//          "11:00 pm",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//          "11:00 pm",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//          "10:00 am",
//    };

//    String [] endTime=new String[]{
//            "11:20 am",
//            "11:20 am",
//            "11:59 pm",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//            "11:59 pm",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//            "11:20 am",
//    };

        //        final List<String> fieldNum=new ArrayList<>();
//        fieldNum.add("field1");fieldNum.add("field2");fieldNum.add("field3");fieldNum.add("field4");fieldNum.add("field5");fieldNum.add("field6");
//        fieldNum.add("field7");fieldNum.add("field8");fieldNum.add("field9");fieldNum.add("field10");




