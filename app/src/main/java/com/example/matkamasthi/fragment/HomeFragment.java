package com.example.matkamasthi.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.activity.HomeActivity;
import com.example.matkamasthi.adapter.NoticeBoardRecyclerAdapter;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
//import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private Button callBtn;
    String tokenValue;

   // private FirebaseRemoteConfig mFirebaseRemoteConfig;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        tokenValue=new PreferenceManager(getContext()).getuserToken();
        callApp(view);
       // settingRemoteConfig();
        //callPopUpButton(view);
        checkConnection();

        return view;
    }

    private void callApp(View view){
        callBtn=view.findViewById(R.id.call_us_btn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                callIntent.setData(Uri.parse("tel:" + "9123456780"));
//                startActivity(callIntent);

                String strMessage="Here is my Message";
                boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "+9123456780"
                            + "&text=" + strMessage));
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "WhatsApp not installed on your Device", Toast.LENGTH_SHORT).show();
                }
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

    private void checkConnection(){
        if (haveNetwork()){
            Toast.makeText(getContext(), "Network connection is available", Toast.LENGTH_SHORT).show();
        } else if (!haveNetwork()) {
            Toast.makeText(getContext(), "Network connection is not available", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean haveNetwork(){
        boolean have_WIFI= false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))if (info.isConnected())have_WIFI=true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE DATA"))if (info.isConnected())have_MobileData=true;
        }
        return have_WIFI||have_MobileData;
    }

    private void callPopUpButton(final View view){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.game_wins_popup, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String success=response.getString("success");
                    if(success.equals("true")){

                        showSuccessDialog(view);
//                        JSONArray jsonArray=response.getJSONArray("game_wins");
//                        JSONObject jsonObject=jsonArray.getJSONObject(0);

                    }
                } catch (JSONException jsonException){
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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(objectRequest);
    }

    private void showSuccessDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.show_win_popup, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button okBtn=alertDialog.findViewById(R.id.ok_btn_popup);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
    }

}

/*
    private void settingRemoteConfig(){
        HashMap<String, Object> defaultsRate = new HashMap<>();
        defaultsRate.put("new_version_code", String.valueOf(getVersionCode()));

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10) // change to 3600 on published app
                .build();

        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(defaultsRate);

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(getActivity(), new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    final String new_version_code = mFirebaseRemoteConfig.getString("new_version_code");

                    if(Integer.parseInt(new_version_code) > getVersionCode())
                        showTheDialog("com.softi.matkamasthi", new_version_code );
                }
                else Log.e("MYLOG", "mFirebaseRemoteConfig.fetchAndActivate() NOT Successful");

            }
        });
    }


    private void showTheDialog(final String appPackageName, String versionFromRemoteConfig){
        final AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Update")
                .setMessage("To Use new Feature, please update to version: "+versionFromRemoteConfig)
                .setPositiveButton("UPDATE", null)
                .show();

        dialog.setCancelable(false);

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("market://details?id=" + appPackageName)));
                    Toast.makeText(getContext(), "New App Update", Toast.LENGTH_SHORT).show();
                }
                catch (android.content.ActivityNotFoundException anfe) {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));

                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private PackageInfo pInfo;
    public int getVersionCode() {
        pInfo = null;
        try {
            pInfo = getContext().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("MYLOG", "NameNotFoundException: "+e.getMessage());
        }
        return pInfo.versionCode;
    }

 */
