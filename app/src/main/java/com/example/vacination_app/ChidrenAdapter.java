package com.example.vacination_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChidrenAdapter  extends RecyclerView.Adapter<ChidrenAdapter.ViewHolder>{

    private List<ChildDisplay> listData;
    private Context mContext ;

    public ChidrenAdapter(Context mContext,List<ChildDisplay> listData) {
        this.listData = listData;
        this.mContext = mContext;
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,child_vaccineList.class);

                // passing data to the Product activity.
                intent.putExtra("Name",listData.get(position).getChild_name());
                intent.putExtra("Age",listData.get(position).getAge());
                intent.putExtra("parentName",listData.get(position).getParent_name());

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
