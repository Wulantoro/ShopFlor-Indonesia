package com.example.shopfloor.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.WorkcenterAdapter;
import com.example.shopfloor.MainActivity;
import com.example.shopfloor.Models.User;
import com.example.shopfloor.Models.Workcenter;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.example.shopfloor.Utils.SharedPrefManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button btnWorkcenter;
    private Button btnstartdoc;
    private Button btnOpendoc;
    private Button btnSucDoc;
    private Calendar calendar;
    private int year, month, day;
    private TextView dateView1;
    private TextView tvWCtampil1;
    private TextView tvusername0;
    private TextView tvdivisi0;

    public static TextView tvNo_Prod1;
    public static TextView tvprod1;
    public static TextView tvRoute_Code1;
    public static TextView tvRoute_Code2;
    public static TextView tvQty_rencProd1;
    public static TextView tvSts_Prod1;
    public static TextView tvNo_doc1;
    public static TextView tvSquence1;
    public static TextView tvSquence_Qty1;
    public static TextView tvNm_prod1;

    private Button btnLogout;

    public SharedPreferences pref, prf;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvWCtampil1 = findViewById(R.id.tvWCtampil1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvusername0 = findViewById(R.id.tvusername0);
        tvdivisi0 = findViewById(R.id.tvdivisi0);
        btnLogout = findViewById(R.id.btnLogout);

        final SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);


        drawer = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawer, R.string.open_drawer, R.string.closedDrawr);

        drawer.addDrawerListener(mToggle);
        mToggle.syncState();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);


        User user = new User();

         TextView tvdivisi = findViewById(R.id.tvdivisi0);
        tvdivisi.setText(String.valueOf(user.getDept()));

        TextView tvusername = findViewById(R.id.tvusername0);
        prf = getSharedPreferences("username", MODE_PRIVATE);
        tvusername.setText(prf.getString("etusername", null));

        TextView tvwc = findViewById(R.id.tvWCtampil1);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("tvworkcenter", null));

        /************result workcenter terpilih***********************/
        if (getIntent().hasExtra("keywc")) {
            String keywc = getIntent().getStringExtra("keywc");
            tvWCtampil1.setText(keywc);
        }
        /*******************************************************************/


                /**********************tampil tanggal****************/
                dateView1 = findViewById(R.id.tvTanggalHome);
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);

                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                showDate(year, month + 1, day);
                /**********end tampil tanggal**************************/

        userLogin();

        btnWorkcenter = findViewById(R.id.btnWorkcenter);
        btnWorkcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iWork = new Intent(getApplicationContext(), WorkcenterListActivity.class);
                startActivity(iWork);
            }
        });


                      btnstartdoc = findViewById(R.id.btnstartdoc);
                      btnstartdoc.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if (tvWCtampil1.length() != 0) {

                                  pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                                  Intent iStart = new Intent(HomeActivity.this, StartDocActivity.class);

                                  String wc = tvWCtampil1.getText().toString();
                                  SharedPreferences.Editor editor = pref.edit();
                                  editor.putString("workcenter", wc);
                                  editor.commit();

                                  pref = getSharedPreferences("userId", MODE_PRIVATE);
                                  String tvuserid = tvusername0.getText().toString();
                                  SharedPreferences.Editor editor1 = pref.edit();
                                  editor1.putString("tvuserid", tvuserid);
                                  editor1.commit();
//
                                  startActivity(iStart);
                              } else {
                                  Toast.makeText(getApplicationContext(), "Workcenter tidak boleh kosong", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });


                btnOpendoc = findViewById(R.id.btnOpendoc);
                btnOpendoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tvWCtampil1.length() != 0) {
                            pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                            Intent iOpen = new Intent(HomeActivity.this, Open_DocActivity.class);
                            String wc1 = tvWCtampil1.getText().toString();
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("workcenter", wc1);
                            editor.commit();

                            pref = getSharedPreferences("Qcname", MODE_PRIVATE);
                            String tvqcname = tvusername0.getText().toString();
                            SharedPreferences.Editor editor1 = pref.edit();
                            editor1.putString("tvqcname", tvqcname);
                            editor1.commit();
                            startActivity(iOpen);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih Workcenter dahulu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnSucDoc = findViewById(R.id.btnSucDoc);
                btnSucDoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (tvWCtampil1.length() != 0) {
                            pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                            Intent iSuc = new Intent(getApplicationContext(), SuccessDocActivity.class);
                            String wc2 = tvWCtampil1.getText().toString();
                            SharedPreferences.Editor editor1 = pref.edit();
                            editor1.putString("workcenter", wc2);
                            editor1.commit();
                            startActivity(iSuc);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih Workcenter dahulu", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

                btnLogout = findViewById(R.id.btnLogout);
                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                        startActivity(new Intent(HomeActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                });
            }


            private void userLogin() {

                prf = getSharedPreferences("username", MODE_PRIVATE);
                prf.getString("etusername", null);
                Log.e("username = ", prf.getString("etusername", null));

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/loginuser?U_STEM_Username="+prf.getString("etusername", null))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("tampil user", response.toString(1));
                            String message = response.getString("message");

                            if (message.equals("User ketemu")) {
                                String records = response.getString("data");
                                JSONArray dataArr = new JSONArray(records);


                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {

                                        Gson gson = new Gson();
                                        User user = gson.fromJson(dataArr.getJSONObject(0).toString(), User.class);
                                        TextView tvdept = findViewById(R.id.tvdivisi0);
                                        tvdept.setText(String.valueOf(user.getDept()));
                                    }
                                }
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                }).create().show();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);

                return true;
            }
        });
    }

    //method untuk eksekusi action dari tiap menu item
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_gallery:
                //action
                break;
            case R.id.nav_slideshow:
                //action
                break;
            case R.id.nav_manage:
                //action
                break;

        }


        menuItem.setChecked(true);
        drawer.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
            //tampil tanggal home
            public void showDate(int year, int month, int day) {
//                dateView1.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
            }


}




