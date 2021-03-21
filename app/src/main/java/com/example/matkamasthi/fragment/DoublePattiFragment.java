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
import com.example.matkamasthi.adapter.TriplePattiRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
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
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoublePattiFragment extends Fragment{

    public DoublePattiFragment() {
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

    TextView field100,field119,field155,field227,field335,field344,field399,field588,field669;
    TextView field200,field110,field228,field255,field336,field499,field660,field688,field778;
    TextView field300,field166,field229,field337,field355,field445,field599,field779,field788;
    TextView field400,field112,field220,field266,field338,field446,field455,field699,field770;
    TextView field500,field113,field122,field177,field339,field366,field447,field799,field889;
    TextView field600,field114,field277,field330,field448,field466,field556,field880,field899;
    TextView field700,field115,field133,field188,field223,field377,field449,field557,field566;
    TextView field800,field116,field224,field233,field288,field440,field477,field558,field990;
    TextView field900,field117,field144,field199,field225,field388,field559,field577,field667;
    TextView field550,field668,field244,field299,field226,field488,field677,field118,field334;

    String one_100,one_119,one_155,one_227,one_335,one_344,one_399,one_588,one_669;
    String two_200,two_110,two_228,two_255,two_336,two_499,two_660,two_688,two_778;
    String three_300,three_166,three_229,three_337,three_355,three_445,three_599,three_779,three_788;
    String four_400,four_112,four_220,four_266,four_338,four_446,four_455,four_699,four_770;
    String five_500,five_113,five_122,five_177,five_339,five_366,five_447,five_799,five_889;
    String six_600,six_114,six_277,six_330,six_448,six_466,six_556,six_880,six_899;
    String seven_700,seven_115,seven_133,seven_188,seven_223,seven_377,seven_449,seven_557,seven_566;
    String eight_800,eight_116,eight_224,eight_233,eight_288,eight_440,eight_477,eight_558,eight_990;
    String nine_900,nine_117,nine_144,nine_199,nine_225,nine_388,nine_559,nine_577,nine_667;
    String ten_550,ten_668,ten_244,ten_299,ten_226,ten_488,ten_677,ten_118,ten_334;

    EditText et100,et119,et155,et227,et335,et344,et399,et588,et669;
    EditText et200,et110,et228,et255,et336,et499,et660,et688,et778;
    EditText et300,et166,et229,et337,et355,et445,et599,et779,et788;
    EditText et400,et112,et220,et266,et338,et446,et455,et699,et770;
    EditText et500,et113,et122,et177,et339,et366,et447,et799,et889;
    EditText et600,et114,et277,et330,et448,et466,et556,et880,et899;
    EditText et700,et115,et133,et188,et223,et377,et449,et557,et566;
    EditText et800,et116,et224,et233,et288,et440,et477,et558,et990;
    EditText et900,et117,et144,et199,et225,et388,et559,et577,et667;
    EditText et550,et668,et244,et299,et226,et488,et677,et118,et334;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_double_patti, container, false);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        dateText=view.findViewById(R.id.double_patti_date_spinner);
        dateText.setText(currentDateandTime);
        initialize(view);
        initializeEditText(view);
        gettingmarketName(view);
        comparingText();
        return view;
    }


    private void initialize(View view){
        totalPoint=view.findViewById(R.id.double_patti_total_point);
        tokenVal=new PreferenceManager(getContext()).getuserToken();
        walletAmount=new PreferenceManager(getContext()).getWalletAmount();
        wallet=Integer.parseInt(walletAmount);
        triplePattiSubmitBtn=view.findViewById(R.id.double_patti_submit_btn);
        recyclerView = view.findViewById(R.id.double_patti_data_recycler_view);
        recyclerModels=populateList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TriplePattiRecyclerAdapter(getContext(),recyclerModels);
        recyclerView.setAdapter(adapter);

        triplePattiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparingText();
                if(isSubmitEnable==true){
                    initializeString();
                    submitData(v);
                } else {
                    Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                }

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

        spinner=view.findViewById(R.id.double_patti_Market_spinner);
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
        } else if(total>wallet){
            Toast.makeText(getContext(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("edited List",enteredAmount.toString());
            submitNumber(view);
        }

    }

    private void submitNumber(final View view){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.submitDoublePattiGame, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String message=jsonObject.getString("message");
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//                    String sucess=jsonObject.getString("double");
//                    if(sucess.isEmpty()){
//                        //progressBar.setVisibility(View.GONE);
//                        String message=jsonObject.getString("message");
//                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//                    }else{
//                        String message=jsonObject.getString("message");
//                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
//                        showSuccessDialog(view);
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
                params.put("one_1000",one_100);
                params.put("two_200",two_200);
                params.put("three_300",three_300);
                params.put("four_400",four_400);
                params.put("five_500",five_500);
                params.put("six_600",six_600);
                params.put("seven_700",seven_700);
                params.put("eight_800",eight_800);
                params.put("nine_900",nine_900);
                params.put("ten_550",ten_550);
                params.put("one_119",one_119);
                params.put("two_110",two_110);
                params.put("three_166",three_166);
                params.put("four_112",four_112);
                params.put("five_113",five_113);
                params.put("six_114",six_114);
                params.put("seven_115",seven_115);
                params.put("eight_116",eight_116);
                params.put("nine_117",nine_117);
                params.put("ten_668",ten_668);
                params.put("one_155",one_155);
                params.put("two_228",two_228);
                params.put("three_229",three_229);
                params.put("four_220",four_220);
                params.put("five_122",five_122);
                params.put("six_277",six_277);
                params.put("seven_133",seven_133);
                params.put("eight_224",eight_224);
                params.put("nine_144",nine_144);
                params.put("ten_244",ten_244);
                params.put("one_227",one_227);
                params.put("two_255",two_255);
                params.put("three_337",three_337);
                params.put("four_266",four_266);
                params.put("five_177",five_177);
                params.put("six_330",six_330);
                params.put("seven_188",seven_188);
                params.put("eight_233",eight_233);
                params.put("nine_199",nine_199);
                params.put("ten_299",ten_299);
                params.put("one_335",one_335);
                params.put("two_336",two_336);
                params.put("three_355",three_355);
                params.put("four_338",four_338);
                params.put("five_339",five_339);
                params.put("six_448",six_448);
                params.put("seven_223",seven_223);
                params.put("eight_288",eight_288);
                params.put("nine_225",nine_225);
                params.put("ten_226",ten_226);
                params.put("one_344",one_344);
                params.put("two_499",two_499);
                params.put("three_445",three_445);
                params.put("four_446",four_446);
                params.put("five_366",five_366);
                params.put("six_466",six_466);
                params.put("seven_377",seven_377);
                params.put("eight_440",eight_440);
                params.put("nine_388",nine_388);
                params.put("ten_488",ten_488);
                params.put("one_399",one_399);
                params.put("two_660",two_660);
                params.put("three_599",three_599);
                params.put("four_455",four_455);
                params.put("five_447",five_447);
                params.put("six_556",six_556);
                params.put("seven_449",seven_449);
                params.put("eight_477",eight_477);
                params.put("nine_559",nine_559);
                params.put("ten_677",ten_677);
                params.put("one_588",one_588);
                params.put("two_688",two_688);
                params.put("three_779",three_779);
                params.put("four_699",four_699);
                params.put("five_799",five_799);
                params.put("six_880",six_880);
                params.put("seven_557",seven_557);
                params.put("eight_558",eight_558);
                params.put("nine_577",nine_577);
                params.put("ten_118",ten_118);
                params.put("one_669",one_669);
                params.put("two_778",two_778);
                params.put("three_788",three_788);
                params.put("four_770",four_770);
                params.put("five_889",five_889);
                params.put("six_899",six_899);
                params.put("seven_566",seven_566);
                params.put("eight_990",eight_990);
                params.put("nine_667",nine_667);
                params.put("ten_334",ten_334);


                params.put("field10",field100.getText().toString());
                params.put("field200",field200.getText().toString());
                params.put("field300",field300.getText().toString());
                params.put("field400",field400.getText().toString());
                params.put("field500",field500.getText().toString());
                params.put("field600",field600.getText().toString());
                params.put("field700",field700.getText().toString());
                params.put("field800",field800.getText().toString());
                params.put("field900",field900.getText().toString());
                params.put("field550",field550.getText().toString());
                params.put("field119",field119.getText().toString());
                params.put("field110",field110.getText().toString());
                params.put("field166",field166.getText().toString());
                params.put("field112",field112.getText().toString());
                params.put("field113",field113.getText().toString());
                params.put("field114",field114.getText().toString());
                params.put("field115",field115.getText().toString());
                params.put("field116",field116.getText().toString());
                params.put("field117",field117.getText().toString());
                params.put("field668",field668.getText().toString());
                params.put("field155",field155.getText().toString());
                params.put("field228",field228.getText().toString());
                params.put("field229",field229.getText().toString());
                params.put("field220",field220.getText().toString());
                params.put("field122",field122.getText().toString());
                params.put("field277",field277.getText().toString());
                params.put("field133",field133.getText().toString());
                params.put("field224",field224.getText().toString());
                params.put("field144",field144.getText().toString());
                params.put("field244",field244.getText().toString());
                params.put("field227",field227.getText().toString());
                params.put("field255",field255.getText().toString());
                params.put("field337",field337.getText().toString());
                params.put("field266",field266.getText().toString());
                params.put("field177",field177.getText().toString());
                params.put("field330",field330.getText().toString());
                params.put("field188",field188.getText().toString());
                params.put("field233",field233.getText().toString());
                params.put("field199",field199.getText().toString());
                params.put("field299",field299.getText().toString());
                params.put("field335",field335.getText().toString());
                params.put("field336",field336.getText().toString());
                params.put("field355",field355.getText().toString());
                params.put("field338",field338.getText().toString());
                params.put("field339",field339.getText().toString());
                params.put("field448",field448.getText().toString());
                params.put("field223",field223.getText().toString());
                params.put("field288",field288.getText().toString());
                params.put("field225",field225.getText().toString());
                params.put("field226",field226.getText().toString());
                params.put("field344",field344.getText().toString());
                params.put("field499",field499.getText().toString());
                params.put("field445",field445.getText().toString());
                params.put("field446",field446.getText().toString());
                params.put("field366",field366.getText().toString());
                params.put("field466",field466.getText().toString());
                params.put("field377",field377.getText().toString());
                params.put("field440",field440.getText().toString());
                params.put("field388",field388.getText().toString());
                params.put("field488",field488.getText().toString());
                params.put("field399",field399.getText().toString());
                params.put("field660",field660.getText().toString());
                params.put("field599",field599.getText().toString());
                params.put("field455",field455.getText().toString());
                params.put("field447",field447.getText().toString());
                params.put("field556",field556.getText().toString());
                params.put("field449",field449.getText().toString());
                params.put("field477",field477.getText().toString());
                params.put("field559",field559.getText().toString());
                params.put("field677",field677.getText().toString());
                params.put("field588",field588.getText().toString());
                params.put("field688",field688.getText().toString());
                params.put("field779",field779.getText().toString());
                params.put("field699",field699.getText().toString());
                params.put("field799",field799.getText().toString());
                params.put("field880",field880.getText().toString());
                params.put("field557",field557.getText().toString());
                params.put("field558",field558.getText().toString());
                params.put("field577",field577.getText().toString());
                params.put("field118",field118.getText().toString());
                params.put("field669",field669.getText().toString());
                params.put("field778",field778.getText().toString());
                params.put("field788",field788.getText().toString());
                params.put("field770",field770.getText().toString());
                params.put("field889",field889.getText().toString());
                params.put("field899",field899.getText().toString());
                params.put("field566",field566.getText().toString());
                params.put("field990",field990.getText().toString());
                params.put("field667",field667.getText().toString());
                params.put("field334",field334.getText().toString());

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



    private void initializeEditText(View view){
        field100=view.findViewById(R.id.frag_double_patti_text_number_100);
        field200=view.findViewById(R.id.frag_double_patti_text_number_200);
        field300=view.findViewById(R.id.frag_double_patti_text_number_300);
        field400=view.findViewById(R.id.frag_double_patti_text_number_400);
        field500=view.findViewById(R.id.frag_double_patti_text_number_500);
        field600=view.findViewById(R.id.frag_double_patti_text_number_600);
        field700=view.findViewById(R.id.frag_double_patti_text_number_700);
        field800=view.findViewById(R.id.frag_double_patti_text_number_800);
        field900=view.findViewById(R.id.frag_double_patti_text_number_900);
        field550=view.findViewById(R.id.frag_double_patti_text_number_550);
        field119=view.findViewById(R.id.frag_double_patti_text_number_119);
        field110=view.findViewById(R.id.frag_double_patti_text_number_110);
        field166=view.findViewById(R.id.frag_double_patti_text_number_166);
        field112=view.findViewById(R.id.frag_double_patti_text_number_112);
        field113=view.findViewById(R.id.frag_double_patti_text_number_113);
        field144=view.findViewById(R.id.frag_double_patti_text_number_144);
        field115=view.findViewById(R.id.frag_double_patti_text_number_115);
        field116=view.findViewById(R.id.frag_double_patti_text_number_116);
        field117=view.findViewById(R.id.frag_double_patti_text_number_117);
        field668=view.findViewById(R.id.frag_double_patti_text_number_668);
        field155=view.findViewById(R.id.frag_double_patti_text_number_155);
        field228=view.findViewById(R.id.frag_double_patti_text_number_228);
        field229=view.findViewById(R.id.frag_double_patti_text_number_229);
        field220=view.findViewById(R.id.frag_double_patti_text_number_220);
        field122=view.findViewById(R.id.frag_double_patti_text_number_122);
        field277=view.findViewById(R.id.frag_double_patti_text_number_277);
        field133=view.findViewById(R.id.frag_double_patti_text_number_133);
        field224=view.findViewById(R.id.frag_double_patti_text_number_224);
        field114=view.findViewById(R.id.frag_double_patti_text_number_114);
        field244=view.findViewById(R.id.frag_double_patti_text_number_244);
        field227=view.findViewById(R.id.frag_double_patti_text_number_227);
        field255=view.findViewById(R.id.frag_double_patti_text_number_255);
        field337=view.findViewById(R.id.frag_double_patti_text_number_337);
        field266=view.findViewById(R.id.frag_double_patti_text_number_266);
        field177=view.findViewById(R.id.frag_double_patti_text_number_177);
        field330=view.findViewById(R.id.frag_double_patti_text_number_330);
        field188=view.findViewById(R.id.frag_double_patti_text_number_188);
        field233=view.findViewById(R.id.frag_double_patti_text_number_233);
        field199=view.findViewById(R.id.frag_double_patti_text_number_199);
        field299=view.findViewById(R.id.frag_double_patti_text_number_299);
        field335=view.findViewById(R.id.frag_double_patti_text_number_335);
        field336=view.findViewById(R.id.frag_double_patti_text_number_336);
        field355=view.findViewById(R.id.frag_double_patti_text_number_355);
        field338=view.findViewById(R.id.frag_double_patti_text_number_338);
        field339=view.findViewById(R.id.frag_double_patti_text_number_339);
        field448=view.findViewById(R.id.frag_double_patti_text_number_448);
        field223=view.findViewById(R.id.frag_double_patti_text_number_223);
        field288=view.findViewById(R.id.frag_double_patti_text_number_288);
        field225=view.findViewById(R.id.frag_double_patti_text_number_225);
        field226=view.findViewById(R.id.frag_double_patti_text_number_226);
        field344=view.findViewById(R.id.frag_double_patti_text_number_344);
        field499=view.findViewById(R.id.frag_double_patti_text_number_499);
        field445=view.findViewById(R.id.frag_double_patti_text_number_445);
        field446=view.findViewById(R.id.frag_double_patti_text_number_446);
        field366=view.findViewById(R.id.frag_double_patti_text_number_366);
        field466=view.findViewById(R.id.frag_double_patti_text_number_466);
        field377=view.findViewById(R.id.frag_double_patti_text_number_377);
        field440=view.findViewById(R.id.frag_double_patti_text_number_440);
        field388=view.findViewById(R.id.frag_double_patti_text_number_388);
        field488=view.findViewById(R.id.frag_double_patti_text_number_488);
        field399=view.findViewById(R.id.frag_double_patti_text_number_399);
        field660=view.findViewById(R.id.frag_double_patti_text_number_660);
        field599=view.findViewById(R.id.frag_double_patti_text_number_599);
        field455=view.findViewById(R.id.frag_double_patti_text_number_455);
        field447=view.findViewById(R.id.frag_double_patti_text_number_447);
        field556=view.findViewById(R.id.frag_double_patti_text_number_556);
        field449=view.findViewById(R.id.frag_double_patti_text_number_449);
        field477=view.findViewById(R.id.frag_double_patti_text_number_477);
        field559=view.findViewById(R.id.frag_double_patti_text_number_559);
        field677=view.findViewById(R.id.frag_double_patti_text_number_677);
        field588=view.findViewById(R.id.frag_double_patti_text_number_588);
        field688=view.findViewById(R.id.frag_double_patti_text_number_688);
        field779=view.findViewById(R.id.frag_double_patti_text_number_779);
        field699=view.findViewById(R.id.frag_double_patti_text_number_699);
        field799=view.findViewById(R.id.frag_double_patti_text_number_799);
        field880=view.findViewById(R.id.frag_double_patti_text_number_880);
        field557=view.findViewById(R.id.frag_double_patti_text_number_557);
        field558=view.findViewById(R.id.frag_double_patti_text_number_558);
        field577=view.findViewById(R.id.frag_double_patti_text_number_577);
        field118=view.findViewById(R.id.frag_double_patti_text_number_118);
        field669=view.findViewById(R.id.frag_double_patti_text_number_669);
        field778=view.findViewById(R.id.frag_double_patti_text_number_778);
        field788=view.findViewById(R.id.frag_double_patti_text_number_788);
        field770=view.findViewById(R.id.frag_double_patti_text_number_770);
        field889=view.findViewById(R.id.frag_double_patti_text_number_889);
        field899=view.findViewById(R.id.frag_double_patti_text_number_899);
        field566=view.findViewById(R.id.frag_double_patti_text_number_566);
        field990=view.findViewById(R.id.frag_double_patti_text_number_990);
        field667=view.findViewById(R.id.frag_double_patti_text_number_667);
        field334=view.findViewById(R.id.frag_double_patti_text_number_334);


        et100=view.findViewById(R.id.frag_double_patti_data_edittext_100);
        et200=view.findViewById(R.id.frag_double_patti_data_edittext_200);
        et300=view.findViewById(R.id.frag_double_patti_data_edittext_300);
        et400=view.findViewById(R.id.frag_double_patti_data_edittext_400);
        et500=view.findViewById(R.id.frag_double_patti_data_edittext_500);
        et600=view.findViewById(R.id.frag_double_patti_data_edittext_600);
        et700=view.findViewById(R.id.frag_double_patti_data_edittext_700);
        et800=view.findViewById(R.id.frag_double_patti_data_edittext_800);
        et900=view.findViewById(R.id.frag_double_patti_data_edittext_900);
        et550=view.findViewById(R.id.frag_double_patti_data_edittext_550);
        et119=view.findViewById(R.id.frag_double_patti_data_edittext_119);
        et110=view.findViewById(R.id.frag_double_patti_data_edittext_110);
        et166=view.findViewById(R.id.frag_double_patti_data_edittext_166);
        et112=view.findViewById(R.id.frag_double_patti_data_edittext_112);
        et113=view.findViewById(R.id.frag_double_patti_data_edittext_113);
        et114=view.findViewById(R.id.frag_double_patti_data_edittext_114);
        et115=view.findViewById(R.id.frag_double_patti_data_edittext_115);
        et116=view.findViewById(R.id.frag_double_patti_data_edittext_116);
        et117=view.findViewById(R.id.frag_double_patti_data_edittext_117);
        et668=view.findViewById(R.id.frag_double_patti_data_edittext_668);
        et155=view.findViewById(R.id.frag_double_patti_data_edittext_155);
        et228=view.findViewById(R.id.frag_double_patti_data_edittext_228);
        et229=view.findViewById(R.id.frag_double_patti_data_edittext_229);
        et220=view.findViewById(R.id.frag_double_patti_data_edittext_220);
        et122=view.findViewById(R.id.frag_double_patti_data_edittext_122);
        et277=view.findViewById(R.id.frag_double_patti_data_edittext_277);
        et133=view.findViewById(R.id.frag_double_patti_data_edittext_133);
        et224=view.findViewById(R.id.frag_double_patti_data_edittext_224);
        et144=view.findViewById(R.id.frag_double_patti_data_edittext_144);
        et244=view.findViewById(R.id.frag_double_patti_data_edittext_244);
        et227=view.findViewById(R.id.frag_double_patti_data_edittext_227);
        et255=view.findViewById(R.id.frag_double_patti_data_edittext_255);
        et337=view.findViewById(R.id.frag_double_patti_data_edittext_337);
        et266=view.findViewById(R.id.frag_double_patti_data_edittext_266);
        et177=view.findViewById(R.id.frag_double_patti_data_edittext_177);
        et330=view.findViewById(R.id.frag_double_patti_data_edittext_330);
        et188=view.findViewById(R.id.frag_double_patti_data_edittext_188);
        et233=view.findViewById(R.id.frag_double_patti_data_edittext_233);
        et199=view.findViewById(R.id.frag_double_patti_data_edittext_199);
        et299=view.findViewById(R.id.frag_double_patti_data_edittext_299);
        et335=view.findViewById(R.id.frag_double_patti_data_edittext_335);
        et336=view.findViewById(R.id.frag_double_patti_data_edittext_336);
        et355=view.findViewById(R.id.frag_double_patti_data_edittext_355);
        et338=view.findViewById(R.id.frag_double_patti_data_edittext_338);
        et339=view.findViewById(R.id.frag_double_patti_data_edittext_339);
        et448=view.findViewById(R.id.frag_double_patti_data_edittext_448);
        et223=view.findViewById(R.id.frag_double_patti_data_edittext_223);
        et288=view.findViewById(R.id.frag_double_patti_data_edittext_288);
        et225=view.findViewById(R.id.frag_double_patti_data_edittext_225);
        et226=view.findViewById(R.id.frag_double_patti_data_edittext_226);
        et344=view.findViewById(R.id.frag_double_patti_data_edittext_344);
        et499=view.findViewById(R.id.frag_double_patti_data_edittext_499);
        et445=view.findViewById(R.id.frag_double_patti_data_edittext_445);
        et446=view.findViewById(R.id.frag_double_patti_data_edittext_446);
        et366=view.findViewById(R.id.frag_double_patti_data_edittext_366);
        et466=view.findViewById(R.id.frag_double_patti_data_edittext_466);
        et377=view.findViewById(R.id.frag_double_patti_data_edittext_377);
        et440=view.findViewById(R.id.frag_double_patti_data_edittext_440);
        et388=view.findViewById(R.id.frag_double_patti_data_edittext_388);
        et488=view.findViewById(R.id.frag_double_patti_data_edittext_488);
        et399=view.findViewById(R.id.frag_double_patti_data_edittext_399);
        et660=view.findViewById(R.id.frag_double_patti_data_edittext_660);
        et599=view.findViewById(R.id.frag_double_patti_data_edittext_599);
        et455=view.findViewById(R.id.frag_double_patti_data_edittext_455);
        et447=view.findViewById(R.id.frag_double_patti_data_edittext_447);
        et556=view.findViewById(R.id.frag_double_patti_data_edittext_556);
        et449=view.findViewById(R.id.frag_double_patti_data_edittext_449);
        et477=view.findViewById(R.id.frag_double_patti_data_edittext_477);
        et559=view.findViewById(R.id.frag_double_patti_data_edittext_559);
        et677=view.findViewById(R.id.frag_double_patti_data_edittext_677);
        et588=view.findViewById(R.id.frag_double_patti_data_edittext_588);
        et688=view.findViewById(R.id.frag_double_patti_data_edittext_688);
        et779=view.findViewById(R.id.frag_double_patti_data_edittext_779);
        et699=view.findViewById(R.id.frag_double_patti_data_edittext_699);
        et799=view.findViewById(R.id.frag_double_patti_data_edittext_799);
        et880=view.findViewById(R.id.frag_double_patti_data_edittext_880);
        et557=view.findViewById(R.id.frag_double_patti_data_edittext_557);
        et558=view.findViewById(R.id.frag_double_patti_data_edittext_558);
        et577=view.findViewById(R.id.frag_double_patti_data_edittext_577);
        et118=view.findViewById(R.id.frag_double_patti_data_edittext_118);
        et669=view.findViewById(R.id.frag_double_patti_data_edittext_669);
        et778=view.findViewById(R.id.frag_double_patti_data_edittext_778);
        et788=view.findViewById(R.id.frag_double_patti_data_edittext_788);
        et770=view.findViewById(R.id.frag_double_patti_data_edittext_770);
        et889=view.findViewById(R.id.frag_double_patti_data_edittext_889);
        et899=view.findViewById(R.id.frag_double_patti_data_edittext_899);
        et566=view.findViewById(R.id.frag_double_patti_data_edittext_566);
        et990=view.findViewById(R.id.frag_double_patti_data_edittext_990);
        et667=view.findViewById(R.id.frag_double_patti_data_edittext_667);
        et334=view.findViewById(R.id.frag_double_patti_data_edittext_334);

    }

    private void initializeString(){
        one_100=et100.getText().toString().trim();
        two_200=et200.getText().toString().trim();
        three_300=et300.getText().toString().trim();
        four_400=et400.getText().toString().trim();
        five_500=et500.getText().toString().trim();
        six_600=et600.getText().toString().trim();
        seven_700=et700.getText().toString().trim();
        eight_800=et800.getText().toString().trim();
        nine_900=et900.getText().toString().trim();
        ten_550=et550.getText().toString().trim();
        one_119=et119.getText().toString().trim();
        two_110=et110.getText().toString().trim();
        three_166=et166.getText().toString().trim();
        four_112=et112.getText().toString().trim();
        five_113=et113.getText().toString().trim();
        six_114=et114.getText().toString().trim();
        seven_115=et115.getText().toString().trim();
        eight_116=et116.getText().toString().trim();
        nine_117=et117.getText().toString().trim();
        ten_668=et668.getText().toString().trim();
        one_155=et155.getText().toString().trim();
        two_228=et228.getText().toString().trim();
        three_229=et229.getText().toString().trim();
        four_220=et220.getText().toString().trim();
        five_122=et122.getText().toString().trim();
        six_277=et277.getText().toString().trim();
        seven_133=et133.getText().toString().trim();
        eight_224=et224.getText().toString().trim();
        nine_144=et144.getText().toString().trim();
        ten_244=et244.getText().toString().trim();
        one_227=et227.getText().toString().trim();
        two_255=et255.getText().toString().trim();
        three_337=et337.getText().toString().trim();
        four_266=et266.getText().toString().trim();
        five_177=et177.getText().toString().trim();
        six_330=et330.getText().toString().trim();
        seven_188=et188.getText().toString().trim();
        eight_233=et233.getText().toString().trim();
        nine_199=et199.getText().toString().trim();
        ten_299=et299.getText().toString().trim();
        one_335=et335.getText().toString().trim();
        two_336=et336.getText().toString().trim();
        three_355=et355.getText().toString().trim();
        four_338=et338.getText().toString().trim();
        five_339=et339.getText().toString().trim();
        six_448=et448.getText().toString().trim();
        seven_223=et223.getText().toString().trim();
        eight_288=et288.getText().toString().trim();
        nine_225=et225.getText().toString().trim();
        ten_226=et226.getText().toString().trim();
        one_344=et344.getText().toString().trim();
        two_499=et499.getText().toString().trim();
        three_445=et445.getText().toString().trim();
        four_446=et446.getText().toString().trim();
        five_366=et366.getText().toString().trim();
        six_466=et466.getText().toString().trim();
        seven_377=et377.getText().toString().trim();
        eight_440=et440.getText().toString().trim();
        nine_388=et388.getText().toString().trim();
        ten_488=et488.getText().toString().trim();
        one_399=et399.getText().toString().trim();
        two_660=et660.getText().toString().trim();
        three_599=et599.getText().toString().trim();
        four_455=et455.getText().toString().trim();
        five_447=et447.getText().toString().trim();
        six_556=et556.getText().toString().trim();
        seven_449=et449.getText().toString().trim();
        eight_477=et477.getText().toString().trim();
        nine_559=et559.getText().toString().trim();
        ten_677=et677.getText().toString().trim();
        one_588=et588.getText().toString().trim();
        two_688=et688.getText().toString().trim();
        three_779=et779.getText().toString().trim();
        four_699=et699.getText().toString().trim();
        five_799=et799.getText().toString().trim();
        six_880=et880.getText().toString().trim();
        seven_557=et557.getText().toString().trim();
        eight_558=et558.getText().toString().trim();
        nine_577=et577.getText().toString().trim();
        ten_118=et118.getText().toString().trim();
        one_669=et669.getText().toString().trim();
        two_778=et778.getText().toString().trim();
        three_788=et788.getText().toString().trim();
        four_770=et770.getText().toString().trim();
        five_889=et889.getText().toString().trim();
        six_899=et899.getText().toString().trim();
        seven_566=et566.getText().toString().trim();
        eight_990=et990.getText().toString().trim();
        nine_667=et667.getText().toString().trim();
        ten_334=et334.getText().toString().trim();
    }

    private void comparingText(){

        ///// 01

        if(!et100.getText().toString().isEmpty()){
            if(Integer.parseInt(et100.getText().toString())!=0 && Integer.parseInt(et100.getText().toString())>10 ){
                total=total+Integer.parseInt(et100.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et100.getText().toString())<10){
                et100.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et119.getText().toString().isEmpty()){
            if(Integer.parseInt(et119.getText().toString())!=0 && Integer.parseInt(et119.getText().toString())>10 ){
                total=total+Integer.parseInt(et119.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et119.getText().toString())<10){
                et119.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et155.getText().toString().isEmpty()){
            if(Integer.parseInt(et155.getText().toString())!=0 && Integer.parseInt(et155.getText().toString())>10 ){
                total=total+Integer.parseInt(et155.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et155.getText().toString())<10){
                et155.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et227.getText().toString().isEmpty()){
            if(Integer.parseInt(et227.getText().toString())!=0 && Integer.parseInt(et227.getText().toString())>10 ){
                total=total+Integer.parseInt(et227.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et227.getText().toString())<10){
                et227.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et335.getText().toString().isEmpty()){
            if(Integer.parseInt(et335.getText().toString())!=0 && Integer.parseInt(et335.getText().toString())>10 ){
                total=total+Integer.parseInt(et335.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et335.getText().toString())<10){
                et335.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et344.getText().toString().isEmpty()){
            if(Integer.parseInt(et344.getText().toString())!=0 && Integer.parseInt(et344.getText().toString())>10 ){
                total=total+Integer.parseInt(et344.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et344.getText().toString())<10){
                et344.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et399.getText().toString().isEmpty()){
            if(Integer.parseInt(et399.getText().toString())!=0 && Integer.parseInt(et399.getText().toString())>10 ){
                total=total+Integer.parseInt(et399.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et399.getText().toString())<10){
                et399.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et588.getText().toString().isEmpty()){
            if(Integer.parseInt(et588.getText().toString())!=0 && Integer.parseInt(et588.getText().toString())>10 ){
                total=total+Integer.parseInt(et588.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et588.getText().toString())<10){
                et588.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et669.getText().toString().isEmpty()){
            if(Integer.parseInt(et669.getText().toString())!=0 && Integer.parseInt(et669.getText().toString())>10 ){
                total=total+Integer.parseInt(et669.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et669.getText().toString())<10){
                et669.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        /// 02


        if(!et200.getText().toString().isEmpty()){
            if(Integer.parseInt(et200.getText().toString())!=0 && Integer.parseInt(et200.getText().toString())>10 ){
                total=total+Integer.parseInt(et200.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et200.getText().toString())<10){
                et200.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et110.getText().toString().isEmpty()){
            if(Integer.parseInt(et110.getText().toString())!=0 && Integer.parseInt(et110.getText().toString())>10 ){
                total=total+Integer.parseInt(et110.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et110.getText().toString())<10){
                et110.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et228.getText().toString().isEmpty()){
            if(Integer.parseInt(et228.getText().toString())!=0 && Integer.parseInt(et228.getText().toString())>10 ){
                total=total+Integer.parseInt(et228.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et228.getText().toString())<10){
                et228.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et255.getText().toString().isEmpty()){
            if(Integer.parseInt(et255.getText().toString())!=0 && Integer.parseInt(et255.getText().toString())>10 ){
                total=total+Integer.parseInt(et255.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et255.getText().toString())<10){
                et255.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et336.getText().toString().isEmpty()){
            if(Integer.parseInt(et336.getText().toString())!=0 && Integer.parseInt(et336.getText().toString())>10 ){
                total=total+Integer.parseInt(et336.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et336.getText().toString())<10){
                et336.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et499.getText().toString().isEmpty()){
            if(Integer.parseInt(et499.getText().toString())!=0 && Integer.parseInt(et499.getText().toString())>10 ){
                total=total+Integer.parseInt(et499.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et499.getText().toString())<10){
                et499.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et660.getText().toString().isEmpty()){
            if(Integer.parseInt(et660.getText().toString())!=0 && Integer.parseInt(et660.getText().toString())>10 ){
                total=total+Integer.parseInt(et660.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et660.getText().toString())<10){
                et660.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et688.getText().toString().isEmpty()){
            if(Integer.parseInt(et688.getText().toString())!=0 && Integer.parseInt(et688.getText().toString())>10 ){
                total=total+Integer.parseInt(et688.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et688.getText().toString())<10){
                et688.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et778.getText().toString().isEmpty()){
            if(Integer.parseInt(et778.getText().toString())!=0 && Integer.parseInt(et778.getText().toString())>10 ){
                total=total+Integer.parseInt(et778.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et778.getText().toString())<10){
                et778.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 03


        if(!et300.getText().toString().isEmpty()){
            if(Integer.parseInt(et200.getText().toString())!=0 && Integer.parseInt(et200.getText().toString())>10 ){
                total=total+Integer.parseInt(et200.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et200.getText().toString())<10){
                et200.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et166.getText().toString().isEmpty()){
            if(Integer.parseInt(et166.getText().toString())!=0 && Integer.parseInt(et166.getText().toString())>10 ){
                total=total+Integer.parseInt(et166.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et166.getText().toString())<10){
                et166.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et229.getText().toString().isEmpty()){
            if(Integer.parseInt(et229.getText().toString())!=0 && Integer.parseInt(et229.getText().toString())>10 ){
                total=total+Integer.parseInt(et229.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et229.getText().toString())<10){
                et229.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et337.getText().toString().isEmpty()){
            if(Integer.parseInt(et337.getText().toString())!=0 && Integer.parseInt(et337.getText().toString())>10 ){
                total=total+Integer.parseInt(et337.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et337.getText().toString())<10){
                et337.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et355.getText().toString().isEmpty()){
            if(Integer.parseInt(et355.getText().toString())!=0 && Integer.parseInt(et355.getText().toString())>10 ){
                total=total+Integer.parseInt(et355.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et355.getText().toString())<10){
                et355.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et445.getText().toString().isEmpty()){
            if(Integer.parseInt(et445.getText().toString())!=0 && Integer.parseInt(et445.getText().toString())>10 ){
                total=total+Integer.parseInt(et445.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et445.getText().toString())<10){
                et445.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et599.getText().toString().isEmpty()){
            if(Integer.parseInt(et599.getText().toString())!=0 && Integer.parseInt(et599.getText().toString())>10 ){
                total=total+Integer.parseInt(et599.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et599.getText().toString())<10){
                et599.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et779.getText().toString().isEmpty()){
            if(Integer.parseInt(et779.getText().toString())!=0 && Integer.parseInt(et779.getText().toString())>10 ){
                total=total+Integer.parseInt(et779.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et779.getText().toString())<10){
                et779.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et788.getText().toString().isEmpty()){
            if(Integer.parseInt(et788.getText().toString())!=0 && Integer.parseInt(et788.getText().toString())>10 ){
                total=total+Integer.parseInt(et788.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et788.getText().toString())<10){
                et788.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 04


        if(!et400.getText().toString().isEmpty()){
            if(Integer.parseInt(et400.getText().toString())!=0 && Integer.parseInt(et400.getText().toString())>10 ){
                total=total+Integer.parseInt(et400.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et400.getText().toString())<10){
                et400.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et112.getText().toString().isEmpty()){
            if(Integer.parseInt(et112.getText().toString())!=0 && Integer.parseInt(et112.getText().toString())>10 ){
                total=total+Integer.parseInt(et112.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et112.getText().toString())<10){
                et112.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et220.getText().toString().isEmpty()){
            if(Integer.parseInt(et220.getText().toString())!=0 && Integer.parseInt(et220.getText().toString())>10 ){
                total=total+Integer.parseInt(et220.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et220.getText().toString())<10){
                et220.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et266.getText().toString().isEmpty()){
            if(Integer.parseInt(et266.getText().toString())!=0 && Integer.parseInt(et266.getText().toString())>10 ){
                total=total+Integer.parseInt(et266.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et266.getText().toString())<10){
                et266.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et338.getText().toString().isEmpty()){
            if(Integer.parseInt(et338.getText().toString())!=0 && Integer.parseInt(et338.getText().toString())>10 ){
                total=total+Integer.parseInt(et338.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et338.getText().toString())<10){
                et338.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et446.getText().toString().isEmpty()){
            if(Integer.parseInt(et446.getText().toString())!=0 && Integer.parseInt(et446.getText().toString())>10 ){
                total=total+Integer.parseInt(et446.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et446.getText().toString())<10){
                et446.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et455.getText().toString().isEmpty()){
            if(Integer.parseInt(et455.getText().toString())!=0 && Integer.parseInt(et455.getText().toString())>10 ){
                total=total+Integer.parseInt(et455.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et455.getText().toString())<10){
                et455.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et699.getText().toString().isEmpty()){
            if(Integer.parseInt(et699.getText().toString())!=0 && Integer.parseInt(et699.getText().toString())>10 ){
                total=total+Integer.parseInt(et699.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et699.getText().toString())<10){
                et699.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et770.getText().toString().isEmpty()){
            if(Integer.parseInt(et770.getText().toString())!=0 && Integer.parseInt(et770.getText().toString())>10 ){
                total=total+Integer.parseInt(et770.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et770.getText().toString())<10){
                et770.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        ///// 05


        if(!et500.getText().toString().isEmpty()){
            if(Integer.parseInt(et500.getText().toString())!=0 && Integer.parseInt(et500.getText().toString())>10 ){
                total=total+Integer.parseInt(et500.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et500.getText().toString())<10){
                et500.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et113.getText().toString().isEmpty()){
            if(Integer.parseInt(et113.getText().toString())!=0 && Integer.parseInt(et113.getText().toString())>10 ){
                total=total+Integer.parseInt(et113.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et113.getText().toString())<10){
                et113.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et122.getText().toString().isEmpty()){
            if(Integer.parseInt(et122.getText().toString())!=0 && Integer.parseInt(et122.getText().toString())>10 ){
                total=total+Integer.parseInt(et122.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et122.getText().toString())<10){
                et122.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et177.getText().toString().isEmpty()){
            if(Integer.parseInt(et177.getText().toString())!=0 && Integer.parseInt(et177.getText().toString())>10 ){
                total=total+Integer.parseInt(et177.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et177.getText().toString())<10){
                et177.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et339.getText().toString().isEmpty()){
            if(Integer.parseInt(et339.getText().toString())!=0 && Integer.parseInt(et339.getText().toString())>10 ){
                total=total+Integer.parseInt(et339.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et339.getText().toString())<10){
                et339.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et366.getText().toString().isEmpty()){
            if(Integer.parseInt(et366.getText().toString())!=0 && Integer.parseInt(et366.getText().toString())>10 ){
                total=total+Integer.parseInt(et366.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et366.getText().toString())<10){
                et366.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et447.getText().toString().isEmpty()){
            if(Integer.parseInt(et447.getText().toString())!=0 && Integer.parseInt(et447.getText().toString())>10 ){
                total=total+Integer.parseInt(et447.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et447.getText().toString())<10){
                et447.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et799.getText().toString().isEmpty()){
            if(Integer.parseInt(et799.getText().toString())!=0 && Integer.parseInt(et799.getText().toString())>10 ){
                total=total+Integer.parseInt(et799.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et799.getText().toString())<10){
                et799.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et889.getText().toString().isEmpty()){
            if(Integer.parseInt(et889.getText().toString())!=0 && Integer.parseInt(et889.getText().toString())>10 ){
                total=total+Integer.parseInt(et889.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et889.getText().toString())<10){
                et889.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 06


        if(!et600.getText().toString().isEmpty()){
            if(Integer.parseInt(et600.getText().toString())!=0 && Integer.parseInt(et600.getText().toString())>10 ){
                total=total+Integer.parseInt(et600.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et600.getText().toString())<10){
                et600.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et114.getText().toString().isEmpty()){
            if(Integer.parseInt(et114.getText().toString())!=0 && Integer.parseInt(et114.getText().toString())>10 ){
                total=total+Integer.parseInt(et114.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et114.getText().toString())<10){
                et114.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et227.getText().toString().isEmpty()){
            if(Integer.parseInt(et227.getText().toString())!=0 && Integer.parseInt(et227.getText().toString())>10 ){
                total=total+Integer.parseInt(et227.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et227.getText().toString())<10){
                et227.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et330.getText().toString().isEmpty()){
            if(Integer.parseInt(et330.getText().toString())!=0 && Integer.parseInt(et330.getText().toString())>10 ){
                total=total+Integer.parseInt(et330.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et330.getText().toString())<10){
                et330.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et448.getText().toString().isEmpty()){
            if(Integer.parseInt(et448.getText().toString())!=0 && Integer.parseInt(et448.getText().toString())>10 ){
                total=total+Integer.parseInt(et448.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et448.getText().toString())<10){
                et448.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et466.getText().toString().isEmpty()){
            if(Integer.parseInt(et466.getText().toString())!=0 && Integer.parseInt(et466.getText().toString())>10 ){
                total=total+Integer.parseInt(et466.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et466.getText().toString())<10){
                et466.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et556.getText().toString().isEmpty()){
            if(Integer.parseInt(et556.getText().toString())!=0 && Integer.parseInt(et556.getText().toString())>10 ){
                total=total+Integer.parseInt(et556.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et556.getText().toString())<10){
                et556.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et880.getText().toString().isEmpty()){
            if(Integer.parseInt(et880.getText().toString())!=0 && Integer.parseInt(et880.getText().toString())>10 ){
                total=total+Integer.parseInt(et880.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et880.getText().toString())<10){
                et880.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et899.getText().toString().isEmpty()){
            if(Integer.parseInt(et899.getText().toString())!=0 && Integer.parseInt(et899.getText().toString())>10 ){
                total=total+Integer.parseInt(et899.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et899.getText().toString())<10){
                et899.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 07


        if(!et700.getText().toString().isEmpty()){
            if(Integer.parseInt(et700.getText().toString())!=0 && Integer.parseInt(et700.getText().toString())>10 ){
                total=total+Integer.parseInt(et700.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et700.getText().toString())<10){
                et700.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et115.getText().toString().isEmpty()){
            if(Integer.parseInt(et115.getText().toString())!=0 && Integer.parseInt(et115.getText().toString())>10 ){
                total=total+Integer.parseInt(et115.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et115.getText().toString())<10){
                et115.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et133.getText().toString().isEmpty()){
            if(Integer.parseInt(et133.getText().toString())!=0 && Integer.parseInt(et133.getText().toString())>10 ){
                total=total+Integer.parseInt(et133.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et133.getText().toString())<10){
                et133.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et188.getText().toString().isEmpty()){
            if(Integer.parseInt(et188.getText().toString())!=0 && Integer.parseInt(et188.getText().toString())>10 ){
                total=total+Integer.parseInt(et188.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et188.getText().toString())<10){
                et188.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et223.getText().toString().isEmpty()){
            if(Integer.parseInt(et223.getText().toString())!=0 && Integer.parseInt(et223.getText().toString())>10 ){
                total=total+Integer.parseInt(et223.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et223.getText().toString())<10){
                et223.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et377.getText().toString().isEmpty()){
            if(Integer.parseInt(et377.getText().toString())!=0 && Integer.parseInt(et377.getText().toString())>10 ){
                total=total+Integer.parseInt(et377.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et377.getText().toString())<10){
                et377.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et449.getText().toString().isEmpty()){
            if(Integer.parseInt(et449.getText().toString())!=0 && Integer.parseInt(et449.getText().toString())>10 ){
                total=total+Integer.parseInt(et449.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et449.getText().toString())<10){
                et449.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et557.getText().toString().isEmpty()){
            if(Integer.parseInt(et557.getText().toString())!=0 && Integer.parseInt(et557.getText().toString())>10 ){
                total=total+Integer.parseInt(et557.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et557.getText().toString())<10){
                et557.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et566.getText().toString().isEmpty()){
            if(Integer.parseInt(et566.getText().toString())!=0 && Integer.parseInt(et566.getText().toString())>10 ){
                total=total+Integer.parseInt(et566.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et566.getText().toString())<10){
                et566.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }


        //// 08



        if(!et800.getText().toString().isEmpty()){
            if(Integer.parseInt(et800.getText().toString())!=0 && Integer.parseInt(et800.getText().toString())>10 ){
                total=total+Integer.parseInt(et800.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et800.getText().toString())<10){
                et800.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et116.getText().toString().isEmpty()){
            if(Integer.parseInt(et116.getText().toString())!=0 && Integer.parseInt(et116.getText().toString())>10 ){
                total=total+Integer.parseInt(et116.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et116.getText().toString())<10){
                et116.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et224.getText().toString().isEmpty()){
            if(Integer.parseInt(et224.getText().toString())!=0 && Integer.parseInt(et224.getText().toString())>10 ){
                total=total+Integer.parseInt(et224.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et224.getText().toString())<10){
                et224.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et233.getText().toString().isEmpty()){
            if(Integer.parseInt(et233.getText().toString())!=0 && Integer.parseInt(et233.getText().toString())>10 ){
                total=total+Integer.parseInt(et233.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et233.getText().toString())<10){
                et233.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et288.getText().toString().isEmpty()){
            if(Integer.parseInt(et288.getText().toString())!=0 && Integer.parseInt(et288.getText().toString())>10 ){
                total=total+Integer.parseInt(et288.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et288.getText().toString())<10){
                et288.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et440.getText().toString().isEmpty()){
            if(Integer.parseInt(et440.getText().toString())!=0 && Integer.parseInt(et440.getText().toString())>10 ){
                total=total+Integer.parseInt(et440.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et440.getText().toString())<10){
                et440.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et477.getText().toString().isEmpty()){
            if(Integer.parseInt(et477.getText().toString())!=0 && Integer.parseInt(et477.getText().toString())>10 ){
                total=total+Integer.parseInt(et477.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et477.getText().toString())<10){
                et477.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et558.getText().toString().isEmpty()){
            if(Integer.parseInt(et558.getText().toString())!=0 && Integer.parseInt(et558.getText().toString())>10 ){
                total=total+Integer.parseInt(et558.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et558.getText().toString())<10){
                et558.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et990.getText().toString().isEmpty()){
            if(Integer.parseInt(et990.getText().toString())!=0 && Integer.parseInt(et990.getText().toString())>10 ){
                total=total+Integer.parseInt(et990.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et990.getText().toString())<10){
                et990.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 09



        if(!et900.getText().toString().isEmpty()){
            if(Integer.parseInt(et900.getText().toString())!=0 && Integer.parseInt(et900.getText().toString())>10 ){
                total=total+Integer.parseInt(et900.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et900.getText().toString())<10){
                et900.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et117.getText().toString().isEmpty()){
            if(Integer.parseInt(et117.getText().toString())!=0 && Integer.parseInt(et117.getText().toString())>10 ){
                total=total+Integer.parseInt(et117.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et117.getText().toString())<10){
                et117.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et144.getText().toString().isEmpty()){
            if(Integer.parseInt(et144.getText().toString())!=0 && Integer.parseInt(et144.getText().toString())>10 ){
                total=total+Integer.parseInt(et144.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et144.getText().toString())<10){
                et144.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et199.getText().toString().isEmpty()){
            if(Integer.parseInt(et199.getText().toString())!=0 && Integer.parseInt(et199.getText().toString())>10 ){
                total=total+Integer.parseInt(et199.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et199.getText().toString())<10){
                et199.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et225.getText().toString().isEmpty()){
            if(Integer.parseInt(et225.getText().toString())!=0 && Integer.parseInt(et225.getText().toString())>10 ){
                total=total+Integer.parseInt(et225.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et225.getText().toString())<10){
                et225.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et388.getText().toString().isEmpty()){
            if(Integer.parseInt(et388.getText().toString())!=0 && Integer.parseInt(et388.getText().toString())>10 ){
                total=total+Integer.parseInt(et388.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et388.getText().toString())<10){
                et388.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et559.getText().toString().isEmpty()){
            if(Integer.parseInt(et559.getText().toString())!=0 && Integer.parseInt(et559.getText().toString())>10 ){
                total=total+Integer.parseInt(et559.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et559.getText().toString())<10){
                et559.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et577.getText().toString().isEmpty()){
            if(Integer.parseInt(et577.getText().toString())!=0 && Integer.parseInt(et577.getText().toString())>10 ){
                total=total+Integer.parseInt(et577.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et577.getText().toString())<10){
                et577.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et667.getText().toString().isEmpty()){
            if(Integer.parseInt(et667.getText().toString())!=0 && Integer.parseInt(et667.getText().toString())>10 ){
                total=total+Integer.parseInt(et667.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et667.getText().toString())<10){
                et667.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 10



        if(!et550.getText().toString().isEmpty()){
            if(Integer.parseInt(et550.getText().toString())!=0 && Integer.parseInt(et550.getText().toString())>10 ){
                total=total+Integer.parseInt(et550.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et550.getText().toString())<10){
                et550.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et668.getText().toString().isEmpty()){
            if(Integer.parseInt(et668.getText().toString())!=0 && Integer.parseInt(et668.getText().toString())>10 ){
                total=total+Integer.parseInt(et668.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et668.getText().toString())<10){
                et668.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et244.getText().toString().isEmpty()){
            if(Integer.parseInt(et244.getText().toString())!=0 && Integer.parseInt(et244.getText().toString())>10 ){
                total=total+Integer.parseInt(et244.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et244.getText().toString())<10){
                et244.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et299.getText().toString().isEmpty()){
            if(Integer.parseInt(et299.getText().toString())!=0 && Integer.parseInt(et299.getText().toString())>10 ){
                total=total+Integer.parseInt(et299.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et299.getText().toString())<10){
                et299.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et226.getText().toString().isEmpty()){
            if(Integer.parseInt(et226.getText().toString())!=0 && Integer.parseInt(et226.getText().toString())>10 ){
                total=total+Integer.parseInt(et226.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et226.getText().toString())<10){
                et226.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et488.getText().toString().isEmpty()){
            if(Integer.parseInt(et488.getText().toString())!=0 && Integer.parseInt(et488.getText().toString())>10 ){
                total=total+Integer.parseInt(et488.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et488.getText().toString())<10){
                et488.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et677.getText().toString().isEmpty()){
            if(Integer.parseInt(et677.getText().toString())!=0 && Integer.parseInt(et677.getText().toString())>10 ){
                total=total+Integer.parseInt(et677.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et677.getText().toString())<10){
                et677.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et118.getText().toString().isEmpty()){
            if(Integer.parseInt(et118.getText().toString())!=0 && Integer.parseInt(et118.getText().toString())>10 ){
                total=total+Integer.parseInt(et118.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et118.getText().toString())<10){
                et118.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }
        if(!et334.getText().toString().isEmpty()){
            if(Integer.parseInt(et334.getText().toString())!=0 && Integer.parseInt(et334.getText().toString())>10 ){
                total=total+Integer.parseInt(et334.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et334.getText().toString())<10){
                et334.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

    }



}


/*
 private void comparingText(){

        ////First

        et100.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et100.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et100.requestFocus();
                }else {
                    total=total+Integer.parseInt(et100.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et119.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et119.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et119.requestFocus();
                }else {
                    total=total+Integer.parseInt(et119.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et155.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et155.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et155.requestFocus();
                }else {
                    total=total+Integer.parseInt(et155.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et227.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et227.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et227.requestFocus();
                }else {
                    total=total+Integer.parseInt(et227.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et335.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et335.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et335.requestFocus();
                }else {
                    total=total+Integer.parseInt(et335.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et344.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et344.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et344.requestFocus();
                }else {
                    total=total+Integer.parseInt(et344.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et399.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et399.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et399.requestFocus();
                }else {
                    total=total+Integer.parseInt(et399.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et588.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et588.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et588.requestFocus();
                }else {
                    total=total+Integer.parseInt(et588.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et669.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et669.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et669.requestFocus();
                }else {
                    total=total+Integer.parseInt(et669.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Second

        et200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et200.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et200.requestFocus();
                }else {
                    total=total+Integer.parseInt(et200.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et110.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et110.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et110.requestFocus();
                }else {
                    total=total+Integer.parseInt(et110.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et228.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et228.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et228.requestFocus();
                }else {
                    total=total+Integer.parseInt(et228.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et255.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et255.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et255.requestFocus();
                }else {
                    total=total+Integer.parseInt(et255.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et336.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et336.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et336.requestFocus();
                }else {
                    total=total+Integer.parseInt(et336.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et499.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et499.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et499.requestFocus();
                }else {
                    total=total+Integer.parseInt(et499.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et660.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et660.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et660.requestFocus();
                }else {
                    total=total+Integer.parseInt(et660.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et688.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et688.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et688.requestFocus();
                }else {
                    total=total+Integer.parseInt(et688.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et778.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et778.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et778.requestFocus();
                }else {
                    total=total+Integer.parseInt(et778.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Third

        et300.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et300.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et300.requestFocus();
                }else {
                    total=total+Integer.parseInt(et300.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et166.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et166.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et166.requestFocus();
                }else {
                    total=total+Integer.parseInt(et166.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et229.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et229.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et229.requestFocus();
                }else {
                    total=total+Integer.parseInt(et229.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et337.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et337.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et337.requestFocus();
                }else {
                    total=total+Integer.parseInt(et337.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et355.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et355.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et355.requestFocus();
                }else {
                    total=total+Integer.parseInt(et355.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et445.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et445.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et445.requestFocus();
                }else {
                    total=total+Integer.parseInt(et445.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et599.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et599.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et599.requestFocus();
                }else {
                    total=total+Integer.parseInt(et599.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et779.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et779.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et779.requestFocus();
                }else {
                    total=total+Integer.parseInt(et779.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et788.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et788.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et788.requestFocus();
                }else {
                    total=total+Integer.parseInt(et788.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ///fourth

        et400.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et400.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et400.requestFocus();
                }else {
                    total=total+Integer.parseInt(et400.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et112.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et112.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et112.requestFocus();
                }else {
                    total=total+Integer.parseInt(et112.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et220.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et220.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et220.requestFocus();
                }else {
                    total=total+Integer.parseInt(et220.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et266.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et266.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et266.requestFocus();
                }else {
                    total=total+Integer.parseInt(et266.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et338.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et338.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et338.requestFocus();
                }else {
                    total=total+Integer.parseInt(et338.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et446.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et446.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et446.requestFocus();
                }else {
                    total=total+Integer.parseInt(et446.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et455.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et455.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et455.requestFocus();
                }else {
                    total=total+Integer.parseInt(et455.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et699.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et699.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et699.requestFocus();
                }else {
                    total=total+Integer.parseInt(et699.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et770.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et770.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et770.requestFocus();
                }else {
                    total=total+Integer.parseInt(et770.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ///fifth

        et500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et500.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et500.requestFocus();
                }else {
                    total=total+Integer.parseInt(et500.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et113.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et113.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et113.requestFocus();
                }else {
                    total=total+Integer.parseInt(et113.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et122.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et122.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et122.requestFocus();
                }else {
                    total=total+Integer.parseInt(et122.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et177.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et177.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et177.requestFocus();
                }else {
                    total=total+Integer.parseInt(et177.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et339.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et339.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et339.requestFocus();
                }else {
                    total=total+Integer.parseInt(et339.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et366.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et366.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et366.requestFocus();
                }else {
                    total=total+Integer.parseInt(et366.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et447.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et447.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et447.requestFocus();
                }else {
                    total=total+Integer.parseInt(et447.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et799.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et799.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et799.requestFocus();
                }else {
                    total=total+Integer.parseInt(et799.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et889.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et889.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et889.requestFocus();
                }else {
                    total=total+Integer.parseInt(et889.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Sixth

        et600.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et600.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et600.requestFocus();
                }else {
                    total=total+Integer.parseInt(et600.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et114.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et114.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et114.requestFocus();
                }else {
                    total=total+Integer.parseInt(et114.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et277.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et277.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et277.requestFocus();
                }else {
                    total=total+Integer.parseInt(et277.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et330.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et330.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et330.requestFocus();
                }else {
                    total=total+Integer.parseInt(et330.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et448.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et448.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et448.requestFocus();
                }else {
                    total=total+Integer.parseInt(et448.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et466.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et466.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et466.requestFocus();
                }else {
                    total=total+Integer.parseInt(et466.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et556.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et556.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et556.requestFocus();
                }else {
                    total=total+Integer.parseInt(et556.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et880.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et880.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et880.requestFocus();
                }else {
                    total=total+Integer.parseInt(et880.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et899.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et899.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et899.requestFocus();
                }else {
                    total=total+Integer.parseInt(et899.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Seventh

        et700.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et700.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et700.requestFocus();
                }else {
                    total=total+Integer.parseInt(et700.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et115.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et115.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et115.requestFocus();
                }else {
                    total=total+Integer.parseInt(et115.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et133.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et133.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et133.requestFocus();
                }else {
                    total=total+Integer.parseInt(et133.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et188.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et188.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et188.requestFocus();
                }else {
                    total=total+Integer.parseInt(et188.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et223.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et223.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et223.requestFocus();
                }else {
                    total=total+Integer.parseInt(et223.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et377.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et377.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et377.requestFocus();
                }else {
                    total=total+Integer.parseInt(et377.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et449.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et449.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et449.requestFocus();
                }else {
                    total=total+Integer.parseInt(et449.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et557.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et557.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et557.requestFocus();
                }else {
                    total=total+Integer.parseInt(et557.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et566.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et566.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et566.requestFocus();
                }else {
                    total=total+Integer.parseInt(et566.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Eight

        et800.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et800.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et800.requestFocus();
                }else {
                    total=total+Integer.parseInt(et800.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et116.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et116.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et116.requestFocus();
                }else {
                    total=total+Integer.parseInt(et116.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et224.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et224.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et224.requestFocus();
                }else {
                    total=total+Integer.parseInt(et224.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et233.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et233.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et233.requestFocus();
                }else {
                    total=total+Integer.parseInt(et233.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et288.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et288.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et288.requestFocus();
                }else {
                    total=total+Integer.parseInt(et288.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et440.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et440.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et440.requestFocus();
                }else {
                    total=total+Integer.parseInt(et440.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et477.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et477.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et477.requestFocus();
                }else {
                    total=total+Integer.parseInt(et477.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et558.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et558.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et558.requestFocus();
                }else {
                    total=total+Integer.parseInt(et558.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et990.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et990.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et990.requestFocus();
                }else {
                    total=total+Integer.parseInt(et990.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Ninth

        et900.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et900.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et900.requestFocus();
                }else {
                    total=total+Integer.parseInt(et900.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et117.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et117.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et117.requestFocus();
                }else {
                    total=total+Integer.parseInt(et117.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et144.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et144.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et144.requestFocus();
                }else {
                    total=total+Integer.parseInt(et144.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et199.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et199.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et199.requestFocus();
                }else {
                    total=total+Integer.parseInt(et199.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et225.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et225.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et225.requestFocus();
                }else {
                    total=total+Integer.parseInt(et225.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et388.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et388.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et388.requestFocus();
                }else {
                    total=total+Integer.parseInt(et388.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et559.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et559.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et559.requestFocus();
                }else {
                    total=total+Integer.parseInt(et559.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et577.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et577.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et577.requestFocus();
                }else {
                    total=total+Integer.parseInt(et577.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et667.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et667.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et667.requestFocus();
                }else {
                    total=total+Integer.parseInt(et667.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////tenth

        et334.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et334.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et334.requestFocus();
                }else {
                    total=total+Integer.parseInt(et334.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et550.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et550.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et550.requestFocus();
                }else {
                    total=total+Integer.parseInt(et550.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et668.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et668.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et668.requestFocus();
                }else {
                    total=total+Integer.parseInt(et668.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et244.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et244.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et244.requestFocus();
                }else {
                    total=total+Integer.parseInt(et244.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et299.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et299.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et299.requestFocus();
                }else {
                    total=total+Integer.parseInt(et299.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et226.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et226.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et226.requestFocus();
                }else {
                    total=total+Integer.parseInt(et226.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et488.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et488.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et488.requestFocus();
                }else {
                    total=total+Integer.parseInt(et488.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et677.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et677.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et677.requestFocus();
                }else {
                    total=total+Integer.parseInt(et677.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et118.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et118.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et118.requestFocus();
                }else {
                    total=total+Integer.parseInt(et118.getText().toString());
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
//        // editedAmount=amount;
//        enteredAmount=hashMap;
//    }