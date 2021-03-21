package com.example.matkamasthi.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.matkamasthi.activity.HomeActivity;
import com.example.matkamasthi.activity.LoginActivity;
import com.example.matkamasthi.activity.PanelResultShow;
import com.example.matkamasthi.adapter.SingleDataRecyclerAdapter;
import com.example.matkamasthi.adapter.WalletHistoryRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.manager.ProfileManager;
import com.example.matkamasthi.model.WalletHistoryRecyclerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }
    private TextView uName,uUserName,uMobile,uWallet;
    private Button withdrawlBtn;
    private EditText withdrawlAmount;
    String token;
    String type;
    String status,date,amount,totalAmount,fieldWin,gameComments;
    TextView changePasswordTextBtn;

    RecyclerView recyclerView;
    ArrayList<WalletHistoryRecyclerModel> arrayList=new ArrayList<>();
    WalletHistoryRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        initialization(view);
        setUserWalletHistory(view);
        token=new PreferenceManager(getContext()).getuserToken();
        getUserDetails(token);
        setWalletHistory(token);
        return view;
    }

    private void initialization(View view){
        uName=view.findViewById(R.id.user_details_name);
        uUserName=view.findViewById(R.id.user_details_user_name);
        uMobile=view.findViewById(R.id.user_detail_mobile_phone);
        uWallet=view.findViewById(R.id.user_details_wallet_amount);
        uWallet.setText(new ProfileManager(getContext()).getAmountWallet());
        withdrawlAmount=view.findViewById(R.id.withdrawl_amount);
        changePasswordTextBtn=view.findViewById(R.id.profile_change_password);


        withdrawlBtn=view.findViewById(R.id.withdrawl_btn);


        withdrawlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int withDrawableAmount=0;
                withDrawableAmount=Integer.parseInt(withdrawlAmount.getText().toString());
                int walletAmount=Integer.parseInt(uWallet.getText().toString());
                if(withDrawableAmount!=0 &&withDrawableAmount<=walletAmount){
                    String strMessage="Want to Withdraw Rs. "+withDrawableAmount+" for mobile Number "+"8287428834"+" from Matka Masti";
                    boolean installed = appInstalledOrNot("com.whatsapp");
                    if (installed){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "+918287428834"
                                + "&text=" + strMessage));
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "WhatsApp not installed on your Device", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Insufficient Amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        changePasswordTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPasswordChange(v);
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

    private void getUserDetails(final String tokenValue){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,Constant.userDetailsUrl, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jresponse = response.getJSONObject("user");
                    //JSONObject jresponse = jsonObject.getJSONObject("user");

                    String name=jresponse.getString("name");
                    String username=jresponse.getString("username");
                    String mobile=jresponse.getString("mobile");
                    String uid=jresponse.getString("id");

                    JSONObject walletObj=jresponse.getJSONObject("wallet");

                    String walletAmount=walletObj.getString("amount");

                    uName.setText(name);
                    uUserName.setText(username);
                    uMobile.setText(mobile);
                    uWallet.setText(walletAmount);

                }catch (JSONException jsonException) {

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

        }){
                        @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                String token="Bearer "+tokenValue;
                headers.put("Authorization", token);

                return headers;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(objectRequest);
    }

    private void setWalletHistory(final String tokenValue){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,Constant.userWalletDetailsUrl, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String success=response.getString("success");
                    if(success=="true"){

                        JSONArray array=response.getJSONArray("wallet_histories");

                        for(int i=0;i<array.length();i++){
                            JSONObject object=array.getJSONObject(i);
                            type=object.getString("type");
                            status=object.getString("Status");
                            amount=object.getString("amount");
                            totalAmount=object.getString("current_points");
                            date=object.getString("date");
                            fieldWin=object.getString("field_win");
                            gameComments=object.getString("comments");
                        }

                        arrayList.add(new WalletHistoryRecyclerModel(type,status,"Amount : Rs. "+amount,date,"Total : Rs. "+totalAmount,fieldWin,"You have "+gameComments));
                        adapter.notifyDataSetChanged();
                    }


                }catch (JSONException jsonException) {

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

        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                String token="Bearer "+tokenValue;
                headers.put("Authorization", token);

                return headers;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(objectRequest);
    }

    private void setUserWalletHistory(View view){
        recyclerView =view.findViewById(R.id.wallet_history_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WalletHistoryRecyclerAdapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
    }

    private void submitPasswordChange(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.change_password_dialog, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //final EditText oldPassword=alertDialog.findViewById(R.id.old_password);
        final EditText newPassword=alertDialog.findViewById(R.id.new_password);
        final EditText confirmPassword=alertDialog.findViewById(R.id.confirm_password);
        Button cancelBtn=alertDialog.findViewById(R.id.change_password_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        Button submitBtn=alertDialog.findViewById(R.id.change_password_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String oPassword=oldPassword.getText().toString().trim();
                String nPassword=newPassword.getText().toString().trim();
                String cPassword=confirmPassword.getText().toString();

                    if(!nPassword.isEmpty() && !cPassword.isEmpty()){
                        if(nPassword.equals(cPassword)){
                            changePassword(nPassword,cPassword,token);
                        } else {
                            Toast.makeText(getContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), "Password Can be Empty", Toast.LENGTH_SHORT).show();
                    }

                alertDialog.dismiss();
            }
        });
    }

    private void changePassword(final String newPassword, final String oldPassword, final String tokenValue){

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.password_change, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("success");
                    if(!sucess.isEmpty()){
                       // progressBar.setVisibility(View.GONE);
                        String message=jsonObject.getString("message");
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            //    progressBar.setVisibility(View.GONE);
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
                String token="Bearer "+tokenValue;
                headers.put("Authorization", token);

                return headers;
            }
            ///name,username,mobile,password
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", oldPassword);
                params.put("c_password", newPassword);
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(objectRequest);

    }
    
}

//final String amount=withdrawlAmount.getText().toString();
//        String name=new PreferenceManager(getContext()).getname();
//        String username=new PreferenceManager(getContext()).getusername();
//        String phone=new PreferenceManager(getContext()).getuserMobile();