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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity extends AppCompatActivity {

    //    private FirebaseAuth auth;
    EditText registerName,registerUserName, registerEmail, registerPassword;
    Button registerBtn;
    TextView loginResendTextIoSignIn;
    FirebaseDatabase database;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        auth =FirebaseAuth.getInstance();
        registerName= findViewById(R.id.NameRegisterfield);
        registerUserName= findViewById(R.id.UserNameRegisterfield);
        registerEmail= findViewById(R.id.EmailAddressRegisterfield);
        registerPassword= findViewById(R.id.PasswordRegisterfield);
        registerBtn= findViewById(R.id.registerBtn);
        loginResendTextIoSignIn= findViewById(R.id.resendTextToSignIn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String name = registerName.getText().toString();
                String username = registerUserName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                HelperClass helperClass = new HelperClass(name, username, email, password);
                reference.child(name).setValue(helperClass);
                Toast.makeText(registerActivity.this, "You are Registered Now!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registerActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        loginResendTextIoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}