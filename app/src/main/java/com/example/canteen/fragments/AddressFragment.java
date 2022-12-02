package com.example.canteen.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.models.User;
import com.example.canteen.models.UserAddress;
import com.example.canteen.utils.LocalStorage;
import com.example.canteen.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddressFragment extends Fragment {

    Context context;
    TextView txt_pyment;
    String _name, _email, _mobile, _address, userString;
    EditText name, email, mobile, address;
    UserAddress userAddress;
    LocalStorage localStorage;
    Bundle bundle;
    Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_address, container, false);
        name = v.findViewById(R.id.sa_name);
        email = v.findViewById(R.id.sa_email);
        mobile = v.findViewById(R.id.sa_mobile);
        address = v.findViewById(R.id.sa_address);

//        localStorage = new LocalStorage(getContext());
//        gson = new Gson();
//        userString = localStorage.getUserLogin();
//
//        User user = gson.fromJson(userString, User.class);
//        userAddress = gson.fromJson(localStorage.getUserAddress(), UserAddress.class);
//        Log.d("User String : ", userString);
//        if (user != null) {
//            name.setText(user.getName());
//            email.setText(user.getEmail());
//            mobile.setText(user.getMobile());
//        }
//
//        if (userAddress != null) {
//            name.setText(userAddress.getName());
//            email.setText(userAddress.getEmail());
//            mobile.setText(userAddress.getMobile());
//            address.setText(userAddress.getAddress());
//        }
        context = container.getContext();
        txt_pyment = v.findViewById(R.id.txt_pyment);
        txt_pyment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _name = name.getText().toString();
//                userAddress.setName(_name);
                _email = email.getText().toString();
//                userAddress.setEmail(_email);
                _mobile = mobile.getText().toString();
                _address = address.getText().toString();
                Pattern p = Pattern.compile(Utils.regEx);
                Matcher m = p.matcher(_email);

                if (_name.length() == 0) {
                    name.setError("Enter Name");
                    name.requestFocus();
                } else if (_email.length() == 0) {
                    email.setError("Enter email");
                    email.requestFocus();
                } else if (!m.find()) {
                    email.setError("Enter Correct email");
                    email.requestFocus();
                } else if (_mobile.length() == 0) {
                    mobile.setError("Enter mobile Number");
                    mobile.requestFocus();
                } else if (_mobile.length() < 10) {
                    mobile.setError("Enter Corretct mobile Number");
                    mobile.requestFocus();
                } else if (_address.length() == 0) {
                    address.setError("Enter your Address");
                    address.requestFocus();
                } else {
//                    userAddress = new UserAddress(_name, _email, _mobile, _address);
//                    String user_address = gson.toJson(userAddress);
//                    localStorage.setUserAddress(user_address);

                    Bundle bundle = new Bundle();
                    bundle.putString("Name",_name);
                    bundle.putString("Email",_email);
                    bundle.putString("Mobile",_mobile);
                    bundle.putString("Address",_address);

                    ConfirmFragment confirmFragment = new ConfirmFragment();
                    confirmFragment.setArguments(bundle);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, confirmFragment);
                    ft.commit();
                }
            }
        });
        return v;
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Address");
    }
}
