package com.example.canteen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.adapters.CartAdapter;
import com.example.canteen.models.Cart;
import com.example.canteen.utils.LocalStorage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class mycart extends Fragment implements View.OnClickListener {
    LocalStorage localStorage;
    List<Cart> cartList = new ArrayList<>();
    Gson gson;
    RecyclerView recyclerView;
    CartAdapter adapter;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    ImageView emptyCart;
    LinearLayout checkoutLL;
    TextView totalPrice;
    View view;
    private String mState = "SHOW_MENU";

    public mycart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mycart, container, false);
        localStorage = new LocalStorage(getContext());
        gson = new Gson();
//        checkoutLL = view.findViewById(R.id.checkout_LL);
//        totalPrice = view.findViewById(R.id.total_price);
//        totalPrice.setText("Rs. " + getTotalPrice() + "");
//        setUpCartRecyclerview();
        // Inflate the layout for this fragment
        return view;
    }
//    private void setUpCartRecyclerview() {
//        cartList = new ArrayList<>();
//        cartList = getCartList();
//        if (cartList.isEmpty()) {
//            mState = "HIDE_MENU";
//            invalidateOptionsMenu();
//            emptyCart.setVisibility(View.VISIBLE);
//            checkoutLL.setVisibility(View.GONE);
//        }
//        recyclerView = view.findViewById(R.id.cart_rv);
//        recyclerView.setHasFixedSize(true);
//        recyclerViewlayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(recyclerViewlayoutManager);
//        adapter = new CartAdapter(cartList, getContext());
//        recyclerView.setAdapter(adapter);
//    }


//    public void onCheckoutClicked(View view) {
//
//        startActivity(new Intent(getContext(), CheckoutActivity.class));
//    }
//
//
//    @Override
//    public void updateTotalPrice() {
//        totalPrice.setText("Rs. " + getTotalPrice() + "");
//        if (getTotalPrice() == 0.0) {
//            mState = "HIDE_MENU";
//            invalidateOptionsMenu();
//            emptyCart.setVisibility(View.VISIBLE);
//            checkoutLL.setVisibility(View.GONE);
//        }
//    }
    @Override
    public void onClick(View v) {

    }
}