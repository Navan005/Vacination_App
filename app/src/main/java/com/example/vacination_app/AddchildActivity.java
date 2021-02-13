package com.example.vacination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddchildActivity extends AppCompatActivity {

    private Button addChild;
    FirebaseFirestore db;
    String child_name;
    EditText childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchild);

        addChild=(Button) findViewById(R.id.btn_addchild);
        childName=findViewById(R.id.child_nameEditxt);
        db = FirebaseFirestore.getInstance();

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                child_name=childName.getText().toString();


                Intent intent=new Intent(AddchildActivity.this, ParentshomepageActivity.class);
                //intent.putExtra("email_key", email);
                startActivity(intent);
            }
        });
    }


}