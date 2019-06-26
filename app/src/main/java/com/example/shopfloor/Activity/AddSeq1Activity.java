package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.CriteriaActivity;
import com.example.shopfloor.R;

public class AddSeq1Activity extends AppCompatActivity {

    SharedPreferences prf, pref;
    Intent intent;

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;
    TextView tvInputseq;
    private TextView tvOutputSeq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seq1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);


        tvOutputSeq = findViewById(R.id.tvOutputSeq);

        /****************nilai input qty seq*******************/
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);

        /**************nilai output qty***********************/
        pref = getSharedPreferences("outQty", MODE_PRIVATE);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_outqty, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_outqty && tvOutputSeq.length() != 0) {
            String outqty = tvOutputSeq.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("outqty", outqty);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), CriteriaActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Output Quantity tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn1Clicked(View v) {
        insert(1);
    }

    public void btn2Clicked(View v) {
        insert(2);
    }
    public void btn3Clicked(View v) {
        insert(3);
    }
    public void btn4Clicked(View v) {
        insert(4);
    }
    public void btn5Clicked(View v) {
        insert(5);
    }
    public void btn6Clicked(View v) {
        insert(6);
    }
    public void btn7Clicked(View v) {
        insert(7);
    }
    public void btn8Clicked(View v) {
        insert(8);
    }
    public void btn9Clicked(View v) {
        insert(9);
    }
    public void btn0Clicked(View v) {
        insert(0);
    }
    public void btnclearClicked(View v) {
        clear();
    }

    private void clear() {
        str = "";
        num = 0;
        numtemp = 0;
        tvOutputSeq.setText("");
    }

    private void insert(int i) {
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvOutputSeq.setText(str);
    }
}
