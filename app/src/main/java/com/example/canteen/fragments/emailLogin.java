package com.example.canteen.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteen.ProductActivity;
import com.example.canteen.R;
import com.example.canteen.activities.EmailLogin;
import com.example.canteen.activities.MainScreen;
import com.example.canteen.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.example.canteen.models.User;
import com.example.canteen.utils.LocalStorage;
import com.sdsmdg.tastytoast.TastyToast;


public class emailLogin extends Fragment implements View.OnClickListener {
    private static View view;

    private static TextInputLayout email, pass;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static FragmentManager fragmentManager;

    ProgressDialog progressDialog;
    LocalStorage localStorage;
    String userString;
    User user;

    TextView Forgotpassword;
    SharedPreferences sp;
    FirebaseAuth Fauth;
    String emailid, pwd;
    Dialog dialog;
    public emailLogin() {
        // Required empty public constructor
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        email = view.findViewById(R.id.Lemail);
        pass = view.findViewById(R.id.Lpassword);
        loginButton = view.findViewById(R.id.login);
        forgotPassword = view.findViewById(R.id.forgotpass);
        signUp = view.findViewById(R.id.createnew);
//      show_hide_password = view.findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);
        progressDialog = new ProgressDialog(getContext());
        localStorage = new LocalStorage(getContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);
        Log.d("User", userString);

        dialog = new Dialog(getContext());
        Fauth = FirebaseAuth.getInstance();

        // Setting text selector over textviews
//        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(),
//                    xrp);
//
//            forgotPassword.setTextColor(csl);
//            show_hide_password.setTextColor(csl);
//            signUp.setTextColor(csl);
//        } catch (Exception e) {
//        }
    }
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
//        show_hide_password
//                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                    @Override
//                    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
//
//                        // If it is checkec then show password else hide
//                        // password
//                        if (isChecked) {
//
//                            show_hide_password.setText(R.string.hide_pwd);// change
//                            // checkbox
//                            // text
//
//                            password.setInputType(InputType.TYPE_CLASS_TEXT);
//                            password.setTransformationMethod(HideReturnsTransformationMethod
//                                    .getInstance());// show password
//                        } else {
//                            show_hide_password.setText(R.string.show_pwd);// change
//                            // checkbox
//                            // text
//
//                            password.setInputType(InputType.TYPE_CLASS_TEXT
//                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                            password.setTransformationMethod(PasswordTransformationMethod
//                                    .getInstance());// hide password
//
//                        }
//
//                    }
//                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_email_login, container, false);
        initViews();
        setListeners();
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                TastyToast.makeText(getActivity(), "Loading....", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
                checkValidation();
//                Toast.makeText(getActivity(),"YO",Toast.LENGTH_SHORT).show();
                break;

            case R.id.forgotpass:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer,
                                new forgotpassword(),
                                Utils.forgotpassword).commit();
                break;
            case R.id.createnew:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer,
                                new signup(),
                                Utils.signuplogin).commit();
                break;
        }
    }
    private void checkValidation(){
        emailid = email.getEditText().getText().toString().trim();
        pwd = pass.getEditText().getText().toString().trim();
        emailid = email.getEditText().getText().toString().trim();
        pwd = pass.getEditText().getText().toString().trim();

        if (isValid()) {
//            dialog.setContentView(R.layout.signingin);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.setCancelable(false);
//            dialog.show();
                       /* final ProgressDialog mDialog = new ProgressDialog(emailogin.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Signing In Please Wait.......");
                        mDialog.show();*/

            Fauth.signInWithEmailAndPassword(emailid, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
//                        dialog.dismiss();
                        // mDialog.dismiss();

                        if (Fauth.getCurrentUser().isEmailVerified()) {
//                            dialog.dismiss();
                            // mDialog.dismiss();
//                            SharedPreferences sharedPreferences = getSharedPreferences(emailogin.PREFS_NAME,0);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("hasloggedin",true);
//                            editor.commit();
                            TastyToast.makeText(getActivity(), "Congratulation!\nYou Have Successfully Login", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                            // Toast.makeText(emailogin.this, "      Congratulation! \nYou Have Successfully Login", Toast.LENGTH_SHORT).show();
                            localStorage = new LocalStorage(getContext());
                            localStorage.createUserLoginSession(userString);
                            Intent Z = new Intent(getActivity(), MainScreen.class);
                            startActivity(Z);

                        } else {
                            TastyToast.makeText(getActivity(), "Verification Failed.\nYou Have Not Verified Account on Your Email.", TastyToast.LENGTH_LONG, TastyToast.INFO);
                            //  Toast.makeText(emailogin.this, "      Verification Failed. \nYou Have Not Verified Account on Your Email Address.",Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        dialog.dismiss();
                        // mDialog.dismiss();
                        TastyToast.makeText(getActivity(), task.getException().getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
                        // Toast.makeText(emailogin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z._/]+";

    public boolean isValid() {

        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid = false, isvalidemail = false, isvalidpassword = false;
        if (TextUtils.isEmpty(emailid)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }
        else {
            if (emailid.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if (TextUtils.isEmpty(pwd)) {

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        } else {
            isvalidpassword = true;
        }
        isvalid = (isvalidemail && isvalidpassword) ? true : false;
        return isvalid;
    }
}