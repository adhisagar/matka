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
public class SinglePattiFragment extends Fragment {

    public SinglePattiFragment() {
        // Required empty public constructor
    }

    TextView dateText;
    RecyclerView recyclerView;
    ArrayList<TriplePattiRecyclerModel> recyclerModels;
    TriplePattiRecyclerAdapter adapter;
    private Button triplePattiSubmitBtn;
    private TextView totalPoint;
    ArrayList<String> editedAmount = new ArrayList<String>();
    HashMap<String, String> enteredAmount = new HashMap<>();

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

    EditText et128,et137,et146,et236,et245,et290,et380,et470,et489,et560,et678,et579;
    EditText et129,et138,et147,et156,et237,et246,et345,et390,et480,et570,et679,et589;
    EditText et120,et139,et148,et157,et238,et247,et256,et346,et490,et580,et670,et689;
    EditText et130,et149,et158,et167,et239,et248,et257,et347,et356,et590,et680,et789;
    EditText et140,et159,et168,et230,et249,et258,et267,et348,et357,et456,et690,et780;
    EditText et123,et150,et169,et178,et240,et259,et268,et349,et358,et457,et367,et790;
    EditText et124,et160,et179,et250,et269,et278,et340,et359,et368,et458,et467,et890;
    EditText et125,et134,et170,et189,et260,et279,et350,et369,et378,et459,et567,et468;
    EditText et126,et135,et180,et234,et270,et289,et360,et379,et450,et469,et478,et568;
    EditText et127,et136,et145,et190,et235,et280,et370,et479,et460,et569,et389,et578;


    TextView field128,field137,field146,field236,field245,field290,field380,field470,field489,field560,field678,field579;
    TextView field129,field138,field147,field156,field237,field246,field345,field390,field480,field570,field679,field589;
    TextView field120,field139,field148,field157,field238,field247,field256,field346,field490,field580,field670,field689;
    TextView field130,field149,field158,field167,field239,field248,field257,field347,field356,field590,field680,field789;
    TextView field140,field159,field168,field230,field249,field258,field267,field348,field357,field456,field690,field780;
    TextView field123,field150,field169,field178,field240,field259,field268,field349,field358,field457,field367,field790;
    TextView field124,field160,field179,field250,field269,field278,field340,field359,field368,field458,field467,field890;
    TextView field125,field134,field170,field189,field260,field279,field350,field369,field378,field459,field567,field468;
    TextView field126,field135,field180,field234,field270,field289,field360,field379,field450,field469,field478,field568;
    TextView field127,field136,field145,field190,field235,field280,field370,field479,field460,field569,field389,field578;

    String one_128,one_137,one_146,one_236,one_245,one_290,one_380,one_470,one_489,one_560,one_678,one_579;
    String two_129,two_138,two_147,two_156,two_237,two_246,two_345,two_390,two_480,two_570,two_679,two_589;
    String three_120,three_139,three_148,three_157,three_238,three_247,three_256,three_346,three_490,three_580,three_670,three_689;
    String four_130,four_149,four_158,four_167,four_239,four_248,four_257,four_347,four_356,four_590,four_680,four_789;
    String five_140,five_159,five_168,five_230,five_249,five_258,five_267,five_348,five_357,five_456,five_690,five_780;
    String six_123,six_150,six_169,six_178,six_240,six_259,six_268,six_349,six_358,six_457,six_367,six_790;
    String seven_124,seven_160,seven_179,seven_250,seven_269,seven_278,seven_340,seven_359,seven_368,seven_458,seven_467,seven_890;
    String eight_125,eight_134,eight_170,eight_189,eight_260,eight_279,eight_350,eight_369,eight_378,eight_459,eight_567,eight_468;
    String nine_126,nine_135,nine_180,nine_234,nine_270,nine_289,nine_360,nine_379,nine_450,nine_469,nine_478,nine_568;
    String ten_127,ten_136,ten_145,ten_190,ten_235,ten_280,ten_370,ten_479,ten_460,ten_569,ten_389,ten_578;

