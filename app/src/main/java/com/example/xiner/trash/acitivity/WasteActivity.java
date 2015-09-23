package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.adapter.WasteAdapter;
import com.example.xiner.trash.main.Main;
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.model.Waste;
import com.example.xiner.trash.util.JsonUtil;
import com.example.xiner.trash.util.NetUtil;

import java.util.ArrayList;

public class WasteActivity extends ActionBarActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    WasteAdapter trashAdapter;
    ImageView publictrash;
    SwipeRefreshLayout swipeRefreshLayout;
    Main app;
    NetUtil net;
    JsonUtil json;
    private mHandler handler = new mHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_waste);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        trashAdapter = new WasteAdapter(this);
        mRecyclerView.setAdapter(trashAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.waste_customview);
        publictrash = (ImageView) findViewById(R.id.publicgoodallgood);
        ClickListener clickListener = new ClickListener();
        publictrash.setOnClickListener(clickListener);
        swipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new RreshListener());
        app = Main.getInstance();
        net = NetUtil.getInstance();
        json = new JsonUtil();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Waste> list = json.getWaste(net.wasteReq(0));
                trashAdapter.setWastes(list);
                Message msg = new Message();
                msg.what = 11;
                handler.sendMessage(msg);
//                allCommoditiesAdapter.notifyDataSetChanged();
            }
        }).start();


    }

    class RreshListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Waste> list = json.getWaste(net.wasteReq(0));
                    trashAdapter.setWastes(list);
                    Message msg = new Message();
                    msg.what = 11;
                    handler.sendMessage(msg);
//                allCommoditiesAdapter.notifyDataSetChanged();
                }
            }).start();


        }
    }

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.publicgoodallgood:

                    Intent intent = new Intent();
                    intent.setClass(WasteActivity.this, PublishWasteActivity.class
                    );
                    startActivity(intent);
                    break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    private class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 11:
                    trashAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 12:
                    trashAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    }

}
