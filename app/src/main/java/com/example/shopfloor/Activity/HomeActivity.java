package com.example.shopfloor.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.example.shopfloor.MainActivity;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.User;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;
import com.example.shopfloor.Utils.SharedPrefManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {

    private Button btnWorkcenter;
//    private ImageButton btnstartdoc;
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
    public static TextView tvnamawc;
    private TextView tvTanggalHome;
    private TextView tvidmobile0;
    private TextView tvip1;

    private Button btnLogout;

    public SharedPreferences pref, prf;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar();
        tvWCtampil1 = findViewById(R.id.tvWCtampil1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvusername0 = findViewById(R.id.tvusername0);
        tvdivisi0 = findViewById(R.id.tvdivisi0);
        tvTanggalHome = findViewById(R.id.tvTanggalHome);
        tvnamawc = findViewById(R.id.tvnamawc);
        tvidmobile0 = findViewById(R.id.tvidmobile0);
        tvip1 = findViewById(R.id.tvip1);

        final SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);

//***********************************drawerrrr layout***********************************/
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.closedDrawr);
//
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        setupDrawerContent(navigationView);
        /*********************************************************/


//        tvidmobile0.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

        User user = new User();

        TextView tvdivisi = findViewById(R.id.tvdivisi0);
        tvdivisi.setText(String.valueOf(user.getDept()));
//
        TextView tvusername = findViewById(R.id.tvusername0);
        prf = getSharedPreferences("username", MODE_PRIVATE);
        tvusername.setText(prf.getString("etusername", null));

        TextView tvnamewc = findViewById(R.id.tvnamawc);
        prf = getSharedPreferences("Namewc", MODE_PRIVATE);
        tvnamewc.setText(prf.getString("tvnamewc", null));

        TextView tvwc = findViewById(R.id.tvWCtampil1);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter1", null));

//        TextView tvipadd = findViewById(R.id.tvip1);
//        prf = getSharedPreferences("Ip", MODE_PRIVATE);
//        tvipadd.setText(prf.getString("tvip", null));

        //        Setup Realm
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
        tvip1.setText(text);

        /************result workcenter terpilih***********************/
        if (getIntent().hasExtra("keywc")) {
            String keywc = getIntent().getStringExtra("keywc");
            tvWCtampil1.setText(keywc);
        }

        if (getIntent().hasExtra("keynamawc")) {
            String keynamawc = getIntent().getStringExtra("keynamawc");
            tvnamawc.setText(keynamawc);
        }
        /*******************************************************************/

        Calendar c = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String getCurentDate = sdf.format(c.getTime());
        tvTanggalHome.setText(getCurentDate);

        /**********end tampil tanggal**************************/

        userLogin();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
