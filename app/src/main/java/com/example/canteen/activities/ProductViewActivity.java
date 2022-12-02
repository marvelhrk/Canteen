package com.example.canteen.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.models.Cart;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductViewActivity extends BaseActivity {
    private static int cart_count = 0;
    public TextView quantity, inc, dec;
    String _id, _title, _image, _description, _price, _currency, _discount, _attribute;
    TextView id, title, description, price, currency, discount, attribute;
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout addToCart, share;
    RelativeLayout quantityLL;
    List<Cart> cartList = new ArrayList<>();
    int cartId;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);


        Intent intent = getIntent();

        _id = intent.getStringExtra("id");
        _title = intent.getStringExtra("title");
        _image = intent.getStringExtra("image");
        _description = intent.getStringExtra("description");
        _price = intent.getStringExtra("price");
        _currency = intent.getStringExtra("currency");
        _discount = intent.getStringExtra("discount");
        _attribute = intent.getStringExtra("attribute");

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
//        changeActionBarTitle(getSupportActionBar());
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        cart_count = cartCount();

        title = findViewById(R.id.apv_title);
        description = findViewById(R.id.apv_description);
        currency = findViewById(R.id.apv_currency);
        price = findViewById(R.id.apv_price);
        attribute = findViewById(R.id.apv_attribute);
        discount = findViewById(R.id.apv_discount);
        imageView = findViewById(R.id.apv_image);
        progressBar = findViewById(R.id.progressbar);
        addToCart = findViewById(R.id.add_to_cart_ll);
        share = findViewById(R.id.apv_share);
        quantityLL = findViewById(R.id.quantity_rl);
        quantity = findViewById(R.id.quantity);
        inc = findViewById(R.id.quantity_plus);
        dec = findViewById(R.id.quantity_minus);

        cartList = getCartList();
        title.setText(_title);
        description.setText(_description);
        price.setText(_price);
        currency.setText(_currency);
        attribute.setText(_attribute);
        discount.setText(_discount);
//        Log.d(TAG, "Discount : " + _discount);
        if (_discount != null || _discount.length() != 0 || _discount != "") {
            discount.setVisibility(View.GONE);
        } else {
            discount.setVisibility(View.GONE);
        }
        if (_image != null) {
            Picasso.get().load(_image).error(R.drawable.search).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        if (!cartList.isEmpty()) {
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getId().equalsIgnoreCase(_id)) {
                    addToCart.setVisibility(View.GONE);
                    quantityLL.setVisibility(View.VISIBLE);
                    quantity.setText(cartList.get(i).getQuantity());
                    cartId = i;

                }
            }
        }


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEntry = _image + "\n" + _title + "\n" + _description + "\n" + _attribute + "-" + _currency + _price + "(" + _discount + ")";

                Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                textShareIntent.putExtra(Intent.EXTRA_TEXT, userEntry);
                textShareIntent.setType("text/plain");
                startActivity(textShareIntent);
            }
        });


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _price = price.getText().toString();

                cart = new Cart(_id, _title, _image, _currency, _price, _attribute, "1", _price);
                cartList.add(cart);
                String cartStr = gson.toJson(cartList);
                //Log.d("CART", cartStr);
                localStorage.setCart(cartStr);
                onAddProduct();
                addToCart.setVisibility(View.GONE);
                quantityLL.setVisibility(View.VISIBLE);
            }
        });


        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _price = price.getText().toString();
                // int total_item = Integer.parseInt(cartList.get(cartId).getQuantity());
                int total_item = Integer.parseInt(quantity.getText().toString());
                total_item++;
                Log.d("totalItem", total_item + "");
                quantity.setText(total_item + "");
                String subTotal = String.valueOf(Double.parseDouble(_price) * total_item);
                cartList.get(cartId).setQuantity(quantity.getText().toString());
                cartList.get(cartId).setSubTotal(subTotal);
                cartList.get(cartId).setAttribute(attribute.getText().toString());
                cartList.get(cartId).setPrice(_price);
                String cartStr = gson.toJson(cartList);
                //Log.d("CART", cartStr);
                localStorage.setCart(cartStr);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _price = price.getText().toString();

                //int total_item = Integer.parseInt(quantity.getText().toString());
                int total_item = Integer.parseInt(quantity.getText().toString());
                if (total_item != 1) {
                    total_item--;
                    quantity.setText(total_item + "");
                    Log.d("totalItem", total_item + "");
                    String subTotal = String.valueOf(Double.parseDouble(_price) * total_item);


                    cartList.get(cartId).setQuantity(quantity.getText().toString());
                    cartList.get(cartId).setSubTotal(subTotal);
                    cartList.get(cartId).setAttribute(attribute.getText().toString());
                    cartList.get(cartId).setPrice(_price);
                    String cartStr = gson.toJson(cartList);
                    //Log.d("CART", cartStr);
                    localStorage.setCart(cartStr);
                }
            }
        });


    }

    @Override
    public void onAddProduct() {
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();

    }

}
