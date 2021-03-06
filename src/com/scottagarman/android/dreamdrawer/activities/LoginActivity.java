package com.scottagarman.android.dreamdrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.actionbarsherlock.view.Menu;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.scottagarman.android.dreamdrawer.R;

public class LoginActivity extends DreamSuperActivity {

    private EditText mEmailTextView;
    private EditText mPasswordTextView;

    private Button mLoginButton;
    private Button mSignupButton;

    private RelativeLayout mHeroView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get views
        mEmailTextView = (EditText) findViewById(R.id.login_email);
        mPasswordTextView = (EditText) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        mSignupButton = (Button) findViewById(R.id.login_btn_signup);
        mHeroView = (RelativeLayout) findViewById(R.id.login_hero);

        mEmailTextView.setOnClickListener(mEditTextOnClickListener);
        mPasswordTextView.setOnClickListener(mEditTextOnClickListener);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeLoginRequest();
            }
        });
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    private View.OnClickListener mEditTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mHeroView.setVisibility(View.GONE);
        }
    };

    private void makeLoginRequest() {
        String email = mEmailTextView.getText().toString();
        String password = mPasswordTextView.getText().toString();

        if(!verifyFields(email, password)) return;

        setLoading(true);

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                setLoading(false);
                if (user != null) {
                    // Hooray! The user is logged in.
                    Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Login failed. Look at the ParseException to see what happened.
                    Toast.makeText(LoginActivity.this, "Failed to log in: " + e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean verifyFields(String email, String password) {
        StringBuilder msg = new StringBuilder();
        //TODO: add check for valid email
        if(email == null || "".equals(email)) {
            msg.append("-Missing valid email address.\n");
        }

        if(password == null || "".equals(password)) {
            msg.append("-Please enter an email.\n");
        }

        //TODO: show notification

        return msg.length() == 0;
    }
}
