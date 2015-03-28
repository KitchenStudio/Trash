package com.example.xiner.trash.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.xiner.trash.R;
import com.example.xiner.trash.model.CustomGallery;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import java.util.ArrayList;


/**
 * Created by xiner on 15-1-20.
 */
public class GalleryAdapter extends BaseAdapter {
    LayoutInflater infalter;
    Context mContext;
    ImageLoader imageLoader;
    public ArrayList<CustomGallery> data = new ArrayList<CustomGallery>();
    private static final int SECOND_BACK=2;
    private static final int FIRST_BACK=2;

    public boolean isActionMultiplePick() {
        return isActionMultiplePick;
    }

    public void setActionMultiplePick(boolean isActionMultiplePick) {
        this.isActionMultiplePick = isActionMultiplePick;
    }

    private boolean isActionMultiplePick;

    public GalleryAdapter(Context c, ImageLoader imageLoader) {
        infalter = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
        this.imageLoader = imageLoader;
        // clearCache();
    }
    @Override
    public int getCount() {
        return data.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void changeSelection(View v, int position) {

        if (data.get(position).isSeleted) {
            data.get(position).isSeleted = false;
        } else {
            data.get(position).isSeleted = true;
        }

        ((ViewHolder) v.getTag()).imgQueueMultiSelected.setSelected(data
                .get(position).isSeleted);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        final ViewHolder holder;
        final ImageView imgQueue;
        ImageView imgQueueMultiSelected;
//        if(convertView==null){
            convertView = infalter.inflate(R.layout.gallery_item,null);
//            holder = new ViewHolder();
            imgQueue=(ImageView)convertView.findViewById(R.id.imgQueue);
            imgQueueMultiSelected =(ImageView)convertView.findViewById(R.id.imgQueueMultiSelected);
            if(isActionMultiplePick){
               imgQueueMultiSelected.setVisibility(View.VISIBLE);

            }else {
                imgQueueMultiSelected.setVisibility(View.GONE);
            }
//            convertView.setTag(holder);

//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        imgQueue.setTag(position);
        try {
            if (position!=data.size()) {
                Log.v("TAG", imageLoader + "imageloader");
                imageLoader.displayImage("file://" + data.get(position).sdcardPath,
                        imgQueue, new SimpleImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
                                imgQueue
                                        .setImageResource(R.drawable.no_media);
                                super.onLoadingStarted(imageUri, view);
                            }
                        });
            }else {
                imgQueue.setImageResource(R.drawable.addgoodpic);

            }
            if (isActionMultiplePick) {

                imgQueueMultiSelected
                        .setSelected(data.get(position).isSeleted);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (position == data.size()){
            return SECOND_BACK;
        }else{
            return FIRST_BACK;
        }

    }


    public void addAll(ArrayList<CustomGallery> files) {

        try {
            this.data.addAll(files);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();
    }

    public ArrayList<CustomGallery> getSelected() {
        ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSeleted) {
                dataT.add(data.get(i));
            }
        }

        return dataT;
    }
    public class ViewHolder {
        ImageView imgQueue;
        ImageView imgQueueMultiSelected;
    }
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
}
