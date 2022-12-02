package com.example.canteen.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.canteen.R;
import com.example.canteen.activities.EmailLogin;
import com.example.canteen.models.User;
import com.example.canteen.utils.LocalStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.HashMap;

public class signup extends Fragment implements View.OnClickListener {
    private static View view;
    private static TextInputLayout Fname, Email, mobileno, Pass,cpass;
    private static TextView login;
    private static Button signUpButton;
    CountryCodePicker Cpp;
    FirebaseAuth Fauth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile;
    String role="Customer";
    Dialog dialog,dialog2;
//    private static CheckBox terms_conditions;
//    ProgressDialog progressDialog;
    User user;
    LocalStorage localStorage;
    Gson gson;

    public signup() {
        // Required empty public constructor
    }



    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    private void initViews() {
        Fname = view.findViewById(R.id.Firstname);
        Email = view.findViewById(R.id.Emailid);
        mobileno = view.findViewById(R.id.Mobileno);
        Pass = view.findViewById(R.id.Pwd);
        cpass = view.findViewById(R.id.Cpass);
        signUpButton = view.findViewById(R.id.registersu);
        login = view.findViewById(R.id.email);
//        progressDialog = new ProgressDialog(getContext());
        dialog = new Dialog (getContext());
        dialog2 = new Dialog (getContext());

     /*   progressBar = new ProgressBar(this);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setVisibility(View.INVISIBLE);*/

        Cpp = view.findViewById(R.id.CountryCode);

        Fauth = FirebaseAuth.getInstance();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        initViews();
        setListeners();
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registersu:
                checker();
                // Call checkValidation method
                break;

            case R.id.email:

                // Replace login fragment
                new EmailLogin().replaceLoginFragment();
                break;
        }

    }
    private void checker(){
        fname = Fname.getEditText().getText().toString().trim();
        emailid = Email.getEditText().getText().toString().trim();
        mobile = mobileno.getEditText().getText().toString().trim();
        password = Pass.getEditText().getText().toString().trim();
        confpassword = cpass.getEditText().getText().toString().trim();


        if (isValid()){
            TastyToast.makeText(getActivity(), "Loading !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
//            dialog2.setContentView(R.layout.progress);
//            dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog2.setCancelable(false);
//            dialog2.show();

                /*    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminateDrawable(doubleBounce);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/


                 /*   final ProgressDialog mDialog = new ProgressDialog(registration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress......");
                    mDialog.show();*/

            Fauth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                        final HashMap<String , String> hashMap = new HashMap<>();
                        hashMap.put("Role",role);
                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                User user = new User(useridd, fname, emailid, mobile, password);
//                                HashMap<String , String> hashMap1 = new HashMap<>();
//                                hashMap1.put("Mobile No",mobile);
//                                hashMap1.put("First Name",fname);
//                                hashMap1.put("Last Name",lname);
//                                hashMap1.put("EmailId",emailid);
//                                hashMap1.put("Password",password);
//                                hashMap1.put("Confirm Password",confpassword);


                                firebaseDatabase.getInstance().getReference("Customer")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //mDialog.dismiss();
                                        // progressBar.setVisibility(View.INVISIBLE);

                                        Fauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful())
                                                {
                                                    TastyToast.makeText(getActivity(), "Done", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                                                    new EmailLogin().replaceLoginFragment();
//                                                    dialog2.dismiss();
//                                                    opendialog();

                                                            /*AlertDialog.Builder builder = new AlertDialog.Builder(registration.this);
                                                            builder.setMessage( "    Your Registration is almost Complete.\n    Next Step, Verify Your Phone. ");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();
                                                                    String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                    Intent b = new Intent(registration.this,phoneverify.class);
                                                                    b.putExtra("phonenumber",phonenumber);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();*/
                                                }
                                                else{
                                                    dialog2.dismiss();
                                                    // mDialog.dismiss();
                                                    TastyToast.makeText(getActivity(), task.getException().getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                                }
                                            }
                                        });

                                    }
                                });

                            }
                        });
                    }
                    else {
                        dialog2.dismiss();
                        // mDialog.dismiss();
                        TastyToast.makeText(getActivity(), task.getException().getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);

                        // Toast.makeText(emailogin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z._-]+";
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");


        boolean isValid = false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false;
        if(TextUtils.isEmpty(fname)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidname = true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Is Required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak.\nA Strong Password must contain Alphabets, Numbers and Special Characters.");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            cpass.setErrorEnabled(true);
            cpass.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile Number");
            }
            else if(mobile.length()>10){
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile Number");
            }
            else{
                isValidmobilenum = true;
            }
        }


        isValid = ( isValidconfpassword && isValidpassword  && isValidemail && isValidmobilenum && isValidname ) ? true : false;
        return isValid;


    }
}