package com.example.digitalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import  android.database.*;
import android.widget.Toast;

import java.io.File;


public class Doctor extends AppCompatActivity {
    SQLiteDatabase docDetails=null;
    EditText dname,ddept, dyroe;
    RadioButton mrng,aftrn,night;
    Button docbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        dname=(EditText) findViewById(R.id.docName);
        ddept=(EditText) findViewById(R.id.deptName);
        dyroe=(EditText) findViewById(R.id.yrExperience);
        mrng= (RadioButton) findViewById(R.id.radioButton);
        aftrn= (RadioButton) findViewById(R.id.radioButton1);
        night= (RadioButton) findViewById(R.id.radioButton2);
        docbutton= (Button) findViewById(R.id.docbutton);
        docbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDB(v);
                addDdets(v);
                Intent i= new Intent (Doctor.this, LandingPage.class);
                startActivity(i);
            }
        });
    }
    public void createDB(View view){
        try {
            docDetails=this.openOrCreateDatabase("docDetails",MODE_PRIVATE,null);
            docDetails.execSQL("CREATE TABLE IF NOT EXISTS details (dname varchar, ddept varchar, dyroe int, shift varchar)");
            File database=getApplicationContext().getDatabasePath("docDetails");
            if(database.exists()){
                Toast.makeText(this,"Database created Successfully"+database.toString(),Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Log.e("Details error","DATABASE CREATION ERROR");
        }
    }
    public void addDdets(View view){
        String docname= dname.getText().toString();
        String docdept= ddept.getText().toString();
        String docexperience= String.valueOf(Integer.parseInt(dyroe.getText().toString()));
        String shift = "" ;
        if(mrng.isChecked()){
            shift="Morning";
        }else if ( aftrn.isChecked()){
            shift="Afternoon";
        }else {
            if (night.isChecked()) {
                shift = "night";
            }
        }
        docDetails.execSQL("Insert into details (" +docname+","+ docdept+","+ docexperience+"," +shift);
    }
}