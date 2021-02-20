package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DeletingchildActivity extends AppCompatActivity {

    EditText childName, childAge;
    Button editInfo, deleteInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletingchild);

        childName=findViewById(R.id.childNametxt);
        childAge=findViewById(R.id.childAgetxt);

        editInfo=findViewById(R.id.btn_editInformation);
        deleteInfo=findViewById(R.id.btn_deleteChild);

        Intent intent = getIntent();
        String childId = intent.getStringExtra("id");
        String Title = intent.getExtras().getString("name");
        String Description = intent.getExtras().getString("age");
        String parentName = intent.getExtras().getString("parentName");

        childName.setText(Title);
        childAge.setText(Description);

        deleteInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String childnamee = childName.getText().toString();
//checking childname from the database to remove from it
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Children");
                Query query=databaseReference.orderByChild("child_name").equalTo(childnamee);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Toast.makeText(DeletingchildActivity.this, "Child deleted.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeletingchildActivity.this, ParentshomepageActivity.class);
                startActivity(intent);
            }
        });

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Children").child(childId);

                Map<String, Object> data = new HashMap<>();
                data.put("child_name", childName.getText().toString());
                data.put("age", childAge.getText().toString());

                databaseReference.updateChildren(data, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null){
                            Toast.makeText(getApplicationContext(), "Child information updated.", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DeletingchildActivity.this, ParentshomepageActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                //Intent intent = new Intent(ConfirmAppointmentActivity.this, ParentshomepageActivity.class);
                //startActivity(intent);

            }
        });


    }
}