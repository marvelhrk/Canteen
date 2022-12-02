package com.example.canteen.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.adapters.CartAdapter;
import com.example.canteen.adapters.CheckoutCartAdapter;
import com.example.canteen.adapters.OrderAdapter;
import com.example.canteen.models.Cart;
import com.example.canteen.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cartlister extends AppCompatActivity {
    TextView id, total, status;
//    ArrayList<Cart> cartlist;
    RecyclerView recyclerView;
    Order order;
    String orderId,orderTotal,orderStatus;
    List<Cart> cartList = null;
    CheckoutCartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlister);

        id = findViewById(R.id.sa_id);
        total = findViewById(R.id.sa_total);
        status = findViewById(R.id.sa_status);
        cartList = new ArrayList<>();
        orderId = getIntent().getStringExtra("orderId");
        orderTotal = getIntent().getStringExtra("total");
        orderStatus = getIntent().getStringExtra("orderStat");

        initiate();

        id.setText(orderId);
        total.setText(orderTotal);
        status.setText(orderStatus);


        recyclerView = findViewById(R.id.recyclervieworder);
        cartAdapter = new CheckoutCartAdapter(cartList, cartlister.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(cartlister.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cartAdapter);

    }

    private void initiate() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(orderId).child("cart");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Cart cart = data.getValue(Cart.class);
                    cartList.add(cart);
                    Log.i(TAG, "onDataChange: order" + order);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}