    int total=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_patti, container, false);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        dateText = view.findViewById(R.id.single_patti_date_spinner);
        dateText.setText(currentDateandTime);
        initialize(view);
        initilaizeEditText(view);
        gettingmarketName(view);
       // comparingEdittext();
        return view;
    }

    private void initialize(View view) {
        totalPoint = view.findViewById(R.id.single_patti_total_point);
        tokenVal=new PreferenceManager(getContext()).getuserToken();
        walletAmount=new PreferenceManager(getContext()).getWalletAmount();
        wallet=Integer.parseInt(walletAmount);
        triplePattiSubmitBtn = view.findViewById(R.id.single_patti_submit_btn);
        recyclerView = view.findViewById(R.id.single_patti_data_recycler_view);
        recyclerModels = populateList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TriplePattiRecyclerAdapter(getContext(), recyclerModels);
        recyclerView.setAdapter(adapter);

        triplePattiSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparingEdittext();

                if(isSubmitEnable==true){
                    one_128=et128.getText().toString().trim();
                    two_129=et129.getText().toString().trim();
                    three_120=et120.getText().toString().trim();
                    four_130=et130.getText().toString().trim();
                    five_140=et140.getText().toString().trim();
                    six_123=et123.getText().toString().trim();
                    seven_124=et124.getText().toString().trim();
                    eight_125=et125.getText().toString().trim();
                    nine_126=et126.getText().toString().trim();
                    ten_127=et127.getText().toString().trim();
                    one_137=et137.getText().toString().trim();
                    two_138=et138.getText().toString().trim();
                    three_139=et139.getText().toString().trim();
                    four_149=et149.getText().toString().trim();
                    five_159=et159.getText().toString().trim();
                    six_150=et150.getText().toString().trim();
                    seven_160=et160.getText().toString().trim();
                    eight_134=et134.getText().toString().trim();
                    nine_135=et135.getText().toString().trim();
                    ten_136=et136.getText().toString().trim();
                    one_146=et146.getText().toString().trim();
                    two_147=et147.getText().toString().trim();
                    three_148=et148.getText().toString().trim();
                    four_158=et158.getText().toString().trim();
                    five_168=et168.getText().toString().trim();
                    six_169=et169.getText().toString().trim();
                    seven_179=et179.getText().toString().trim();
                    eight_170=et170.getText().toString().trim();
                    nine_180=et180.getText().toString().trim();
                    ten_145=et145.getText().toString().trim();
                    one_236=et236.getText().toString().trim();
                    two_156=et156.getText().toString().trim();
                    three_157=et157.getText().toString().trim();
                    four_167=et167.getText().toString().trim();
                    five_230=et230.getText().toString().trim();
                    six_178=et178.getText().toString().trim();
                    seven_250=et250.getText().toString().trim();
                    eight_189=et189.getText().toString().trim();
                    nine_234=et234.getText().toString().trim();
                    ten_190=et190.getText().toString().trim();
                    one_245=et245.getText().toString().trim();
                    two_237=et237.getText().toString().trim();
                    three_238=et238.getText().toString().trim();
                    four_239=et239.getText().toString().trim();
                    five_249=et249.getText().toString().trim();
                    six_240=et240.getText().toString().trim();
                    seven_269=et269.getText().toString().trim();
                    eight_260=et260.getText().toString().trim();
                    nine_270=et270.getText().toString().trim();
                    ten_235=et235.getText().toString().trim();
                    one_290=et290.getText().toString().trim();
                    two_246=et246.getText().toString().trim();
                    three_247=et247.getText().toString().trim();
                    four_248=et248.getText().toString().trim();
                    five_258=et258.getText().toString().trim();
                    six_259=et259.getText().toString().trim();
                    seven_278=et278.getText().toString().trim();
                    eight_279=et279.getText().toString().trim();
                    nine_289=et289.getText().toString().trim();
                    ten_280=et280.getText().toString().trim();
                    one_380=et380.getText().toString().trim();
                    two_345=et345.getText().toString().trim();
                    three_256=et256.getText().toString().trim();
                    four_257=et257.getText().toString().trim();
                    five_267=et267.getText().toString().trim();
                    six_268=et268.getText().toString().trim();
                    seven_340=et340.getText().toString().trim();
                    eight_350=et350.getText().toString().trim();
                    nine_360=et360.getText().toString().trim();
                    ten_370=et370.getText().toString().trim();
                    one_470=et470.getText().toString().trim();
                    two_390=et390.getText().toString().trim();
                    three_346=et346.getText().toString().trim();
                    four_347=et347.getText().toString().trim();
                    five_348=et348.getText().toString().trim();
                    six_349=et349.getText().toString().trim();
                    seven_359=et359.getText().toString().trim();
                    eight_369=et369.getText().toString().trim();
                    nine_379=et379.getText().toString().trim();
                    ten_479=et479.getText().toString().trim();
                    one_489=et489.getText().toString().trim();
                    two_480=et480.getText().toString().trim();
                    three_490=et490.getText().toString().trim();
                    four_356=et356.getText().toString().trim();
                    five_357=et357.getText().toString().trim();
                    six_358=et358.getText().toString().trim();
                    seven_368=et368.getText().toString().trim();
                    eight_378=et378.getText().toString().trim();
                    nine_450=et450.getText().toString().trim();
                    ten_460=et460.getText().toString().trim();
                    one_560=et560.getText().toString().trim();
                    two_570=et570.getText().toString().trim();
                    three_580=et580.getText().toString().trim();
                    four_590=et590.getText().toString().trim();
                    five_456=et456.getText().toString().trim();
                    six_457=et457.getText().toString().trim();
                    seven_458=et458.getText().toString().trim();
                    eight_459=et459.getText().toString().trim();
                    nine_469=et469.getText().toString().trim();
                    ten_569=et569.getText().toString().trim();
                    one_678=et678.getText().toString().trim();
                    two_679=et679.getText().toString().trim();
                    three_670=et670.getText().toString().trim();
                    four_680=et680.getText().toString().trim();
                    five_690=et690.getText().toString().trim();
                    six_367=et367.getText().toString().trim();
                    seven_467=et467.getText().toString().trim();
                    eight_567=et567.getText().toString().trim();
                    nine_478=et478.getText().toString().trim();
                    ten_389=et389.getText().toString().trim();
                    one_579=et579.getText().toString().trim();
                    two_589=et589.getText().toString().trim();
                    three_689=et689.getText().toString().trim();
                    four_789=et789.getText().toString().trim();
                    five_780=et780.getText().toString().trim();
                    six_790=et790.getText().toString().trim();
                    seven_890=et890.getText().toString().trim();
                    eight_468=et468.getText().toString().trim();
                    nine_568=et568.getText().toString().trim();
                    ten_578=et578.getText().toString().trim();

                    submitData(v);
                }else {
                    Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private ArrayList<TriplePattiRecyclerModel> populateList() {
        ArrayList<TriplePattiRecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TriplePattiRecyclerModel model = new TriplePattiRecyclerModel();
            model.setEditedValueText("");
            model.setNumberText(String.valueOf(i * 111));

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

        spinner=view.findViewById(R.id.single_patti_Market_spinner);
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

    private void showSuccessDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.submit_succesful_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button okBtn = alertDialog.findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                totalPoint.setText("00");
                enteredAmount.clear();
                for (int i = 0; i < recyclerModels.size(); i++) {
                    recyclerModels.get(i).setEditedValueText("");
                }
            }
        });
    }

    private void submitNumber(final View view){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.submitSinglePattiGame, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                   // String sucess=jsonObject.getString("single");
                    String message=jsonObject.getString("message");
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
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

                params.put("field1281",field128.getText().toString());
                params.put("field1291",field129.getText().toString());
                params.put("field1201",field120.getText().toString());
                params.put("field1301",field130.getText().toString());
                params.put("field1401",field140.getText().toString());
                params.put("field1231",field123.getText().toString());
                params.put("field1241",field124.getText().toString());
                params.put("field1251",field125.getText().toString());
                params.put("field1261",field126.getText().toString());
                params.put("field1271",field127.getText().toString());
                params.put("field1371",field137.getText().toString());
                params.put("field1381",field138.getText().toString());
                params.put("field1391",field139.getText().toString());
                params.put("field1491",field149.getText().toString());
                params.put("field1591",field159.getText().toString());
                params.put("field1501",field150.getText().toString());
                params.put("field1601",field160.getText().toString());
                params.put("field1341",field134.getText().toString());
                params.put("field1351",field135.getText().toString());
                params.put("field1361",field136.getText().toString());
                params.put("field1461",field146.getText().toString());
                params.put("field1471",field147.getText().toString());
                params.put("field1481",field148.getText().toString());
                params.put("field1581",field158.getText().toString());
                params.put("field1681",field168.getText().toString());
                params.put("field1691",field169.getText().toString());
                params.put("field1791",field179.getText().toString());
                params.put("field1701",field170.getText().toString());
                params.put("field1801",field180.getText().toString());
                params.put("field1451",field145.getText().toString());
                params.put("field2361",field236.getText().toString());
                params.put("field1561",field156.getText().toString());
                params.put("field1571",field157.getText().toString());
                params.put("field1671",field167.getText().toString());
                params.put("field2301",field230.getText().toString());
                params.put("field1781",field178.getText().toString());
                params.put("field2501",field250.getText().toString());
                params.put("field1891",field189.getText().toString());
                params.put("field2341",field234.getText().toString());
                params.put("field1901",field190.getText().toString());
                params.put("field2451",field245.getText().toString());
                params.put("field2371",field237.getText().toString());
                params.put("field2381",field238.getText().toString());
                params.put("field2391",field239.getText().toString());
                params.put("field2491",field249.getText().toString());
                params.put("field2401",field240.getText().toString());
                params.put("field2691",field269.getText().toString());
                params.put("field2601",field260.getText().toString());
                params.put("field2701",field270.getText().toString());
                params.put("field2351",field235.getText().toString());
                params.put("field2901",field290.getText().toString());
                params.put("field2461",field246.getText().toString());
                params.put("field2471",field247.getText().toString());
                params.put("field2481",field248.getText().toString());
                params.put("field2581",field258.getText().toString());
                params.put("field2591",field259.getText().toString());
                params.put("field2781",field278.getText().toString());
                params.put("field2791",field279.getText().toString());
                params.put("field2891",field289.getText().toString());
                params.put("field2801",field280.getText().toString());
                params.put("field3801",field380.getText().toString());
                params.put("field3451",field345.getText().toString());
                params.put("field2561",field256.getText().toString());
                params.put("field2571",field257.getText().toString());
                params.put("field2671",field267.getText().toString());
                params.put("field2681",field268.getText().toString());
                params.put("field3401",field340.getText().toString());
                params.put("field3501",field350.getText().toString());
                params.put("field3601",field360.getText().toString());
                params.put("field3701",field370.getText().toString());
                params.put("field4701",field470.getText().toString());
                params.put("field3901",field390.getText().toString());
                params.put("field3461",field346.getText().toString());
                params.put("field3471",field347.getText().toString());
                params.put("field3481",field348.getText().toString());
                params.put("field3491",field349.getText().toString());
                params.put("field3591",field359.getText().toString());
                params.put("field3691",field369.getText().toString());
                params.put("field3791",field379.getText().toString());
                params.put("field4791",field479.getText().toString());
                params.put("field4891",field489.getText().toString());
                params.put("field4801",field480.getText().toString());
                params.put("field4901",field490.getText().toString());
                params.put("field3561",field356.getText().toString());
                params.put("field3571",field357.getText().toString());
                params.put("field3581",field358.getText().toString());
                params.put("field3681",field368.getText().toString());
                params.put("field3781",field378.getText().toString());
                params.put("field4501",field450.getText().toString());
                params.put("field4601",field460.getText().toString());
                params.put("field5601",field560.getText().toString());
                params.put("field5701",field570.getText().toString());
                params.put("field5801",field580.getText().toString());
                params.put("field5901",field590.getText().toString());
                params.put("field4561",field456.getText().toString());
                params.put("field4571",field457.getText().toString());
                params.put("field4581",field458.getText().toString());
                params.put("field4591",field459.getText().toString());
                params.put("field4691",field469.getText().toString());
                params.put("field5691",field569.getText().toString());
                params.put("field6781",field678.getText().toString());
                params.put("field6791",field679.getText().toString());
                params.put("field6701",field670.getText().toString());
                params.put("field6801",field680.getText().toString());
                params.put("field6901",field690.getText().toString());
                params.put("field3671",field367.getText().toString());
                params.put("field4671",field467.getText().toString());
                params.put("field5671",field567.getText().toString());
                params.put("field4781",field478.getText().toString());
                params.put("field3891",field389.getText().toString());
                params.put("field5791",field579.getText().toString());
                params.put("field5891",field589.getText().toString());
                params.put("field6891",field689.getText().toString());
                params.put("field7891",field789.getText().toString());
                params.put("field7801",field780.getText().toString());
                params.put("field7901",field790.getText().toString());
                params.put("field8901",field890.getText().toString());
                params.put("field4681",field468.getText().toString());
                params.put("field5681",field568.getText().toString());
                params.put("field5781",field578.getText().toString());



                params.put("num1281",one_128);
                params.put("num1291",two_129);
                params.put("num1201",three_120);
                params.put("num1301",four_130);
                params.put("num1401",five_140);
                params.put("num1231",six_123);
                params.put("num1241",seven_124);
                params.put("num1251",eight_125);
                params.put("num1261",nine_126);
                params.put("num1271",ten_127);
                params.put("num1371",one_137);
                params.put("num1381",two_138);
                params.put("num1391",three_139);
                params.put("num1491",four_149);
                params.put("num1591",five_159);
                params.put("num1501",six_150);
                params.put("num1601",seven_160);
                params.put("num1341",eight_134);
                params.put("num1351",nine_135);
                params.put("num1361",ten_136);
                params.put("num1461",one_146);
                params.put("num1471",two_147);
                params.put("num1481",three_148);
                params.put("num1581",four_158);
                params.put("num1681",five_168);
                params.put("num1691",six_169);
                params.put("num1791",seven_179);
                params.put("num1701",eight_170);
                params.put("num1801",nine_180);
                params.put("num1451",ten_145);
                params.put("num2361",one_236);
                params.put("num1561",two_156);
                params.put("num1571",three_157);
                params.put("num1671",four_167);
                params.put("num2301",five_230);
                params.put("num1781",six_178);
                params.put("num2501",seven_250);
                params.put("num1891",eight_189);
                params.put("num2341",nine_234);
                params.put("num1901",ten_190);
                params.put("num2451",one_245);
                params.put("num2371",two_237);
                params.put("num2381",three_238);
                params.put("num2391",four_239);
                params.put("num2491",five_249);
                params.put("num2401",six_240);
                params.put("num2691",seven_269);
                params.put("num2601",eight_260);
                params.put("num2701",nine_270);
                params.put("num2351",ten_235);
                params.put("num2901",one_290);
                params.put("num2461",two_246);
                params.put("num2471",three_247);
                params.put("num2481",four_248);
                params.put("num2581",five_258);
                params.put("num2591",six_259);
                params.put("num2781",seven_278);
                params.put("num2791",eight_279);
                params.put("num2891",nine_289);
                params.put("num2801",ten_280);
                params.put("num3801",one_380);
                params.put("num3451",two_345);
                params.put("num2561",three_256);
                params.put("num2571",four_257);
                params.put("num2671",five_267);
                params.put("num2681",six_268);
                params.put("num3401",seven_340);
                params.put("num3501",eight_350);
                params.put("num3601",nine_360);
                params.put("num3701",ten_370);
                params.put("num4701",one_470);
                params.put("num3901",two_390);
                params.put("num3461",three_346);
                params.put("num3471",four_347);
                params.put("num3481",five_348);
                params.put("num3491",six_349);
                params.put("num3591",seven_359);
                params.put("num3691",eight_369);
                params.put("num3791",nine_379);
                params.put("num4791",ten_479);
                params.put("num4891",one_489);
                params.put("num4801",two_480);
                params.put("num4901",three_490);
                params.put("num3561",four_356);
                params.put("num3571",five_357);
                params.put("num3581",six_358);
                params.put("num3681",seven_368);
                params.put("num3781",eight_378);
                params.put("num4501",nine_450);
                params.put("num4601",ten_460);
                params.put("num5601",one_560);
                params.put("num5701",two_570);
                params.put("num5801",three_580);
                params.put("num5901",four_590);
                params.put("num4561",five_456);
                params.put("num4571",six_457);
                params.put("num4581",seven_458);
                params.put("num4591",eight_459);
                params.put("num4691",nine_469);
                params.put("num5691",ten_569);
                params.put("num6781",one_678);
                params.put("num6791",two_679);
                params.put("num6701",three_670);
                params.put("num6801",four_680);
                params.put("num6901",five_690);
                params.put("num3671",six_367);
                params.put("num4671",seven_467);
                params.put("num5671",eight_567);
                params.put("num4781",nine_478);
                params.put("num3891",ten_389);
                params.put("num5791",one_579);
                params.put("num5891",two_589);
                params.put("num6891",three_689);
                params.put("num7891",four_789);
                params.put("num7801",five_780);
                params.put("num7901",six_790);
                params.put("num8901",seven_890);
                params.put("num4681",eight_468);
                params.put("num5681",nine_568);
                params.put("num5781",ten_578);


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

    private void initilaizeEditText(View view){
        et128=view.findViewById(R.id.frag_single_patti_data_edittext_128);
        et129=view.findViewById(R.id.frag_single_patti_data_edittext_129);
        et120=view.findViewById(R.id.frag_single_patti_data_edittext_120);
        et130=view.findViewById(R.id.frag_single_patti_data_edittext_130);
        et140=view.findViewById(R.id.frag_single_patti_data_edittext_140);
        et123=view.findViewById(R.id.frag_single_patti_data_edittext_123);
        et124=view.findViewById(R.id.frag_single_patti_data_edittext_124);
        et125=view.findViewById(R.id.frag_single_patti_data_edittext_125);
        et126=view.findViewById(R.id.frag_single_patti_data_edittext_126);
        et127=view.findViewById(R.id.frag_single_patti_data_edittext_127);
        et137=view.findViewById(R.id.frag_single_patti_data_edittext_137);
        et138=view.findViewById(R.id.frag_single_patti_data_edittext_138);
        et139=view.findViewById(R.id.frag_single_patti_data_edittext_139);
        et149=view.findViewById(R.id.frag_single_patti_data_edittext_149);
        et159=view.findViewById(R.id.frag_single_patti_data_edittext_159);
        et150=view.findViewById(R.id.frag_single_patti_data_edittext_150);
        et160=view.findViewById(R.id.frag_single_patti_data_edittext_160);
        et134=view.findViewById(R.id.frag_single_patti_data_edittext_134);
        et135=view.findViewById(R.id.frag_single_patti_data_edittext_135);
        et136=view.findViewById(R.id.frag_single_patti_data_edittext_136);
        et146=view.findViewById(R.id.frag_single_patti_data_edittext_146);
        et147=view.findViewById(R.id.frag_single_patti_data_edittext_147);
        et148=view.findViewById(R.id.frag_single_patti_data_edittext_148);
        et158=view.findViewById(R.id.frag_single_patti_data_edittext_158);
        et168=view.findViewById(R.id.frag_single_patti_data_edittext_168);
        et169=view.findViewById(R.id.frag_single_patti_data_edittext_169);
        et179=view.findViewById(R.id.frag_single_patti_data_edittext_179);
        et170=view.findViewById(R.id.frag_single_patti_data_edittext_170);
        et180=view.findViewById(R.id.frag_single_patti_data_edittext_180);
        et145=view.findViewById(R.id.frag_single_patti_data_edittext_145);
        et236=view.findViewById(R.id.frag_single_patti_data_edittext_236);
        et156=view.findViewById(R.id.frag_single_patti_data_edittext_156);
        et157=view.findViewById(R.id.frag_single_patti_data_edittext_157);
        et167=view.findViewById(R.id.frag_single_patti_data_edittext_167);
        et230=view.findViewById(R.id.frag_single_patti_data_edittext_230);
        et178=view.findViewById(R.id.frag_single_patti_data_edittext_178);
        et250=view.findViewById(R.id.frag_single_patti_data_edittext_250);
        et189=view.findViewById(R.id.frag_single_patti_data_edittext_189);
        et234=view.findViewById(R.id.frag_single_patti_data_edittext_234);
        et190=view.findViewById(R.id.frag_single_patti_data_edittext_190);
        et245=view.findViewById(R.id.frag_single_patti_data_edittext_245);
        et237=view.findViewById(R.id.frag_single_patti_data_edittext_237);
        et238=view.findViewById(R.id.frag_single_patti_data_edittext_238);
        et239=view.findViewById(R.id.frag_single_patti_data_edittext_239);
        et249=view.findViewById(R.id.frag_single_patti_data_edittext_249);
        et240=view.findViewById(R.id.frag_single_patti_data_edittext_240);
        et269=view.findViewById(R.id.frag_single_patti_data_edittext_269);
        et260=view.findViewById(R.id.frag_single_patti_data_edittext_260);
        et270=view.findViewById(R.id.frag_single_patti_data_edittext_270);
        et235=view.findViewById(R.id.frag_single_patti_data_edittext_235);
        et290=view.findViewById(R.id.frag_single_patti_data_edittext_290);
        et246=view.findViewById(R.id.frag_single_patti_data_edittext_246);
        et247=view.findViewById(R.id.frag_single_patti_data_edittext_247);
        et248=view.findViewById(R.id.frag_single_patti_data_edittext_248);
        et258=view.findViewById(R.id.frag_single_patti_data_edittext_258);
        et259=view.findViewById(R.id.frag_single_patti_data_edittext_259);
        et278=view.findViewById(R.id.frag_single_patti_data_edittext_278);
        et279=view.findViewById(R.id.frag_single_patti_data_edittext_279);
        et289=view.findViewById(R.id.frag_single_patti_data_edittext_289);
        et280=view.findViewById(R.id.frag_single_patti_data_edittext_280);
        et380=view.findViewById(R.id.frag_single_patti_data_edittext_380);
        et345=view.findViewById(R.id.frag_single_patti_data_edittext_345);
        et256=view.findViewById(R.id.frag_single_patti_data_edittext_256);
        et257=view.findViewById(R.id.frag_single_patti_data_edittext_257);
        et267=view.findViewById(R.id.frag_single_patti_data_edittext_267);
        et268=view.findViewById(R.id.frag_single_patti_data_edittext_268);
        et340=view.findViewById(R.id.frag_single_patti_data_edittext_340);
        et350=view.findViewById(R.id.frag_single_patti_data_edittext_350);
        et360=view.findViewById(R.id.frag_single_patti_data_edittext_360);
        et370=view.findViewById(R.id.frag_single_patti_data_edittext_370);
        et470=view.findViewById(R.id.frag_single_patti_data_edittext_470);
        et390=view.findViewById(R.id.frag_single_patti_data_edittext_390);
        et346=view.findViewById(R.id.frag_single_patti_data_edittext_346);
        et347=view.findViewById(R.id.frag_single_patti_data_edittext_347);
        et348=view.findViewById(R.id.frag_single_patti_data_edittext_348);
        et349=view.findViewById(R.id.frag_single_patti_data_edittext_349);
        et359=view.findViewById(R.id.frag_single_patti_data_edittext_359);
        et369=view.findViewById(R.id.frag_single_patti_data_edittext_369);
        et379=view.findViewById(R.id.frag_single_patti_data_edittext_379);
        et479=view.findViewById(R.id.frag_single_patti_data_edittext_479);
        et489=view.findViewById(R.id.frag_single_patti_data_edittext_489);
        et480=view.findViewById(R.id.frag_single_patti_data_edittext_480);
        et490=view.findViewById(R.id.frag_single_patti_data_edittext_490);
        et356=view.findViewById(R.id.frag_single_patti_data_edittext_356);
        et357=view.findViewById(R.id.frag_single_patti_data_edittext_357);
        et358=view.findViewById(R.id.frag_single_patti_data_edittext_358);
        et368=view.findViewById(R.id.frag_single_patti_data_edittext_368);
        et378=view.findViewById(R.id.frag_single_patti_data_edittext_378);
        et450=view.findViewById(R.id.frag_single_patti_data_edittext_450);
        et460=view.findViewById(R.id.frag_single_patti_data_edittext_460);
        et560=view.findViewById(R.id.frag_single_patti_data_edittext_560);
        et570=view.findViewById(R.id.frag_single_patti_data_edittext_570);
        et580=view.findViewById(R.id.frag_single_patti_data_edittext_580);
        et590=view.findViewById(R.id.frag_single_patti_data_edittext_590);
        et456=view.findViewById(R.id.frag_single_patti_data_edittext_456);
        et457=view.findViewById(R.id.frag_single_patti_data_edittext_457);
        et458=view.findViewById(R.id.frag_single_patti_data_edittext_458);
        et459=view.findViewById(R.id.frag_single_patti_data_edittext_459);
        et469=view.findViewById(R.id.frag_single_patti_data_edittext_469);
        et569=view.findViewById(R.id.frag_single_patti_data_edittext_569);
        et678=view.findViewById(R.id.frag_single_patti_data_edittext_678);
        et679=view.findViewById(R.id.frag_single_patti_data_edittext_679);
        et670=view.findViewById(R.id.frag_single_patti_data_edittext_670);
        et680=view.findViewById(R.id.frag_single_patti_data_edittext_680);
        et690=view.findViewById(R.id.frag_single_patti_data_edittext_690);
        et367=view.findViewById(R.id.frag_single_patti_data_edittext_367);
        et467=view.findViewById(R.id.frag_single_patti_data_edittext_467);
        et567=view.findViewById(R.id.frag_single_patti_data_edittext_567);
        et478=view.findViewById(R.id.frag_single_patti_data_edittext_478);
        et389=view.findViewById(R.id.frag_single_patti_data_edittext_389);
        et579=view.findViewById(R.id.frag_single_patti_data_edittext_579);
        et589=view.findViewById(R.id.frag_single_patti_data_edittext_589);
        et689=view.findViewById(R.id.frag_single_patti_data_edittext_689);
        et789=view.findViewById(R.id.frag_single_patti_data_edittext_789);
        et780=view.findViewById(R.id.frag_single_patti_data_edittext_780);
        et790=view.findViewById(R.id.frag_single_patti_data_edittext_790);
        et890=view.findViewById(R.id.frag_single_patti_data_edittext_890);
        et468=view.findViewById(R.id.frag_single_patti_data_edittext_468);
        et568=view.findViewById(R.id.frag_single_patti_data_edittext_568);
        et578=view.findViewById(R.id.frag_single_patti_data_edittext_578);

        field128=view.findViewById(R.id.frag_single_patti_text_number_128);
        field129=view.findViewById(R.id.frag_single_patti_text_number_129);
        field120=view.findViewById(R.id.frag_single_patti_text_number_120);
        field130=view.findViewById(R.id.frag_single_patti_text_number_130);
        field140=view.findViewById(R.id.frag_single_patti_text_number_140);
        field123=view.findViewById(R.id.frag_single_patti_text_number_123);
        field124=view.findViewById(R.id.frag_single_patti_text_number_124);
        field125=view.findViewById(R.id.frag_single_patti_text_number_125);
        field126=view.findViewById(R.id.frag_single_patti_text_number_126);
        field127=view.findViewById(R.id.frag_single_patti_text_number_127);
        field137=view.findViewById(R.id.frag_single_patti_text_number_137);
        field138=view.findViewById(R.id.frag_single_patti_text_number_138);
        field139=view.findViewById(R.id.frag_single_patti_text_number_139);
        field149=view.findViewById(R.id.frag_single_patti_text_number_149);
        field159=view.findViewById(R.id.frag_single_patti_text_number_159);
        field150=view.findViewById(R.id.frag_single_patti_text_number_150);
        field160=view.findViewById(R.id.frag_single_patti_text_number_160);
        field134=view.findViewById(R.id.frag_single_patti_text_number_134);
        field135=view.findViewById(R.id.frag_single_patti_text_number_135);
        field136=view.findViewById(R.id.frag_single_patti_text_number_136);
        field146=view.findViewById(R.id.frag_single_patti_text_number_146);
        field147=view.findViewById(R.id.frag_single_patti_text_number_147);
        field148=view.findViewById(R.id.frag_single_patti_text_number_148);
        field158=view.findViewById(R.id.frag_single_patti_text_number_158);
        field168=view.findViewById(R.id.frag_single_patti_text_number_168);
        field169=view.findViewById(R.id.frag_single_patti_text_number_169);
        field179=view.findViewById(R.id.frag_single_patti_text_number_179);
        field170=view.findViewById(R.id.frag_single_patti_text_number_170);
        field180=view.findViewById(R.id.frag_single_patti_text_number_180);
        field145=view.findViewById(R.id.frag_single_patti_text_number_145);
        field236=view.findViewById(R.id.frag_single_patti_text_number_236);
        field156=view.findViewById(R.id.frag_single_patti_text_number_156);
        field157=view.findViewById(R.id.frag_single_patti_text_number_157);
        field167=view.findViewById(R.id.frag_single_patti_text_number_167);
        field230=view.findViewById(R.id.frag_single_patti_text_number_230);
        field178=view.findViewById(R.id.frag_single_patti_text_number_178);
        field250=view.findViewById(R.id.frag_single_patti_text_number_250);
        field189=view.findViewById(R.id.frag_single_patti_text_number_189);
        field234=view.findViewById(R.id.frag_single_patti_text_number_234);
        field190=view.findViewById(R.id.frag_single_patti_text_number_190);
        field245=view.findViewById(R.id.frag_single_patti_text_number_245);
        field237=view.findViewById(R.id.frag_single_patti_text_number_237);
        field238=view.findViewById(R.id.frag_single_patti_text_number_238);
        field239=view.findViewById(R.id.frag_single_patti_text_number_239);
        field249=view.findViewById(R.id.frag_single_patti_text_number_249);
        field240=view.findViewById(R.id.frag_single_patti_text_number_240);
        field269=view.findViewById(R.id.frag_single_patti_text_number_269);
        field260=view.findViewById(R.id.frag_single_patti_text_number_260);
        field270=view.findViewById(R.id.frag_single_patti_text_number_270);
        field235=view.findViewById(R.id.frag_single_patti_text_number_235);
        field290=view.findViewById(R.id.frag_single_patti_text_number_290);
        field246=view.findViewById(R.id.frag_single_patti_text_number_246);
        field247=view.findViewById(R.id.frag_single_patti_text_number_247);
        field248=view.findViewById(R.id.frag_single_patti_text_number_248);
        field258=view.findViewById(R.id.frag_single_patti_text_number_258);
        field259=view.findViewById(R.id.frag_single_patti_text_number_259);
        field278=view.findViewById(R.id.frag_single_patti_text_number_278);
        field279=view.findViewById(R.id.frag_single_patti_text_number_279);
        field289=view.findViewById(R.id.frag_single_patti_text_number_289);
        field280=view.findViewById(R.id.frag_single_patti_text_number_280);
        field380=view.findViewById(R.id.frag_single_patti_text_number_380);
        field345=view.findViewById(R.id.frag_single_patti_text_number_345);
        field256=view.findViewById(R.id.frag_single_patti_text_number_256);
        field257=view.findViewById(R.id.frag_single_patti_text_number_257);
        field267=view.findViewById(R.id.frag_single_patti_text_number_267);
        field268=view.findViewById(R.id.frag_single_patti_text_number_268);
        field340=view.findViewById(R.id.frag_single_patti_text_number_340);
        field350=view.findViewById(R.id.frag_single_patti_text_number_350);
        field360=view.findViewById(R.id.frag_single_patti_text_number_360);
        field370=view.findViewById(R.id.frag_single_patti_text_number_370);
        field470=view.findViewById(R.id.frag_single_patti_text_number_470);
        field390=view.findViewById(R.id.frag_single_patti_text_number_390);
        field346=view.findViewById(R.id.frag_single_patti_text_number_346);
        field347=view.findViewById(R.id.frag_single_patti_text_number_347);
        field348=view.findViewById(R.id.frag_single_patti_text_number_348);
        field349=view.findViewById(R.id.frag_single_patti_text_number_349);
        field359=view.findViewById(R.id.frag_single_patti_text_number_359);
        field369=view.findViewById(R.id.frag_single_patti_text_number_369);
        field379=view.findViewById(R.id.frag_single_patti_text_number_379);
        field479=view.findViewById(R.id.frag_single_patti_text_number_479);
        field489=view.findViewById(R.id.frag_single_patti_text_number_489);
        field480=view.findViewById(R.id.frag_single_patti_text_number_480);
        field490=view.findViewById(R.id.frag_single_patti_text_number_490);
        field356=view.findViewById(R.id.frag_single_patti_text_number_356);
        field357=view.findViewById(R.id.frag_single_patti_text_number_357);
        field358=view.findViewById(R.id.frag_single_patti_text_number_358);
        field368=view.findViewById(R.id.frag_single_patti_text_number_368);
        field378=view.findViewById(R.id.frag_single_patti_text_number_378);
        field450=view.findViewById(R.id.frag_single_patti_text_number_450);
        field460=view.findViewById(R.id.frag_single_patti_text_number_460);
        field560=view.findViewById(R.id.frag_single_patti_text_number_560);
        field570=view.findViewById(R.id.frag_single_patti_text_number_570);
        field580=view.findViewById(R.id.frag_single_patti_text_number_580);
        field590=view.findViewById(R.id.frag_single_patti_text_number_590);
        field456=view.findViewById(R.id.frag_single_patti_text_number_456);
        field457=view.findViewById(R.id.frag_single_patti_text_number_457);
        field458=view.findViewById(R.id.frag_single_patti_text_number_458);
        field459=view.findViewById(R.id.frag_single_patti_text_number_459);
        field469=view.findViewById(R.id.frag_single_patti_text_number_469);
        field569=view.findViewById(R.id.frag_single_patti_text_number_569);
        field678=view.findViewById(R.id.frag_single_patti_text_number_678);
        field679=view.findViewById(R.id.frag_single_patti_text_number_679);
        field670=view.findViewById(R.id.frag_single_patti_text_number_670);
        field680=view.findViewById(R.id.frag_single_patti_text_number_680);
        field690=view.findViewById(R.id.frag_single_patti_text_number_690);
        field367=view.findViewById(R.id.frag_single_patti_text_number_367);
        field467=view.findViewById(R.id.frag_single_patti_text_number_467);
        field567=view.findViewById(R.id.frag_single_patti_text_number_567);
        field478=view.findViewById(R.id.frag_single_patti_text_number_478);
        field389=view.findViewById(R.id.frag_single_patti_text_number_389);
        field579=view.findViewById(R.id.frag_single_patti_text_number_579);
        field589=view.findViewById(R.id.frag_single_patti_text_number_589);
        field689=view.findViewById(R.id.frag_single_patti_text_number_689);
        field789=view.findViewById(R.id.frag_single_patti_text_number_789);
        field780=view.findViewById(R.id.frag_single_patti_text_number_780);
        field790=view.findViewById(R.id.frag_single_patti_text_number_790);
        field890=view.findViewById(R.id.frag_single_patti_text_number_890);
        field468=view.findViewById(R.id.frag_single_patti_text_number_468);
        field568=view.findViewById(R.id.frag_single_patti_text_number_568);
        field578=view.findViewById(R.id.frag_single_patti_text_number_578);

    }

   private void comparingEdittext(){
        //// 01
       if(!et128.getText().toString().isEmpty()){
           if(Integer.parseInt(et128.getText().toString())!=0 && Integer.parseInt(et128.getText().toString())>10 ){
               total=total+Integer.parseInt(et128.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et128.getText().toString())<10){
               et128.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et137.getText().toString().isEmpty()){
           if(Integer.parseInt(et137.getText().toString())!=0 && Integer.parseInt(et137.getText().toString())>10 ){
               total=total+Integer.parseInt(et137.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et137.getText().toString())<10){
               et137.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et146.getText().toString().isEmpty()){
           if(Integer.parseInt(et146.getText().toString())!=0 && Integer.parseInt(et146.getText().toString())>10 ){
               total=total+Integer.parseInt(et146.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et146.getText().toString())<10){
               et146.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et236.getText().toString().isEmpty()){
           if(Integer.parseInt(et236.getText().toString())!=0 && Integer.parseInt(et236.getText().toString())>10 ){
               total=total+Integer.parseInt(et236.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et236.getText().toString())<10){
               et236.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et245.getText().toString().isEmpty()){
           if(Integer.parseInt(et245.getText().toString())!=0 && Integer.parseInt(et245.getText().toString())>10 ){
               total=total+Integer.parseInt(et245.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et245.getText().toString())<10){
               et245.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et290.getText().toString().isEmpty()){
           if(Integer.parseInt(et290.getText().toString())!=0 && Integer.parseInt(et290.getText().toString())>10 ){
               total=total+Integer.parseInt(et290.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et290.getText().toString())<10){
               et290.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et380.getText().toString().isEmpty()){
           if(Integer.parseInt(et380.getText().toString())!=0 && Integer.parseInt(et380.getText().toString())>10 ){
               total=total+Integer.parseInt(et380.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et380.getText().toString())<10){
               et380.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et470.getText().toString().isEmpty()){
           if(Integer.parseInt(et470.getText().toString())!=0 && Integer.parseInt(et470.getText().toString())>10 ){
               total=total+Integer.parseInt(et470.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et470.getText().toString())<10){
               et470.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et489.getText().toString().isEmpty()){
           if(Integer.parseInt(et489.getText().toString())!=0 && Integer.parseInt(et489.getText().toString())>10 ){
               total=total+Integer.parseInt(et489.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et489.getText().toString())<10){
               et489.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et560.getText().toString().isEmpty()){
           if(Integer.parseInt(et560.getText().toString())!=0 && Integer.parseInt(et560.getText().toString())>10 ){
               total=total+Integer.parseInt(et560.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et560.getText().toString())<10){
               et560.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et678.getText().toString().isEmpty()){
           if(Integer.parseInt(et678.getText().toString())!=0 && Integer.parseInt(et678.getText().toString())>10 ){
               total=total+Integer.parseInt(et678.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et678.getText().toString())<10){
               et678.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et579.getText().toString().isEmpty()){
           if(Integer.parseInt(et579.getText().toString())!=0 && Integer.parseInt(et579.getText().toString())>10 ){
               total=total+Integer.parseInt(et579.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et579.getText().toString())<10){
               et579.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       /////  02

       if(!et129.getText().toString().isEmpty()){
           if(Integer.parseInt(et129.getText().toString())!=0 && Integer.parseInt(et129.getText().toString())>10 ){
               total=total+Integer.parseInt(et129.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et129.getText().toString())<10){
               et129.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et138.getText().toString().isEmpty()){
           if(Integer.parseInt(et138.getText().toString())!=0 && Integer.parseInt(et138.getText().toString())>10 ){
               total=total+Integer.parseInt(et138.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et138.getText().toString())<10){
               et138.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et147.getText().toString().isEmpty()){
           if(Integer.parseInt(et147.getText().toString())!=0 && Integer.parseInt(et147.getText().toString())>10 ){
               total=total+Integer.parseInt(et147.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et147.getText().toString())<10){
               et147.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et156.getText().toString().isEmpty()){
           if(Integer.parseInt(et156.getText().toString())!=0 && Integer.parseInt(et156.getText().toString())>10 ){
               total=total+Integer.parseInt(et156.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et156.getText().toString())<10){
               et156.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et237.getText().toString().isEmpty()){
           if(Integer.parseInt(et237.getText().toString())!=0 && Integer.parseInt(et237.getText().toString())>10 ){
               total=total+Integer.parseInt(et237.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et237.getText().toString())<10){
               et237.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et246.getText().toString().isEmpty()){
           if(Integer.parseInt(et246.getText().toString())!=0 && Integer.parseInt(et246.getText().toString())>10 ){
               total=total+Integer.parseInt(et246.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et246.getText().toString())<10){
               et246.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et345.getText().toString().isEmpty()){
           if(Integer.parseInt(et345.getText().toString())!=0 && Integer.parseInt(et345.getText().toString())>10 ){
               total=total+Integer.parseInt(et345.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et345.getText().toString())<10){
               et345.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et390.getText().toString().isEmpty()){
           if(Integer.parseInt(et390.getText().toString())!=0 && Integer.parseInt(et390.getText().toString())>10 ){
               total=total+Integer.parseInt(et390.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et390.getText().toString())<10){
               et390.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et480.getText().toString().isEmpty()){
           if(Integer.parseInt(et480.getText().toString())!=0 && Integer.parseInt(et480.getText().toString())>10 ){
               total=total+Integer.parseInt(et480.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et480.getText().toString())<10){
               et480.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et570.getText().toString().isEmpty()){
           if(Integer.parseInt(et570.getText().toString())!=0 && Integer.parseInt(et570.getText().toString())>10 ){
               total=total+Integer.parseInt(et570.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et570.getText().toString())<10){
               et570.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et679.getText().toString().isEmpty()){
           if(Integer.parseInt(et679.getText().toString())!=0 && Integer.parseInt(et679.getText().toString())>10 ){
               total=total+Integer.parseInt(et679.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et679.getText().toString())<10){
               et679.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et589.getText().toString().isEmpty()){
           if(Integer.parseInt(et589.getText().toString())!=0 && Integer.parseInt(et589.getText().toString())>10 ){
               total=total+Integer.parseInt(et589.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et589.getText().toString())<10){
               et589.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       //// 03


       if(!et120.getText().toString().isEmpty()){
           if(Integer.parseInt(et120.getText().toString())!=0 && Integer.parseInt(et120.getText().toString())>10 ){
               total=total+Integer.parseInt(et120.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et120.getText().toString())<10){
               et120.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et139.getText().toString().isEmpty()){
           if(Integer.parseInt(et139.getText().toString())!=0 && Integer.parseInt(et139.getText().toString())>10 ){
               total=total+Integer.parseInt(et139.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et139.getText().toString())<10){
               et139.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et148.getText().toString().isEmpty()){
           if(Integer.parseInt(et148.getText().toString())!=0 && Integer.parseInt(et148.getText().toString())>10 ){
               total=total+Integer.parseInt(et148.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et148.getText().toString())<10){
               et148.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et157.getText().toString().isEmpty()){
           if(Integer.parseInt(et157.getText().toString())!=0 && Integer.parseInt(et157.getText().toString())>10 ){
               total=total+Integer.parseInt(et157.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et157.getText().toString())<10){
               et157.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et238.getText().toString().isEmpty()){
           if(Integer.parseInt(et238.getText().toString())!=0 && Integer.parseInt(et238.getText().toString())>10 ){
               total=total+Integer.parseInt(et238.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et238.getText().toString())<10){
               et238.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et247.getText().toString().isEmpty()){
           if(Integer.parseInt(et247.getText().toString())!=0 && Integer.parseInt(et247.getText().toString())>10 ){
               total=total+Integer.parseInt(et247.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et247.getText().toString())<10){
               et247.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et256.getText().toString().isEmpty()){
           if(Integer.parseInt(et256.getText().toString())!=0 && Integer.parseInt(et256.getText().toString())>10 ){
               total=total+Integer.parseInt(et256.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et256.getText().toString())<10){
               et256.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et346.getText().toString().isEmpty()){
           if(Integer.parseInt(et346.getText().toString())!=0 && Integer.parseInt(et346.getText().toString())>10 ){
               total=total+Integer.parseInt(et346.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et346.getText().toString())<10){
               et346.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et490.getText().toString().isEmpty()){
           if(Integer.parseInt(et490.getText().toString())!=0 && Integer.parseInt(et490.getText().toString())>10 ){
               total=total+Integer.parseInt(et490.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et490.getText().toString())<10){
               et490.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et580.getText().toString().isEmpty()){
           if(Integer.parseInt(et580.getText().toString())!=0 && Integer.parseInt(et580.getText().toString())>10 ){
               total=total+Integer.parseInt(et580.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et580.getText().toString())<10){
               et580.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et670.getText().toString().isEmpty()){
           if(Integer.parseInt(et670.getText().toString())!=0 && Integer.parseInt(et670.getText().toString())>10 ){
               total=total+Integer.parseInt(et670.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et670.getText().toString())<10){
               et670.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et689.getText().toString().isEmpty()){
           if(Integer.parseInt(et689.getText().toString())!=0 && Integer.parseInt(et689.getText().toString())>10 ){
               total=total+Integer.parseInt(et689.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et689.getText().toString())<10){
               et689.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       ///// 04


       if(!et130.getText().toString().isEmpty()){
           if(Integer.parseInt(et130.getText().toString())!=0 && Integer.parseInt(et130.getText().toString())>10 ){
               total=total+Integer.parseInt(et130.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et130.getText().toString())<10){
               et130.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et149.getText().toString().isEmpty()){
           if(Integer.parseInt(et149.getText().toString())!=0 && Integer.parseInt(et149.getText().toString())>10 ){
               total=total+Integer.parseInt(et149.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et149.getText().toString())<10){
               et149.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et158.getText().toString().isEmpty()){
           if(Integer.parseInt(et158.getText().toString())!=0 && Integer.parseInt(et158.getText().toString())>10 ){
               total=total+Integer.parseInt(et158.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et158.getText().toString())<10){
               et158.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et167.getText().toString().isEmpty()){
           if(Integer.parseInt(et167.getText().toString())!=0 && Integer.parseInt(et167.getText().toString())>10 ){
               total=total+Integer.parseInt(et167.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et167.getText().toString())<10){
               et167.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et239.getText().toString().isEmpty()){
           if(Integer.parseInt(et239.getText().toString())!=0 && Integer.parseInt(et239.getText().toString())>10 ){
               total=total+Integer.parseInt(et239.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et239.getText().toString())<10){
               et239.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et248.getText().toString().isEmpty()){
           if(Integer.parseInt(et248.getText().toString())!=0 && Integer.parseInt(et248.getText().toString())>10 ){
               total=total+Integer.parseInt(et248.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et248.getText().toString())<10){
               et248.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et257.getText().toString().isEmpty()){
           if(Integer.parseInt(et257.getText().toString())!=0 && Integer.parseInt(et257.getText().toString())>10 ){
               total=total+Integer.parseInt(et257.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et257.getText().toString())<10){
               et257.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et347.getText().toString().isEmpty()){
           if(Integer.parseInt(et347.getText().toString())!=0 && Integer.parseInt(et347.getText().toString())>10 ){
               total=total+Integer.parseInt(et347.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et347.getText().toString())<10){
               et347.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et356.getText().toString().isEmpty()){
           if(Integer.parseInt(et356.getText().toString())!=0 && Integer.parseInt(et356.getText().toString())>10 ){
               total=total+Integer.parseInt(et356.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et356.getText().toString())<10){
               et356.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et590.getText().toString().isEmpty()){
           if(Integer.parseInt(et590.getText().toString())!=0 && Integer.parseInt(et590.getText().toString())>10 ){
               total=total+Integer.parseInt(et590.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et590.getText().toString())<10){
               et590.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et680.getText().toString().isEmpty()){
           if(Integer.parseInt(et680.getText().toString())!=0 && Integer.parseInt(et680.getText().toString())>10 ){
               total=total+Integer.parseInt(et680.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et680.getText().toString())<10){
               et680.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et789.getText().toString().isEmpty()){
           if(Integer.parseInt(et789.getText().toString())!=0 && Integer.parseInt(et789.getText().toString())>10 ){
               total=total+Integer.parseInt(et789.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et789.getText().toString())<10){
               et789.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }
        /////05


       if(!et140.getText().toString().isEmpty()){
           if(Integer.parseInt(et140.getText().toString())!=0 && Integer.parseInt(et140.getText().toString())>10 ){
               total=total+Integer.parseInt(et140.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et140.getText().toString())<10){
               et140.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et159.getText().toString().isEmpty()){
           if(Integer.parseInt(et159.getText().toString())!=0 && Integer.parseInt(et159.getText().toString())>10 ){
               total=total+Integer.parseInt(et159.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et159.getText().toString())<10){
               et159.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et168.getText().toString().isEmpty()){
           if(Integer.parseInt(et168.getText().toString())!=0 && Integer.parseInt(et168.getText().toString())>10 ){
               total=total+Integer.parseInt(et168.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et168.getText().toString())<10){
               et168.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et230.getText().toString().isEmpty()){
           if(Integer.parseInt(et230.getText().toString())!=0 && Integer.parseInt(et230.getText().toString())>10 ){
               total=total+Integer.parseInt(et230.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et230.getText().toString())<10){
               et230.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et249.getText().toString().isEmpty()){
           if(Integer.parseInt(et249.getText().toString())!=0 && Integer.parseInt(et249.getText().toString())>10 ){
               total=total+Integer.parseInt(et249.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et249.getText().toString())<10){
               et249.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et258.getText().toString().isEmpty()){
           if(Integer.parseInt(et258.getText().toString())!=0 && Integer.parseInt(et258.getText().toString())>10 ){
               total=total+Integer.parseInt(et258.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et258.getText().toString())<10){
               et258.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et267.getText().toString().isEmpty()){
           if(Integer.parseInt(et267.getText().toString())!=0 && Integer.parseInt(et267.getText().toString())>10 ){
               total=total+Integer.parseInt(et267.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et267.getText().toString())<10){
               et267.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et348.getText().toString().isEmpty()){
           if(Integer.parseInt(et348.getText().toString())!=0 && Integer.parseInt(et348.getText().toString())>10 ){
               total=total+Integer.parseInt(et348.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et348.getText().toString())<10){
               et348.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et357.getText().toString().isEmpty()){
           if(Integer.parseInt(et357.getText().toString())!=0 && Integer.parseInt(et357.getText().toString())>10 ){
               total=total+Integer.parseInt(et357.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et357.getText().toString())<10){
               et357.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et690.getText().toString().isEmpty()){
           if(Integer.parseInt(et690.getText().toString())!=0 && Integer.parseInt(et690.getText().toString())>10 ){
               total=total+Integer.parseInt(et690.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et690.getText().toString())<10){
               et690.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et780.getText().toString().isEmpty()){
           if(Integer.parseInt(et780.getText().toString())!=0 && Integer.parseInt(et780.getText().toString())>10 ){
               total=total+Integer.parseInt(et780.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et780.getText().toString())<10){
               et780.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et456.getText().toString().isEmpty()){
           if(Integer.parseInt(et456.getText().toString())!=0 && Integer.parseInt(et456.getText().toString())>10 ){
               total=total+Integer.parseInt(et456.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et456.getText().toString())<10){
               et456.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       //// 06


       if(!et123.getText().toString().isEmpty()){
           if(Integer.parseInt(et123.getText().toString())!=0 && Integer.parseInt(et123.getText().toString())>10 ){
               total=total+Integer.parseInt(et123.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et123.getText().toString())<10){
               et123.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et150.getText().toString().isEmpty()){
           if(Integer.parseInt(et150.getText().toString())!=0 && Integer.parseInt(et150.getText().toString())>10 ){
               total=total+Integer.parseInt(et150.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et150.getText().toString())<10){
               et150.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et169.getText().toString().isEmpty()){
           if(Integer.parseInt(et169.getText().toString())!=0 && Integer.parseInt(et169.getText().toString())>10 ){
               total=total+Integer.parseInt(et169.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et169.getText().toString())<10){
               et169.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et178.getText().toString().isEmpty()){
           if(Integer.parseInt(et178.getText().toString())!=0 && Integer.parseInt(et178.getText().toString())>10 ){
               total=total+Integer.parseInt(et178.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et178.getText().toString())<10){
               et178.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et240.getText().toString().isEmpty()){
           if(Integer.parseInt(et240.getText().toString())!=0 && Integer.parseInt(et240.getText().toString())>10 ){
               total=total+Integer.parseInt(et240.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et240.getText().toString())<10){
               et240.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et259.getText().toString().isEmpty()){
           if(Integer.parseInt(et259.getText().toString())!=0 && Integer.parseInt(et259.getText().toString())>10 ){
               total=total+Integer.parseInt(et259.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et259.getText().toString())<10){
               et259.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et268.getText().toString().isEmpty()){
           if(Integer.parseInt(et268.getText().toString())!=0 && Integer.parseInt(et268.getText().toString())>10 ){
               total=total+Integer.parseInt(et268.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et268.getText().toString())<10){
               et268.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et349.getText().toString().isEmpty()){
           if(Integer.parseInt(et349.getText().toString())!=0 && Integer.parseInt(et349.getText().toString())>10 ){
               total=total+Integer.parseInt(et349.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et349.getText().toString())<10){
               et349.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et358.getText().toString().isEmpty()){
           if(Integer.parseInt(et358.getText().toString())!=0 && Integer.parseInt(et358.getText().toString())>10 ){
               total=total+Integer.parseInt(et358.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et358.getText().toString())<10){
               et358.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et457.getText().toString().isEmpty()){
           if(Integer.parseInt(et457.getText().toString())!=0 && Integer.parseInt(et457.getText().toString())>10 ){
               total=total+Integer.parseInt(et457.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et457.getText().toString())<10){
               et457.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et367.getText().toString().isEmpty()){
           if(Integer.parseInt(et367.getText().toString())!=0 && Integer.parseInt(et367.getText().toString())>10 ){
               total=total+Integer.parseInt(et367.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et367.getText().toString())<10){
               et367.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et790.getText().toString().isEmpty()){
           if(Integer.parseInt(et790.getText().toString())!=0 && Integer.parseInt(et790.getText().toString())>10 ){
               total=total+Integer.parseInt(et790.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et790.getText().toString())<10){
               et790.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       //// 07

       if(!et124.getText().toString().isEmpty()){
           if(Integer.parseInt(et124.getText().toString())!=0 && Integer.parseInt(et124.getText().toString())>10 ){
               total=total+Integer.parseInt(et124.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et124.getText().toString())<10){
               et124.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et160.getText().toString().isEmpty()){
           if(Integer.parseInt(et160.getText().toString())!=0 && Integer.parseInt(et160.getText().toString())>10 ){
               total=total+Integer.parseInt(et160.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et160.getText().toString())<10){
               et160.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et179.getText().toString().isEmpty()){
           if(Integer.parseInt(et179.getText().toString())!=0 && Integer.parseInt(et179.getText().toString())>10 ){
               total=total+Integer.parseInt(et179.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et179.getText().toString())<10){
               et179.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et250.getText().toString().isEmpty()){
           if(Integer.parseInt(et250.getText().toString())!=0 && Integer.parseInt(et250.getText().toString())>10 ){
               total=total+Integer.parseInt(et250.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et250.getText().toString())<10){
               et250.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et269.getText().toString().isEmpty()){
           if(Integer.parseInt(et269.getText().toString())!=0 && Integer.parseInt(et269.getText().toString())>10 ){
               total=total+Integer.parseInt(et269.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et269.getText().toString())<10){
               et269.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et278.getText().toString().isEmpty()){
           if(Integer.parseInt(et278.getText().toString())!=0 && Integer.parseInt(et278.getText().toString())>10 ){
               total=total+Integer.parseInt(et278.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et278.getText().toString())<10){
               et278.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et340.getText().toString().isEmpty()){
           if(Integer.parseInt(et340.getText().toString())!=0 && Integer.parseInt(et340.getText().toString())>10 ){
               total=total+Integer.parseInt(et340.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et340.getText().toString())<10){
               et340.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et359.getText().toString().isEmpty()){
           if(Integer.parseInt(et359.getText().toString())!=0 && Integer.parseInt(et359.getText().toString())>10 ){
               total=total+Integer.parseInt(et359.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et359.getText().toString())<10){
               et359.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et368.getText().toString().isEmpty()){
           if(Integer.parseInt(et368.getText().toString())!=0 && Integer.parseInt(et368.getText().toString())>10 ){
               total=total+Integer.parseInt(et368.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et368.getText().toString())<10){
               et368.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et458.getText().toString().isEmpty()){
           if(Integer.parseInt(et458.getText().toString())!=0 && Integer.parseInt(et458.getText().toString())>10 ){
               total=total+Integer.parseInt(et458.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et458.getText().toString())<10){
               et458.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et467.getText().toString().isEmpty()){
           if(Integer.parseInt(et467.getText().toString())!=0 && Integer.parseInt(et467.getText().toString())>10 ){
               total=total+Integer.parseInt(et467.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et467.getText().toString())<10){
               et467.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et890.getText().toString().isEmpty()){
           if(Integer.parseInt(et890.getText().toString())!=0 && Integer.parseInt(et890.getText().toString())>10 ){
               total=total+Integer.parseInt(et890.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et890.getText().toString())<10){
               et890.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       //// 08


       if(!et125.getText().toString().isEmpty()){
           if(Integer.parseInt(et125.getText().toString())!=0 && Integer.parseInt(et125.getText().toString())>10 ){
               total=total+Integer.parseInt(et125.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et125.getText().toString())<10){
               et125.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et134.getText().toString().isEmpty()){
           if(Integer.parseInt(et134.getText().toString())!=0 && Integer.parseInt(et134.getText().toString())>10 ){
               total=total+Integer.parseInt(et134.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et134.getText().toString())<10){
               et134.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et170.getText().toString().isEmpty()){
           if(Integer.parseInt(et170.getText().toString())!=0 && Integer.parseInt(et170.getText().toString())>10 ){
               total=total+Integer.parseInt(et170.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et170.getText().toString())<10){
               et170.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et189.getText().toString().isEmpty()){
           if(Integer.parseInt(et189.getText().toString())!=0 && Integer.parseInt(et189.getText().toString())>10 ){
               total=total+Integer.parseInt(et189.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et189.getText().toString())<10){
               et189.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et260.getText().toString().isEmpty()){
           if(Integer.parseInt(et260.getText().toString())!=0 && Integer.parseInt(et260.getText().toString())>10 ){
               total=total+Integer.parseInt(et260.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et260.getText().toString())<10){
               et260.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et279.getText().toString().isEmpty()){
           if(Integer.parseInt(et279.getText().toString())!=0 && Integer.parseInt(et279.getText().toString())>10 ){
               total=total+Integer.parseInt(et279.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et279.getText().toString())<10){
               et279.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et350.getText().toString().isEmpty()){
           if(Integer.parseInt(et350.getText().toString())!=0 && Integer.parseInt(et350.getText().toString())>10 ){
               total=total+Integer.parseInt(et350.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et350.getText().toString())<10){
               et350.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et369.getText().toString().isEmpty()){
           if(Integer.parseInt(et369.getText().toString())!=0 && Integer.parseInt(et369.getText().toString())>10 ){
               total=total+Integer.parseInt(et369.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et369.getText().toString())<10){
               et369.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et378.getText().toString().isEmpty()){
           if(Integer.parseInt(et378.getText().toString())!=0 && Integer.parseInt(et378.getText().toString())>10 ){
               total=total+Integer.parseInt(et378.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et378.getText().toString())<10){
               et378.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et459.getText().toString().isEmpty()){
           if(Integer.parseInt(et459.getText().toString())!=0 && Integer.parseInt(et459.getText().toString())>10 ){
               total=total+Integer.parseInt(et459.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et459.getText().toString())<10){
               et459.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et567.getText().toString().isEmpty()){
           if(Integer.parseInt(et567.getText().toString())!=0 && Integer.parseInt(et567.getText().toString())>10 ){
               total=total+Integer.parseInt(et567.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et567.getText().toString())<10){
               et567.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et468.getText().toString().isEmpty()){
           if(Integer.parseInt(et468.getText().toString())!=0 && Integer.parseInt(et468.getText().toString())>10 ){
               total=total+Integer.parseInt(et468.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et468.getText().toString())<10){
               et468.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       //// 09

       if(!et126.getText().toString().isEmpty()){
           if(Integer.parseInt(et126.getText().toString())!=0 && Integer.parseInt(et126.getText().toString())>10 ){
               total=total+Integer.parseInt(et126.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et126.getText().toString())<10){
               et126.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et135.getText().toString().isEmpty()){
           if(Integer.parseInt(et135.getText().toString())!=0 && Integer.parseInt(et135.getText().toString())>10 ){
               total=total+Integer.parseInt(et135.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et135.getText().toString())<10){
               et135.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et180.getText().toString().isEmpty()){
           if(Integer.parseInt(et180.getText().toString())!=0 && Integer.parseInt(et180.getText().toString())>10 ){
               total=total+Integer.parseInt(et180.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et180.getText().toString())<10){
               et180.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et234.getText().toString().isEmpty()){
           if(Integer.parseInt(et234.getText().toString())!=0 && Integer.parseInt(et234.getText().toString())>10 ){
               total=total+Integer.parseInt(et234.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et234.getText().toString())<10){
               et234.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et270.getText().toString().isEmpty()){
           if(Integer.parseInt(et270.getText().toString())!=0 && Integer.parseInt(et270.getText().toString())>10 ){
               total=total+Integer.parseInt(et270.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et270.getText().toString())<10){
               et270.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et289.getText().toString().isEmpty()){
           if(Integer.parseInt(et289.getText().toString())!=0 && Integer.parseInt(et289.getText().toString())>10 ){
               total=total+Integer.parseInt(et289.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et289.getText().toString())<10){
               et289.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et360.getText().toString().isEmpty()){
           if(Integer.parseInt(et360.getText().toString())!=0 && Integer.parseInt(et360.getText().toString())>10 ){
               total=total+Integer.parseInt(et360.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et360.getText().toString())<10){
               et360.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et379.getText().toString().isEmpty()){
           if(Integer.parseInt(et379.getText().toString())!=0 && Integer.parseInt(et379.getText().toString())>10 ){
               total=total+Integer.parseInt(et379.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et379.getText().toString())<10){
               et379.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et450.getText().toString().isEmpty()){
           if(Integer.parseInt(et450.getText().toString())!=0 && Integer.parseInt(et450.getText().toString())>10 ){
               total=total+Integer.parseInt(et450.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et450.getText().toString())<10){
               et450.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et469.getText().toString().isEmpty()){
           if(Integer.parseInt(et469.getText().toString())!=0 && Integer.parseInt(et469.getText().toString())>10 ){
               total=total+Integer.parseInt(et469.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et469.getText().toString())<10){
               et469.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et478.getText().toString().isEmpty()){
           if(Integer.parseInt(et478.getText().toString())!=0 && Integer.parseInt(et478.getText().toString())>10 ){
               total=total+Integer.parseInt(et478.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et478.getText().toString())<10){
               et478.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et568.getText().toString().isEmpty()){
           if(Integer.parseInt(et568.getText().toString())!=0 && Integer.parseInt(et568.getText().toString())>10 ){
               total=total+Integer.parseInt(et568.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et568.getText().toString())<10){
               et568.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       ///// 10

       if(!et127.getText().toString().isEmpty()){
           if(Integer.parseInt(et127.getText().toString())!=0 && Integer.parseInt(et127.getText().toString())>10 ){
               total=total+Integer.parseInt(et127.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et127.getText().toString())<10){
               et127.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et136.getText().toString().isEmpty()){
           if(Integer.parseInt(et136.getText().toString())!=0 && Integer.parseInt(et136.getText().toString())>10 ){
               total=total+Integer.parseInt(et136.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et136.getText().toString())<10){
               et136.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et145.getText().toString().isEmpty()){
           if(Integer.parseInt(et145.getText().toString())!=0 && Integer.parseInt(et145.getText().toString())>10 ){
               total=total+Integer.parseInt(et145.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et145.getText().toString())<10){
               et145.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et190.getText().toString().isEmpty()){
           if(Integer.parseInt(et190.getText().toString())!=0 && Integer.parseInt(et190.getText().toString())>10 ){
               total=total+Integer.parseInt(et190.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et190.getText().toString())<10){
               et190.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et235.getText().toString().isEmpty()){
           if(Integer.parseInt(et235.getText().toString())!=0 && Integer.parseInt(et235.getText().toString())>10 ){
               total=total+Integer.parseInt(et235.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et235.getText().toString())<10){
               et235.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et280.getText().toString().isEmpty()){
           if(Integer.parseInt(et280.getText().toString())!=0 && Integer.parseInt(et280.getText().toString())>10 ){
               total=total+Integer.parseInt(et280.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et280.getText().toString())<10){
               et280.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et370.getText().toString().isEmpty()){
           if(Integer.parseInt(et370.getText().toString())!=0 && Integer.parseInt(et370.getText().toString())>10 ){
               total=total+Integer.parseInt(et370.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et370.getText().toString())<10){
               et370.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et479.getText().toString().isEmpty()){
           if(Integer.parseInt(et479.getText().toString())!=0 && Integer.parseInt(et479.getText().toString())>10 ){
               total=total+Integer.parseInt(et479.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et479.getText().toString())<10){
               et479.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et460.getText().toString().isEmpty()){
           if(Integer.parseInt(et460.getText().toString())!=0 && Integer.parseInt(et460.getText().toString())>10 ){
               total=total+Integer.parseInt(et460.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et460.getText().toString())<10){
               et460.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et569.getText().toString().isEmpty()){
           if(Integer.parseInt(et569.getText().toString())!=0 && Integer.parseInt(et569.getText().toString())>10 ){
               total=total+Integer.parseInt(et569.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et569.getText().toString())<10){
               et569.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et389.getText().toString().isEmpty()){
           if(Integer.parseInt(et389.getText().toString())!=0 && Integer.parseInt(et389.getText().toString())>10 ){
               total=total+Integer.parseInt(et389.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et389.getText().toString())<10){
               et389.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }

       if(!et578.getText().toString().isEmpty()){
           if(Integer.parseInt(et578.getText().toString())!=0 && Integer.parseInt(et578.getText().toString())>10 ){
               total=total+Integer.parseInt(et578.getText().toString());
               isSubmitEnable=true;
               totalPoint.setText(String.valueOf(total));
               istotalCalculate=true;
           } else if(Integer.parseInt(et578.getText().toString())<10){
               et578.requestFocus();
               Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
               isSubmitEnable=false;
               istotalCalculate=false;
           }
       }


   }

}


/*
    private void comparingEdittext(){
        ///first
        et128.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et128.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et128.requestFocus();
                }else {
                    total=total+Integer.parseInt(et128.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et137.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et137.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et137.requestFocus();
                }else {
                    total=total+Integer.parseInt(et137.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et146.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et146.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et146.requestFocus();
                }else {
                    total=total+Integer.parseInt(et146.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et236.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et236.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et236.requestFocus();
                }else {
                    total=total+Integer.parseInt(et236.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et245.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et245.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et245.requestFocus();
                }else {
                    total=total+Integer.parseInt(et245.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et290.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et290.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et290.requestFocus();
                }else {
                    total=total+Integer.parseInt(et290.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et380.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et380.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et380.requestFocus();
                }else {
                    total=total+Integer.parseInt(et380.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et470.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et470.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et470.requestFocus();
                }else {
                    total=total+Integer.parseInt(et470.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et489.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et489.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et489.requestFocus();
                }else {
                    total=total+Integer.parseInt(et489.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et560.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et560.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et560.requestFocus();
                }else {
                    total=total+Integer.parseInt(et560.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et678.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et678.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et678.requestFocus();
                }else {
                    total=total+Integer.parseInt(et678.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et579.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et579.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et579.requestFocus();
                }else {
                    total=total+Integer.parseInt(et579.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Second

        et129.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et129.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et129.requestFocus();
                }else {
                    total=total+Integer.parseInt(et129.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et138.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et138.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et138.requestFocus();
                }else {
                    total=total+Integer.parseInt(et138.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et147.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et147.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et147.requestFocus();
                }else {
                    total=total+Integer.parseInt(et147.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et156.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et156.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et156.requestFocus();
                }else {
                    total=total+Integer.parseInt(et156.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et237.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et237.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et237.requestFocus();
                }else {
                    total=total+Integer.parseInt(et237.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et246.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et246.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et246.requestFocus();
                }else {
                    total=total+Integer.parseInt(et246.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et345.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et345.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et345.requestFocus();
                }else {
                    total=total+Integer.parseInt(et345.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et390.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et390.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et390.requestFocus();
                }else {
                    total=total+Integer.parseInt(et390.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et480.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et480.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et480.requestFocus();
                }else {
                    total=total+Integer.parseInt(et480.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et570.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et570.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et570.requestFocus();
                }else {
                    total=total+Integer.parseInt(et570.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et679.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et679.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et679.requestFocus();
                }else {
                    total=total+Integer.parseInt(et679.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et589.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et589.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et589.requestFocus();
                }else {
                    total=total+Integer.parseInt(et589.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et129.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et129.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et129.requestFocus();
                }else {
                    total=total+Integer.parseInt(et129.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et138.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et138.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et138.requestFocus();
                }else {
                    total=total+Integer.parseInt(et138.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et147.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et147.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et147.requestFocus();
                }else {
                    total=total+Integer.parseInt(et147.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et156.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et156.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et156.requestFocus();
                }else {
                    total=total+Integer.parseInt(et156.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et237.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et237.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et237.requestFocus();
                }else {
                    total=total+Integer.parseInt(et237.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et246.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et246.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et246.requestFocus();
                }else {
                    total=total+Integer.parseInt(et246.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et345.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et345.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et345.requestFocus();
                }else {
                    total=total+Integer.parseInt(et345.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et390.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et390.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et390.requestFocus();
                }else {
                    total=total+Integer.parseInt(et390.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et480.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et480.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et480.requestFocus();
                }else {
                    total=total+Integer.parseInt(et480.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et570.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et570.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et570.requestFocus();
                }else {
                    total=total+Integer.parseInt(et570.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et679.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et679.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et679.requestFocus();
                }else {
                    total=total+Integer.parseInt(et679.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et589.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et589.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et589.requestFocus();
                }else {
                    total=total+Integer.parseInt(et589.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////third

        et120.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et120.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et120.requestFocus();
                }else {
                    total=total+Integer.parseInt(et120.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et139.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et139.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et139.requestFocus();
                }else {
                    total=total+Integer.parseInt(et139.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et148.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et148.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et148.requestFocus();
                }else {
                    total=total+Integer.parseInt(et148.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et157.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et157.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et157.requestFocus();
                }else {
                    total=total+Integer.parseInt(et157.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et238.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et238.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et238.requestFocus();
                }else {
                    total=total+Integer.parseInt(et238.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et247.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et247.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et247.requestFocus();
                }else {
                    total=total+Integer.parseInt(et247.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et256.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et256.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et256.requestFocus();
                }else {
                    total=total+Integer.parseInt(et256.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et346.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et346.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et346.requestFocus();
                }else {
                    total=total+Integer.parseInt(et346.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et490.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et490.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et490.requestFocus();
                }else {
                    total=total+Integer.parseInt(et490.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et580.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et580.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et580.requestFocus();
                }else {
                    total=total+Integer.parseInt(et580.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et670.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et670.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et670.requestFocus();
                }else {
                    total=total+Integer.parseInt(et670.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et689.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et689.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et689.requestFocus();
                }else {
                    total=total+Integer.parseInt(et689.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////fourth

        et130.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et130.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et130.requestFocus();
                }else {
                    total=total+Integer.parseInt(et130.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et789.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et789.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et789.requestFocus();
                }else {
                    total=total+Integer.parseInt(et789.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et149.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et149.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et149.requestFocus();
                }else {
                    total=total+Integer.parseInt(et149.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et158.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et158.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et158.requestFocus();
                }else {
                    total=total+Integer.parseInt(et158.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et167.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et167.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et167.requestFocus();
                }else {
                    total=total+Integer.parseInt(et167.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et239.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et239.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et239.requestFocus();
                }else {
                    total=total+Integer.parseInt(et239.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et248.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et248.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et248.requestFocus();
                }else {
                    total=total+Integer.parseInt(et248.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et257.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et257.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et257.requestFocus();
                }else {
                    total=total+Integer.parseInt(et257.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et347.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et347.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et347.requestFocus();
                }else {
                    total=total+Integer.parseInt(et347.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et356.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et356.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et356.requestFocus();
                }else {
                    total=total+Integer.parseInt(et356.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et590.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et590.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et590.requestFocus();
                }else {
                    total=total+Integer.parseInt(et590.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et680.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et680.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et680.requestFocus();
                }else {
                    total=total+Integer.parseInt(et680.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ///fifth

        et140.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et140.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et140.requestFocus();
                }else {
                    total=total+Integer.parseInt(et140.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et159.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et159.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et159.requestFocus();
                }else {
                    total=total+Integer.parseInt(et159.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et168.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et168.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et168.requestFocus();
                }else {
                    total=total+Integer.parseInt(et168.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et230.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et230.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et230.requestFocus();
                }else {
                    total=total+Integer.parseInt(et230.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et249.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et249.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et249.requestFocus();
                }else {
                    total=total+Integer.parseInt(et249.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et258.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et258.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et258.requestFocus();
                }else {
                    total=total+Integer.parseInt(et258.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et267.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et267.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et267.requestFocus();
                }else {
                    total=total+Integer.parseInt(et267.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et348.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et348.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et348.requestFocus();
                }else {
                    total=total+Integer.parseInt(et348.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et357.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et357.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et357.requestFocus();
                }else {
                    total=total+Integer.parseInt(et357.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et456.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et456.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et456.requestFocus();
                }else {
                    total=total+Integer.parseInt(et456.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et690.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et690.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et690.requestFocus();
                }else {
                    total=total+Integer.parseInt(et690.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et780.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et780.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et780.requestFocus();
                }else {
                    total=total+Integer.parseInt(et780.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Sixth

        et123.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et123.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et123.requestFocus();
                }else {
                    total=total+Integer.parseInt(et123.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et150.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et150.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et150.requestFocus();
                }else {
                    total=total+Integer.parseInt(et150.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et169.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et169.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et169.requestFocus();
                }else {
                    total=total+Integer.parseInt(et169.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et178.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et178.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et178.requestFocus();
                }else {
                    total=total+Integer.parseInt(et178.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et240.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et240.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et240.requestFocus();
                }else {
                    total=total+Integer.parseInt(et240.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et259.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et259.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et259.requestFocus();
                }else {
                    total=total+Integer.parseInt(et259.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et268.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et268.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et268.requestFocus();
                }else {
                    total=total+Integer.parseInt(et268.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et349.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et349.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et349.requestFocus();
                }else {
                    total=total+Integer.parseInt(et349.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et358.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et358.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et358.requestFocus();
                }else {
                    total=total+Integer.parseInt(et358.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et457.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et457.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et457.requestFocus();
                }else {
                    total=total+Integer.parseInt(et457.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et367.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et367.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et367.requestFocus();
                }else {
                    total=total+Integer.parseInt(et367.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et790.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et790.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et790.requestFocus();
                }else {
                    total=total+Integer.parseInt(et790.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Seventh

        et124.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et124.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et124.requestFocus();
                }else {
                    total=total+Integer.parseInt(et124.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et160.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et160.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et160.requestFocus();
                }else {
                    total=total+Integer.parseInt(et160.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et179.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et179.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et179.requestFocus();
                }else {
                    total=total+Integer.parseInt(et179.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et250.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et250.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et250.requestFocus();
                }else {
                    total=total+Integer.parseInt(et250.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et269.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et269.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et269.requestFocus();
                }else {
                    total=total+Integer.parseInt(et269.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et278.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et259.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et259.requestFocus();
                }else {
                    total=total+Integer.parseInt(et259.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et340.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et340.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et340.requestFocus();
                }else {
                    total=total+Integer.parseInt(et340.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et359.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et359.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et359.requestFocus();
                }else {
                    total=total+Integer.parseInt(et359.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et368.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et368.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et368.requestFocus();
                }else {
                    total=total+Integer.parseInt(et368.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et458.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et458.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et458.requestFocus();
                }else {
                    total=total+Integer.parseInt(et458.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et467.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et467.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et467.requestFocus();
                }else {
                    total=total+Integer.parseInt(et467.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et890.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et890.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et890.requestFocus();
                }else {
                    total=total+Integer.parseInt(et890.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////Eight

        et125.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et125.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et125.requestFocus();
                }else {
                    total=total+Integer.parseInt(et125.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et134.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et134.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et134.requestFocus();
                }else {
                    total=total+Integer.parseInt(et134.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et170.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et170.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et170.requestFocus();
                }else {
                    total=total+Integer.parseInt(et170.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et189.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et189.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et189.requestFocus();
                }else {
                    total=total+Integer.parseInt(et189.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et260.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et260.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et260.requestFocus();
                }else {
                    total=total+Integer.parseInt(et260.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et279.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et279.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et279.requestFocus();
                }else {
                    total=total+Integer.parseInt(et279.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et350.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et350.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et350.requestFocus();
                }else {
                    total=total+Integer.parseInt(et350.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et369.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et369.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et369.requestFocus();
                }else {
                    total=total+Integer.parseInt(et369.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et378.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et378.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et378.requestFocus();
                }else {
                    total=total+Integer.parseInt(et378.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et459.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et459.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et459.requestFocus();
                }else {
                    total=total+Integer.parseInt(et459.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et567.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et567.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et567.requestFocus();
                }else {
                    total=total+Integer.parseInt(et567.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et468.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et468.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et468.requestFocus();
                }else {
                    total=total+Integer.parseInt(et468.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////ninth

        et126.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et126.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et126.requestFocus();
                }else {
                    total=total+Integer.parseInt(et126.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et135.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et135.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et135.requestFocus();
                }else {
                    total=total+Integer.parseInt(et135.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et180.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et180.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et180.requestFocus();
                }else {
                    total=total+Integer.parseInt(et180.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et234.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et234.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et234.requestFocus();
                }else {
                    total=total+Integer.parseInt(et234.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et270.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et270.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et270.requestFocus();
                }else {
                    total=total+Integer.parseInt(et270.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et289.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et289.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et289.requestFocus();
                }else {
                    total=total+Integer.parseInt(et289.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et360.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et360.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et360.requestFocus();
                }else {
                    total=total+Integer.parseInt(et360.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et379.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et379.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et379.requestFocus();
                }else {
                    total=total+Integer.parseInt(et379.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et450.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et450.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et450.requestFocus();
                }else {
                    total=total+Integer.parseInt(et450.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et469.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et469.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et469.requestFocus();
                }else {
                    total=total+Integer.parseInt(et469.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et478.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et478.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et478.requestFocus();
                }else {
                    total=total+Integer.parseInt(et478.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et568.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et568.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et568.requestFocus();
                }else {
                    total=total+Integer.parseInt(et568.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ///tenth
        et127.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et127.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et127.requestFocus();
                }else {
                    total=total+Integer.parseInt(et127.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et136.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et136.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et136.requestFocus();
                }else {
                    total=total+Integer.parseInt(et136.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et145.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et145.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et145.requestFocus();
                }else {
                    total=total+Integer.parseInt(et145.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et190.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et190.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et190.requestFocus();
                }else {
                    total=total+Integer.parseInt(et190.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et235.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et235.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et235.requestFocus();
                }else {
                    total=total+Integer.parseInt(et235.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et280.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et280.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et280.requestFocus();
                }else {
                    total=total+Integer.parseInt(et280.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et370.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et370.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et370.requestFocus();
                }else {
                    total=total+Integer.parseInt(et370.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et479.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et479.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et479.requestFocus();
                }else {
                    total=total+Integer.parseInt(et479.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et460.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et460.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et460.requestFocus();
                }else {
                    total=total+Integer.parseInt(et460.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et569.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et569.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et569.requestFocus();
                }else {
                    total=total+Integer.parseInt(et569.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et389.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et389.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et389.requestFocus();
                }else {
                    total=total+Integer.parseInt(et389.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et578.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et578.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et578.requestFocus();
                }else {
                    total=total+Integer.parseInt(et578.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////tenth




    }

 */

/*
 @Override
    public void sendTotal(String total, HashMap<String, String> hashMap) {
        totalPoint.setText(total);
        // editedAmount=amount;
        enteredAmount = hashMap;
    }
 */
