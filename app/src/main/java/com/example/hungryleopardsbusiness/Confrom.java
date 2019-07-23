package com.example.hungryleopardsbusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Confrom extends AppCompatActivity {

    private CameraManager manager;
    String cameraId;
    TextView resultText;
    TextView toolbarTitle;

    ImageButton backToHome;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrom);

        //Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("Order Confirm");

        resultText = findViewById(R.id.result);
        backToHome = findViewById(R.id.goHome);

        try {

            for (int i = 0; i < MainActivity.arrayList.size(); i++) {
                if (QRscanner.result1.equals(MainActivity.arrayList.get(i))){
                    resultText.setText("Order# " + MainActivity.arrayList.get(i) + "\n" + " Pick Up Successful");
                    manager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
                    cameraId = manager.getCameraIdList()[0];
                    manager.setTorchMode(cameraId, true);
                    db.collection("OrdersNumBusiness").document(MainActivity.arrayList.get(i)).delete();
                    DocumentReference statusRef = db.collection("OrdersNum").document(MainActivity.arrayList.get(i));
                    statusRef.update("Status", "Order has been picked!");
                    break;
                }
                else{

                    resultText.setText("Order Not Found");
                    backToHome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Confrom.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }

            }

        } catch (CameraAccessException e){
            e.printStackTrace();
        }

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Confrom.this, MainActivity.class);
                startActivity(intent);
                try {
                    if (manager == null) {
                        return;
                    }
                    manager.setTorchMode("0", false);
                    manager = null;
                } catch (Exception e) {
                }


            }
        });
    }
}
