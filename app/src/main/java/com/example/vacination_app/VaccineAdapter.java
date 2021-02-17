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

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.ViewHolder>{

    private List<VaccineDisplay> listData;
    private Context mContext ;

    public VaccineAdapter(Context mContext,List<VaccineDisplay> listData) {
        this.listData = listData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VaccineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_child,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineAdapter.ViewHolder holder, int position) {
        VaccineDisplay ld=listData.get(position);
        holder.txtname.setText(ld.getName());
        holder.txtage.setText(ld.getRecommendedAge() + " year");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,vaccine_detailview.class);

                // passing data to the Product activity.
                intent.putExtra("Name",listData.get(position).getName());
                intent.putExtra("Description",listData.get(position).getDescription());
                intent.putExtra("Age",listData.get(position).getRecommendedAge());

                mContext.startActivity(intent);
            }
        });
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