package com.example.pythin;

import static com.example.pythin.ItemClass.LayoutOne;
import static com.example.pythin.ItemClass.LayoutTwo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    private WeakReference<Context> mContext;
    private List<Items> itemsList;
    Context context;
    String typ;
//    private ClickListener clickListener;

    AdapterClass(List<Items> mItemList,String t){
        this.itemsList = mItemList;
        this.typ=t;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rows,parent,false);
        return new MyViewHolder(view);


    }



    @Override
    public void onBindViewHolder(AdapterClass.MyViewHolder holder, final int position) {
        final Items item = itemsList.get(position);

        holder.Date.setText(String.valueOf(item.getData()));
        String to="UnAnswerd";

        if(item.getStatus().equals("0")){
            holder.Update.setText(String.valueOf("Update"));
            holder.Update.setTextColor(Color.parseColor("#EF2B2B"));
        }
        else if(item.getStatus().equals("1")){
            holder.Update.setText(String.valueOf("checked"));
            holder.Update.setTextColor(Color.parseColor("#5A7948"));
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i=new Intent(v.getContext(), ShowData.class);
                    i.putExtra("Half_Mature",item.getImMature());
                    i.putExtra("Mature",item.getMature());
                    i.putExtra("date",item.getData());
                    i.putExtra("time",item.getTotalImages());
                    i.putExtra("harvest",item.getHarvest());
                    i.putExtra("status",item.getStatus());
                    i.putExtra("key",item.getKey());

                    //i.putExtra("issue",item.getIncidentDetail());
                    v.getContext().startActivity(i);

                }catch (Exception e){
                    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView Date,Update;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            Date=itemView.findViewById(R.id.tvDate);
            Update=itemView.findViewById(R.id.tvUpdate);

            itemLayout =  itemView.findViewById(R.id.itemLayout1);
        }
    }
}