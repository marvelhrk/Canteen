package com.example.canteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.canteen.adapters.NewProductAdapter;
import com.example.canteen.models.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    TextView textview;
    RecyclerView recyclerView;
    NewProductAdapter newProductAdapter;
    List<Product> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        textview = findViewById(R.id.product);
        recyclerView = findViewById(R.id.recyclerviewproduct);



    }
}