package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class chatFragment extends Fragment {

    public chatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        Button buttonC =(Button) view.findViewById(R.id.buttonC);                   // This is to connect a fragment to the main activity
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),ParentshomepageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
