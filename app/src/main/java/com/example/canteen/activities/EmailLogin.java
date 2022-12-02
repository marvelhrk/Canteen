package com.example.canteen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.canteen.R;
import com.example.canteen.fragments.emailLogin;
import com.example.canteen.utils.Utils;

public class EmailLogin extends AppCompatActivity {
    private static FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);
//        getSupportActionBar().hide();
        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new emailLogin(),Utils.emailLogin).commit();
        }
    }


    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new emailLogin(),Utils.emailLogin).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.signuplogin);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.forgotpassword);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task
        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}