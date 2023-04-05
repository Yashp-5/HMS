package com.example.digitalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Appointment extends AppCompatActivity {
    SQLiteDatabase aptDetails=null;
    EditText patientID,docName, time;
    CalendarView dte;
    Button bookapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        EditText patientID= (EditText) findViewById(R.id.pID);
        EditText docName= (EditText) findViewById(R.id.dname2);
        EditText time= (EditText) findViewById(R.id.appTime);
        CalendarView dte= (CalendarView) findViewById(R.id.calendarView);
        Button bookapp= (Button) findViewById(R.id.appBook);
        bookapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDB(v);
                addAdets(v);
                Intent i = new Intent(Appointment.this, LandingPage.class);
                startActivity(i);
            }
        });


    }
    public void createDB(View view){
        try {
            aptDetails=this.openOrCreateDatabase("aptDetails",MODE_PRIVATE,null);
            aptDetails.execSQL("CREATE TABLE IF NOT EXISTS aptdetails (pid varchar, dName varchar, time int, date varchar)");
            File database=getApplicationContext().getDatabasePath("aptDetails");
            if(database.exists()){
                Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.e("aptDetails error","DATABASE CREATION ERROR");
        }
    }
    public void addAdets(View view){
        String pid= patientID.getText().toString();
        String dName= docName.getText().toString();
        String tme= time.getText().toString();
        String date= String.valueOf(dte.getDate());

        aptDetails.execSQL("Insert into aptdetails (" +pid+","+ dName+","+ tme+"," +date);
    }
}
