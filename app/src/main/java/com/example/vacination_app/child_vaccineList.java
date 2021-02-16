package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class child_vaccineList extends AppCompatActivity {

    TextView childName, childAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_vaccine_list);

        childName=findViewById(R.id.child_name);
        childAge=findViewById(R.id.child_age);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Age");

        childAge.setText(Description);
        childName.setText(Title);
    }
}