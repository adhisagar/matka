package com.example.matkamasthi.manager;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class ErrorHelper {

    public static boolean isServerProblem(Object object){

        return (object instanceof ServerError ||object instanceof AuthFailureError ||object instanceof TimeoutError);
    }

    public static boolean isNetworkProblem(Object object){
        return (object instanceof NetworkError || object instanceof NoConnectionError);
    }

    public static String errorMessageFromData(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getString("error");
        } catch (Exception e){
            return null;
        }

    }

    public static void handleErrorOfVolley(Context context, VolleyError error){
        if (context==null){
            return;
        }
        if (ErrorHelper.isNetworkProblem(error)) {

            Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show();
        }
        NetworkResponse networkResponse=error.networkResponse;
        if (networkResponse !=null && networkResponse.statusCode==400){
            String errorMesage= ErrorHelper.errorMessageFromData(new String(networkResponse.data));
            if (errorMesage !=null){
                Toast.makeText(context, errorMesage, Toast.LENGTH_SHORT).show();
            }

        }
        if (ErrorHelper.isServerProblem(error)){
            Toast.makeText(context, "Error in Connection", Toast.LENGTH_SHORT).show();
        }

    }
}
