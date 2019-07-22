package com.example.hungryleopardsbusiness;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import java.util.Scanner;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;
import static android.content.Intent.ACTION_VIEW;

public class QRscanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView ScannerView;
    private  static  final int REQUEST_CAMERA = 1;

    private CameraManager manager;
    public static String result1 = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(QRscanner.this, " Permission is granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }




    }

    public void lightOn(Context context) {
        try {
            manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode("0", true);
            Log.v("check", "im here 1");

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(QRscanner.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private  void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permission[], int grantResults[])
    {
        switch (requestCode)
        {
            case REQUEST_CAMERA :
                if(grantResults.length > 0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(QRscanner.this, " Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(QRscanner.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                            }
                                        });

                                return;
                            }
                        }
                    }
                }

                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                if(ScannerView == null)
                {
                    ScannerView = new ZXingScannerView(this);
                    setContentView(ScannerView);
                }
                ScannerView.setResultHandler(this);
                ScannerView.startCamera();
            }
            else
            {
                requestPermission();

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ScannerView.stopCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }


    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(QRscanner.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(final Result result)
    {

        final String scanResult = result.getText();
        result1 = scanResult;

        Intent intent = new Intent(QRscanner.this,Confrom.class);
        startActivity(intent);

        Log.v("hR", "result" +scanResult);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");

//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//               Intent intent = new Intent(QRscanner.this, Confrom.class);
//               startActivity(intent);
//            }
//        });
//
//        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                ScannerView.resumeCameraPreview(QRscanner.this);
//            }
//        });
//        builder.setMessage(scanResult);
//        AlertDialog alert = builder.create();
//        alert.show();



    }


}