//                        Intent intent = new Intent(HomeActivity.this, Main2Activity.class);
//                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Upload Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation2:
                        Toast.makeText(getApplicationContext(),"Profil Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation3:
                        Toast.makeText(getApplicationContext(),"Daftar Telah Dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation4:
                        Toast.makeText(getApplicationContext(),"Setting telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation5:
                        Toast.makeText(getApplicationContext(),"About telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation6:
                        Toast.makeText(getApplicationContext(),"About telah dipilih",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer, R.string.closedDrawr){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();





        btnWorkcenter = findViewById(R.id.btnWorkcenter);
        btnWorkcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pref = getSharedPreferences("Ip", MODE_PRIVATE);
//
//                String tvipadd = tvip1.getText().toString();
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("tvip", tvipadd);
//                editor.commit();
                Intent iWork = new Intent(getApplicationContext(), WorkcenterListActivity.class);
                startActivity(iWork);
            }
        });


        btnstartdoc = findViewById(R.id.btnstartdoc);
        btnstartdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvWCtampil1.length() != 0) {
//
                    pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                    Intent iStart = new Intent(HomeActivity.this, StartDocActivity.class);

                    String wc = tvWCtampil1.getText().toString();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("workcenter0", wc);
                    editor.commit();

                    pref = getSharedPreferences("userId", MODE_PRIVATE);
                    String tvuserid = tvusername0.getText().toString();
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putString("tvuserid", tvuserid);
                    editor1.commit();

                    pref = getSharedPreferences("Namewc", MODE_PRIVATE);
                    String tvnamewc = tvnamawc.getText().toString();
                    SharedPreferences.Editor editor2 = pref.edit();
                    editor2.putString("tvnamewc", tvnamewc);
                    editor2.commit();

//                    pref = getSharedPreferences("Ip", MODE_PRIVATE);
//                    String tvipadd = tvip1.getText().toString();
//                    SharedPreferences.Editor editor3 = pref.edit();
//                    editor3.putString("tvip", tvipadd);
//                    editor3.commit();
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
                    editor.putString("workcenter1", wc1);
                    editor.commit();

                    pref = getSharedPreferences("Username", MODE_PRIVATE);
                    String tvqcname = tvusername0.getText().toString();
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putString("tvusername", tvqcname);
                    editor1.commit();

                    pref = getSharedPreferences("keynamawc", MODE_PRIVATE);
                    String tvnamewc = tvnamawc.getText().toString();
                    SharedPreferences.Editor editor2 = pref.edit();
                    editor2.putString("keynamawc1", tvnamewc);
                    editor2.commit();

//                    pref = getSharedPreferences("Ip", MODE_PRIVATE);
//                    String tvipadd = tvip1.getText().toString();
//                    SharedPreferences.Editor editor3 = pref.edit();
//                    editor3.putString("tvip", tvipadd);
//                    editor3.commit();

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
                    editor1.putString("workcenter1", wc2);
                    editor1.commit();


                    startActivity(iSuc);
                } else {
                    Toast.makeText(getApplicationContext(), "Pilih Workcenter dahulu", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void userLogout() {
        final SharedPrefManager sharedPrefManager;
        sharedPrefManager = new SharedPrefManager(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Really Logout?")
                .setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();

                    }
                }).create().show();
    }

            private void userLogin() {

                Realm realm = Realm.getDefaultInstance();
                RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
                String text = "";
                for (ServerModel c:results1) {
                    text = text + c.getAddress();

                    prf = getSharedPreferences("username", MODE_PRIVATE);
                    prf.getString("etusername", null);
                    Log.e("username = ", prf.getString("etusername", null));

//        AndroidNetworking.get(GlobalVars.BASE_IP + "shopfloor2/index.php/loginuser?U_STEM_Username="+prf.getString("etusername", null))
                    AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/loginuser?U_STEM_Username=" + prf.getString("etusername", null))
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
//                                        TextView tvdept = findViewById(R.id.tvdivisi0);
//                                        tvdept.setText(String.valueOf(user.getDept()));
                                                }
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });
                }
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


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            userLogout();
        }
        return super.onOptionsItemSelected(item);
    }
//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                selectDrawerItem(menuItem);
//
//                return true;
//            }
//        });
//    }

//    //method untuk eksekusi action dari tiap menu item
//    public void selectDrawerItem(MenuItem menuItem) {
//        Fragment fragment = null;
//        Class fragmentClass;
//        final SharedPrefManager sharedPrefManager;
//        sharedPrefManager = new SharedPrefManager(this);
//
//        int id = menuItem.getItemId();
//
//        switch (id){
//            case R.id.home:
//                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.nav_manage:
//                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_slideshow:
//                Toast.makeText(getApplicationContext(),"Trash",Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.nav_logout:
//                finish();
//
//        }

//        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//        startActivity(new Intent(getApplicationContext(), MainActivity.class)
//                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        finish();
        /******************************************************************************/

//        int id = R.id.nav_logout;
//        if (menuItem.getItemId() == R.id.nav_logout) {
//            userLogout();
//        }
/**************************************************/
//        switch (menuItem.getItemId()) {
//            case R.id.nav_logout:
//                //action
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//
//                break;
//            case R.id.nav_slideshow:
//                //action
//                break;
//            case R.id.nav_manage:
//                //action
//                break;
//
//        }
//
//
//        menuItem.setChecked(true);
//        mDrawerLayout.closeDrawers();
//    }
//
//    public void SelectItem(int possition) {
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem){
//        if(mToggle.onOptionsItemSelected(menuItem)){
//            return true;
//        }
//
//        menuItem.setChecked(true);
//        mDrawerLayout.closeDrawers();
//
//        return super.onOptionsItemSelected(menuItem);
//    }

}




