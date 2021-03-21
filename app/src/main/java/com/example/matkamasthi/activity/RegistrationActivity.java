 package com.example.matkamasthi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.matkamasthi.fragment.HomeFragment;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, username, mobile, password;
    Button submitBtn;
    TextView termsAndCondition, goToLoginActivity;
    CheckBox agreementChkBox;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initilization();
    }

    private void initilization() {
        progressBar=findViewById(R.id.registration_progress_bar);
        name = findViewById(R.id.register_name);
        username = findViewById(R.id.register_user_name);
        mobile = findViewById(R.id.register_user_mobile);
        password = findViewById(R.id.register_user_password);
        termsAndCondition = findViewById(R.id.terms_condition_text);
        goToLoginActivity = findViewById(R.id.go_to_login_page);
        submitBtn = findViewById(R.id.registeration_submit);
        agreementChkBox=findViewById(R.id.agree_check_box);
        goToLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }

    private void checkUser() {
        if (!TextUtils.isEmpty(name.getText().toString().trim())) {
            if (!TextUtils.isEmpty(username.getText().toString().trim())) {
                if (!TextUtils.isEmpty(mobile.getText().toString().trim()) && mobile.length() == 10) {

                    if (!TextUtils.isEmpty(password.getText().toString().trim()) && password.length() > 6) {
                        registerUser(name.getText().toString(),username.getText().toString(),mobile.getText().toString(),password.getText().toString());
//                        new PreferenceManager(RegistrationActivity.this).saveSignupDetails(name.getText().toString(),username.getText().toString(),mobile.getText().toString(),password.getText().toString());
//                         startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
                    } else {
                        password.requestFocus();
                        password.setError("Password length should be 6 or greater");
                    }
                } else {
                    mobile.requestFocus();
                    mobile.setError("mobile number should be of 10 digits");
                }
            } else {
                username.requestFocus();
                username.setError("Enter UserName");
            }
        } else {
            name.requestFocus();
            name.setError("Enter Name");
        }
    }

    private void registerUser(String name,String username,String mobile,String password) {
        if(agreementChkBox.isChecked()==true){
            signingProcess(name,username,mobile,password);
//            new PreferenceManager(RegistrationActivity.this).saveSignupDetails(name,username,mobile,password);
//                         startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
        } else {
            Toast.makeText(RegistrationActivity.this, "Please Agree on the Terms and Condition", Toast.LENGTH_SHORT).show();
        }
    }

    private void signingProcess(final String name, final String username, final String mobile, final String password) {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest objectRequest = new StringRequest(Request.Method.POST, Constant.registerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("success");
                    if(sucess.isEmpty() ||sucess.equals("false")){
                        progressBar.setVisibility(View.GONE);
                        String message=jsonObject.getString("message");
                        Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }else{
                        JSONObject jresponse = jsonObject.getJSONObject("user");
                        JSONObject object = new JSONObject(response);
                        String name=jresponse.getString("name");
                        String username=jresponse.getString("username");
                        String mobile=jresponse.getString("mobile");
                        String uid=jresponse.getString("id");
                        String token=jsonObject.getString("token");

                        Log.i("register",name+" "+username+" "+mobile+" "+uid);
                        progressBar.setVisibility(View.GONE);

                        new PreferenceManager(RegistrationActivity.this).saveSignupDetails(uid,name,username,mobile,password,token);
                        Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
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
                    Toast.makeText(RegistrationActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                }

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    String errorMesage = ErrorHelper.errorMessageFromData(new String(networkResponse.data));
                    if (errorMesage != null) {
                        Toast.makeText(RegistrationActivity.this, errorMesage, Toast.LENGTH_SHORT).show();
                    }

                }
                if (ErrorHelper.isServerProblem(error))
                    Toast.makeText(RegistrationActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
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
                params.put("name", name);
                params.put("username", username);
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }

        };


        objectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
        objectRequest.setShouldCache(false);
        requestQueue.add(objectRequest);
    }

    @Override
    public void onBackPressed() {
            finishAffinity();
    }
}
