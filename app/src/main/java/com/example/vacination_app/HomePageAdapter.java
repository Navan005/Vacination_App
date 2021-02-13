package com.example.vacination_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    ArrayList<Child> child;


    public HomePageAdapter(ArrayList<Child> childName) {
        this.child = childName;
    }


    @NonNull
    @Override
    public HomePageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_child_name, parent, false);
        return new HomePageAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull HomePageAdapter.ViewHolder holder, int position) {
        holder.childName.setText(child.get(position).getChildName());

    }

    @Override
    public int getItemCount() {
        return child.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView childName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            childName = itemView.findViewById(R.id.childName);
            itemView.setTag(this);

        }
    }
}
