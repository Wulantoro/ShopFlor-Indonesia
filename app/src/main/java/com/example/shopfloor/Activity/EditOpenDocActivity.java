package com.example.shopfloor.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditOpenDocActivity extends AppCompatActivity {

    private OpenDocAdapter openDocAdapter;
    private Gson gson;
    private Header header;
    private TextView tvNo_doc1;
    private TextView tvNo_prod1;
    private TextView tvprod1;
    private TextView tvNm_prod1;
    private TextView tvRoute_Code1;
    private TextView tvRoute_Code2;
    private TextView tvQty_rencProd1;
    private TextView tvSts_prod1;
    private TextView tvSquence1;
    private TextView tvSquence_Qty1;
    private Spinner tvShift1;
    private TextView tvTgl_mulai1;
    private TextView tvJam_mulai1;
    private TextView tvinqty4;
    private TextView tvworkcenter3;
    private TextView tvdocentry1;
    private TextView tvstatus;
    private TextView tvposted4;
    private TextView tvqcname1;
    private TextView tvusername5;
    private TextView tvcodeshift1;
    private ImageView imgTgl;
    private TextView tvmobileid;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvid1;
    private Button btsimpan;
    private String TAG = EditOpenDocActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_open_doc);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edit Open Document");

        TextView toolbarText = findViewById(R.id.toolbar_text);
        if (toolbarText != null && toolbar != null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        tvNo_doc1 = findViewById(R.id.tvNo_doc1);
        tvNo_prod1 = findViewById(R.id.tvNo_prod1);
        tvprod1 = findViewById(R.id.tvprod1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvRoute_Code1 = findViewById(R.id.tvRoute_Code1);
        tvRoute_Code2 = findViewById(R.id.tvRoute_Code2);
        tvQty_rencProd1 = findViewById(R.id.tvQty_rencProd1);
        tvSts_prod1 = findViewById(R.id.tvSts_prod1);
        tvSquence1 = findViewById(R.id.tvSquence1);
        tvSquence_Qty1 = findViewById(R.id.tvSquence_Qty1);
        tvShift1 = findViewById(R.id.tvShift1);
        tvTgl_mulai1 = findViewById(R.id.tvTgl_mulai1);
        tvJam_mulai1 = findViewById(R.id.tvJam_mulai1);
        tvinqty4 = findViewById(R.id.tvinqty4);
        tvworkcenter3 = findViewById(R.id.tvworkcenter3);
        tvdocentry1 = findViewById(R.id.tvdocentry1);
        tvstatus = findViewById(R.id.tvstatus);
        tvposted4 = findViewById(R.id.tvposted4);
        tvqcname1 = findViewById(R.id.tvqcname1);
        tvusername5 = findViewById(R.id.tvusername5);
        tvcodeshift1 = findViewById(R.id.tvcodeshift1);
//        tvnamawc3 = findViewById(R.id.tvnamawc3);
//        tvid1 = findViewById(R.id.tvid1);
        imgTgl = findViewById(R.id.imgTgl);
        tvid1 = findViewById(R.id.tvid1);
        tvmobileid = findViewById(R.id.tvmobileid);
        btsimpan = findViewById(R.id.btsimpan);


        openDocAdapter = new OpenDocAdapter(this);
        gson = new Gson();

        header = getIntent().getParcelableExtra("edit_doc");
        tvNo_doc1.setText(header.getDocNum());
        tvNo_prod1.setText(header.getProdNo());
        tvprod1.setText(header.getProdCode());
        tvNm_prod1.setText(header.getProdName());
        tvRoute_Code1.setText(header.getRouteCode());
        tvRoute_Code2.setText(header.getRouteName());
        tvQty_rencProd1.setText(header.getProdPlanQty().replace(".000000", ""));
        tvSts_prod1.setText(header.getProdStatus());
        tvSquence1.setText(header.getSequence());
        tvSquence_Qty1.setText(header.getSequenceQty().replace(".000000", ""));
//        tvShift1.setText(header.getShiftName());
//        tvShift1.getItemAtPosition(tvShift1.getSelectedItemPosition()).toString();
        tvTgl_mulai1.setText(header.getTanggalMulai().substring(0, 10));
        tvJam_mulai1.setText(header.getJamMulai());
        tvinqty4.setText(header.getInQty());
        tvworkcenter3.setText(header.getWorkCenter());
        tvdocentry1.setText(String.valueOf(header.getDocEntry()));
        tvstatus.setText(header.getStatus());
        tvposted4.setText(String.valueOf(header.getPosted()));
        tvusername5.setText(header.getUserId());
        tvcodeshift1.setText(header.getShift());
        tvid1.setText(String.valueOf(header.getId()));
        tvmobileid.setText(header.getMobileId());

        String[] sift = {"Senin - Shift 1", "Senin - Shift 2", "Senin - Shift 3", "Selasa - Shift 1", "Selasa - Shift 2", "Selasa - Shift 3", "Rabu - Shift 1",
                "Rabu - Shift 2", "Rabu - Shift 3", "Kamis - Shift 1", "Kamis - Shift 2", "Kamis - Shift 3", "Jumat - Shift 1",
                "Jumat - Shift 3", "Sabtu - Shift 1", "Sabtu - Shift 2", "Sabtu - Shift 3", "Minggu - Shift 1", "Minggu - Shift 2", "Minggu - Shift 3"
        };

//        tvShift1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sift));

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sift);
        tvShift1.setAdapter(adapter);

        tvShift1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Selected "+ adapter.getItem(i), Toast.LENGTH_SHORT).show();

                String shift = adapter.getItem(i);

                TextView codeshitf = findViewById(R.id.tvcodeshift1);
                if (shift.equalsIgnoreCase("Senin - Shift 1")) {
                    codeshitf.setText("SS1");
                }else if (shift.equalsIgnoreCase("Senin - Shift 2")) {
                    codeshitf.setText("SS2");
                }else if (shift.equalsIgnoreCase("Senin - Shift 3")) {
                    codeshitf.setText("SS3");
                }else if (shift.equalsIgnoreCase("Selasa - Shift 1")) {
                    codeshitf.setText("SlS1");
                }else if (shift.equalsIgnoreCase("Selasa - Shift 2")) {
                    codeshitf.setText("SlS2");
                }else if (shift.equalsIgnoreCase("Selasa - Shift 3")) {
                    codeshitf.setText("SlS3");
                }else if (shift.equalsIgnoreCase("Rabu - Shift 1")) {
                    codeshitf.setText("RbS1");
                }else if (shift.equalsIgnoreCase("Rabu - Shift 2")) {
                    codeshitf.setText("RbS2");
                }else if (shift.equalsIgnoreCase("Rabu - Shift 3")) {
                    codeshitf.setText("RbS3");
                }else if (shift.equalsIgnoreCase("Kamis - Shift 1")) {
                    codeshitf.setText("KmS1");
                }else if (shift.equalsIgnoreCase("Kamis - Shift 2")) {
                    codeshitf.setText("KmS2");
                }else if (shift.equalsIgnoreCase("Kamis - Shift 3")) {
                    codeshitf.setText("KmS3");
                }else if (shift.equalsIgnoreCase("Jumat - Shift 1")) {
                    codeshitf.setText("JmS1");
                }else if (shift.equalsIgnoreCase("Jumat - Shift 2")) {
                    codeshitf.setText("JmS1");
                }else if (shift.equalsIgnoreCase("Jumat - Shift 3")) {
                    codeshitf.setText("JmS3");
                }else if (shift.equalsIgnoreCase("Sabtu - Shift 1")) {
                    codeshitf.setText("SbS1");
                }else if (shift.equalsIgnoreCase("Sabtu - Shift 2")) {
                    codeshitf.setText("SbS2");
                }else if (shift.equalsIgnoreCase("Sabtu - Shift 3")) {
                    codeshitf.setText("SbS3");
                }else if (shift.equalsIgnoreCase("Minggu - Shift 1")) {
                    codeshitf.setText("MgS1");
                }else if (shift.equalsIgnoreCase("Minggu - Shift 2")) {
                    codeshitf.setText("MgS2");
                }else if (shift.equalsIgnoreCase("Minggu - Shift 3")) {
                    codeshitf.setText("MgS3");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        imgTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        btsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editHeader();
            }
        });

    }

    private void showDialog() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvTgl_mulai1.setText(dateFormatter.format(newDate.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void editHeader() {

        JSONObject jsonObject = new JSONObject();

        try {
            //ntar arrau nya diilangin lagi ya
            JSONArray newArr = new JSONArray();

            jsonObject.put("docEntry", tvdocentry1.getText().toString());
            jsonObject.put("docNum", tvNo_doc1.getText().toString());
            jsonObject.put("prodNo", tvNo_prod1.getText().toString());
            jsonObject.put("prodCode", tvprod1.getText().toString());
            jsonObject.put("prodName", tvNm_prod1.getText().toString());
            jsonObject.put("prodPlanQty", tvQty_rencProd1.getText().toString());
            jsonObject.put("prodStatus", tvSts_prod1.getText().toString());
            jsonObject.put("routeCode", tvRoute_Code1.getText().toString());
            jsonObject.put("routeName", tvRoute_Code2.getText().toString());
            jsonObject.put("sequence", tvSquence1.getText().toString());
            jsonObject.put("sequenceQty", tvSquence_Qty1.getText().toString());
            jsonObject.put("shiftName", tvShift1.getItemAtPosition(tvShift1.getSelectedItemPosition()).toString());
            jsonObject.put("shift", tvcodeshift1.getText().toString());
            jsonObject.put("docDate", tvTgl_mulai1.getText().toString());
            jsonObject.put("tanggalMulai", tvTgl_mulai1.getText().toString());
            jsonObject.put("jamMulai", tvJam_mulai1.getText().toString());
            jsonObject.put("inQty", tvinqty4.getText().toString());
//            jsonObject.put("outQty", tvOutputQty1.getText().toString());
            jsonObject.put("workCenter", tvworkcenter3.getText().toString());
//            jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
//            jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
//            jsonObject.put("status", tvstatus0.getText().toString());
            jsonObject.put("posted", tvposted4.getText().toString());
//            jsonObject.put("UploadTime", tvjamsel1.getText().toString()); muncul otomatis
//            jsonObject.put("QcName", tvqcname4.getText().toString());
            jsonObject.put("userId", tvusername5.getText().toString());
            jsonObject.put("id", tvid1.getText().toString());
            jsonObject.put("mobileId", tvmobileid.getText().toString());

            newArr.put(jsonObject);
                Log.e(TAG, "coba edit = " + newArr.toString(1));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

//        AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/SincReject")
            AndroidNetworking.put(c.getAddress() + "shopfloor2/index.php/simpanheader?docEntry")
//            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/UploadSap")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toasty.success(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(getApplicationContext(), DetailOpenDocActivity.class);
//                                startActivity(intent);
                                finish();


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toasty.error(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }


}