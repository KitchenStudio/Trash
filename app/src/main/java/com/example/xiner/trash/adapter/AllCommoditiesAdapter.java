package com.example.xiner.trash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiner.trash.R;
import com.example.xiner.trash.acitivity.CommodityDetailActivity;

/**
 * Created by xiner on 15-3-10.
 */
public class AllCommoditiesAdapter extends RecyclerView.Adapter<AllCommoditiesAdapter.ViewHolder>{

    Context context;
    public AllCommoditiesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_commodities,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CommodityDetailActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView)itemView.findViewById(R.id.card_view_goods);
        }
    }

}
