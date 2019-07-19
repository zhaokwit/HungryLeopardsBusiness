package com.example.hungryleopardsbusiness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class OrderList  extends AppCompatActivity {

    Bundle bundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);

        ListView lists = findViewById(R.id.View);



        final List<OrderInfo> list = new ArrayList<OrderInfo>();
        OrderInfo item1 = new OrderInfo();
        item1.OrderNumber = "Order number 1";
        item1.Name = "David";
        item1.Date = "10/03/2020";
        list.add(item1);

        OrderInfo item2 = new OrderInfo();
        item2.OrderNumber = "Order number 2";
        item2.Name = "Jack";
        item2.Date = "10/03/2021";
        list.add(item2);

        OrderInfo item3 = new OrderInfo();
        item3.OrderNumber = "Order number 3";
        item3.Name = "Rax";
        item3.Date = "10/03/2022";
        list.add(item3);


        OrderAdapter adapter;
        adapter = new OrderAdapter(this, 0,list);

        ListView listView = (ListView)findViewById(R.id.View);
        listView.setAdapter(adapter);


        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);

                startActivity(intent);

            }
        });


        }


    }




