package com.example.intersectionsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerEmail, registerPassword;
    private Button registerBtn;
    private TextView loginResendText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth =FirebaseAuth.getInstance();
        registerEmail= findViewById(R.id.EmailAddressRegisterfield);
        registerPassword= findViewById(R.id.PasswordRegisterfield);
        registerBtn= findViewById(R.id.registerBtn);
        loginResendText= findViewById(R.id.resendTextToLogin);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= registerEmail.getText().toString().trim();
                String passW = registerPassword.getText().toString().trim();
                if (user.isEmpty()){
                    registerEmail.setError("Email should be filed !");
                }
                if (passW.isEmpty()){
                    registerPassword.setError("Password should be filed !");
                }
                else {
                    auth.createUserWithEmailAndPassword(user,passW).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(registerActivity.this,"Register ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registerActivity.this,MainActivity.class));
                            }else {
                                Toast.makeText(registerActivity.this, "No Register"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        loginResendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerActivity.this, SignInActivity.class));
            }
        });
    }
}