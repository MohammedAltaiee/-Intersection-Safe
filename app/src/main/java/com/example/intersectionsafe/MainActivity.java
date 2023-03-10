package com.example.intersectionsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerEmail, registerPassword;
    private Button registerBtn;
    private TextView loginResendText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth =FirebaseAuth.getInstance();
        registerEmail= findViewById(R.id.EmailAddressfield);
        registerPassword= findViewById(R.id.Passwordfield);
        registerBtn= findViewById(R.id.loginBtn);
        loginResendText= findViewById(R.id.registerBtn);
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
                                Toast.makeText(MainActivity.this,"Register ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,registerActivity.class));
                            }else {
                                Toast.makeText(MainActivity.this, "No Register"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        loginResendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, registerActivity.class));
            }
        });
    }
}