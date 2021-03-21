package com.example.matkamasthi.manager;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.model.WalletHistoryRecyclerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager {
    Context context;
    String amountWallet;


    public ProfileManager(Context context) {
        this.context = context;
    }


    public String getUserProfileDetails(){
        final String tokenval=new PreferenceManager(context).getuserToken();
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
                   // amountWallet=walletAmount;
                    setAmountWallet(walletAmount);

                }catch (JSONException jsonException) {

                    jsonException.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (ErrorHelper.isNetworkProblem(error)) {
                    Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                }

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    String errorMesage = ErrorHelper.errorMessageFromData(new String(networkResponse.data));
                    if (errorMesage != null) {
                        Toast.makeText(context, errorMesage, Toast.LENGTH_SHORT).show();
                    }

                }
                if (ErrorHelper.isServerProblem(error))
                    Toast.makeText(context, "Error" + error, Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                String token="Bearer "+tokenval;
                headers.put("Authorization", token);

                return headers;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(context);
        queue.add(objectRequest);

        return amountWallet;
    }

    public String getAmountWallet() {
        return amountWallet;
    }

    public void setAmountWallet(String amountWallet) {
        this.amountWallet = amountWallet;
    }
}
