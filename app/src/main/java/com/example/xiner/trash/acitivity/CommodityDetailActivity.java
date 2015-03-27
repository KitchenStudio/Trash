package com.example.xiner.trash.acitivity;


import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.xiner.trash.R;
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.util.NetCallBack;
import com.example.xiner.trash.util.NetError;
import com.example.xiner.trash.util.NetUtil;


public class CommodityDetailActivity extends ActionBarActivity implements NetCallBack<Commodity> {

    private Handler handler = new Handler();
    private Commodity commodity;
    private NetError error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.commodities_detail_customview);

        int id = getIntent().getIntExtra("id", -1);
        if (id != -1) {
            NetUtil.getInstance().getFiveItem(this);
        } else {
            // TODO if some error happen
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

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    // not in UI thread
    @Override
    public void receivedResult(Commodity result, NetError error) {
        Message msg = new Message();
        msg.arg1 = 1;
        this.commodity = result;
        this.error = error;
        handler.sendMessage(msg);
    }

    private class Handler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 1:
                    break;
            }
        }
    }
}
