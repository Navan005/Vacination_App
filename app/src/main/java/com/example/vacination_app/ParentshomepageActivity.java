package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//in this parents can add their child and view already added child
public class ParentshomepageActivity extends AppCompatActivity {

    private List<ChildDisplay> listData;
    private RecyclerView rv;
    private ChidrenAdapter adapter;

    private Button addChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentshomepage);

        addChild=(Button) findViewById(R.id.addRecords);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Total = user.getEmail();
        //String Total=getIntent().getStringExtra("email");

        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Children");
        Query query = nm.orderByChild("parent_name").equalTo(Total);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ChildDisplay l=npsnapshot.getValue(ChildDisplay.class);
                        l.setId(npsnapshot.getKey());
                        listData.add(l);
                    }
                    adapter=new ChidrenAdapter(ParentshomepageActivity.this,listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ParentshomepageActivity.this, AddchildActivity.class);
                intent.putExtra("email", Total);
                startActivity(intent);
            }
        });


    }
}
