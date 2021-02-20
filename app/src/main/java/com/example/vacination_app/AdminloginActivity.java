package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
//login for admin
public class AdminloginActivity extends AppCompatActivity {

    Button Login_button;
    EditText email,password;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        Login_button = (Button) findViewById(R.id.btnLogin);
        email=findViewById(R.id.email_edit);
        password=findViewById(R.id.password_edit);

        db= FirebaseFirestore.getInstance();


        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//verifying email and password
                if(email.getText().toString().equals("")){
                    Toast.makeText(AdminloginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("")){
                    Toast.makeText(AdminloginActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                }

                db.collection("admin")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot doc : task.getResult()){
                                        String a=doc.getString("Email");
                                        String b=doc.getString("Password");
                                        String a1=email.getText().toString().trim();
                                        String b1=password.getText().toString().trim();
                                        if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                            Intent home = new Intent(AdminloginActivity.this, Admin_dashboard.class);
                                            startActivity(home);
                                            //display message about successfull login or not
                                            Toast.makeText(AdminloginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                                            break;
                                        }else
                                            Toast.makeText(AdminloginActivity.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                //Intent intent = new Intent(AdminloginActivity.this, Admin_dashboard.class);
                //startActivity(intent);
            }
        });
    }
}