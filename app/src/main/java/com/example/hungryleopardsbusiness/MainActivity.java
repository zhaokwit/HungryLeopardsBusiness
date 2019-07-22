package com.example.hungryleopardsbusiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout, Scan;
    public static TextView textView;

    TextView toolbarTitle;

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
        //textView = findViewById(R.id.result);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderList.class);
                startActivity(intent);


                Scan.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        startActivity(new Intent(getApplicationContext(), QRscanner.class));
                    }
                });

            }
        });


    }
}
