package com.example.shopfloor.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    public SharedPreferences pref, prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvWCtampil1 = findViewById(R.id.tvWCtampil1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvusername0 = findViewById(R.id.tvusername0);
        tvdivisi0 = findViewById(R.id.tvdivisi0);

        User user = new User();

         TextView tvdivisi = findViewById(R.id.tvdivisi0);
        tvdivisi.setText(String.valueOf(user.getDept()));

        TextView tvusername = findViewById(R.id.tvusername0);
        prf = getSharedPreferences("username", MODE_PRIVATE);
        tvusername.setText(prf.getString("etusername", null));

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
            }

            public void onBackPressed() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }


            //tampil tanggal home
            public void showDate(int year, int month, int day) {
                dateView1.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
            }

            private void userLogin() {
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

                                           Gson gson = new Gson();
                                           User user = gson.fromJson(dataArr.getJSONObject(0).toString(), User.class);
                                           tvdivisi0.setText(user.getDept());
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



        }




