package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class vaccine_detailview extends AppCompatActivity {

    TextView vaccineName, vaccineAge, vaccineDescription;
    Button appointmentBtn;
    FirebaseFirestore db;
    DatabaseReference childrn;
    AddingAppointment member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_detailview);

        vaccineName=findViewById(R.id.vaccine_name);
        vaccineAge=findViewById(R.id.vaccine_age);
        vaccineDescription=findViewById(R.id.vaccine_description);
        appointmentBtn=findViewById(R.id.appointment_button);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String Age = intent.getExtras().getString("Age");

        vaccineName.setText(Title);
        vaccineAge.setText(Age + " year");
        vaccineDescription.setText(Description);

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String child_name=childName.getText().toString();
                String age=childAge.getText().toString();
                String parentname=Total;

                member.setchild_name(child_name);
                member.setage(age);
                member.setparent_name(parentname);
                childrn.push().setValue(member);

                Intent intent=new Intent(AddchildActivity.this, ParentshomepageActivity.class);
                intent.putExtra("email", Total);
                startActivity(intent);
            }
        });

    }
}