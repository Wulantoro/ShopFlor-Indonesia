package com.example.shopfloor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Utils.RealmHelper;
import com.example.shopfloor.Utils.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etusername0;
    private EditText etpassword0;
    private ConnectivityManager conMgr;
    private ProgressDialog pDialog;
    public SharedPreferences pref, prf;
    private TextView btnKonf;
    private TextView tvip0;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);

        etusername0 = findViewById(R.id.etusername0);
        etpassword0 = findViewById(R.id.etpassword0);
        tvip0 = findViewById(R.id.tvip0);
        btnKonf = findViewById(R.id.btnKonf);

//        Setup Realm
//        Realm realm = Realm.getDefaultInstance();
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        serverModels = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();
        }
        tvip0.setText(text);

        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            }else {
//                Toast.makeText(getApplicationContext(), "No Internet Connecting", Toast.LENGTH_LONG).show();
                Toasty.error(getApplicationContext(), "No Internet Connecting", Toast.LENGTH_LONG).show();
            }
        }

        btnLogin =  (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginUser();

//                pref = getSharedPreferences("username", MODE_PRIVATE);
//                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                String username = etusername0.getText().toString();
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("etusername", username);
//                editor.commit();
//                startActivity(intent);

//                ntar dibalikin lagi ya
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
                    Toasty.error(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnKonf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences("Ip", MODE_PRIVATE);
                Intent intent = new Intent(getApplicationContext(), KonfigurasiActivity.class);
                String tvip = tvip0.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("tvip", tvip);
                editor.commit();
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

        private void loginUser() {

        String username99 = etusername0.getText().toString();
        String password99 = etpassword0.getText().toString();

            String[] user = {"nunu", "meli"};
            String[] pass = {"12345", "1234"};

            if (username99.equals("meli") && password99.equals("1234") || (username99.equals("nunu") && password99.equals("12345")) || (username99.equals("maria") && password99.equals("1234"))
                 || (username99.equals("devi") && password99.equals("1234")) || (username99.equals("nina") && password99.equals("1234")) || (username99.equals("adrian") && password99.equals("1234"))
                    || (username99.equals("buhari") && password99.equals("1234")) || (username99.equals("ichwan") && password99.equals("1234")) || (username99.equals("endang") && password99.equals("1234"))
                    || (username99.equals("nabila") && password99.equals("1234")) || (username99.equals("nur") && password99.equals("1234")) || (username99.equals("Rahmawati") && password99.equals("1234"))
                    || (username99.equals("Anita") && password99.equals("1234")) || (username99.equals("Anis") && password99.equals("1234")) || (username99.equals("Febriyani") && password99.equals("1234"))
                    || (username99.equals("SRukmana") && password99.equals("1234")) || (username99.equals("Dewi") && password99.equals("dewi1")) || (username99.equals("Yuli") && password99.equals("yuli1"))
                    || (username99.equals("Ghani") && password99.equals("1234")) || (username99.equals("rini") && password99.equals("ini01")) || (username99.equals("imas") && password99.equals("mas01"))
                    || (username99.equals("Gita") && password99.equals("ita01")) || (username99.equals("Aisah") && password99.equals("isah01")) || (username99.equals("Aidah2") && password99.equals("idah01"))
                    || (username99.equals("Rini") && password99.equals("ini01")) || (username99.equals("Aidah") && password99.equals("idah01")) || (username99.equals("fitri") && password99.equals("12345"))
                    || (username99.equals("Mila") && password99.equals("1234"))) {

                Log.e(TAG, "username = " + user);
                Log.e(TAG, "pass = " + pass);

                pref = getSharedPreferences("username", MODE_PRIVATE);
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String username = etusername0.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("etusername", username);
                editor.commit();
                startActivity(intent);


            } else {
                Toasty.error(MainActivity.this, "Password atau Username salah", Toast.LENGTH_SHORT).show();
            }
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
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

        Log.e("ip address = " ,  c.getAddress() + "shopfloor2/index.php/loginuser");
            Log.e("ip = ", String.valueOf(AndroidNetworking.post(c.getAddress() + "index.php/loginuser")));
//            AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/loginuser")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/loginuser")
                    .addJSONObjectBody(jObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                String message = response.getString("message");
                                String success = response.getString("success");
                                Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


                                if (success.equals("1")) {

                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                    pref = getSharedPreferences("username", MODE_PRIVATE);
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    String username = etusername0.getText().toString();
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("etusername", username);
                                    editor.commit();

                                    startActivity(intent);

//                                    //sementara di matiin dulu ya, ga bisa login soal nya
                                } else {
                                    Toasty.error(MainActivity.this, "Password atau Username salah", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toasty .error(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.isShowing();
    }
    }

