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
    TextView carsPerHour,carsPerHourV,numberOfCarsNow,numberOfCarsNowV,numberOfCarsTotal,numberOfCarsTotalV;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("SYSTEM");

        carsPerHour = findViewById(R.id.CarPerHourfield);
        numberOfCarsNow = findViewById(R.id.numberOfCarsNowfield);
        numberOfCarsTotal = findViewById(R.id.numberOfCarsTotalfield);
        carsPerHourV = findViewById(R.id.CarPerHourfieldV);
        numberOfCarsNowV = findViewById(R.id.numberOfCarsNowfieldV);
        numberOfCarsTotalV = findViewById(R.id.numberOfCarsTotalfieldV);
               getdata();
    }

    private void getdata() {
        reference = FirebaseDatabase.getInstance().getReference().child("py");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //horizantal
                Integer carsPH= snapshot.child("Intersection").child("HorizontalTraffic").child("carsPerHour").getValue(Integer.class);
                carsPerHour.setText(String.valueOf(carsPH));
                Integer nocn=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsRN").getValue(Integer.class);
                numberOfCarsNow.setText(String.valueOf(nocn));
                Integer noct=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsTotal").getValue(Integer.class);
                numberOfCarsTotal.setText(String.valueOf(noct));
                //Vertical
                Integer carsPHV= snapshot.child("Intersection").child("VerticalTraffic").child("carsPerHour").getValue(Integer.class);
                carsPerHourV.setText(String.valueOf(carsPHV));
                Integer nocnV=snapshot.child("Intersection").child("VerticalTraffic").child("numOfCarsRN").getValue(Integer.class);
                numberOfCarsNowV.setText(String.valueOf(nocnV));
                Integer noctV=snapshot.child("Intersection").child("VerticalTraffic").child("numOfCarsTotal").getValue(Integer.class);
                numberOfCarsTotalV.setText(String.valueOf(noctV));



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