package com.example.xiner.trash.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.acitivity.WasteDetailActivity;
import com.example.xiner.trash.model.Waste;

import java.util.ArrayList;

/**
 * Created by xiner on 15-3-11.
 */
public class WasteAdapter extends RecyclerView.Adapter<WasteAdapter.ViewHolder> {
    private Context context;

    public ArrayList<Waste> getWastes() {
        return wastes;
    }

    public void setWastes(ArrayList<Waste> wastes) {
        this.wastes = wastes;
    }

    private ArrayList<Waste> wastes = new ArrayList<>();

    public WasteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_waste, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.subject.setText(wastes.get(i).getGname());
        viewHolder.detail.setText(wastes.get(i).getDescription());
        viewHolder.date.setText(wastes.get(i).getTime().split(" ")[0]);
        viewHolder.phone.setText(wastes.get(i).getGphone());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("waste",wastes.get(i));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(context, WasteDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wastes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView subject,detail,date,phone;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_trash);
            subject =(TextView)itemView.findViewById(R.id.subject);
            detail=(TextView)itemView.findViewById(R.id.detail);
            date=(TextView)itemView.findViewById(R.id.date);
            phone=(TextView)itemView.findViewById(R.id.list_waste_phone);


        }
    }
}
