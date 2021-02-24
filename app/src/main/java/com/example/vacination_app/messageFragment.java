package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class messageFragment extends Fragment {

    public messageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_message, container, false);
        Button buttonM =(Button) view.findViewById(R.id.buttonM);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),ParentshomepageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
