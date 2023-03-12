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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView carsPerHour,numberOfCarsNow,numberOfCarsTotal,TimeElapsed;
    TextView UserNameTitle;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carsPerHour = findViewById(R.id.CarPerHourfield);
        numberOfCarsNow = findViewById(R.id.numberOfCarsNowfield);
        numberOfCarsTotal = findViewById(R.id.numberOfCarsTotalfield);
        TimeElapsed = findViewById(R.id.TimeElapsedfiled);
        showIntersectionSafeInfo();
    }

        public void showIntersectionSafeInfo(){
            Intent intent =getIntent();
            auth = FirebaseAuth.getInstance();
//            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("py");
             reference = FirebaseDatabase.getInstance().getReference().child("py")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("carsPerHour");
//            reference.child(name).setValue(helperClass);
            String carPH= intent.getStringExtra("carsPerHour");
            carsPerHour.setText(carPH);
        }

    }
