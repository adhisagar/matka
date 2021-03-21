package com.example.matkamasthi.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.SingleDataRecyclerAdapter;
import com.example.matkamasthi.adapter.TriplePattiRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.model.SingleDataRecyclerModel;
import com.example.matkamasthi.model.TriplePattiRecyclerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TriplePattiFragment extends Fragment{

    public TriplePattiFragment() {
        // Required empty public constructor
    }

    TextView dateText;
    RecyclerView recyclerView;
    ArrayList<TriplePattiRecyclerModel> recyclerModels;
    TriplePattiRecyclerAdapter adapter;
    private Button triplePattiSubmitBtn;
    private TextView totalPoint;
    ArrayList<String> editedAmount=new ArrayList<String>();
    HashMap<String,String> enteredAmount=new HashMap<>();

    boolean isSubmitEnable=false;
    boolean istotalCalculate=true;
    String gameId;
    Spinner spinner;
    String currentDateandTime;
    String tokenVal;
    String walletAmount;
    int wallet;
    boolean isTimeLies=false;

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> startTime=new ArrayList<>();
    ArrayList<String> endTime=new ArrayList<>();

    int total=0;

    EditText et000,et111,et222,et333,et444,et555,et666,et777,et888,et999;
    TextView field000,field111,field222,field333,field444,field555,field666,field777,field888,field999;
    String one_000,one_111,one_222,one_333,one_444,one_555,one_666,one_777,one_888,one_999;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_triple_patti, container, false);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        dateText=view.findViewById(R.id.triple_patti_date_spinner);
        dateText.setText(currentDateandTime);
        tokenVal=new PreferenceManager(getContext()).getuserToken();
        initialize(view);
        initializeEdittext(view);
        gettingmarketName(view);
        return view;
    }

    private void initialize(View view){
        totalPoint=view.findViewById(R.id.triple_patti_total_point);
        walletAmount=new PreferenceManager(getContext()).getWalletAmount();
        wallet=Integer.parseInt(walletAmount);
        triplePattiSubmitBtn=view.findViewById(R.id.triple_patti_submit_btn);
        recyclerView = view.findViewById(R.id.triple_patti_data_recycler_view);
        recyclerModels=populateList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TriplePattiRecyclerAdapter(getContext(),recyclerModels);
        recyclerView.setAdapter(adapter);

        triplePattiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparingEditText();
                //initializeString();
                if(isSubmitEnable==true){
                    one_000=et000.getText().toString();
                    one_111=et111.getText().toString();
                    one_222=et222.getText().toString();
                    one_333=et333.getText().toString();
                    one_444=et444.getText().toString();
                    one_555=et555.getText().toString();
                    one_666=et666.getText().toString();
                    one_777=et777.getText().toString();
                    one_888=et888.getText().toString();
                    one_999=et999.getText().toString();
                    if (!one_000.isEmpty()||!one_111.isEmpty()||!one_222.isEmpty()||!one_333.isEmpty()||!one_444.isEmpty()
                            ||!one_555.isEmpty()||!one_666.isEmpty()||!one_777.isEmpty()||!one_888.isEmpty()||!one_999.isEmpty()){
                        submitData(v);
                    }else {
                        Toast.makeText(getContext(), "Please Enter Correct Amount", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                }

                //submitData(v);
            }
        });

    }


    private ArrayList<TriplePattiRecyclerModel> populateList(){
        ArrayList<TriplePattiRecyclerModel> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            TriplePattiRecyclerModel model=new TriplePattiRecyclerModel();
            model.setEditedValueText("");
            if(i==0){
                model.setNumberText("000");
            }else {
                model.setNumberText(String.valueOf(i*111));
            }

            list.add(model);
        }
        return list;
    }

    private void gettingmarketName(final View view){
        //progressBar.setVisibility(View.VISIBLE);

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
                    //progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressBar.setVisibility(View.GONE);
                Log.i("volleyerror",error.toString());
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(objectRequest);
    }

    private void initializeSpinner(View view){

        ///Setting Array
        ArrayList<String> category=new ArrayList<>();
        for(int i=0;i<name.size();i++){
            category.add(name.get(i) +" "+ startTime.get(i) +"-"+ endTime.get(i));
        }
        // final List<String> categoryList = new ArrayList<>(Arrays.asList(category));



        ///Setting Array

        spinner=view.findViewById(R.id.triple_patti_Market_spinner);
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
        //   progressBar.setVisibility(View.GONE);
    }


    private void submitData(View view) {

        if(total==0){
            Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
        }else if(total>wallet){
            Toast.makeText(getContext(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("edited List",enteredAmount.toString());
            submitNumber(view);
        }

    }

    private void submitNumber(final View view){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.submitTriplePattiGame, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("double");
                    if(sucess.isEmpty()){
                        //progressBar.setVisibility(View.GONE);
                        String message=jsonObject.getString("message");
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }else{
                        String message=jsonObject.getString("message");
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        showSuccessDialog(view);

                    }

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
                params.put("one_000",one_000);
                params.put("one_111",one_111);
                params.put("one_222",one_222);
                params.put("one_333",one_333);
                params.put("one_444",one_444);
                params.put("one_555",one_555);
                params.put("one_666",one_666);
                params.put("one_777",one_777);
                params.put("one_888",one_888);
                params.put("one_999",one_999);

                params.put("field000",field000.getText().toString());
                params.put("field111",field111.getText().toString());
                params.put("field222",field222.getText().toString());
                params.put("field333",field333.getText().toString());
                params.put("field444",field444.getText().toString());
                params.put("field555",field555.getText().toString());
                params.put("field666",field666.getText().toString());
                params.put("field777",field777.getText().toString());
                params.put("field888",field888.getText().toString());
                params.put("field999",field999.getText().toString());

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
                totalPoint.setText("00");
                enteredAmount.clear();
                for(int i=0;i<recyclerModels.size();i++){
                    recyclerModels.get(i).setEditedValueText("");
                }
            }
        });
    }

    private void initializeEdittext(View view){
        et000=view.findViewById(R.id.frag_tripple_patti_data_edittext_000);
        et111=view.findViewById(R.id.frag_tripple_patti_data_edittext_111);
        et222=view.findViewById(R.id.frag_tripple_patti_data_edittext_222);
        et333=view.findViewById(R.id.frag_tripple_patti_data_edittext_333);
        et444=view.findViewById(R.id.frag_tripple_patti_data_edittext_444);
        et555=view.findViewById(R.id.frag_tripple_patti_data_edittext_555);
        et666=view.findViewById(R.id.frag_tripple_patti_data_edittext_666);
        et777=view.findViewById(R.id.frag_tripple_patti_data_edittext_777);
        et888=view.findViewById(R.id.frag_tripple_patti_data_edittext_888);
        et999=view.findViewById(R.id.frag_tripple_patti_data_edittext_999);

        field000=view.findViewById(R.id.frag_tripple_patti_text_number_000);
        field111=view.findViewById(R.id.frag_tripple_patti_text_number_111);
        field222=view.findViewById(R.id.frag_tripple_patti_text_number_222);
        field333=view.findViewById(R.id.frag_tripple_patti_text_number_333);
        field444=view.findViewById(R.id.frag_tripple_patti_text_number_444);
        field555=view.findViewById(R.id.frag_tripple_patti_text_number_555);
        field666=view.findViewById(R.id.frag_tripple_patti_text_number_666);
        field777=view.findViewById(R.id.frag_tripple_patti_text_number_777);
        field888=view.findViewById(R.id.frag_tripple_patti_text_number_888);
        field999=view.findViewById(R.id.frag_tripple_patti_text_number_999);
    }

    private void initializeString(){
        one_000=et000.getText().toString();
        one_111=et111.getText().toString();
        one_222=et222.getText().toString();
        one_333=et333.getText().toString();
        one_444=et444.getText().toString();
        one_555=et555.getText().toString();
        one_666=et666.getText().toString();
        one_777=et777.getText().toString();
        one_888=et888.getText().toString();
        one_999=et999.getText().toString();
    }

    private void comparingEditText(){
        if(!et000.getText().toString().isEmpty()){
            if(Integer.parseInt(et000.getText().toString())!=0 && Integer.parseInt(et000.getText().toString())>10 ){
                total=total+Integer.parseInt(et000.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et000.getText().toString())<10){
                et000.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et111.getText().toString().isEmpty()){
            if(Integer.parseInt(et111.getText().toString())!=0 || Integer.parseInt(et111.getText().toString())>10 ){
                total=total+Integer.parseInt(et111.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
            else if(Integer.parseInt(et111.getText().toString())<10){
                et111.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et222.getText().toString().isEmpty()){
            if(Integer.parseInt(et222.getText().toString())!=0 || Integer.parseInt(et222.getText().toString())>10 ){
                total=total+Integer.parseInt(et222.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
            else if(Integer.parseInt(et222.getText().toString())<10){
                et222.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et333.getText().toString().isEmpty()){
            if(Integer.parseInt(et333.getText().toString())!=0 || Integer.parseInt(et333.getText().toString())>10 ){
                total=total+Integer.parseInt(et333.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et333.getText().toString())<10){
                et333.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et444.getText().toString().isEmpty()){
            if(Integer.parseInt(et444.getText().toString())!=0 || Integer.parseInt(et444.getText().toString())>10 ){
                total=total+Integer.parseInt(et444.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et444.getText().toString())<10){
                et444.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et555.getText().toString().isEmpty()){
            if(Integer.parseInt(et555.getText().toString())!=0 || Integer.parseInt(et555.getText().toString())>10 ){
                total=total+Integer.parseInt(et555.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et555.getText().toString())<10){
                et555.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et666.getText().toString().isEmpty()){
            if(Integer.parseInt(et666.getText().toString())!=0 || Integer.parseInt(et666.getText().toString())>10 ){
                total=total+Integer.parseInt(et666.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et666.getText().toString())<10){
                et666.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et777.getText().toString().isEmpty()){
            if(Integer.parseInt(et777.getText().toString())!=0 || Integer.parseInt(et777.getText().toString())>10 ){
                total=total+Integer.parseInt(et777.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et777.getText().toString())<10){
                et777.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et888.getText().toString().isEmpty()){
            if(Integer.parseInt(et888.getText().toString())!=0 || Integer.parseInt(et888.getText().toString())>10 ){
                total=total+Integer.parseInt(et888.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et888.getText().toString())<10){
                et888.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et999.getText().toString().isEmpty()){
            if(Integer.parseInt(et999.getText().toString())!=0 || Integer.parseInt(et999.getText().toString())>10 ){
                total=total+Integer.parseInt(et999.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }else if(Integer.parseInt(et999.getText().toString())<10){
                et999.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
    }


}


/*
private void comparingEditText(View v){
        et000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et000.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et000.requestFocus();
                    Log.i("value",et000.getText().toString());
                }else {
                    total=total+Integer.parseInt(et000.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });


        et111.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et111.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et111.requestFocus();
                }else {
                    total=total+Integer.parseInt(et111.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et222.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et222.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et222.requestFocus();
                }else {
                    total=total+Integer.parseInt(et222.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et333.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et333.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et333.requestFocus();
                }else {
                    total=total+Integer.parseInt(et333.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et444.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et444.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et444.requestFocus();
                }else {
                    total=total+Integer.parseInt(et444.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et555.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et555.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et555.requestFocus();
                }else {
                    total=total+Integer.parseInt(et555.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et666.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et666.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et666.requestFocus();
                }else {
                    total=total+Integer.parseInt(et666.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et777.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et777.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et777.requestFocus();
                }else {
                    total=total+Integer.parseInt(et777.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et888.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et888.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et888.requestFocus();
                }else {
                    total=total+Integer.parseInt(et888.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et999.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et999.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et999.requestFocus();
                }else {
                    total=total+Integer.parseInt(et999.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });
    }
 */

//    @Override
//    public void sendTotal(String total, HashMap<String,String> hashMap) {
//        totalPoint.setText(total);
//       // editedAmount=amount;
//        enteredAmount=hashMap;
//    }
