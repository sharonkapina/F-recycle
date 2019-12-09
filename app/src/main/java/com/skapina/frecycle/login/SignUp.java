package com.skapina.frecycle.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.skapina.frecycle.MainActivity;
import com.skapina.frecycle.R;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText username, password,confirmpassword;
    Button signup;
    TextView status;


    private FirebaseAuth firebaseAuth;


    ProgressBar progressbar;



    // This captures logged in user to save it locally
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();



        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
       confirmpassword= findViewById(R.id.password2);
        signup = findViewById(R.id.signup);
        progressbar = findViewById(R.id.progressbar);
        status = findViewById(R.id.log_status);




            firebaseAuth = FirebaseAuth.getInstance();

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   SignUp.this.startActivity(new Intent(SignUp.this, MainActivity.class));
                }
            });




       signup.setOnClickListener(new View.OnClickListener() {
           private android.content.Context Context;

           @Override
           public void onClick(View v) {
               String userEmail = username.getText().toString().trim();
               String userPassword = password.getText().toString().trim();

               if (TextUtils.isEmpty(userEmail)) {
                   showToast("Enter email address!");
                   return;
               }

               if (TextUtils.isEmpty(userPassword)) {
                   showToast("Enter Password!");
                   return;
               }

               if (userPassword.length() < 6) {
                   showToast("Password too short, enter minimum 6 characters");
                   return;
               }

               sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
               progressbar.setVisibility(View.INVISIBLE);


               //register user
               firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                       .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               Log.d(TAG, "New user registration: " + task.isSuccessful());

                               if (!task.isSuccessful()) {
                                   SignUp.this.showToast("Authentication failed. " + task.getException());
                               } else {
                                   SignUp.this.startActivity(new Intent(SignUp.this, MainActivity.class));
                                   SignUp.this.finish();
                               }
                           }
                       });
           }









       });
}
    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}