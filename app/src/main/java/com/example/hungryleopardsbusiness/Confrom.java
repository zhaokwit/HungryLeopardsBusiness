package com.example.hungryleopardsbusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class Confrom extends AppCompatActivity {

    private CameraManager manager;
    String cameraId;
    TextView resultText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrom);

        resultText=findViewById(R.id.result);

        try {
            for (int i = 0; i < MainActivity.arrayList.size(); i++) {
                if (QRscanner.result1.equals(MainActivity.arrayList.get(i))){
                    resultText.setText("Order# " + MainActivity.arrayList.get(i) + " Pick Up Successful");
                    manager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
                    cameraId = manager.getCameraIdList()[0];
                    manager.setTorchMode(cameraId, true);
                    break;
                }
                else{
                    resultText.setText("Order Not Found");
                    break;
                }
            }
        } catch (CameraAccessException e){
            e.printStackTrace();
        }





    }
//    public void lightOn(Context context) {
//        try {
//
//            manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
//            manager.setTorchMode("0", true);
//            Log.v("haha", "I am here2");
//
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//
//    }

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
