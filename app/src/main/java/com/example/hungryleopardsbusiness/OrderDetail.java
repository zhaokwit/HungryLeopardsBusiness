package com.example.hungryleopardsbusiness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import java.text.NumberFormat;





public class OrderDetail extends AppCompatActivity {

    TextView toolbarTitle;
    ImageButton readybtn;
    TextView textViewData, orderNumber, orderDate, tp;
    String oN, oD, oNTV, totalPriceOP;
    double totalprice, ftotal, taxNum;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        //Tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("Order Detail");


        readybtn = (ImageButton) findViewById(R.id.ready);
        textViewData = (TextView)findViewById(R.id.textViewData);
        orderNumber = (TextView) findViewById(R.id.orderNumber);
        orderDate = (TextView) findViewById(R.id.date);
        tp = (TextView) findViewById(R.id.totalPrice);

        totalprice = 0;
        taxNum = 0;


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            oN = bundle.getString("OrderNum");
            oD = bundle.getString("OrderDate");
        }
        oNTV = "Order Number: " + oN;
        orderNumber.setText(oNTV);
        orderDate.setText(oD);

        loadNote();

        readybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DocumentReference statusRef = db.collection("OrdersNum").document(oN);
                statusRef.update("Status", "Ready to Pick up!");



                Intent intent = new Intent(OrderDetail.this, MainActivity.class);
                startActivity(intent);



            }
        });

    }



    public void loadNote()
    {

        db.collection(oN).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = " ";

                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots)
                        {
                            String itemName = documentSnapshot.getId();
                            String itemQty = Long.toString(documentSnapshot.getLong("Qty"));
                            String itemPrice = Double.toString(documentSnapshot.getDouble("Price"));

                            data +=  itemName + "\n Qty: " + itemQty + "\n Price: " + itemPrice + "\n\n" + " ";
                            textViewData.setText(data);

                            if (documentSnapshot != null) {
                                int itemQty2 = documentSnapshot.getLong("Qty").intValue();
                                double itemPrice2 = documentSnapshot.getDouble("Price");

                                totalprice = (double) itemQty2 * itemPrice2 + totalprice;

                                ftotal=0;
                                taxNum = totalprice*0.0625;
                                ftotal = taxNum+ftotal+totalprice;

                                NumberFormat format = NumberFormat.getCurrencyInstance();
                                totalPriceOP = "Subtotal: " + format.format(totalprice) +"\n" + "Tax: " + format.format(taxNum) +"\n" + "Total: " + format.format(ftotal);

                                tp.setText(totalPriceOP);
                            }

                        }



                    }
                });

    }

    public void updateNote() {

        db.collection(oN).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = " ";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String itemName = documentSnapshot.getId();
                            String itemQty = Long.toString(documentSnapshot.getLong("Qty"));
                            String itemPrice = Double.toString(documentSnapshot.getDouble("Price"));

                            data += itemName + "\n Qty: " + itemQty + "\n Price: " + itemPrice + "\n\n" + " ";
                            textViewData.setText(data);

                            if (documentSnapshot != null) {
                                int itemQty2 = documentSnapshot.getLong("Qty").intValue();
                                double itemPrice2 = documentSnapshot.getDouble("Price");

                                totalprice = (double) itemQty2 * itemPrice2 + totalprice;

                                ftotal = 0;
                                taxNum = totalprice * 0.0625;
                                ftotal = taxNum + ftotal + totalprice;

                                NumberFormat format = NumberFormat.getCurrencyInstance();
                                totalPriceOP = "Subtotal: " + format.format(totalprice) + "\n" + "Tax: " + format.format(taxNum) + "\n" + "Total: " + format.format(ftotal);

                                tp.setText(totalPriceOP);
                            }

                        }


                    }
                });
    }




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
