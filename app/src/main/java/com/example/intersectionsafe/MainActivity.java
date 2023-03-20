package com.example.intersectionsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat dfInteger = new DecimalFormat("0");


    TextView carsPerHour,carsPerHourV,numberOfCarsNow,numberOfCarsNowV,numberOfCarsTotal,numberOfCarsTotalV,optimizedCarsH,optimizedCarsV;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("SYSTEM");

        carsPerHour = findViewById(R.id.CarPerHourfield);
        numberOfCarsNow = findViewById(R.id.numberOfCarsNowfield);
        numberOfCarsTotal = findViewById(R.id.numberOfCarsTotalfield);
        optimizedCarsH= findViewById(R.id.optimizedCarsH);
        optimizedCarsV= findViewById(R.id.optimizedCarsV);
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
                float carsPH= snapshot.child("Intersection").child("HorizontalTraffic").child("carsPerHour").getValue(Integer.class);
                carsPerHour.setText(dfInteger.format(carsPH));
                Integer nocn=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsRN").getValue(Integer.class);
                numberOfCarsNow.setText(String.valueOf(nocn));
                Integer noct=snapshot.child("Intersection").child("HorizontalTraffic").child("numOfCarsTotal").getValue(Integer.class);
                numberOfCarsTotal.setText(String.valueOf(noct));
                //Vertical
                float carsPHV=snapshot.child("Intersection").child("VerticalTraffic").child("carsPerHour").getValue(Integer.class);
                carsPerHourV.setText(dfInteger.format(carsPHV));
                Integer nocnV=snapshot.child("Intersection").child("VerticalTraffic").child("numOfCarsRN").getValue(Integer.class);
                numberOfCarsNowV.setText(String.valueOf(nocnV));
                Integer noctV=snapshot.child("Intersection").child("VerticalTraffic").child("numOfCarsTotal").getValue(Integer.class);
                numberOfCarsTotalV.setText(String.valueOf(noctV));
                //optimization
//                private static final DecimalFormat df = new DecimalFormat("0.00");
                double optimizedH= (10+(45*(carsPH/(carsPH+carsPHV))));
                double optimizedV= (10+(45*(carsPHV/(carsPHV+carsPH))));
//                double optimizedH=carsPH/(carsPH+carsPHV);
                optimizedCarsH.setText(df.format(optimizedH)+" seconds");
                optimizedCarsV.setText(df.format(optimizedV)+" seconds");



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