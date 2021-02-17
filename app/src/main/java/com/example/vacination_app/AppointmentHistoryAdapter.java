package com.example.vacination_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentHistoryAdapter extends RecyclerView.Adapter<AppointmentHistoryAdapter.ViewHolder>{

    private List<AppointmentDisplay> listData;
    private Context mContext ;

    public AppointmentHistoryAdapter(Context mContext,List<AppointmentDisplay> listData) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AppointmentHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHistoryAdapter.ViewHolder holder, int position) {
        AppointmentDisplay ld=listData.get(position);
        holder.txtname.setText("Vaccine: " + ld.getVaccineRequested());
        holder.txtage.setText("Date: " +ld.getAppointmentDate());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        private TextView txtname,txtage;

        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.childNametxt);
            txtage=(TextView)itemView.findViewById(R.id.childAgetxt);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
