package com.example.canteen.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.models.Order;
import com.example.canteen.models.User;
import com.example.canteen.models.UserAddress;
import com.example.canteen.utils.LocalStorage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class profile extends Fragment {
    UserAddress userAddress;
    LocalStorage localStorage;
    Gson gson;
    User user;
    HashMap<String , String> hashMap1;
    FirebaseDatabase firebaseDatabase;
    TextView name,email,phoneno,address;
    String stname,stemail,stphone;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.sa_name);
        email = view.findViewById(R.id.sa_email);
        phoneno = view.findViewById(R.id.sa_mobile);
        address = view.findViewById(R.id.sa_address);

       initiate();
//       email.setText(user.getEmail());
//       phoneno.setText(user.getMobile());

        return view;
    }

    private void initiate() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Customer");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if(user.getId() == FirebaseAuth.getInstance().getCurrentUser().getUid()){
                        stname = user.getName();
                        name.setText(stname);
                    }
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}