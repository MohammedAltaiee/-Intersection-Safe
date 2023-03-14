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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

        private FirebaseAuth auth;
    EditText SignInUserName, SignInPassword;
    Button SignInBtn;
    TextView ResendTextTextToRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        auth = FirebaseAuth.getInstance();
        SignInUserName = findViewById(R.id.UserNameSignInfield);
        SignInPassword = findViewById(R.id.PasswordSignInfield);
        SignInBtn = findViewById(R.id.SignInBtn);
        ResendTextTextToRegister = findViewById(R.id.resendTextToRegister);

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validUserName()| !validPassW()){
                    Intent intent= new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    checkUser();
                }
            }
        });

        ResendTextTextToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignInActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validUserName () {
        String val = SignInUserName.getText().toString();
        if (val.isEmpty()){
            SignInUserName.setError("User Name should not be empty");
            return false;
        }else
        {
            SignInUserName.setError(null);
            return true;
        }
    }
    public Boolean validPassW () {
        String val = SignInPassword.getText().toString();
        if (val.isEmpty()){
            SignInPassword.setError("Password should not be empty");
            return false;
        }else {
            SignInPassword.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername= SignInUserName.getText().toString().trim();
        String userPassword= SignInPassword.getText().toString().trim();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase= reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    SignInUserName.setError(null);
                    String passwordFromDateBase= snapshot.child(userUsername).child("password").getValue(String.class);
                    if (!Objects.equals(passwordFromDateBase,userPassword)){
                        SignInUserName.setError(null);
                        Intent intent= new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        SignInUserName.setError("Invalid value");
                        SignInPassword.requestFocus();
                    }

                }else {
                    SignInUserName.setError("User does not exisit ");
                    SignInUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}