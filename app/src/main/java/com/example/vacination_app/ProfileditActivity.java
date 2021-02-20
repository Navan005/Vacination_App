package com.example.vacination_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ProfileditActivity extends AppCompatActivity {

    private EditText name,email,phone;
    private Button updateBtn;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledit);

        updateBtn = (Button) findViewById(R.id.btn_update);
        name = (EditText) findViewById(R.id.nametxt);
        email = (EditText) findViewById(R.id.emailtxt);
        phone = (EditText) findViewById(R.id.phonetxt);
        db = FirebaseFirestore.getInstance();




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();


        DocumentReference docRef = db.collection("users").document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
             if(documentSnapshot.exists()){
                 String named=documentSnapshot.getString("fname");
                 String emaild=documentSnapshot.getString("email");
                 String phoned=documentSnapshot.getString("phone");

                 name.setText(named);
                 email.setText(emaild);
                 phone.setText(phoned);
             }

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullNamee = name.getText().toString();
                String emaill = email.getText().toString();
                String phonee = phone.getText().toString();

                DocumentReference docRef = db.collection("users").document(userId);

                HashMap hashMap=new HashMap();
                hashMap.put("fname",fullNamee);
                hashMap.put("email",emaill);
                hashMap.put("phone",phonee);

                docRef.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent=new Intent(ProfileditActivity.this, ParentshomepageActivity.class);
                        Toast.makeText(ProfileditActivity.this, "Child info updated", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });



            }
        });




    }
}