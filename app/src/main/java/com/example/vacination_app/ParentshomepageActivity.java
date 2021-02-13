package com.example.vacination_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

public class ParentshomepageActivity extends AppCompatActivity {

   private RecyclerView mChildList;
   private FirebaseFirestore firebaseFirestore;

   private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentshomepage);

        mChildList=findViewById(R.id.recyclerview);
        firebaseFirestore=FirebaseFirestore.getInstance();

        Query query=firebaseFirestore.collection("children");

        FirestoreRecyclerOptions<ChildDisplay> options = new FirestoreRecyclerOptions.Builder<ChildDisplay>()
                .setQuery(query, ChildDisplay.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ChildDisplay, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder productsViewHolder, int i   , @NonNull ChildDisplay childDisplay) {
                productsViewHolder.child_name.setText(childDisplay.getChild_name());
                productsViewHolder.child_age.setText(childDisplay.getAge());
            }
        };

        mChildList.setHasFixedSize(true);
        mChildList.setLayoutManager(new LinearLayoutManager(this));
        mChildList.setAdapter(adapter);


    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        private TextView child_name,child_age;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            child_name=itemView.findViewById(R.id.childNametxt);
            child_age= itemView.findViewById(R.id.childAgetxt);


        }
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }



}
