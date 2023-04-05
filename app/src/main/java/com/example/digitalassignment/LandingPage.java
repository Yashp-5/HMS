package com.example.digitalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class LandingPage extends AppCompatActivity {
    Button doc,pat,apt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);
        doc= (Button) findViewById(R.id.docDetails);
        pat= (Button) findViewById(R.id.patientReg);
        apt= (Button) findViewById(R.id.appointment);
        doc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LandingPage.this, Doctor.class);
                startActivity(i);
            }
        } );
        pat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent j= new Intent(LandingPage.this, Patient.class);
                startActivity(j);
            }
        } );
        apt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent k= new Intent(LandingPage.this, Appointment.class);
                startActivity(k);
            }
        } );

    }
}