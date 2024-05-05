package com.example.tempsign.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tempsign.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
    }
    public void loginFunc(View view) {

        String email=((EditText) findViewById(R.id.login_username)).getText().toString().trim();
        String password=((EditText) findViewById(R.id.login_password)).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignIn.this,"login ok",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignIn.this,"please try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void hyperlinkSignUpFunc(View view) {
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }
}