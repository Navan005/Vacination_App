package com.example.vacination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddchildActivity extends AppCompatActivity {

    private Button addChild;
    FirebaseFirestore db;
    EditText childName,childAge;
    DatabaseReference childrn;
    AddingChild member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchild);

        addChild=(Button) findViewById(R.id.btn_addchild);
        childName=findViewById(R.id.child_nameEditxt);
        childAge=findViewById(R.id.dateofbirth);
        db = FirebaseFirestore.getInstance();
        childrn= FirebaseDatabase.getInstance().getReference().child("Children");
        member=new AddingChild();

        String Total=getIntent().getStringExtra("email");

        addChild.setOnClickListener(new View.OnClickListener() {
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