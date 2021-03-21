
package com.example.matkamasthi.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matkamasthi.R;
import com.example.matkamasthi.adapter.NoticeBoardRecyclerAdapter;
import com.example.matkamasthi.fragment.AddFundFragment;
import com.example.matkamasthi.fragment.DoublePattiFragment;
import com.example.matkamasthi.fragment.FullSangamFragment;
import com.example.matkamasthi.fragment.GameRatioFragment;
import com.example.matkamasthi.fragment.GamesHistory;
import com.example.matkamasthi.fragment.HalfSangamFragment;
import com.example.matkamasthi.fragment.HomeFragment;
import com.example.matkamasthi.fragment.HowToPlayFragment;
import com.example.matkamasthi.fragment.JodiChartFragment;
import com.example.matkamasthi.fragment.JodiFragment;
import com.example.matkamasthi.fragment.PanelChartFragment;
import com.example.matkamasthi.fragment.ProfileFragment;
import com.example.matkamasthi.fragment.ReferFragment;
import com.example.matkamasthi.fragment.SingleFragment;
import com.example.matkamasthi.fragment.SinglePattiFragment;
import com.example.matkamasthi.fragment.TriplePattiFragment;
import com.example.matkamasthi.fragment.homefragment.HomeJodiChartFragment;
import com.example.matkamasthi.fragment.homefragment.PanelChartRecordFragment;
import com.example.matkamasthi.manager.Constant;
import com.example.matkamasthi.manager.ErrorHelper;
import com.example.matkamasthi.manager.PreferenceManager;
import com.example.matkamasthi.manager.ProfileManager;
import com.example.matkamasthi.model.NoticeBoardRecyclerModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    static final float END_SCALE = 0.7f;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private TextView header_name,header_username,header_userwallet;
    FrameLayout frameLayout;
    boolean isHomePressed=true;
    private long pressedTime;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        frameLayout=findViewById(R.id.frame_layout);
        setFragment(new HomeFragment(),getString(R.string.app_name));

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        token=new PreferenceManager(HomeActivity.this).getuserToken();
        getUserDetails(token);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                animateNavigationDrawer();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(new HomeFragment(),getString(R.string.app_name));
                        isHomePressed=true;
                        break;
                    case R.id.nav_single:
                        setFragment(new SingleFragment(),getString(R.string.menu_single));
                        isHomePressed=false;
                        break;

                    case R.id.nav_jodi:
                        setFragment(new JodiFragment(),getString(R.string.menu_jodi));
                        isHomePressed=false;
                        break;
                    case R.id.nav_single_patti:
                        setFragment(new SinglePattiFragment(),getString(R.string.menu_single_patti));
                        isHomePressed=false;
                        break;
                    case R.id.nav_double_patti:
                        setFragment(new DoublePattiFragment(),getString(R.string.double_patti));
                        isHomePressed=false;
                        break;
                    case R.id.nav_triple_patti:
                        setFragment(new TriplePattiFragment(),getString(R.string.triple_patti));
                        isHomePressed=false;
                        break;
                    case R.id.nav_half_sangam:
                        setFragment(new HalfSangamFragment(),getString(R.string.menu_half_sangam));
                        isHomePressed=false;
                        break;
                    case R.id.nav_full_sangam:
                        setFragment(new FullSangamFragment(),getString(R.string.menu_full_sangam));
                        isHomePressed=false;
                        break;
                    case R.id.nav_jodi_chart:
                        setFragment(new HomeJodiChartFragment(),getString(R.string.menu_jodi_chart));
                        isHomePressed=false;
                        break;
                    case R.id.nav_panel_chart:
                        setFragment(new PanelChartRecordFragment(),getString(R.string.menu_panel_chart));
                        isHomePressed=false;
                        break;
                    case R.id.nav_game_ratio:
                        setFragment(new GameRatioFragment(),getString(R.string.menu_game_ratio));
                        isHomePressed=false;
                        break;
                    case R.id.nav_game_history:
                        setFragment(new GamesHistory(),getString(R.string.menu_game_history));
                        isHomePressed=false;
                        break;
                    case R.id.nav_how_to_play:
                        setFragment(new HowToPlayFragment(),getString(R.string.menu_how_to_play));
                        isHomePressed=false;
                        break;
//                    case R.id.nav_refer:
//                        setFragment(new ReferFragment(),getString(R.string.menu_refer));
//                        isHomePressed=false;
//                        break;
                    case R.id.nav_add_fund:
                        setFragment(new AddFundFragment(),getString(R.string.add_fund));
                        isHomePressed=false;
                        break;
                    case R.id.nav_profile:
                        setFragment(new ProfileFragment(),getString(R.string.menu_profile));
                        isHomePressed=false;
                        break;
                }
                return true;
            }
        });

        View view = navigationView.getHeaderView(0);
        header_name = view.findViewById(R.id.nav_header_name);
        header_username=view.findViewById(R.id.nav_header_user_name);
        header_userwallet=view.findViewById(R.id.nav_header_user_wallet);
        String name=new PreferenceManager(HomeActivity.this).getname();
        String username=new PreferenceManager(HomeActivity.this).getusername();
//        String wallet=new ProfileManager(HomeActivity.this).getAmountWallet();
//        header_name.setText(name);
//        header_username.setText(username);
//        header_userwallet.setText("500");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(isHomePressed){
            finishAffinity();
        }else if(isHomePressed==false) {
            isHomePressed=true;
            setFragment(new HomeFragment(),getString(R.string.app_name));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.call_app:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9123456780"));
                startActivity(callIntent);
                break;

            case R.id.logout:
                new PreferenceManager(HomeActivity.this).logout();
                Intent intent=new Intent(HomeActivity.this,RegistrationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment fragment,String title){

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(frameLayout.getId(),fragment);
        ft.commit();
    }

    private void animateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                frameLayout.setScaleX(offsetScale);
                frameLayout.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = frameLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                frameLayout.setTranslationX(xTranslation);
            }
        });

    }

    private void getUserDetails(final String tokenValue){
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Constant.userDetailsUrl, null,new Response.Listener<JSONObject>() {
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
//                    if(walletAmount.equals(null)){
//                        walletAmount= String.valueOf(0);
//                    }
//                    walletAmount= String.valueOf(0);
                    new PreferenceManager(HomeActivity.this).saveWalletAmount(walletAmount);
                    header_name.setText(name);
                    header_username.setText(username);
//                    uMobile.setText(mobile);
                    header_userwallet.setText(walletAmount);
                }catch (JSONException jsonException) {

                    jsonException.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (ErrorHelper.isNetworkProblem(error)) {
                    Toast.makeText(HomeActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                }

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 400) {
                    String errorMesage = ErrorHelper.errorMessageFromData(new String(networkResponse.data));
                    if (errorMesage != null) {
                        Toast.makeText(HomeActivity.this, errorMesage, Toast.LENGTH_SHORT).show();
                    }

                }
                if (ErrorHelper.isServerProblem(error))
                    Toast.makeText(HomeActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue queue= Volley.newRequestQueue(HomeActivity.this);
        queue.add(objectRequest);
    }

}





