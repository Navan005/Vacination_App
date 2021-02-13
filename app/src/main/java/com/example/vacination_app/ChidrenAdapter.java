package com.example.vacination_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChidrenAdapter  extends RecyclerView.Adapter<ChidrenAdapter.ViewHolder>{
    private List<ChildDisplay> listData;


    public ChidrenAdapter(List<ChildDisplay> listData) {
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
        ChildDisplay ld=listData.get(position);
        holder.txtname.setText(ld.getChild_name());
        holder.txtage.setText(ld.getAge());
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
