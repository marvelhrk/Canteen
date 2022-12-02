package com.example.canteen.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.canteen.R;
import com.example.canteen.activities.EmailLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.sdsmdg.tastytoast.TastyToast;


public class forgotpassword extends Fragment implements View.OnClickListener {
    TextInputLayout forgetpassword;
    Button Reset;
    FirebaseAuth FAuth;
    Dialog dialog;
    private static View view;

    public forgotpassword() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void initViews() {
        forgetpassword = view.findViewById(R.id.Emailid);
        Reset = view.findViewById(R.id.reset);
        FAuth = FirebaseAuth.getInstance();

        // Setting text selector over textviews
//        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(),
//                    xrp);
//
//            back.setTextColor(csl);
//            submit.setTextColor(csl);
//
//        } catch (Exception e) {
//        }

    }

    // Set Listeners over buttons
    private void setListeners() {
        Reset.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgotpassword, container,
                false);
        initViews();
        setListeners();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                checker();
                break;
        }
    }

    private void checker() {
//        dialog.setContentView(R.layout.checkinfo);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(false);
//        dialog.show();
               /* final ProgressDialog mDialog = new ProgressDialog(forgotpass.this);
                mDialog.setCancelable(false);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setMessage("Checking info...");
                mDialog.show();*/

        FAuth.sendPasswordResetEmail(forgetpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    dialog.dismiss();
                    TastyToast.makeText(getActivity(), "Reset Password Link sent Succesfully to your Email Account ", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                    new EmailLogin().replaceLoginFragment();

                } else {
//                    dialog.dismiss();
                    TastyToast.makeText(getActivity(), task.getException().getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    //  ReusableCodeForAll.ShowAlert(ChefForgotPassword.this, "Error", task.getException().getMessage());
                }
            }
        });
    }
}