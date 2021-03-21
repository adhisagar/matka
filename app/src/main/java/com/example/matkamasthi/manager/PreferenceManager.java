package com.example.matkamasthi.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.matkamasthi.activity.HomeActivity;
import com.example.matkamasthi.activity.RegistrationActivity;
import com.example.matkamasthi.activity.SplashActivity;

public class PreferenceManager {
    Context context;
    public PreferenceManager(Context context){
        this.context=context;
    }
    public void saveSignupDetails(String uid,String name,String username,String mobileNo,String password,String token){
        SharedPreferences preferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("id",uid);
        editor.putString("name",name);
        editor.putString("username",username);
        editor.putString("mobileNo",mobileNo);
        editor.putString("password",password);
        editor.putString("token",token);
        editor.apply();
    }
    public void saveLoginDetails(String mobile,String password){
        SharedPreferences preferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("mobileNo",mobile);
        editor.putString("password",password);
        editor.apply();
    }

    public void saveWalletAmount(String amount){
        SharedPreferences preferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("wallet",amount);
        editor.apply();
    }

    public String getWalletAmount(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        return sharedPreferences.getString("wallet","");
    }
    public String getname(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }
    public String getusername(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        return sharedPreferences.getString("username","");
    }
    public String getuserMobile(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobileNo","");
    }
    public String getuserToken(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        return sharedPreferences.getString("token","");
    }

    public boolean isUserLoggedOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Preference", Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobileNo","").isEmpty();
    }

    public void mainConfiguration(){
        if (!isUserLoggedOut()) {
            Intent spalshIntent = new Intent(context, HomeActivity.class);
            spalshIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(spalshIntent);
        } else {
            Intent intent = new Intent(context, RegistrationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }

    }

    public void logout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Preference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }
}
