package com.skapina.frecycle.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skapina.frecycle.MainActivity;
import com.skapina.frecycle.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, userpassword;
    Button login, signup;
    TextView status;


    private FirebaseAuth firebaseAuth;

    Boolean CheckEditText;
    ProgressBar progressbar;


    // This captures logged in user to save it locally
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();


        username = findViewById(R.id.username);
        userpassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        progressbar = findViewById(R.id.progressbar);
        status = findViewById(R.id.log_status);




            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, SignUp.class));
                }
            });

            //resetButton.setOnClickListener(new View.OnClickListener() {
            //  @Override
            // public void onClick(View v) {
            //     LoginActivity.this.startActivity(new Intent(LoginActivity.this, ResetActivity.class));

        //  });


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String password = userpassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                progressbar.setVisibility(View.INVISIBLE);

                //login user
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressbar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {

                                    if (username.length() < 6) {
                                        userpassword.setError(LoginActivity.this.getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    LoginActivity.this.finish();
                                }
                            }
                        });

            }


        });


    }
    public static Intent getStartIntent(Activity mActivity) {
        return new Intent(mActivity, LoginActivity.class);
    }


}
