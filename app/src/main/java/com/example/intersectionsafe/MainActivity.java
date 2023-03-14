package com.example.intersectionsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView carsPerHour,numberOfCarsNow,numberOfCarsTotal,TimeElapsed;
    TextView UserNameTitle;
    DatabaseReference reference,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("SYSTEM");

        carsPerHour = findViewById(R.id.CarPerHourfield);
        numberOfCarsNow = findViewById(R.id.numberOfCarsNowfield);
        numberOfCarsTotal = findViewById(R.id.numberOfCarsTotalfield);
        TimeElapsed = findViewById(R.id.TimeElapsedfiled);
        UserNameTitle= findViewById(R.id.UserNameTitle);

        getdata();


    }

    private void getdata() {
        reference = FirebaseDatabase.getInstance().getReference().child("py");
//        reference2= FirebaseDatabase.getInstance().getReference("users");
//        reference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String user= snapshot.getValue(String.class);
//                UserNameTitle.setText(user);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Integer carsPH= snapshot.child("Intersection").child("HorizontalTraffic").child("carsPerHour").getValue(Integer.class);
                carsPerHour.setText(String.valueOf(carsPH));
                Integer nocn=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsRN").getValue(Integer.class);
                numberOfCarsNow.setText(String.valueOf(nocn));
                Integer noct=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsTotal").getValue(Integer.class);
                numberOfCarsTotal.setText(String.valueOf(noct));
                Integer te=snapshot.child("Intersection").child("HorizontalTraffic").child("timeElapsed").getValue(Integer.class);
                TimeElapsed.setText(String.valueOf(te));
                // after getting the value we are setting
                // our value to our text view in below line.

//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}