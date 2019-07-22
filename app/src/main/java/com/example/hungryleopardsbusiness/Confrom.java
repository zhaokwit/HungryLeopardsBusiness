package com.example.hungryleopardsbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;

public class Confrom extends AppCompatActivity {

    private CameraManager manager;
    Context context;
    String cameraId;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrom);

        context=Confrom.this;

        try {
            if(QRscanner.result1.equals("105171")) {
                manager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
                cameraId = manager.getCameraIdList()[0];
                manager.setTorchMode(cameraId, true);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }




    }
    public void lightOn(Context context) {
        try {

            manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode("0", true);
            Log.v("haha", "I am here2");

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    public void lightOff() {
        try {
            if (manager == null) {
                return;
            }
            manager.setTorchMode("0", false);
            manager = null;
        } catch (Exception e) {
        }
    }
}
