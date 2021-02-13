package com.example.vacination_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccinesAdapter extends RecyclerView.Adapter<VaccinesAdapter.ViewHolder>{

    private List<VaccineDisplay> listData;

    public VaccinesAdapter(List<VaccineDisplay> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VaccineDisplay ld=listData.get(position);
        holder.txtname.setText(ld.getName());
        holder.txtage.setText(ld.getRecommendedAge());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtage;

        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.childNametxt);
            txtage=(TextView)itemView.findViewById(R.id.childAgetxt);

        }
    }
}
