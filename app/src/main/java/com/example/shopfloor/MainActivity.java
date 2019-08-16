package com.example.shopfloor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Activity.HomeActivity;
import com.example.shopfloor.Activity.KonfigurasiActivity;
import com.example.shopfloor.Models.User;
import com.example.shopfloor.Utils.GlobalVars;
import com.example.shopfloor.Utils.SharedPrefManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etusername0;
    private EditText etpassword0;
    private ConnectivityManager conMgr;
    private ProgressDialog pDialog;
    public SharedPreferences pref, prf;
    private TextView btnKonf;
    public final static String TAG_USERNAME = "U_STEM_Username";
    public final static String TAG_PASSWORD = "U_STEM_Password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);

        etusername0 = findViewById(R.id.etusername0);
        etpassword0 = findViewById(R.id.etpassword0);
        btnKonf = findViewById(R.id.btnKonf);

        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            }else {
                Toast.makeText(getApplicationContext(), "No Internet Connecting", Toast.LENGTH_LONG).show();
            }
        }

        btnLogin =  (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etusername0.getText().toString();
                String password = etpassword0.getText().toString();

                //mengecek kolom yang kosong
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(username, password);

                    }else {
                        Toast.makeText(getApplicationContext(), "No Internet Connecting", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnKonf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KonfigurasiActivity.class);
                startActivity(intent);
            }
        });
    }


        public void onBackPressed(){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }


    private void checkLogin(final String username, final String password) {

        final SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in.....");
        showDialog();

         final JSONObject jObject = new JSONObject();
        try {
            jObject.put("U_STEM_Username", etusername0.getText().toString());
            jObject.put("U_STEM_Password", etpassword0.getText().toString());
        }catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/loginuser")
                .addJSONObjectBody(jObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String message = response.getString("message");
                            String success = response.getString("success");
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


                            if ( success.equals("1")) {
//                                String username = jObject.getString(TAG_USERNAME);
//                                String password = jObject.getString(TAG_PASSWORD);

                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                pref = getSharedPreferences("username", MODE_PRIVATE);
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                String username = etusername0.getText().toString();
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("etusername", username);
                                editor.commit();
//                                intent.putExtra(TAG_USERNAME, username);
//                                intent.putExtra(TAG_PASSWORD, password);
//                                finish();
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "Password atau Username salah", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.isShowing();
    }
    }

