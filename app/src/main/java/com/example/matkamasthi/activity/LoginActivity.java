package com.example.matkamasthi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText lUsername,lPassword;
    private Button loginBtn;
    private TextView gotoRegistration;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();
    }

    private void initialization(){
        progressBar=findViewById(R.id.login_progress_bar);
        lUsername=findViewById(R.id.login_user_name);
        lPassword=findViewById(R.id.login_user_password);
        loginBtn=findViewById(R.id.login_btn);
        gotoRegistration=findViewById(R.id.go_to_registration_page);
        gotoRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }

    private void checkUser() {

                if (!TextUtils.isEmpty(lUsername.getText().toString().trim())) {

                    if (!TextUtils.isEmpty(lPassword.getText().toString().trim()) && lPassword.length() > 6) {
                        loginProcess(lUsername.getText().toString(),lPassword.getText().toString());
                    } else {
                        lPassword.requestFocus();
                        lPassword.setError("Password length should be 6 or greater");
                    }
                } else {
                    lUsername.requestFocus();
                    lUsername.setError("mobile number should be of 10 digits");
                }

    }


    private void loginProcess(final String userName, final String password) {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("success");
                    if(sucess.isEmpty() ||sucess.equals("false")){
                        progressBar.setVisibility(View.GONE);
                        String message=jsonObject.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else {
                        JSONObject jresponse = jsonObject.getJSONObject("user");
                        JSONObject object = new JSONObject(response);
                        String name = jresponse.getString("name");
                        String username = jresponse.getString("username");
                        String mobile = jresponse.getString("mobile");
                        String uid = jresponse.getString("id");
                        String token = jsonObject.getString("token");

                        Log.i("register", name + " " + username + " " + mobile + " " + uid);
                        progressBar.setVisibility(View.GONE);
                        new PreferenceManager(LoginActivity.this).saveSignupDetails(uid, name, username, mobile, password, token);
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                } catch (JSONException jsonException) {
                    progressBar.setVisibility(View.GONE);
                    jsonException.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                if (ErrorHelper.isNetworkProblem(error)) {
                    Toast.makeText(LoginActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                }

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    String errorMesage = ErrorHelper.errorMessageFromData(new String(networkResponse.data));
                    if (errorMesage != null) {
                        Toast.makeText(LoginActivity.this, errorMesage, Toast.LENGTH_SHORT).show();
                    }

                }
                if (ErrorHelper.isServerProblem(error))
                    Toast.makeText(LoginActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            //            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<>();
////                headers.put("Content-Type", "application/x-www-form-urlencoded");
////                headers.put("x-api-key", "vijai@123");
////                String creds = String.format("%s:%s", "first_name", "last_name", "email", "password", "mobile");
////                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
////                headers.put("Authorization", "Basic YWRtaW46MTIzNDU=");
////
//
//                return headers;
//            }
            ///name,username,mobile,password
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                params.put("password", password);
                return params;
            }

        };


        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        objectRequest.setShouldCache(false);
        requestQueue.add(objectRequest);


    }
}
