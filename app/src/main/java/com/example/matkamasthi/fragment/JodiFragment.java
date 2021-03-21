package com.example.matkamasthi.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.matkamasthi.adapter.DoubleDataRecylerAdapter;
import com.example.matkamasthi.adapter.JodiRecyclerAdapter;
import com.example.matkamasthi.adapter.JodiRecyclerViewAdapter;
import com.example.matkamasthi.adapter.SingleDataRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.model.DoubleDataRecyclerModel;
import com.example.matkamasthi.model.JodiAnotherModel;
import com.example.matkamasthi.model.SingleDataRecyclerModel;

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

public class JodiFragment extends Fragment{

    public JodiFragment() {
        // Required empty public constructor
    }

    private Spinner spinner;
    private TextView dateText;
    RecyclerView recyclerView;
    ArrayList<SingleDataRecyclerModel> recyclerModels;
    JodiRecyclerViewAdapter adapter;
    private Button submitBtn;

    boolean isSubmitEnable=false;
    String gameId;
    String currentDateandTime;
    String tokenVal;

    boolean isTimeLies=false;
    boolean istotalCalculate=true;

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> startTime=new ArrayList<>();
    ArrayList<String> endTime=new ArrayList<>();


    private TextView totalPoint;
    ArrayList<String> editedAmount=new ArrayList<String>();
    HashMap<String,String> enteredAmount=new HashMap<>();
    ProgressBar progressBar;

    TextView field00,field01,field02,field03,field04,field05,field06,field07,field08,field09;
    TextView field10,field11,field12,field13,field14,field15,field16,field17,field18,field19;
    TextView field20,field21,field22,field23,field24,field25,field26,field27,field28,field29;
    TextView field30,field31,field32,field33,field34,field35,field36,field37,field38,field39;
    TextView field40,field41,field42,field43,field44,field45,field46,field47,field48,field49;
    TextView field50,field51,field52,field53,field54,field55,field56,field57,field58,field59;
    TextView field60,field61,field62,field63,field64,field65,field66,field67,field68,field69;
    TextView field70,field71,field72,field73,field74,field75,field76,field77,field78,field79;
    TextView field80,field81,field82,field83,field84,field85,field86,field87,field88,field89;
    TextView field90,field91,field92,field93,field94,field95,field96,field97,field98,field99;

    EditText et00,et01,et02,et03,et04,et05,et06,et07,et08,et09;
    EditText et10,et11,et12,et13,et14,et15,et16,et17,et18,et19;
    EditText et20,et21,et22,et23,et24,et25,et26,et27,et28,et29;
    EditText et30,et31,et32,et33,et34,et35,et36,et37,et38,et39;
    EditText et40,et41,et42,et43,et44,et45,et46,et47,et48,et49;
    EditText et50,et51,et52,et53,et54,et55,et56,et57,et58,et59;
    EditText et60,et61,et62,et63,et64,et65,et66,et67,et68,et69;
    EditText et70,et71,et72,et73,et74,et75,et76,et77,et78,et79;
    EditText et80,et81,et82,et83,et84,et85,et86,et87,et88,et89;
    EditText et90,et91,et92,et93,et94,et95,et96,et97,et98,et99;

    String one_00,one_01,one_02,one_03,one_04,one_05,one_06,one_07,one_08,one_09;
    String two_10,two_11,two_12,two_13,two_14,two_15,two_16,two_17,two_18,two_19;
    String three_20,three_21,three_22,three_23,three_24,three_25,three_26,three_27,three_28,three_29;
    String four_30,four_31,four_32,four_33,four_34,four_35,four_36,four_37,four_38,four_39;
    String five_40,five_41,five_42,five_43,five_44,five_45,five_46,five_47,five_48,five_49;
    String six_50,six_51,six_52,six_53,six_54,six_55,six_56,six_57,six_58,six_59;
    String seven_60,seven_61,seven_62,seven_63,seven_64,seven_65,seven_66,seven_67,seven_68,seven_69;
    String eight_70,eight_71,eight_72,eight_73,eight_74,eight_75,eight_76,eight_77,eight_78,eight_79;
    String nine_80,nine_81,nine_82,nine_83,nine_84,nine_85,nine_86,nine_87,nine_88,nine_89;
    String ten_90,ten_91,ten_92,ten_93,ten_94,ten_95,ten_96,ten_97,ten_98,ten_99;

