package com.example.matkamasthi.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.matkamasthi.activity.MainActivity;
import com.example.matkamasthi.adapter.SingleDataRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.OnSwipeTouchListener;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.model.SingleDataRecyclerModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
public class FullSangamFragment extends Fragment {

    public FullSangamFragment() {
        // Required empty public constructor
    }

    LinearLayout firstLinearLayout;

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

    String currentDateandTime;
    String gameId,gameCategoryId;
    String tokenVal;
    int total=0;
    String walletAmount;
    int wallet;
    String openDigitValue,closeDigitValue,amountValue;
    EditText openDigit,closeDigit,amount;
    String game_name,game_timing,game_end_time,type;
    int amount1=0;

    List<String> openDigitList=new ArrayList<>();
    List<String> closeDigitList=new ArrayList<>();
    List<String> amountList=new ArrayList<>();
    int[] amountValueList;

    Button fullSangamTotalPointText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_full_sangam, container, false);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        dateText=view.findViewById(R.id.full_sangam_date_spinner);
        dateText.setText(currentDateandTime);

        tokenVal=new PreferenceManager(getContext()).getuserToken();
        walletAmount=new PreferenceManager(getContext()).getWalletAmount();
        wallet=Integer.parseInt(walletAmount);

        gettingmarketName(view);
        initializeView(view);
        settingFirstLayout(view);


        return view;
    }


    private void initializeView(View view){
        totalPoint=view.findViewById(R.id.full_sangam_total_point);
        fullSangamTotalPointText=view.findViewById(R.id.full_sanagam_total_point_text);
        singleSubmitBtn=view.findViewById(R.id.full_sangam_submit_btn);

        fullSangamTotalPointText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAmount();

            }
        });
        singleSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sum=0;
                openDigitValue=openDigit.getText().toString();
                closeDigitValue=closeDigit.getText().toString();
//                for(int i =0;i<amountList.size();i++){
//                    sum= Integer.parseInt(sum+ amountList.get(i));
//                }
//                amountValue=amount.getText().toString();
//                total=sum;
 //               totalPoint.setText(total);

 //               total=Integer.parseInt(openDigitValue)+Integer.parseInt(closeDigitValue);
//                amount1=Integer.parseInt(openAnkValue)+Integer.parseInt(closePattiValue);
//                amount2=Integer.parseInt(closeAnkValue)+Integer.parseInt(openPattiValue);
                if(!openDigitValue.isEmpty() || !closeDigitValue.isEmpty()){
                    submitData(v);
                } else {
                    Toast.makeText(getContext(), "Data not field Properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void submitData(View view) {
      //  totalPoint.setText(total);
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
        final String gameDataId=gameId;
        final String userName=new PreferenceManager(getContext()).getusername();
        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.full_sangam_game_submit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("success");
//                    Toast.makeText(getContext(), sucess, Toast.LENGTH_SHORT).show();
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
                params.put("gameid","3");
                params.put("game_play_date", currentDateandTime);

                params.put("open_digit[]", openDigitList.toArray().toString());
                params.put("close_digit[]",closeDigitList.toArray().toString());
               params.put("amount[]", amountList.toArray().toString());

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

    private void gettingmarketName(final View view){

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

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

        spinner=view.findViewById(R.id.half_sangam_Market_spinner);
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

    }

    private void settingFirstLayout(final View view){
        firstLinearLayout=view.findViewById(R.id.dynamic_first_layout_full_sangam);
        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);

        firstLinearLayout.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                removeView(view);
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(getContext(), "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
            }
        });;

        addView();

    }

    private void addView() {

        final View layoutView = getLayoutInflater().inflate(R.layout.full_sangam_design,null,false);
        openDigit=layoutView.findViewById(R.id.first_open_digit_edittext);
        closeDigit=layoutView.findViewById(R.id.first_close_digit_edittext);
        amount=layoutView.findViewById(R.id.full_sangam_amount_val);

        openDigitList.add(openDigit.getText().toString());
        closeDigitList.add(closeDigit.getText().toString());
        amountList.add(amount.getText().toString());

        FloatingActionButton addFloating=layoutView.findViewById(R.id.dynamic_add_full_sangam);
        addFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

        firstLinearLayout.addView(layoutView);



    }

    private void calculateAmount(){
        total=0;
        if(!amount.getText().toString().isEmpty() && Integer.parseInt(amount.getText().toString())>10){
            total=total+Integer.parseInt(amount.getText().toString());
        }

        totalPoint.setText(String.valueOf(total));
    }

    private void removeView(View view){

         firstLinearLayout.removeView(view);

    }
}
