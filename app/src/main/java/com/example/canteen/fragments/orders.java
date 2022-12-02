package com.example.canteen.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.canteen.R;
import com.example.canteen.activities.BaseActivity;
import com.example.canteen.adapters.OrderAdapter;
import com.example.canteen.models.Cart;
import com.example.canteen.models.Order;
import com.example.canteen.utils.LocalStorage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class orders extends Fragment {

    LinearLayout linearLayout;
    private List<Order> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;
    private Order order;
    FirebaseDatabase firebaseDatabase;

    public orders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.order_rv);
        initiate();
        mAdapter = new OrderAdapter(orderList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        linearLayout = view.findViewById(R.id.no_order_ll);
//        if (orderList.isEmpty()) {
//            linearLayout.setVisibility(View.VISIBLE);
//        }
        return view;
    }

    private void initiate() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Order order = data.getValue(Order.class);
                    Log.i(TAG, "onDataChange: order" + order);
                    orderList.add(order);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("MyOrder");
    }
}