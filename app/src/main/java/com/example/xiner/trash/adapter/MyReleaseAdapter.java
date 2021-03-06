package com.example.xiner.trash.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiner.trash.R;
import com.example.xiner.trash.acitivity.EditCommodityActivity;

/**
 * Created by xiner on 15-3-11.
 */
public class MyReleaseAdapter extends RecyclerView.Adapter<MyReleaseAdapter.ViewHolder> {

    Context context;
    public MyReleaseAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.myreleaselist,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, EditCommodityActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView =(CardView)itemView.findViewById(R.id.card_view_releasegoods);

        }
    }
}
