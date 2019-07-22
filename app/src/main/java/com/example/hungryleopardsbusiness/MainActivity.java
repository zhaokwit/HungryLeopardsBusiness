package com.example.hungryleopardsbusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout, Scan;
    public static TextView textView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView toolbarTitle;
    ImageButton logo;

    android.hardware.Camera Cam;
    android.hardware.Camera. Parameters parameters;
    boolean isflash = false;
    boolean ison = false;
    private CameraManager manager;
    public static ArrayList<String> arrayList;
    public static ArrayList<String> arrayListItem;
    public static ArrayList<String> arrayListOrderNum;
    public static ArrayList<String> arrayListDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);



        toolbarTitle.setText("Hungry Leopards - Busniess");

        linearLayout = findViewById(R.id.order);
        Scan = findViewById(R.id.scan);
        logo = findViewById(R.id.Logo);
        arrayList = new ArrayList<>();
        arrayListItem = new ArrayList<>();
        arrayListOrderNum = new ArrayList<>();
        arrayListDate = new ArrayList<>();

        db.collection("OrdersNumBusiness").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                            String orderNums = documentSnapshot.getId();
                            arrayList.add(orderNums);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
            }
        });

        db.collection("OrdersNumBusiness").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                            final String orderNums = documentSnapshot.getId();
                            String userID = documentSnapshot.getString("User");
                            final Date date = documentSnapshot.getTimestamp("Date").toDate();

                            db.collection("users").document(userID).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String userName=documentSnapshot.getString("Name");
                                            String listText = "Order Numers: " + orderNums + "\n" + date.toString() + "\n" + userName;
                                            arrayListItem.add(listText);
                                            arrayListOrderNum.add(orderNums);
                                            arrayListDate.add(date.toString());
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                                }
                            });



                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
            }
        });

        //textView = findViewById(R.id.result);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderList.class);
                startActivity(intent);


            }
        });

        Scan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                startActivity(new Intent(getApplicationContext(), QRscanner.class));

            }
        });






    }




}
