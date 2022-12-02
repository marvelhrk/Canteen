package com.example.canteen.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.canteen.CartActivity;
import com.example.canteen.R;
import com.example.canteen.activities.BaseActivity;
import com.example.canteen.activities.MainScreen;
import com.example.canteen.adapters.CheckoutCartAdapter;
import com.example.canteen.models.Cart;
import com.example.canteen.models.Order;
import com.example.canteen.models.UserAddress;
import com.example.canteen.utils.LocalStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ConfirmFragment extends Fragment {
    private DatabaseReference mdata;
    FirebaseDatabase firebaseDatabase;
    LocalStorage localStorage;
    List<Cart> cartList = new ArrayList<>();
    Gson gson;
    RecyclerView recyclerView;
    CheckoutCartAdapter adapter;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    TextView back, placeOrder;
    TextView total, shipping, totalAmount;
    Double _total, _shipping, _totalAmount;
    ProgressDialog progressDialog;
    UserAddress userAddress;
    String name,address,mobile,email;
    List<Order> orderList = new ArrayList<>();
    String orderNo;
    String id;


    public ConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        Bundle bundle = this.getArguments();
        name = bundle.getString("Name");
        address = bundle.getString("Address");
        mobile = bundle.getString("Mobile");
        email = bundle.getString("Email");

        localStorage = new LocalStorage(getContext());
        recyclerView = view.findViewById(R.id.cart_rv);
        totalAmount = view.findViewById(R.id.total_amount);
        total = view.findViewById(R.id.total);
        shipping = view.findViewById(R.id.shipping_amount);
        back = view.findViewById(R.id.back);
        placeOrder = view.findViewById(R.id.place_order);
        progressDialog = new ProgressDialog(getContext());
        gson = new Gson();
        userAddress = gson.fromJson(localStorage.getUserAddress(), UserAddress.class);
        orderList = ((BaseActivity) getActivity()).getOrderList();


        Random rnd = new Random();
        orderNo = "Order " + (100000 + rnd.nextInt(900000));
        setUpCartRecyclerview();

        if (orderList.isEmpty()) {
            id = "1";
        } else {
            id = String.valueOf(orderList.size() + 1);
        }
        _total = ((BaseActivity) getActivity()).getTotalPrice();
        _shipping = 0.0;
        _totalAmount = _total + _shipping;
        total.setText(_total + "");
        shipping.setText(_shipping + "");
        totalAmount.setText(_totalAmount + "");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait....");
                progressDialog.show();
                closeProgress();

            }
        });
        return view;
    }

    private void closeProgress() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();

            }
        }, 3000); // 5000 milliseconds

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        Order order = new Order(id,FirebaseAuth.getInstance().getCurrentUser().getUid(), orderNo, currentDateandTime, "Rs. " + _totalAmount, "Pending",name,address,mobile,cartList);
        orderList.add(order);
        String orderString = gson.toJson(orderList);
        localStorage.setOrder(orderString);
        localStorage.deleteCart();

        firebaseDatabase.getInstance().getReference("Orders")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(orderNo)
                .setValue(order);

        firebaseDatabase.getInstance().getReference("All Orders")
                .child(orderNo)
                .setValue(order);

        showCustomDialog();
    }

    private void showCustomDialog() {

        // Create custom dialog object
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        // Include dialog.xml file
        dialog.setContentView(R.layout.success_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        Button button = dialog.findViewById(R.id.lesgo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainScreen.class));
                getActivity().finish();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startActivity(new Intent(getContext(), MainScreen.class));
                getActivity().finish();
            }
        });
        // Set dialog title

        dialog.show();
    }

    private void setUpCartRecyclerview() {

        cartList = new ArrayList<>();
        cartList = ((BaseActivity) getContext()).getCartList();
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        adapter = new CheckoutCartAdapter(cartList, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Confirm");
    }


}