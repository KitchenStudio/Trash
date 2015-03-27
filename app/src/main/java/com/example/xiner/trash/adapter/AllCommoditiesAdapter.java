package com.example.xiner.trash.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.acitivity.CommodityDetailActivity;
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.util.JsonUtil;
import com.example.xiner.trash.util.NetUtil;

import java.util.ArrayList;

/**
 * Created by xiner on 15-3-10.
 */
public class AllCommoditiesAdapter extends RecyclerView.Adapter<AllCommoditiesAdapter.ViewHolder> {

    private static final String TAG = "AllCommoditiesAdapter";
    private Context context;
    private ArrayList<Commodity> commodities = new ArrayList<Commodity>();

    public AllCommoditiesAdapter(Context context) {
        this.context = context;
    }

    public void setCommodities(ArrayList<Commodity> commodities) {
        this.commodities = commodities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_all_commodities, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        for (Commodity item : commodities) {
            viewHolder.inameTv.setText(item.getIname());
           // viewHolder.descTv.setText(item.getDesc());
           // viewHolder.dateTv.setText(item.getCreateTime());
            viewHolder.priceTv.setText(item.getPrice());

        }

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

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView inameTv;
        TextView imageNumTv;
        TextView descTv;
        TextView dateTv;
        TextView priceTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_commodities);
            inameTv = (TextView) itemView.findViewById(R.id.list_commodity_tv_iname);
            imageNumTv = (TextView) itemView.findViewById(R.id.list_commodity_tv_num);
            descTv = (TextView) itemView.findViewById(R.id.list_commodity_tv_desc);
            dateTv = (TextView) itemView.findViewById(R.id.list_commodity_tv_date);
            priceTv = (TextView) itemView.findViewById(R.id.list_commodity_tv_price);

        }
    }

}
