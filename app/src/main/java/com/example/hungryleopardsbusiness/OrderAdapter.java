package com.example.hungryleopardsbusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<OrderInfo> {

    private LayoutInflater mInflater;
    public OrderAdapter (Context context, int rid, List<OrderInfo> list){
        super (context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){

        OrderInfo item =(OrderInfo) getItem(position);
        View view =  mInflater.inflate(R.layout.order_list_item, null);


        TextView order;
        order = (TextView)view.findViewById(R.id.order);
        order.setText(item.OrderNumber);

        TextView name;
        name = (TextView) view.findViewById(R.id.name);
        name.setText(item.Name);

        TextView date;
        date = (TextView) view.findViewById(R.id.date);
        date.setText(item.Date);

        return view;




    }
}
