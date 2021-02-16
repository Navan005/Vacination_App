package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class vaccine_detailview extends AppCompatActivity {

    TextView vaccineName, vaccineAge, vaccineDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_detailview);

        vaccineName=findViewById(R.id.vaccine_name);
        vaccineAge=findViewById(R.id.vaccine_age);
        vaccineDescription=findViewById(R.id.vaccine_description);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String Age = intent.getExtras().getString("Age");

        vaccineName.setText(Title);
        vaccineAge.setText(Age);
        vaccineDescription.setText(Description);

    }
}