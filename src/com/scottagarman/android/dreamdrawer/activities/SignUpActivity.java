package com.scottagarman.android.dreamdrawer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.scottagarman.android.dreamdrawer.R;

public class SignUpActivity extends SherlockActivity {
    private EditText mEmailTextView;
    private EditText mPasswordTextView;

    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        // get views
        mEmailTextView = (EditText) findViewById(R.id.signup_email);
        mPasswordTextView = (EditText) findViewById(R.id.signup_password);
        mLoginButton = (Button) findViewById(R.id.signup_btn_signup);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeSignupRequest();
            }
        });
    }

    private void makeSignupRequest() {
        String email = mEmailTextView.getText().toString();
        String username = mEmailTextView.getText().toString();
        String password = mPasswordTextView.getText().toString();

        if(!verifyFields(email, username, password)) return;

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // other fields can be set just like with ParseObject
        //user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
          public void done(ParseException e) {
              if (e == null) {
                  // Hooray! The user is logged in.
                  Toast.makeText(SignUpActivity.this, "Signed up!", Toast.LENGTH_SHORT).show();
                  finish();
              } else {
                  // Login failed. Look at the ParseException to see what happened.
                  Toast.makeText(SignUpActivity.this, "Failed to sign up." + e.toString(), Toast.LENGTH_SHORT).show();
                  e.printStackTrace();
              }
          }
        });
    }

    private boolean verifyFields(String email, String username, String password) {
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
