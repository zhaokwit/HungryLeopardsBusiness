package com.example.hungryleopardsbusiness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class OrderList  extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        //Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("Orders");

        listView = findViewById(R.id.orderlist);
        arrayAdapter = new ArrayAdapter<String>(OrderList.this,android.R.layout.simple_list_item_activated_1,MainActivity.arrayListItem);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                String orderNum = MainActivity.arrayListOrderNum.get(position);
                String orderDate = MainActivity.arrayListDate.get(position);
                intent.putExtra("OrderNum", orderNum);
                intent.putExtra("OrderDate", orderDate);

                startActivity(intent);

            }
        });


        }


    }




