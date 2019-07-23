package com.example.hungryleopardsbusiness;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail extends AppCompatActivity {

    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        //Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("Order Detail");








//        Johnny start
//        List<OrderDetailInfo> list = new ArrayList<OrderDetailInfo>();
//        OrderDetailInfo item1 = new OrderDetailInfo();
//        item1.ItemName = "Chicken";
//        item1.Quantity = "quantity: 2";
//        item1.Price = "$ 7.00";
//        list.add(item1);
//
//        OrderDetailInfo item2 = new OrderDetailInfo();
//        item2.ItemName = "Ice Cream";
//        item2.Quantity = "quantity: 1";
//        item2.Price = "$ 3.00";
//        list.add(item2);
//
//        OrderDetailInfo item3 = new OrderDetailInfo();
//        item3.ItemName = "Coke";
//        item3.Quantity = "quantity: 2";
//        item3.Price = "$ 5.00";
//        list.add(item3);
//
//        OrderDetailAdapter adapter;
//        adapter = new OrderDetailAdapter(this, 0,list);
//
//        ListView listView = (ListView)findViewById(R.id.View2);
//        listView.setAdapter(adapter);


    }
}
