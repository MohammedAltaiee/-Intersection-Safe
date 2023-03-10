package com.example.intersectionsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText SignInEmail, SignInPassword;
    private Button SignInBtn;
    private TextView registerResendText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth =FirebaseAuth.getInstance();
        SignInEmail= findViewById(R.id.EmailAddressSignInfield);
        SignInPassword= findViewById(R.id.PasswordSignInfield);
        SignInBtn= findViewById(R.id.SignInBtn);
        registerResendText= findViewById(R.id.resendTextToLogin);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = SignInEmail.getText().toString().trim();
                String passW= SignInPassword.getText().toString().trim();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!passW.isEmpty()){
                        auth.signInWithEmailAndPassword(email,passW).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this,MainActivity.class ));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignInActivity.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        SignInPassword.setError("Password CAN NOT be Empty!");
                    }
                }else if (email.isEmpty()){
                    SignInEmail.setError("Email CAN NOT be Empty!");
                }else {
                    SignInEmail.setError("Enter a Valid Email Please!");
                }



            }
        });
            registerResendText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SignInActivity.this,registerActivity.class));
                }
            });

    }
}