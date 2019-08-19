package com.example.shopfloor.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.example.shopfloor.Models.Productorder;
import com.example.shopfloor.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.Result;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;
    private Productorder productorder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);

    }

    @Override
    public void onScanned(Barcode barcode) {

        Intent intent = new Intent(ScanActivity.this, Add_DocActivity.class);
        intent.putExtra("wccode", barcode.displayValue);
        startActivity(intent);

    }



    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        finish();
    }
}



//public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
//
//    private ZXingScannerView mScannerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan);
//
//        mScannerView = new ZXingScannerView(this);
//        setContentView(mScannerView);
//    }
//
//    public void onResume() {
//        super.onResume();
//        mScannerView.setResultHandler(this);
//        mScannerView.startCamera();
//    }
//
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();
//    }
//
//    @Override
//    public void handleResult(Result rawResult) {
//        Log.v("TAG", rawResult.getText()); // Prints scan results
//        Log.v("TAG", rawResult.getBarcodeFormat().toString());
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");
//        builder.setMessage(rawResult.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();
//
//        mScannerView.resumeCameraPreview(this);
//    }
//}