    int total=0;
    int count=0;
    Button jodi_total_point_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jodi, container, false);

        dateText=view.findViewById(R.id.jodi_date_spinner);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        dateText.setText(currentDateandTime);
        progressBar=view.findViewById(R.id.market_name_jodi_progressbar);
        initialize(view);
        initializeEditText(view);
        gettingJodimarketName(view);
       // compareEdittext();

        return view;
    }

    private void initialize(View view){
        totalPoint=view.findViewById(R.id.jodi_total_point);
        jodi_total_point_text=view.findViewById(R.id.jodi_total_point_text);
        submitBtn=view.findViewById(R.id.jodi_submit_btn);
        recyclerView = view.findViewById(R.id.jodi_data_recycler_view);
        recyclerModels=populateList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new JodiRecyclerViewAdapter(getContext(),recyclerModels);
        recyclerView.setAdapter(adapter);

        jodi_total_point_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareEdittext();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //compareEdittext();
                if (count==0) {
                    initializeString();
                    submitData(v);
                }
                else {
                    Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void gettingJodimarketName(final View view){
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest objectRequest=new JsonArrayRequest(Request.Method.GET, Constant.marketNameJodiGameUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    JSONArray arry=response.getJSONArray(1);
                    //JSONObject object=arry.getJSONObject(0);

                    //JSONArray categoryArry=arry.getJSONArray("categories");
                    for (int i=0;i<arry.length();i++){
                        JSONObject data=arry.getJSONObject(i);
                        String game_name=data.getString("jodi_game_name");
                        String game_timing=data.getString("jodi_game_starttime");
                        String game_end_time=data.getString("jodi_game_endtime");
                        String game_id_value=data.getString("jodi_games_id");
//                        String game_result_time=data.getString("game_result_time");
//                        String type=data.getString("type");
                            gameId=game_id_value;
                        name.add(game_name);
                        startTime.add(game_timing);
                        endTime.add(game_end_time);

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
        for(int i=0;i<100;i++){
            SingleDataRecyclerModel model=new SingleDataRecyclerModel();
            if(i<10){
                model.setEditedValueText("");
                model.setNumberText("0"+String.valueOf(i));
            }else {
                model.setEditedValueText("");
                model.setNumberText(String.valueOf(i));
            }

            list.add(model);
        }
        return list;
    }

    private void submitData(View view) {
        if(total==0){
            Toast.makeText(getContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("edited List",enteredAmount.toString());
            submitNumber(view);
        }
    }

    private void submitNumber(final View view){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.submitJodiGame, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("jodi");
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
                params.put("num001",one_00);
                params.put("num011",one_01);
                params.put("num021",one_02);
                params.put("num031",one_03);
                params.put("num041",one_04);
                params.put("num051",one_05);
                params.put("num061",one_06);
                params.put("num071",one_07);
                params.put("num081",one_08);
                params.put("num091",one_09);
                params.put("num101",two_10);
                params.put("num111",two_11);
                params.put("num121",two_12);
                params.put("num131",two_13);
                params.put("num141",two_14);
                params.put("num151",two_15);
                params.put("num161",two_16);
                params.put("num171",two_17);
                params.put("num181",two_18);
                params.put("num191",two_19);
                params.put("num201",three_20);
                params.put("num211",three_21);
                params.put("num221",three_22);
                params.put("num231",three_23);
                params.put("num241",three_24);
                params.put("num251",three_25);
                params.put("num261",three_26);
                params.put("num271",three_27);
                params.put("num281",three_28);
                params.put("num291",three_29);
                params.put("num301",four_30);
                params.put("num311",four_31);
                params.put("num321",four_32);
                params.put("num331",four_33);
                params.put("num341",four_34);
                params.put("num351",four_35);
                params.put("num361",four_36);
                params.put("num371",four_37);
                params.put("num381",four_38);
                params.put("num391",four_39);
                params.put("num401",five_40);
                params.put("num411",five_41);
                params.put("num421",five_42);
                params.put("num431",five_43);
                params.put("num441",five_44);
                params.put("num451",five_45);
                params.put("num461",five_46);
                params.put("num471",five_47);
                params.put("num481",five_48);
                params.put("num491",five_49);
                params.put("num501",six_50);
                params.put("num511",six_51);
                params.put("num521",six_52);
                params.put("num531",six_53);
                params.put("num541",six_54);
                params.put("num551",six_55);
                params.put("num561",six_56);
                params.put("num571",six_57);
                params.put("num581",six_58);
                params.put("num591",six_59);
                params.put("num601",seven_60);
                params.put("num611",seven_61);
                params.put("num621",seven_62);
                params.put("num631",seven_63);
                params.put("num641",seven_64);
                params.put("num651",seven_65);
                params.put("num661",seven_66);
                params.put("num671",seven_67);
                params.put("num681",seven_68);
                params.put("num691",seven_69);
                params.put("num701",eight_70);
                params.put("num711",eight_71);
                params.put("num721",eight_72);
                params.put("num731",eight_73);
                params.put("num741",eight_74);
                params.put("num751",eight_75);
                params.put("num761",eight_76);
                params.put("num771",eight_77);
                params.put("num781",eight_78);
                params.put("num791",eight_79);
                params.put("num801",nine_80);
                params.put("num811",nine_81);
                params.put("num821",nine_82);
                params.put("num831",nine_83);
                params.put("num841",nine_84);
                params.put("num851",nine_85);
                params.put("num861",nine_86);
                params.put("num871",nine_87);
                params.put("num881",nine_88);
                params.put("num891",nine_89);
                params.put("num901",ten_90);
                params.put("num911",ten_91);
                params.put("num921",ten_92);
                params.put("num931",ten_93);
                params.put("num941",ten_94);
                params.put("num951",ten_95);
                params.put("num961",ten_96);
                params.put("num971",ten_97);
                params.put("num981",ten_98);
                params.put("num991",ten_99);

                params.put("field001",field00.getText().toString());
                params.put("field011",field01.getText().toString());
                params.put("field021",field02.getText().toString());
                params.put("field031",field03.getText().toString());
                params.put("field041",field04.getText().toString());
                params.put("field051",field05.getText().toString());
                params.put("field061",field06.getText().toString());
                params.put("field071",field07.getText().toString());
                params.put("field081",field08.getText().toString());
                params.put("field091",field09.getText().toString());
                params.put("field101",field10.getText().toString());
                params.put("field111",field11.getText().toString());
                params.put("field121",field12.getText().toString());
                params.put("field131",field13.getText().toString());
                params.put("field141",field14.getText().toString());
                params.put("field151",field15.getText().toString());
                params.put("field161",field16.getText().toString());
                params.put("field171",field17.getText().toString());
                params.put("field181",field18.getText().toString());
                params.put("field191",field19.getText().toString());
                params.put("field201",field20.getText().toString());
                params.put("field211",field21.getText().toString());
                params.put("field221",field22.getText().toString());
                params.put("field231",field23.getText().toString());
                params.put("field241",field24.getText().toString());
                params.put("field251",field25.getText().toString());
                params.put("field261",field26.getText().toString());
                params.put("field271",field27.getText().toString());
                params.put("field281",field28.getText().toString());
                params.put("field291",field29.getText().toString());
                params.put("field301",field30.getText().toString());
                params.put("field311",field31.getText().toString());
                params.put("field321",field32.getText().toString());
                params.put("field331",field33.getText().toString());
                params.put("field341",field34.getText().toString());
                params.put("field351",field35.getText().toString());
                params.put("field361",field36.getText().toString());
                params.put("field371",field37.getText().toString());
                params.put("field381",field38.getText().toString());
                params.put("field391",field39.getText().toString());
                params.put("field401",field40.getText().toString());
                params.put("field411",field41.getText().toString());
                params.put("field421",field42.getText().toString());
                params.put("field431",field43.getText().toString());
                params.put("field441",field44.getText().toString());
                params.put("field451",field45.getText().toString());
                params.put("field461",field46.getText().toString());
                params.put("field471",field47.getText().toString());
                params.put("field481",field48.getText().toString());
                params.put("field491",field49.getText().toString());
                params.put("field501",field50.getText().toString());
                params.put("field511",field51.getText().toString());
                params.put("field521",field52.getText().toString());
                params.put("field531",field53.getText().toString());
                params.put("field541",field54.getText().toString());
                params.put("field551",field55.getText().toString());
                params.put("field561",field56.getText().toString());
                params.put("field571",field57.getText().toString());
                params.put("field581",field58.getText().toString());
                params.put("field591",field59.getText().toString());
                params.put("field601",field60.getText().toString());
                params.put("field611",field61.getText().toString());
                params.put("field621",field62.getText().toString());
                params.put("field631",field63.getText().toString());
                params.put("field641",field64.getText().toString());
                params.put("field651",field65.getText().toString());
                params.put("field661",field66.getText().toString());
                params.put("field671",field67.getText().toString());
                params.put("field681",field68.getText().toString());
                params.put("field691",field69.getText().toString());
                params.put("field701",field70.getText().toString());
                params.put("field711",field71.getText().toString());
                params.put("field721",field72.getText().toString());
                params.put("field731",field73.getText().toString());
                params.put("field741",field74.getText().toString());
                params.put("field751",field75.getText().toString());
                params.put("field761",field76.getText().toString());
                params.put("field771",field77.getText().toString());
                params.put("field781",field78.getText().toString());
                params.put("field791",field79.getText().toString());
                params.put("field801",field80.getText().toString());
                params.put("field811",field81.getText().toString());
                params.put("field821",field82.getText().toString());
                params.put("field831",field83.getText().toString());
                params.put("field841",field84.getText().toString());
                params.put("field851",field85.getText().toString());
                params.put("field861",field86.getText().toString());
                params.put("field871",field87.getText().toString());
                params.put("field881",field88.getText().toString());
                params.put("field891",field89.getText().toString());
                params.put("field901",field90.getText().toString());
                params.put("field911",field91.getText().toString());
                params.put("field921",field92.getText().toString());
                params.put("field931",field93.getText().toString());
                params.put("field941",field94.getText().toString());
                params.put("field951",field95.getText().toString());
                params.put("field961",field96.getText().toString());
                params.put("field971",field97.getText().toString());
                params.put("field981",field98.getText().toString());
                params.put("field991",field99.getText().toString());

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
            }
        });
    }

    private void initializeSpinner(View view){

        ///Setting Array
        ArrayList<String> category=new ArrayList<>();
        for(int i=0;i<name.size();i++){
            category.add(name.get(i) +" "+ startTime.get(i) +"-"+ endTime.get(i));
        }
        // final List<String> categoryList = new ArrayList<>(Arrays.asList(category));

        ///Setting Array

        spinner=view.findViewById(R.id.jodi_market_spinner);
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
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        progressBar.setVisibility(View.GONE);
    }



    private void initializeEditText(View view){

        et00=view.findViewById(R.id.frag_jodi_data_edittext_00);
        et01=view.findViewById(R.id.frag_jodi_data_edittext_01);
        et02=view.findViewById(R.id.frag_jodi_data_edittext_02);
        et03=view.findViewById(R.id.frag_jodi_data_edittext_03);
        et04=view.findViewById(R.id.frag_jodi_data_edittext_04);
        et05=view.findViewById(R.id.frag_jodi_data_edittext_05);
        et06=view.findViewById(R.id.frag_jodi_data_edittext_06);
        et07=view.findViewById(R.id.frag_jodi_data_edittext_07);
        et08=view.findViewById(R.id.frag_jodi_data_edittext_08);
        et09=view.findViewById(R.id.frag_jodi_data_edittext_09);
        et10=view.findViewById(R.id.frag_jodi_data_edittext_10);
        et11=view.findViewById(R.id.frag_jodi_data_edittext_11);
        et12=view.findViewById(R.id.frag_jodi_data_edittext_12);
        et13=view.findViewById(R.id.frag_jodi_data_edittext_13);
        et14=view.findViewById(R.id.frag_jodi_data_edittext_14);
        et15=view.findViewById(R.id.frag_jodi_data_edittext_15);
        et16=view.findViewById(R.id.frag_jodi_data_edittext_16);
        et17=view.findViewById(R.id.frag_jodi_data_edittext_17);
        et18=view.findViewById(R.id.frag_jodi_data_edittext_18);
        et19=view.findViewById(R.id.frag_jodi_data_edittext_19);
        et20=view.findViewById(R.id.frag_jodi_data_edittext_20);
        et21=view.findViewById(R.id.frag_jodi_data_edittext_21);
        et22=view.findViewById(R.id.frag_jodi_data_edittext_22);
        et23=view.findViewById(R.id.frag_jodi_data_edittext_23);
        et24=view.findViewById(R.id.frag_jodi_data_edittext_24);
        et25=view.findViewById(R.id.frag_jodi_data_edittext_25);
        et26=view.findViewById(R.id.frag_jodi_data_edittext_26);
        et27=view.findViewById(R.id.frag_jodi_data_edittext_27);
        et28=view.findViewById(R.id.frag_jodi_data_edittext_28);
        et29=view.findViewById(R.id.frag_jodi_data_edittext_29);
        et30=view.findViewById(R.id.frag_jodi_data_edittext_30);
        et31=view.findViewById(R.id.frag_jodi_data_edittext_31);
        et32=view.findViewById(R.id.frag_jodi_data_edittext_32);
        et33=view.findViewById(R.id.frag_jodi_data_edittext_33);
        et34=view.findViewById(R.id.frag_jodi_data_edittext_34);
        et35=view.findViewById(R.id.frag_jodi_data_edittext_35);
        et36=view.findViewById(R.id.frag_jodi_data_edittext_36);
        et37=view.findViewById(R.id.frag_jodi_data_edittext_37);
        et38=view.findViewById(R.id.frag_jodi_data_edittext_38);
        et39=view.findViewById(R.id.frag_jodi_data_edittext_39);
        et40=view.findViewById(R.id.frag_jodi_data_edittext_40);
        et41=view.findViewById(R.id.frag_jodi_data_edittext_41);
        et42=view.findViewById(R.id.frag_jodi_data_edittext_42);
        et43=view.findViewById(R.id.frag_jodi_data_edittext_43);
        et44=view.findViewById(R.id.frag_jodi_data_edittext_44);
        et45=view.findViewById(R.id.frag_jodi_data_edittext_45);
        et46=view.findViewById(R.id.frag_jodi_data_edittext_46);
        et47=view.findViewById(R.id.frag_jodi_data_edittext_47);
        et48=view.findViewById(R.id.frag_jodi_data_edittext_48);
        et49=view.findViewById(R.id.frag_jodi_data_edittext_49);
        et50=view.findViewById(R.id.frag_jodi_data_edittext_50);
        et51=view.findViewById(R.id.frag_jodi_data_edittext_51);
        et52=view.findViewById(R.id.frag_jodi_data_edittext_52);
        et53=view.findViewById(R.id.frag_jodi_data_edittext_53);
        et54=view.findViewById(R.id.frag_jodi_data_edittext_54);
        et55=view.findViewById(R.id.frag_jodi_data_edittext_55);
        et56=view.findViewById(R.id.frag_jodi_data_edittext_56);
        et57=view.findViewById(R.id.frag_jodi_data_edittext_57);
        et58=view.findViewById(R.id.frag_jodi_data_edittext_58);
        et59=view.findViewById(R.id.frag_jodi_data_edittext_59);
        et60=view.findViewById(R.id.frag_jodi_data_edittext_60);
        et61=view.findViewById(R.id.frag_jodi_data_edittext_61);
        et62=view.findViewById(R.id.frag_jodi_data_edittext_62);
        et63=view.findViewById(R.id.frag_jodi_data_edittext_63);
        et64=view.findViewById(R.id.frag_jodi_data_edittext_64);
        et65=view.findViewById(R.id.frag_jodi_data_edittext_65);
        et66=view.findViewById(R.id.frag_jodi_data_edittext_66);
        et67=view.findViewById(R.id.frag_jodi_data_edittext_67);
        et68=view.findViewById(R.id.frag_jodi_data_edittext_68);
        et69=view.findViewById(R.id.frag_jodi_data_edittext_69);
        et70=view.findViewById(R.id.frag_jodi_data_edittext_70);
        et71=view.findViewById(R.id.frag_jodi_data_edittext_71);
        et72=view.findViewById(R.id.frag_jodi_data_edittext_72);
        et73=view.findViewById(R.id.frag_jodi_data_edittext_73);
        et74=view.findViewById(R.id.frag_jodi_data_edittext_74);
        et75=view.findViewById(R.id.frag_jodi_data_edittext_75);
        et76=view.findViewById(R.id.frag_jodi_data_edittext_76);
        et77=view.findViewById(R.id.frag_jodi_data_edittext_77);
        et78=view.findViewById(R.id.frag_jodi_data_edittext_78);
        et79=view.findViewById(R.id.frag_jodi_data_edittext_79);
        et80=view.findViewById(R.id.frag_jodi_data_edittext_80);
        et81=view.findViewById(R.id.frag_jodi_data_edittext_81);
        et82=view.findViewById(R.id.frag_jodi_data_edittext_82);
        et83=view.findViewById(R.id.frag_jodi_data_edittext_83);
        et84=view.findViewById(R.id.frag_jodi_data_edittext_84);
        et85=view.findViewById(R.id.frag_jodi_data_edittext_85);
        et86=view.findViewById(R.id.frag_jodi_data_edittext_86);
        et87=view.findViewById(R.id.frag_jodi_data_edittext_87);
        et88=view.findViewById(R.id.frag_jodi_data_edittext_88);
        et89=view.findViewById(R.id.frag_jodi_data_edittext_89);
        et90=view.findViewById(R.id.frag_jodi_data_edittext_90);
        et91=view.findViewById(R.id.frag_jodi_data_edittext_91);
        et92=view.findViewById(R.id.frag_jodi_data_edittext_92);
        et93=view.findViewById(R.id.frag_jodi_data_edittext_93);
        et94=view.findViewById(R.id.frag_jodi_data_edittext_94);
        et95=view.findViewById(R.id.frag_jodi_data_edittext_95);
        et96=view.findViewById(R.id.frag_jodi_data_edittext_96);
        et97=view.findViewById(R.id.frag_jodi_data_edittext_97);
        et98=view.findViewById(R.id.frag_jodi_data_edittext_98);
        et99=view.findViewById(R.id.frag_jodi_data_edittext_99);


        field00=view.findViewById(R.id.frag_jodi_text_number_00);
        field01=view.findViewById(R.id.frag_jodi_text_number_01);
        field02=view.findViewById(R.id.frag_jodi_text_number_02);
        field03=view.findViewById(R.id.frag_jodi_text_number_03);
        field04=view.findViewById(R.id.frag_jodi_text_number_04);
        field05=view.findViewById(R.id.frag_jodi_text_number_05);
        field06=view.findViewById(R.id.frag_jodi_text_number_06);
        field07=view.findViewById(R.id.frag_jodi_text_number_07);
        field08=view.findViewById(R.id.frag_jodi_text_number_08);
        field09=view.findViewById(R.id.frag_jodi_text_number_09);
        field10=view.findViewById(R.id.frag_jodi_text_number_10);
        field11=view.findViewById(R.id.frag_jodi_text_number_11);
        field12=view.findViewById(R.id.frag_jodi_text_number_12);
        field13=view.findViewById(R.id.frag_jodi_text_number_13);
        field14=view.findViewById(R.id.frag_jodi_text_number_14);
        field15=view.findViewById(R.id.frag_jodi_text_number_15);
        field16=view.findViewById(R.id.frag_jodi_text_number_16);
        field17=view.findViewById(R.id.frag_jodi_text_number_17);
        field18=view.findViewById(R.id.frag_jodi_text_number_18);
        field19=view.findViewById(R.id.frag_jodi_text_number_19);
        field20=view.findViewById(R.id.frag_jodi_text_number_20);
        field21=view.findViewById(R.id.frag_jodi_text_number_21);
        field22=view.findViewById(R.id.frag_jodi_text_number_22);
        field23=view.findViewById(R.id.frag_jodi_text_number_23);
        field24=view.findViewById(R.id.frag_jodi_text_number_24);
        field25=view.findViewById(R.id.frag_jodi_text_number_25);
        field26=view.findViewById(R.id.frag_jodi_text_number_26);
        field27=view.findViewById(R.id.frag_jodi_text_number_27);
        field28=view.findViewById(R.id.frag_jodi_text_number_28);
        field29=view.findViewById(R.id.frag_jodi_text_number_29);
        field30=view.findViewById(R.id.frag_jodi_text_number_30);
        field31=view.findViewById(R.id.frag_jodi_text_number_31);
        field32=view.findViewById(R.id.frag_jodi_text_number_32);
        field33=view.findViewById(R.id.frag_jodi_text_number_33);
        field34=view.findViewById(R.id.frag_jodi_text_number_34);
        field35=view.findViewById(R.id.frag_jodi_text_number_35);
        field36=view.findViewById(R.id.frag_jodi_text_number_36);
        field37=view.findViewById(R.id.frag_jodi_text_number_37);
        field38=view.findViewById(R.id.frag_jodi_text_number_38);
        field39=view.findViewById(R.id.frag_jodi_text_number_39);
        field40=view.findViewById(R.id.frag_jodi_text_number_40);
        field41=view.findViewById(R.id.frag_jodi_text_number_41);
        field42=view.findViewById(R.id.frag_jodi_text_number_42);
        field43=view.findViewById(R.id.frag_jodi_text_number_43);
        field44=view.findViewById(R.id.frag_jodi_text_number_44);
        field45=view.findViewById(R.id.frag_jodi_text_number_45);
        field46=view.findViewById(R.id.frag_jodi_text_number_46);
        field47=view.findViewById(R.id.frag_jodi_text_number_47);
        field48=view.findViewById(R.id.frag_jodi_text_number_48);
        field49=view.findViewById(R.id.frag_jodi_text_number_49);
        field50=view.findViewById(R.id.frag_jodi_text_number_50);
        field51=view.findViewById(R.id.frag_jodi_text_number_51);
        field52=view.findViewById(R.id.frag_jodi_text_number_52);
        field53=view.findViewById(R.id.frag_jodi_text_number_53);
        field54=view.findViewById(R.id.frag_jodi_text_number_54);
        field55=view.findViewById(R.id.frag_jodi_text_number_55);
        field56=view.findViewById(R.id.frag_jodi_text_number_56);
        field57=view.findViewById(R.id.frag_jodi_text_number_57);
        field58=view.findViewById(R.id.frag_jodi_text_number_58);
        field59=view.findViewById(R.id.frag_jodi_text_number_59);
        field60=view.findViewById(R.id.frag_jodi_text_number_60);
        field61=view.findViewById(R.id.frag_jodi_text_number_61);
        field62=view.findViewById(R.id.frag_jodi_text_number_62);
        field63=view.findViewById(R.id.frag_jodi_text_number_63);
        field64=view.findViewById(R.id.frag_jodi_text_number_64);
        field65=view.findViewById(R.id.frag_jodi_text_number_65);
        field66=view.findViewById(R.id.frag_jodi_text_number_66);
        field67=view.findViewById(R.id.frag_jodi_text_number_67);
        field68=view.findViewById(R.id.frag_jodi_text_number_68);
        field69=view.findViewById(R.id.frag_jodi_text_number_69);
        field70=view.findViewById(R.id.frag_jodi_text_number_70);
        field71=view.findViewById(R.id.frag_jodi_text_number_71);
        field72=view.findViewById(R.id.frag_jodi_text_number_72);
        field73=view.findViewById(R.id.frag_jodi_text_number_73);
        field74=view.findViewById(R.id.frag_jodi_text_number_74);
        field75=view.findViewById(R.id.frag_jodi_text_number_75);
        field76=view.findViewById(R.id.frag_jodi_text_number_76);
        field77=view.findViewById(R.id.frag_jodi_text_number_77);
        field78=view.findViewById(R.id.frag_jodi_text_number_78);
        field79=view.findViewById(R.id.frag_jodi_text_number_79);
        field80=view.findViewById(R.id.frag_jodi_text_number_80);
        field81=view.findViewById(R.id.frag_jodi_text_number_81);
        field82=view.findViewById(R.id.frag_jodi_text_number_82);
        field83=view.findViewById(R.id.frag_jodi_text_number_83);
        field84=view.findViewById(R.id.frag_jodi_text_number_84);
        field85=view.findViewById(R.id.frag_jodi_text_number_85);
        field86=view.findViewById(R.id.frag_jodi_text_number_86);
        field87=view.findViewById(R.id.frag_jodi_text_number_87);
        field88=view.findViewById(R.id.frag_jodi_text_number_88);
        field89=view.findViewById(R.id.frag_jodi_text_number_89);
        field90=view.findViewById(R.id.frag_jodi_text_number_90);
        field91=view.findViewById(R.id.frag_jodi_text_number_91);
        field92=view.findViewById(R.id.frag_jodi_text_number_92);
        field93=view.findViewById(R.id.frag_jodi_text_number_93);
        field94=view.findViewById(R.id.frag_jodi_text_number_94);
        field95=view.findViewById(R.id.frag_jodi_text_number_95);
        field96=view.findViewById(R.id.frag_jodi_text_number_96);
        field97=view.findViewById(R.id.frag_jodi_text_number_97);
        field98=view.findViewById(R.id.frag_jodi_text_number_98);
        field99=view.findViewById(R.id.frag_jodi_text_number_99);

    }

    private void initializeString(){
        one_00=et00.getText().toString().trim();
        one_01=et01.getText().toString().trim();
        one_02=et02.getText().toString().trim();
        one_03=et03.getText().toString().trim();
        one_04=et04.getText().toString().trim();
        one_05=et05.getText().toString().trim();
        one_06=et06.getText().toString().trim();
        one_07=et07.getText().toString().trim();
        one_08=et08.getText().toString().trim();
        one_09=et09.getText().toString().trim();
        two_10=et10.getText().toString().trim();
        two_11=et11.getText().toString().trim();
        two_12=et12.getText().toString().trim();
        two_13=et13.getText().toString().trim();
        two_14=et14.getText().toString().trim();
        two_15=et15.getText().toString().trim();
        two_16=et16.getText().toString().trim();
        two_17=et17.getText().toString().trim();
        two_18=et18.getText().toString().trim();
        two_19=et19.getText().toString().trim();
        three_20=et20.getText().toString().trim();
        three_21=et21.getText().toString().trim();
        three_22=et22.getText().toString().trim();
        three_23=et23.getText().toString().trim();
        three_24=et24.getText().toString().trim();
        three_25=et25.getText().toString().trim();
        three_26=et26.getText().toString().trim();
        three_27=et27.getText().toString().trim();
        three_28=et28.getText().toString().trim();
        three_29=et29.getText().toString().trim();
        four_30=et30.getText().toString().trim();
        four_31=et31.getText().toString().trim();
        four_32=et32.getText().toString().trim();
        four_33=et33.getText().toString().trim();
        four_34=et34.getText().toString().trim();
        four_35=et35.getText().toString().trim();
        four_36=et36.getText().toString().trim();
        four_37=et37.getText().toString().trim();
        four_38=et38.getText().toString().trim();
        four_39=et39.getText().toString().trim();
        five_40=et40.getText().toString().trim();
        five_41=et41.getText().toString().trim();
        five_42=et42.getText().toString().trim();
        five_43=et43.getText().toString().trim();
        five_44=et44.getText().toString().trim();
        five_45=et45.getText().toString().trim();
        five_46=et46.getText().toString().trim();
        five_47=et47.getText().toString().trim();
        five_48=et48.getText().toString().trim();
        five_49=et49.getText().toString().trim();
        six_50=et50.getText().toString().trim();
        six_51=et51.getText().toString().trim();
        six_52=et52.getText().toString().trim();
        six_53=et53.getText().toString().trim();
        six_54=et54.getText().toString().trim();
        six_55=et55.getText().toString().trim();
        six_56=et56.getText().toString().trim();
        six_57=et57.getText().toString().trim();
        six_58=et58.getText().toString().trim();
        six_59=et59.getText().toString().trim();
        seven_60=et60.getText().toString().trim();
        seven_61=et61.getText().toString().trim();
        seven_62=et62.getText().toString().trim();
        seven_63=et63.getText().toString().trim();
        seven_64=et64.getText().toString().trim();
        seven_65=et65.getText().toString().trim();
        seven_66=et66.getText().toString().trim();
        seven_67=et67.getText().toString().trim();
        seven_68=et68.getText().toString().trim();
        seven_69=et69.getText().toString().trim();
        eight_70=et70.getText().toString().trim();
        eight_71=et71.getText().toString().trim();
        eight_72=et72.getText().toString().trim();
        eight_73=et73.getText().toString().trim();
        eight_74=et74.getText().toString().trim();
        eight_75=et75.getText().toString().trim();
        eight_76=et76.getText().toString().trim();
        eight_77=et77.getText().toString().trim();
        eight_78=et78.getText().toString().trim();
        eight_79=et79.getText().toString().trim();
        nine_80=et80.getText().toString().trim();
        nine_81=et81.getText().toString().trim();
        nine_82=et82.getText().toString().trim();
        nine_83=et83.getText().toString().trim();
        nine_84=et84.getText().toString().trim();
        nine_85=et85.getText().toString().trim();
        nine_86=et86.getText().toString().trim();
        nine_87=et87.getText().toString().trim();
        nine_88=et88.getText().toString().trim();
        nine_89=et89.getText().toString().trim();
        ten_90=et90.getText().toString().trim();
        ten_91=et91.getText().toString().trim();
        ten_92=et92.getText().toString().trim();
        ten_93=et93.getText().toString().trim();
        ten_94=et94.getText().toString().trim();
        ten_95=et95.getText().toString().trim();
        ten_96=et96.getText().toString().trim();
        ten_97=et97.getText().toString().trim();
        ten_98=et98.getText().toString().trim();
        ten_99=et99.getText().toString().trim();
    }

    private void compareEdittext(){

        ////00 - 09
        total=0;
        count=0;
        if(!et00.getText().toString().isEmpty()){
            if(Integer.parseInt(et00.getText().toString())!=0 && Integer.parseInt(et00.getText().toString())>10 ){
                total=total+Integer.parseInt(et00.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(Integer.parseInt(et00.getText().toString())<10){
            et00.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
            count++;
        }

        if(!et01.getText().toString().isEmpty()){
            if(Integer.parseInt(et01.getText().toString())!=0 && Integer.parseInt(et01.getText().toString())>10 ){
                total=total+Integer.parseInt(et01.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(Integer.parseInt(et01.getText().toString())<10){
            et01.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et02.getText().toString().isEmpty()){
            if(Integer.parseInt(et02.getText().toString())!=0 && Integer.parseInt(et02.getText().toString())>10 ){
                total=total+Integer.parseInt(et02.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(Integer.parseInt(et02.getText().toString())<10){
            et02.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et03.getText().toString().isEmpty()){
            if(Integer.parseInt(et03.getText().toString())!=0 && Integer.parseInt(et03.getText().toString())>10 ){
                total=total+Integer.parseInt(et03.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(Integer.parseInt(et03.getText().toString())<10){
            et03.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et04.getText().toString().isEmpty()){
            if(Integer.parseInt(et04.getText().toString())!=0 && Integer.parseInt(et00.getText().toString())>10 ){
                total=total+Integer.parseInt(et04.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(Integer.parseInt(et04.getText().toString())<10){
            et04.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et05.getText().toString().isEmpty()){
            if(Integer.parseInt(et05.getText().toString())!=0 && Integer.parseInt(et05.getText().toString())>10 ){
                total=total+Integer.parseInt(et05.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(Integer.parseInt(et05.getText().toString())<10){
            et05.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et06.getText().toString().isEmpty()){
            if(Integer.parseInt(et06.getText().toString())!=0 && Integer.parseInt(et06.getText().toString())>10 ){
                total=total+Integer.parseInt(et06.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(Integer.parseInt(et06.getText().toString())<10){
            et06.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et07.getText().toString().isEmpty()){
            if(Integer.parseInt(et07.getText().toString())!=0 && Integer.parseInt(et07.getText().toString())>10 ){
                total=total+Integer.parseInt(et07.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(Integer.parseInt(et07.getText().toString())<10){
            et07.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et08.getText().toString().isEmpty()){
            if(Integer.parseInt(et08.getText().toString())!=0 && Integer.parseInt(et08.getText().toString())>10 ){
                total=total+Integer.parseInt(et08.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        }else if(Integer.parseInt(et08.getText().toString())<10){
            et08.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        if(!et09.getText().toString().isEmpty()){
            if(Integer.parseInt(et09.getText().toString())!=0 && Integer.parseInt(et09.getText().toString())>10 ){
                total=total+Integer.parseInt(et09.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            }
        } else if(Integer.parseInt(et09.getText().toString())<10){
            et09.requestFocus();
            Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
            isSubmitEnable=false;
            istotalCalculate=false;
        }

        //// 10 -19
        if(!et10.getText().toString().isEmpty()){
            if(Integer.parseInt(et10.getText().toString())!=0 && Integer.parseInt(et10.getText().toString())>10 ){
                total=total+Integer.parseInt(et10.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et10.getText().toString())<10){
                et10.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et11.getText().toString().isEmpty()){
            if(Integer.parseInt(et11.getText().toString())!=0 && Integer.parseInt(et11.getText().toString())>10 ){
                total=total+Integer.parseInt(et11.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et11.getText().toString())<10){
                et11.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et12.getText().toString().isEmpty()){
            if(Integer.parseInt(et12.getText().toString())!=0 && Integer.parseInt(et12.getText().toString())>10 ){
                total=total+Integer.parseInt(et12.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et12.getText().toString())<10){
                et12.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et13.getText().toString().isEmpty()){
            if(Integer.parseInt(et13.getText().toString())!=0 && Integer.parseInt(et13.getText().toString())>10 ){
                total=total+Integer.parseInt(et13.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et13.getText().toString())<10){
                et13.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et14.getText().toString().isEmpty()){
            if(Integer.parseInt(et14.getText().toString())!=0 && Integer.parseInt(et14.getText().toString())>10 ){
                total=total+Integer.parseInt(et14.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et14.getText().toString())<10){
                et14.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et15.getText().toString().isEmpty()){
            if(Integer.parseInt(et15.getText().toString())!=0 && Integer.parseInt(et15.getText().toString())>10 ){
                total=total+Integer.parseInt(et15.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et15.getText().toString())<10){
                et15.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et16.getText().toString().isEmpty()){
            if(Integer.parseInt(et16.getText().toString())!=0 && Integer.parseInt(et16.getText().toString())>10 ){
                total=total+Integer.parseInt(et16.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et16.getText().toString())<10){
                et16.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et17.getText().toString().isEmpty()){
            if(Integer.parseInt(et17.getText().toString())!=0 && Integer.parseInt(et07.getText().toString())>10 ){
                total=total+Integer.parseInt(et17.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et17.getText().toString())<10){
                et17.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et18.getText().toString().isEmpty()){
            if(Integer.parseInt(et18.getText().toString())!=0 && Integer.parseInt(et18.getText().toString())>10 ){
                total=total+Integer.parseInt(et18.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et18.getText().toString())<10){
                et18.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et19.getText().toString().isEmpty()){
            if(Integer.parseInt(et19.getText().toString())!=0 && Integer.parseInt(et19.getText().toString())>10 ){
                total=total+Integer.parseInt(et19.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et19.getText().toString())<10){
                et19.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 20 -29

        if(!et20.getText().toString().isEmpty()){
            if(Integer.parseInt(et20.getText().toString())!=0 && Integer.parseInt(et20.getText().toString())>10 ){
                total=total+Integer.parseInt(et20.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et20.getText().toString())<10){
                et20.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et21.getText().toString().isEmpty()){
            if(Integer.parseInt(et21.getText().toString())!=0 && Integer.parseInt(et21.getText().toString())>10 ){
                total=total+Integer.parseInt(et21.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et21.getText().toString())<10){
                et21.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et22.getText().toString().isEmpty()){
            if(Integer.parseInt(et22.getText().toString())!=0 && Integer.parseInt(et22.getText().toString())>10 ){
                total=total+Integer.parseInt(et22.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et22.getText().toString())<10){
                et22.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et23.getText().toString().isEmpty()){
            if(Integer.parseInt(et23.getText().toString())!=0 && Integer.parseInt(et23.getText().toString())>10 ){
                total=total+Integer.parseInt(et23.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et23.getText().toString())<10){
                et23.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et24.getText().toString().isEmpty()){
            if(Integer.parseInt(et24.getText().toString())!=0 && Integer.parseInt(et24.getText().toString())>10 ){
                total=total+Integer.parseInt(et24.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et24.getText().toString())<10){
                et24.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et25.getText().toString().isEmpty()){
            if(Integer.parseInt(et25.getText().toString())!=0 && Integer.parseInt(et25.getText().toString())>10 ){
                total=total+Integer.parseInt(et25.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et25.getText().toString())<10){
                et25.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et26.getText().toString().isEmpty()){
            if(Integer.parseInt(et26.getText().toString())!=0 && Integer.parseInt(et26.getText().toString())>10 ){
                total=total+Integer.parseInt(et26.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et26.getText().toString())<10){
                et26.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et27.getText().toString().isEmpty()){
            if(Integer.parseInt(et27.getText().toString())!=0 && Integer.parseInt(et27.getText().toString())>10 ){
                total=total+Integer.parseInt(et27.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et27.getText().toString())<10){
                et27.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et28.getText().toString().isEmpty()){
            if(Integer.parseInt(et28.getText().toString())!=0 && Integer.parseInt(et28.getText().toString())>10 ){
                total=total+Integer.parseInt(et28.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et28.getText().toString())<10){
                et28.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et29.getText().toString().isEmpty()){
            if(Integer.parseInt(et29.getText().toString())!=0 && Integer.parseInt(et29.getText().toString())>10 ){
                total=total+Integer.parseInt(et29.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et29.getText().toString())<10){
                et29.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 30 - 39


        if(!et30.getText().toString().isEmpty()){
            if(Integer.parseInt(et30.getText().toString())!=0 && Integer.parseInt(et30.getText().toString())>10 ){
                total=total+Integer.parseInt(et30.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et30.getText().toString())<10){
                et30.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et31.getText().toString().isEmpty()){
            if(Integer.parseInt(et31.getText().toString())!=0 && Integer.parseInt(et31.getText().toString())>10 ){
                total=total+Integer.parseInt(et31.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et31.getText().toString())<10){
                et31.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et32.getText().toString().isEmpty()){
            if(Integer.parseInt(et32.getText().toString())!=0 && Integer.parseInt(et32.getText().toString())>10 ){
                total=total+Integer.parseInt(et32.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et32.getText().toString())<10){
                et32.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et33.getText().toString().isEmpty()){
            if(Integer.parseInt(et33.getText().toString())!=0 && Integer.parseInt(et33.getText().toString())>10 ){
                total=total+Integer.parseInt(et33.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et33.getText().toString())<10){
                et33.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et34.getText().toString().isEmpty()){
            if(Integer.parseInt(et34.getText().toString())!=0 && Integer.parseInt(et34.getText().toString())>10 ){
                total=total+Integer.parseInt(et34.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et34.getText().toString())<10){
                et34.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et35.getText().toString().isEmpty()){
            if(Integer.parseInt(et35.getText().toString())!=0 && Integer.parseInt(et35.getText().toString())>10 ){
                total=total+Integer.parseInt(et35.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et35.getText().toString())<10){
                et35.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et36.getText().toString().isEmpty()){
            if(Integer.parseInt(et36.getText().toString())!=0 && Integer.parseInt(et36.getText().toString())>10 ){
                total=total+Integer.parseInt(et36.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et36.getText().toString())<10){
                et36.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et37.getText().toString().isEmpty()){
            if(Integer.parseInt(et37.getText().toString())!=0 && Integer.parseInt(et37.getText().toString())>10 ){
                total=total+Integer.parseInt(et37.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et37.getText().toString())<10){
                et37.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et38.getText().toString().isEmpty()){
            if(Integer.parseInt(et38.getText().toString())!=0 && Integer.parseInt(et38.getText().toString())>10 ){
                total=total+Integer.parseInt(et38.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et38.getText().toString())<10){
                et38.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et39.getText().toString().isEmpty()){
            if(Integer.parseInt(et39.getText().toString())!=0 && Integer.parseInt(et39.getText().toString())>10 ){
                total=total+Integer.parseInt(et39.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et39.getText().toString())<10){
                et39.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 40 - 49

        if(!et40.getText().toString().isEmpty()){
            if(Integer.parseInt(et40.getText().toString())!=0 && Integer.parseInt(et40.getText().toString())>10 ){
                total=total+Integer.parseInt(et40.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et40.getText().toString())<10){
                et40.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et41.getText().toString().isEmpty()){
            if(Integer.parseInt(et41.getText().toString())!=0 && Integer.parseInt(et41.getText().toString())>10 ){
                total=total+Integer.parseInt(et41.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et41.getText().toString())<10){
                et41.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et42.getText().toString().isEmpty()){
            if(Integer.parseInt(et42.getText().toString())!=0 && Integer.parseInt(et42.getText().toString())>10 ){
                total=total+Integer.parseInt(et42.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et42.getText().toString())<10){
                et42.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et43.getText().toString().isEmpty()){
            if(Integer.parseInt(et43.getText().toString())!=0 && Integer.parseInt(et43.getText().toString())>10 ){
                total=total+Integer.parseInt(et43.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et43.getText().toString())<10){
                et43.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et44.getText().toString().isEmpty()){
            if(Integer.parseInt(et44.getText().toString())!=0 && Integer.parseInt(et44.getText().toString())>10 ){
                total=total+Integer.parseInt(et44.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et44.getText().toString())<10){
                et44.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et45.getText().toString().isEmpty()){
            if(Integer.parseInt(et45.getText().toString())!=0 && Integer.parseInt(et45.getText().toString())>10 ){
                total=total+Integer.parseInt(et45.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et45.getText().toString())<10){
                et45.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et46.getText().toString().isEmpty()){
            if(Integer.parseInt(et46.getText().toString())!=0 && Integer.parseInt(et46.getText().toString())>10 ){
                total=total+Integer.parseInt(et46.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et46.getText().toString())<10){
                et46.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et47.getText().toString().isEmpty()){
            if(Integer.parseInt(et47.getText().toString())!=0 && Integer.parseInt(et47.getText().toString())>10 ){
                total=total+Integer.parseInt(et47.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et47.getText().toString())<10){
                et47.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et48.getText().toString().isEmpty()){
            if(Integer.parseInt(et48.getText().toString())!=0 && Integer.parseInt(et48.getText().toString())>10 ){
                total=total+Integer.parseInt(et48.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et48.getText().toString())<10){
                et48.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et49.getText().toString().isEmpty()){
            if(Integer.parseInt(et49.getText().toString())!=0 && Integer.parseInt(et49.getText().toString())>10 ){
                total=total+Integer.parseInt(et49.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et49.getText().toString())<10){
                et49.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        ////50 -59


        if(!et50.getText().toString().isEmpty()){
            if(Integer.parseInt(et50.getText().toString())!=0 && Integer.parseInt(et50.getText().toString())>10 ){
                total=total+Integer.parseInt(et50.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et50.getText().toString())<10){
                et50.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et51.getText().toString().isEmpty()){
            if(Integer.parseInt(et51.getText().toString())!=0 && Integer.parseInt(et51.getText().toString())>10 ){
                total=total+Integer.parseInt(et51.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et51.getText().toString())<10){
                et51.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et52.getText().toString().isEmpty()){
            if(Integer.parseInt(et52.getText().toString())!=0 && Integer.parseInt(et52.getText().toString())>10 ){
                total=total+Integer.parseInt(et52.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et52.getText().toString())<10){
                et52.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et53.getText().toString().isEmpty()){
            if(Integer.parseInt(et53.getText().toString())!=0 && Integer.parseInt(et53.getText().toString())>10 ){
                total=total+Integer.parseInt(et53.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et53.getText().toString())<10){
                et53.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et54.getText().toString().isEmpty()){
            if(Integer.parseInt(et54.getText().toString())!=0 && Integer.parseInt(et54.getText().toString())>10 ){
                total=total+Integer.parseInt(et54.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et54.getText().toString())<10){
                et54.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et55.getText().toString().isEmpty()){
            if(Integer.parseInt(et55.getText().toString())!=0 && Integer.parseInt(et55.getText().toString())>10 ){
                total=total+Integer.parseInt(et55.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et55.getText().toString())<10){
                et55.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et56.getText().toString().isEmpty()){
            if(Integer.parseInt(et56.getText().toString())!=0 && Integer.parseInt(et56.getText().toString())>10 ){
                total=total+Integer.parseInt(et56.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et56.getText().toString())<10){
                et56.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et57.getText().toString().isEmpty()){
            if(Integer.parseInt(et57.getText().toString())!=0 && Integer.parseInt(et57.getText().toString())>10 ){
                total=total+Integer.parseInt(et57.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et57.getText().toString())<10){
                et57.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et58.getText().toString().isEmpty()){
            if(Integer.parseInt(et58.getText().toString())!=0 && Integer.parseInt(et58.getText().toString())>10 ){
                total=total+Integer.parseInt(et58.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et58.getText().toString())<10){
                et58.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et59.getText().toString().isEmpty()){
            if(Integer.parseInt(et59.getText().toString())!=0 && Integer.parseInt(et59.getText().toString())>10 ){
                total=total+Integer.parseInt(et59.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et59.getText().toString())<10){
                et59.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 60 - 69

        if(!et60.getText().toString().isEmpty()){
            if(Integer.parseInt(et60.getText().toString())!=0 && Integer.parseInt(et60.getText().toString())>10 ){
                total=total+Integer.parseInt(et60.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et60.getText().toString())<10){
                et60.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et61.getText().toString().isEmpty()){
            if(Integer.parseInt(et61.getText().toString())!=0 && Integer.parseInt(et61.getText().toString())>10 ){
                total=total+Integer.parseInt(et61.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et61.getText().toString())<10){
                et61.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et62.getText().toString().isEmpty()){
            if(Integer.parseInt(et62.getText().toString())!=0 && Integer.parseInt(et62.getText().toString())>10 ){
                total=total+Integer.parseInt(et62.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et62.getText().toString())<10){
                et62.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et63.getText().toString().isEmpty()){
            if(Integer.parseInt(et63.getText().toString())!=0 && Integer.parseInt(et63.getText().toString())>10 ){
                total=total+Integer.parseInt(et63.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et63.getText().toString())<10){
                et63.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et64.getText().toString().isEmpty()){
            if(Integer.parseInt(et64.getText().toString())!=0 && Integer.parseInt(et64.getText().toString())>10 ){
                total=total+Integer.parseInt(et64.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et64.getText().toString())<10){
                et64.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et65.getText().toString().isEmpty()){
            if(Integer.parseInt(et65.getText().toString())!=0 && Integer.parseInt(et65.getText().toString())>10 ){
                total=total+Integer.parseInt(et65.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et65.getText().toString())<10){
                et65.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et66.getText().toString().isEmpty()){
            if(Integer.parseInt(et66.getText().toString())!=0 && Integer.parseInt(et66.getText().toString())>10 ){
                total=total+Integer.parseInt(et66.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et66.getText().toString())<10){
                et66.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et67.getText().toString().isEmpty()){
            if(Integer.parseInt(et67.getText().toString())!=0 && Integer.parseInt(et67.getText().toString())>10 ){
                total=total+Integer.parseInt(et67.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et67.getText().toString())<10){
                et67.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et68.getText().toString().isEmpty()){
            if(Integer.parseInt(et68.getText().toString())!=0 && Integer.parseInt(et68.getText().toString())>10 ){
                total=total+Integer.parseInt(et68.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et68.getText().toString())<10){
                et68.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et69.getText().toString().isEmpty()){
            if(Integer.parseInt(et69.getText().toString())!=0 && Integer.parseInt(et69.getText().toString())>10 ){
                total=total+Integer.parseInt(et69.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et69.getText().toString())<10){
                et69.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 70 -79

        if(!et70.getText().toString().isEmpty()){
            if(Integer.parseInt(et70.getText().toString())!=0 && Integer.parseInt(et70.getText().toString())>10 ){
                total=total+Integer.parseInt(et70.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et70.getText().toString())<10){
                et70.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et71.getText().toString().isEmpty()){
            if(Integer.parseInt(et71.getText().toString())!=0 && Integer.parseInt(et71.getText().toString())>10 ){
                total=total+Integer.parseInt(et71.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et71.getText().toString())<10){
                et71.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et72.getText().toString().isEmpty()){
            if(Integer.parseInt(et72.getText().toString())!=0 && Integer.parseInt(et72.getText().toString())>10 ){
                total=total+Integer.parseInt(et72.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et72.getText().toString())<10){
                et72.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et73.getText().toString().isEmpty()){
            if(Integer.parseInt(et73.getText().toString())!=0 && Integer.parseInt(et73.getText().toString())>10 ){
                total=total+Integer.parseInt(et73.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et73.getText().toString())<10){
                et73.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et74.getText().toString().isEmpty()){
            if(Integer.parseInt(et74.getText().toString())!=0 && Integer.parseInt(et74.getText().toString())>10 ){
                total=total+Integer.parseInt(et74.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et74.getText().toString())<10){
                et74.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et75.getText().toString().isEmpty()){
            if(Integer.parseInt(et75.getText().toString())!=0 && Integer.parseInt(et75.getText().toString())>10 ){
                total=total+Integer.parseInt(et75.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et75.getText().toString())<10){
                et75.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et76.getText().toString().isEmpty()){
            if(Integer.parseInt(et76.getText().toString())!=0 && Integer.parseInt(et76.getText().toString())>10 ){
                total=total+Integer.parseInt(et76.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et76.getText().toString())<10){
                et76.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et77.getText().toString().isEmpty()){
            if(Integer.parseInt(et77.getText().toString())!=0 && Integer.parseInt(et77.getText().toString())>10 ){
                total=total+Integer.parseInt(et77.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et77.getText().toString())<10){
                et77.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et78.getText().toString().isEmpty()){
            if(Integer.parseInt(et78.getText().toString())!=0 && Integer.parseInt(et78.getText().toString())>10 ){
                total=total+Integer.parseInt(et78.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et78.getText().toString())<10){
                et78.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et79.getText().toString().isEmpty()){
            if(Integer.parseInt(et79.getText().toString())!=0 && Integer.parseInt(et79.getText().toString())>10 ){
                total=total+Integer.parseInt(et79.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et79.getText().toString())<10){
                et79.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 80 - 89

        if(!et80.getText().toString().isEmpty()){
            if(Integer.parseInt(et80.getText().toString())!=0 && Integer.parseInt(et80.getText().toString())>10 ){
                total=total+Integer.parseInt(et80.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et80.getText().toString())<10){
                et80.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et81.getText().toString().isEmpty()){
            if(Integer.parseInt(et81.getText().toString())!=0 && Integer.parseInt(et81.getText().toString())>10 ){
                total=total+Integer.parseInt(et81.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et81.getText().toString())<10){
                et81.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et82.getText().toString().isEmpty()){
            if(Integer.parseInt(et82.getText().toString())!=0 && Integer.parseInt(et82.getText().toString())>10 ){
                total=total+Integer.parseInt(et82.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et82.getText().toString())<10){
                et82.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et83.getText().toString().isEmpty()){
            if(Integer.parseInt(et83.getText().toString())!=0 && Integer.parseInt(et83.getText().toString())>10 ){
                total=total+Integer.parseInt(et83.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et83.getText().toString())<10){
                et83.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et84.getText().toString().isEmpty()){
            if(Integer.parseInt(et84.getText().toString())!=0 && Integer.parseInt(et84.getText().toString())>10 ){
                total=total+Integer.parseInt(et84.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et84.getText().toString())<10){
                et84.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et85.getText().toString().isEmpty()){
            if(Integer.parseInt(et85.getText().toString())!=0 && Integer.parseInt(et85.getText().toString())>10 ){
                total=total+Integer.parseInt(et85.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et85.getText().toString())<10){
                et85.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et86.getText().toString().isEmpty()){
            if(Integer.parseInt(et86.getText().toString())!=0 && Integer.parseInt(et86.getText().toString())>10 ){
                total=total+Integer.parseInt(et86.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et86.getText().toString())<10){
                et86.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et87.getText().toString().isEmpty()){
            if(Integer.parseInt(et87.getText().toString())!=0 && Integer.parseInt(et87.getText().toString())>10 ){
                total=total+Integer.parseInt(et87.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et87.getText().toString())<10){
                et87.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et88.getText().toString().isEmpty()){
            if(Integer.parseInt(et88.getText().toString())!=0 && Integer.parseInt(et88.getText().toString())>10 ){
                total=total+Integer.parseInt(et88.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et88.getText().toString())<10){
                et88.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et89.getText().toString().isEmpty()){
            if(Integer.parseInt(et89.getText().toString())!=0 && Integer.parseInt(et89.getText().toString())>10 ){
                total=total+Integer.parseInt(et89.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et89.getText().toString())<10){
                et89.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        //// 90 - 99

        if(!et90.getText().toString().isEmpty()){
            if(Integer.parseInt(et90.getText().toString())!=0 && Integer.parseInt(et90.getText().toString())>10 ){
                total=total+Integer.parseInt(et90.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et90.getText().toString())<10){
                et90.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et91.getText().toString().isEmpty()){
            if(Integer.parseInt(et91.getText().toString())!=0 && Integer.parseInt(et91.getText().toString())>10 ){
                total=total+Integer.parseInt(et91.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et91.getText().toString())<10){
                et91.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et92.getText().toString().isEmpty()){
            if(Integer.parseInt(et92.getText().toString())!=0 && Integer.parseInt(et92.getText().toString())>10 ){
                total=total+Integer.parseInt(et92.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et92.getText().toString())<10){
                et92.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et93.getText().toString().isEmpty()){
            if(Integer.parseInt(et93.getText().toString())!=0 && Integer.parseInt(et93.getText().toString())>10 ){
                total=total+Integer.parseInt(et93.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et93.getText().toString())<10){
                et93.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et94.getText().toString().isEmpty()){
            if(Integer.parseInt(et94.getText().toString())!=0 && Integer.parseInt(et94.getText().toString())>10 ){
                total=total+Integer.parseInt(et94.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et94.getText().toString())<10){
                et94.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et95.getText().toString().isEmpty()){
            if(Integer.parseInt(et95.getText().toString())!=0 && Integer.parseInt(et95.getText().toString())>10 ){
                total=total+Integer.parseInt(et95.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et95.getText().toString())<10){
                et95.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et96.getText().toString().isEmpty()){
            if(Integer.parseInt(et96.getText().toString())!=0 && Integer.parseInt(et96.getText().toString())>10 ){
                total=total+Integer.parseInt(et96.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et96.getText().toString())<10){
                et96.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et97.getText().toString().isEmpty()){
            if(Integer.parseInt(et97.getText().toString())!=0 && Integer.parseInt(et97.getText().toString())>10 ){
                total=total+Integer.parseInt(et97.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et97.getText().toString())<10){
                et97.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et98.getText().toString().isEmpty()){
            if(Integer.parseInt(et98.getText().toString())!=0 && Integer.parseInt(et98.getText().toString())>10 ){
                total=total+Integer.parseInt(et98.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et98.getText().toString())<10){
                et98.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }

        if(!et99.getText().toString().isEmpty()){
            if(Integer.parseInt(et99.getText().toString())!=0 && Integer.parseInt(et99.getText().toString())>10 ){
                total=total+Integer.parseInt(et99.getText().toString());
                isSubmitEnable=true;
                totalPoint.setText(String.valueOf(total));
                istotalCalculate=true;
            } else if(Integer.parseInt(et99.getText().toString())<10){
                et99.requestFocus();
                Toast.makeText(getContext(), "Minimum Amount is 10", Toast.LENGTH_SHORT).show();
                isSubmitEnable=false;
                istotalCalculate=false;
            }
        }


    }


}

/*
    private void compareEdittext(){

        ///00-09
        et00.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et00.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et00.requestFocus();
                }else {
                    total=total+Integer.parseInt(et00.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et01.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et01.requestFocus();
                }else {
                    total=total+Integer.parseInt(et01.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et02.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et02.requestFocus();
                }else {
                    total=total+Integer.parseInt(et02.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et03.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et03.requestFocus();
                }else {
                    total=total+Integer.parseInt(et03.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et04.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et04.requestFocus();
                }else {
                    total=total+Integer.parseInt(et04.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et05.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et05.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et05.requestFocus();
                }else {
                    total=total+Integer.parseInt(et05.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et06.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et06.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et06.requestFocus();
                }else {
                    total=total+Integer.parseInt(et06.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et07.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et07.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et07.requestFocus();
                }else {
                    total=total+Integer.parseInt(et07.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et08.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et08.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et08.requestFocus();
                }else {
                    total=total+Integer.parseInt(et08.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et09.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et09.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et09.requestFocus();
                }else {
                    total=total+Integer.parseInt(et09.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ///10-19

        et10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et10.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et10.requestFocus();
                }else {
                    total=total+Integer.parseInt(et10.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et11.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et11.requestFocus();
                }else {
                    total=total+Integer.parseInt(et11.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et12.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et12.requestFocus();
                }else {
                    total=total+Integer.parseInt(et12.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et13.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et13.requestFocus();
                }else {
                    total=total+Integer.parseInt(et13.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et14.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et14.requestFocus();
                }else {
                    total=total+Integer.parseInt(et14.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et15.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et15.requestFocus();
                }else {
                    total=total+Integer.parseInt(et15.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et16.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et16.requestFocus();
                }else {
                    total=total+Integer.parseInt(et16.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et17.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et17.requestFocus();
                }else {
                    total=total+Integer.parseInt(et17.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et18.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et18.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et18.requestFocus();
                }else {
                    total=total+Integer.parseInt(et18.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et19.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et19.requestFocus();
                }else {
                    total=total+Integer.parseInt(et19.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////20-29

        et20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et20.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et20.requestFocus();
                }else {
                    total=total+Integer.parseInt(et20.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et21.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et21.requestFocus();
                }else {
                    total=total+Integer.parseInt(et21.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et22.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et22.requestFocus();
                }else {
                    total=total+Integer.parseInt(et22.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et23.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et23.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et23.requestFocus();
                }else {
                    total=total+Integer.parseInt(et23.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et24.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et24.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et24.requestFocus();
                }else {
                    total=total+Integer.parseInt(et24.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et25.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et25.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et25.requestFocus();
                }else {
                    total=total+Integer.parseInt(et25.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et26.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et26.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et26.requestFocus();
                }else {
                    total=total+Integer.parseInt(et26.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et27.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et27.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et27.requestFocus();
                }else {
                    total=total+Integer.parseInt(et27.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et28.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et28.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et28.requestFocus();
                }else {
                    total=total+Integer.parseInt(et28.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et29.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et29.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et29.requestFocus();
                }else {
                    total=total+Integer.parseInt(et29.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////30-39

        et30.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et30.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et30.requestFocus();
                }else {
                    total=total+Integer.parseInt(et30.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et31.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et31.requestFocus();
                }else {
                    total=total+Integer.parseInt(et31.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et32.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et32.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et32.requestFocus();
                }else {
                    total=total+Integer.parseInt(et32.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et33.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et33.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et33.requestFocus();
                }else {
                    total=total+Integer.parseInt(et33.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et34.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et34.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et34.requestFocus();
                }else {
                    total=total+Integer.parseInt(et34.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et35.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et35.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et35.requestFocus();
                }else {
                    total=total+Integer.parseInt(et35.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et36.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et36.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et36.requestFocus();
                }else {
                    total=total+Integer.parseInt(et36.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et37.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et37.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et37.requestFocus();
                }else {
                    total=total+Integer.parseInt(et37.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et38.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et38.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et38.requestFocus();
                }else {
                    total=total+Integer.parseInt(et38.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et39.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et39.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et39.requestFocus();
                }else {
                    total=total+Integer.parseInt(et39.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////40 - 49

        et40.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et40.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et40.requestFocus();
                }else {
                    total=total+Integer.parseInt(et40.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et41.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et41.requestFocus();
                }else {
                    total=total+Integer.parseInt(et41.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et42.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et42.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et42.requestFocus();
                }else {
                    total=total+Integer.parseInt(et42.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et43.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et43.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et43.requestFocus();
                }else {
                    total=total+Integer.parseInt(et43.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et44.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et44.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et44.requestFocus();
                }else {
                    total=total+Integer.parseInt(et44.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et45.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et45.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et45.requestFocus();
                }else {
                    total=total+Integer.parseInt(et45.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et46.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et46.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et46.requestFocus();
                }else {
                    total=total+Integer.parseInt(et46.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et47.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et47.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et47.requestFocus();
                }else {
                    total=total+Integer.parseInt(et47.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et48.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et48.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et48.requestFocus();
                }else {
                    total=total+Integer.parseInt(et48.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et49.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et49.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et49.requestFocus();
                }else {
                    total=total+Integer.parseInt(et49.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        //// 50 - 59

        et50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et50.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et50.requestFocus();
                }else {
                    total=total+Integer.parseInt(et50.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et51.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et51.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et51.requestFocus();
                }else {
                    total=total+Integer.parseInt(et51.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et52.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et52.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et52.requestFocus();
                }else {
                    total=total+Integer.parseInt(et52.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et53.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et53.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et53.requestFocus();
                }else {
                    total=total+Integer.parseInt(et53.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et54.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et54.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et54.requestFocus();
                }else {
                    total=total+Integer.parseInt(et54.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et55.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et55.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et55.requestFocus();
                }else {
                    total=total+Integer.parseInt(et55.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et56.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et56.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et56.requestFocus();
                }else {
                    total=total+Integer.parseInt(et56.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et57.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et57.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et57.requestFocus();
                }else {
                    total=total+Integer.parseInt(et57.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et58.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et58.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et58.requestFocus();
                }else {
                    total=total+Integer.parseInt(et58.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et59.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et59.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et59.requestFocus();
                }else {
                    total=total+Integer.parseInt(et59.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        //// 60 - 69

        et60.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et60.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et60.requestFocus();
                }else {
                    total=total+Integer.parseInt(et60.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et61.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et61.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et61.requestFocus();
                }else {
                    total=total+Integer.parseInt(et61.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et62.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et62.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et62.requestFocus();
                }else {
                    total=total+Integer.parseInt(et62.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et63.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et63.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et63.requestFocus();
                }else {
                    total=total+Integer.parseInt(et63.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et64.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et64.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et64.requestFocus();
                }else {
                    total=total+Integer.parseInt(et64.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et65.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et65.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et65.requestFocus();
                }else {
                    total=total+Integer.parseInt(et65.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et66.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et66.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et66.requestFocus();
                }else {
                    total=total+Integer.parseInt(et66.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et67.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et67.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et67.requestFocus();
                }else {
                    total=total+Integer.parseInt(et67.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et68.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et68.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et68.requestFocus();
                }else {
                    total=total+Integer.parseInt(et68.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et69.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et69.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et69.requestFocus();
                }else {
                    total=total+Integer.parseInt(et69.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        ////70 - 79

        et70.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et70.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et70.requestFocus();
                }else {
                    total=total+Integer.parseInt(et70.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et71.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et71.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et71.requestFocus();
                }else {
                    total=total+Integer.parseInt(et71.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et72.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et72.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et72.requestFocus();
                }else {
                    total=total+Integer.parseInt(et72.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et73.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et73.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et73.requestFocus();
                }else {
                    total=total+Integer.parseInt(et73.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et74.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et74.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et74.requestFocus();
                }else {
                    total=total+Integer.parseInt(et74.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et75.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et75.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et75.requestFocus();
                }else {
                    total=total+Integer.parseInt(et75.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et76.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et76.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et76.requestFocus();
                }else {
                    total=total+Integer.parseInt(et76.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et77.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et77.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et77.requestFocus();
                }else {
                    total=total+Integer.parseInt(et77.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et78.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et78.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et78.requestFocus();
                }else {
                    total=total+Integer.parseInt(et78.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et79.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et79.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et79.requestFocus();
                }else {
                    total=total+Integer.parseInt(et79.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        //// 80 - 89

        et80.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et80.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et80.requestFocus();
                }else {
                    total=total+Integer.parseInt(et80.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et81.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et81.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et81.requestFocus();
                }else {
                    total=total+Integer.parseInt(et81.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et82.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et82.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et82.requestFocus();
                }else {
                    total=total+Integer.parseInt(et82.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et83.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et83.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et83.requestFocus();
                }else {
                    total=total+Integer.parseInt(et83.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et84.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et84.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et84.requestFocus();
                }else {
                    total=total+Integer.parseInt(et84.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et85.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et85.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et85.requestFocus();
                }else {
                    total=total+Integer.parseInt(et85.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et86.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et86.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et86.requestFocus();
                }else {
                    total=total+Integer.parseInt(et86.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et87.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et87.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et87.requestFocus();
                }else {
                    total=total+Integer.parseInt(et87.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et88.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et88.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et88.requestFocus();
                }else {
                    total=total+Integer.parseInt(et88.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et89.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et89.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et89.requestFocus();
                }else {
                    total=total+Integer.parseInt(et89.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        //// 90 - 99

        et90.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et90.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et90.requestFocus();
                }else {
                    total=total+Integer.parseInt(et90.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et91.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et91.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et91.requestFocus();
                }else {
                    total=total+Integer.parseInt(et91.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et92.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et92.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et92.requestFocus();
                }else {
                    total=total+Integer.parseInt(et92.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et93.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et93.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et93.requestFocus();
                }else {
                    total=total+Integer.parseInt(et93.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et94.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et94.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et94.requestFocus();
                }else {
                    total=total+Integer.parseInt(et94.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et95.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et95.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et95.requestFocus();
                }else {
                    total=total+Integer.parseInt(et95.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et96.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et96.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et96.requestFocus();
                }else {
                    total=total+Integer.parseInt(et96.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et97.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et97.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et97.requestFocus();
                }else {
                    total=total+Integer.parseInt(et97.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et98.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et98.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et98.requestFocus();
                }else {
                    total=total+Integer.parseInt(et98.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

        et99.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.parseInt(et99.getText().toString())<10){
                    Toast.makeText(getContext(), "Minimum bet amount is 10", Toast.LENGTH_SHORT).show();
                    et99.requestFocus();
                }else {
                    total=total+Integer.parseInt(et99.getText().toString());
                    isSubmitEnable=true;
                    totalPoint.setText(String.valueOf(total));
                }
            }
        });

    }

 */





/*

 @Override
    public void sendTotal(String total, HashMap<String, String> hashMap) {
        totalPoint.setText(total);
        enteredAmount=hashMap;
    }

 String[] name=new String[]{
            "Sridevi",
            "Time Bazar",
            "Madhur Day",
            "Milan Day",
            "Rajdhani Day",
            "Supreme Day",
            "Kalyan",
            "Sridevi Night",
            "Supreme Night",
            "Milan Night",
            "Kalyan Night",
            "Rajdhani Night",
            "Main Ratan",
            "Madhur Night"
    };
    String [] startTime=new String[]{
            "10:00 am", "10:00 am", "11:00 pm", "10:00 am", "10:00 am", "10:00 am", "10:00 am", "10:00 am", "11:00 pm", "10:00 am", "10:00 am", "10:00 am", "10:00 am", "10:00 am",
    };

    String [] endTime=new String[]{
            "11:20 am", "11:20 am", "11:59 pm", "11:20 am", "11:20 am", "11:20 am", "11:20 am", "11:20 am", "11:59 pm", "11:20 am", "11:20 am", "11:20 am", "11:20 am", "11:20 am",
    };

 */