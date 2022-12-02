package com.example.canteen.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.canteen.R;
import com.example.canteen.adapters.CategoryAdapter;
import com.example.canteen.adapters.NewProductAdapter;
import com.example.canteen.models.Product;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class search extends Fragment {
    EditText searchbar;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    List<Product> productList;
    NewProductAdapter productAdapter;

    public search() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        firestore = FirebaseFirestore.getInstance();
        searchbar = view.findViewById(R.id.searchbart);
        productList = new ArrayList<Product>();

        initiate();
        recyclerView = view.findViewById(R.id.recyclerViewsearch);
        productAdapter = new NewProductAdapter(productList, getContext(), "Home");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        return view;
    }

    private void initiate() {
        firestore.collection("Canteen Specials").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        // shimmer2.setVisibility(View.GONE);

                    }
                });
        firestore.collection("Biscuits").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        // shimmer2.setVisibility(View.GONE);

                    }
                });
        firestore.collection("Chocolate").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        // shimmer2.setVisibility(View.GONE);

                    }
                });
        firestore.collection("Drinks").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        // shimmer2.setVisibility(View.GONE);

                    }
                });
        firestore.collection("Chips").orderBy("id", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList.add(dc.getDocument().toObject(Product.class));
                            }
                            productAdapter.notifyDataSetChanged();
                        }
                        // shimmer2.setVisibility(View.GONE);

                    }
                });
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                filter(s.toString());
            }
        });
    }
    private void filter(String Text) {
        ArrayList<Product> filterList = new ArrayList<>();
        for(Product items :productList){
            if(items.getTitle().toLowerCase().contains(Text.toLowerCase()))
            {
                filterList.add(items);
//                nores.setVisibility(View.GONE);
//                notxt.setVisibility(View.GONE);
            }
            else if(filterList.isEmpty()){
//                nores.setVisibility(View.VISIBLE);
//                notxt.setVisibility(View.VISIBLE);
            }

        }
        productAdapter.filterList(filterList);
    }

}