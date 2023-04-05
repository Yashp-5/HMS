package com.example.digitalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.File;

public class Patient extends AppCompatActivity {
    SQLiteDatabase patDetails;
    Button patbutton;
    EditText pname, page, pContact, pAdd, pDept;
    Spinner gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Button patbutton = (Button) findViewById(R.id.patButton);
        EditText pname= (EditText) findViewById(R.id.pname);
        EditText page= (EditText) findViewById(R.id.pAge);
        EditText pContact= (EditText) findViewById(R.id.pContact);
        EditText pAdd= (EditText) findViewById(R.id.pAddress);
        EditText pDept= (EditText) findViewById(R.id.pDept);
        Spinner gender= (Spinner) findViewById(R.id.spinner);
        patbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDB(v);
                addPdets(v);
                Intent i = new Intent(Patient.this, LandingPage.class);
                startActivity(i);
            }
        });
         String[] gend = { "Male" , "Female", "Rather not say"};

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, gend);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(aa);

    }
    public void createDB(View view){
        try {
            patDetails=this.openOrCreateDatabase("patDetails",MODE_PRIVATE,null);
            patDetails.execSQL("CREATE TABLE IF NOT EXISTS patDetails (pname varchar, page int, pcontact int, padd varchar, pdept varchar)");
            File database=getApplicationContext().getDatabasePath("patDetails");
            if(database.exists()){
                Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.e("Details error","DATABASE CREATION ERROR");
        }
    }
    public void addPdets(View view){
        try {
            String pName = pname.getText().toString();
            String pAge = page.getText().toString();
            String pcontact = pContact.getText().toString();
            String padd = pAdd.getText().toString();
            String pdept = pDept.getText().toString();
            patDetails.execSQL("Insert into patDetails (" + pName + "," + pAge + "," + pcontact + "," + padd + "," + pdept);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
