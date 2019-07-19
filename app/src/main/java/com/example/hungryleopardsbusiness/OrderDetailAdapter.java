package com.example.hungryleopardsbusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetailInfo> {

    private LayoutInflater mInflater;
    public OrderDetailAdapter (Context context, int rid, List<OrderDetailInfo> list){
        super (context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){

        OrderDetailInfo item =(OrderDetailInfo) getItem(position);
        View view =  mInflater.inflate(R.layout.order_detail_item, null);


        TextView order;
        order = (TextView)view.findViewById(R.id.itemName);
        order.setText(item.ItemName);

        TextView name;
        name = (TextView) view.findViewById(R.id.quantity);
        name.setText(item.Quantity);

        TextView date;
        date = (TextView) view.findViewById(R.id.price);
        date.setText(item.Price);

        return view;




    }
}
