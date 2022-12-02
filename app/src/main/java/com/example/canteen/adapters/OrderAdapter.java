package com.example.canteen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canteen.R;
import com.example.canteen.activities.cartlister;
import com.example.canteen.models.Order;
import com.example.canteen.utils.LocalStorage;
import com.google.gson.Gson;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    List<Order> orderList;
    Context context;
    int pQuantity = 1;
    String _subtotal, _price, _quantity;
    LocalStorage localStorage;
    Gson gson;

    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Order order = orderList.get(position);
        holder.orderId.setText(order.getOrderId());
        holder.date.setText(order.getDate());
        holder.total.setText(order.getTotal());
        holder.status.setText(order.getStatus());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, cartlister.class);
                intent.putExtra("orderId",order.getOrderId());
                intent.putExtra("total",order.getTotal());
                intent.putExtra("orderStat",order.getStatus());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, date, total, status;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            date = itemView.findViewById(R.id.date);
            total = itemView.findViewById(R.id.total_amount);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}

