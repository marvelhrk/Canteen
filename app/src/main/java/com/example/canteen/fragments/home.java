package com.example.canteen.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.canteen.R;
import com.example.canteen.activities.EmailLogin;
import com.example.canteen.activities.MainScreen;
import com.example.canteen.adapters.CategoryAdapter;
import com.example.canteen.adapters.NewProductAdapter;
import com.example.canteen.helper.Data;
import com.example.canteen.models.Category;
import com.example.canteen.models.Product;
import com.example.canteen.utils.LocalStorage;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class home extends Fragment implements View.OnClickListener {
    Timer timer;
    Data data;
    Button logout;
    LocalStorage localStorage;
    private static FragmentManager fragmentManager;
    private List<Category> categoryList = new ArrayList<>();
    private List<Product> productList1,productList2,productList3,productList4,productList5;
    private RecyclerView recyclerView, nRecyclerView, pRecyclerView,qRecyclerView,rRecyclerView,sRecyclerView;
    private CategoryAdapter mAdapter;
    private NewProductAdapter nAdapter,pAdapter,qAdapter,rAdapter,sAdapter;
    private FirebaseFirestore db;

    public home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        data = new Data();
        logout = view.findViewById(R.id.logoutus);
        logout.setOnClickListener(this);

        localStorage = new LocalStorage(getContext());
        fragmentManager = getActivity().getSupportFragmentManager();
        productList1 = new ArrayList<>();
        productList2 = new ArrayList<>();
        productList3 = new ArrayList<>();
        productList4 = new ArrayList<>();
        productList5 = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        initspecials();
        initdrinks();
        initchips();
        initbiscuits();
        initchocolate();

        recyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new CategoryAdapter(data.getCategoryList(), getContext(), "Home");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //Canteen Specials
        nRecyclerView = view.findViewById(R.id.recyclerView2);
        nAdapter = new NewProductAdapter(productList1, getContext(), "specials");
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        nRecyclerView.setLayoutManager(nLayoutManager);
        nRecyclerView.setItemAnimator(new DefaultItemAnimator());
        nRecyclerView.setAdapter(nAdapter);

        //Beverages
        pRecyclerView = view.findViewById(R.id.recyclerView3);
        pAdapter = new NewProductAdapter(productList2, getContext(), "Beverages");
        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pRecyclerView.setLayoutManager(pLayoutManager);
        pRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pRecyclerView.setAdapter(pAdapter);

        //Chips
        qRecyclerView = view.findViewById(R.id.recyclerview4);
        qAdapter = new NewProductAdapter(productList3, getContext(), "chips");
        RecyclerView.LayoutManager qLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        qRecyclerView.setLayoutManager(qLayoutManager);
        qRecyclerView.setItemAnimator(new DefaultItemAnimator());
        qRecyclerView.setAdapter(qAdapter);

        //Biscuits
        rRecyclerView = view.findViewById(R.id.recyclerView5);
        rAdapter = new NewProductAdapter(productList4, getContext(), "biscuits");
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rRecyclerView.setLayoutManager(rLayoutManager);
        rRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rRecyclerView.setAdapter(rAdapter);

        //Chocolate
        sRecyclerView = view.findViewById(R.id.recyclerView6);
        sAdapter = new NewProductAdapter(productList5, getContext(), "chocolate");
        RecyclerView.LayoutManager sLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sRecyclerView.setAdapter(sAdapter);





        // Inflate the layout for this fragment
        return view;
    }

    private void initchocolate() {
        db.collection("Chocolate").orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList5.add(dc.getDocument().toObject(Product.class));
                            }
                            sAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

    private void initbiscuits() {
        db.collection("Biscuits").orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList4.add(dc.getDocument().toObject(Product.class));
                            }
                            rAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

    private void initchips() {
        db.collection("Chips").orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList3.add(dc.getDocument().toObject(Product.class));
                            }
                            qAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    private void initdrinks() {
        db.collection("Drinks").orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList2.add(dc.getDocument().toObject(Product.class));
                            }
                            pAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    private void initspecials() {
        db.collection("Canteen Specials").orderBy("id", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == (DocumentChange.Type.ADDED)){
                                productList1.add(dc.getDocument().toObject(Product.class));
                            }
                            nAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutus:
                Log.d("Logout Button", "Clicked");
                localStorage = new LocalStorage(getContext());
                localStorage.logoutUser();
                Intent Z = new Intent(getActivity(), EmailLogin.class);
                startActivity(Z);
                getActivity().finishAffinity();
                break;
        }

    }

//    @Override
//    public void onStop() {
//        timer.cancel();
//        super.onStop();
//    }

//    @Override
//    public void onPause() {
//        timer.cancel();
//        super.onPause();
//    }


    public void onLetsClicked(View view) {
        startActivity(new Intent(getContext(), MainScreen.class));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

}