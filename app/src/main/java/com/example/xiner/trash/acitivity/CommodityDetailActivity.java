package com.example.xiner.trash.acitivity;


import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.util.NetCallBack;
import com.example.xiner.trash.util.NetError;
import com.example.xiner.trash.util.NetUtil;


public class CommodityDetailActivity extends ActionBarActivity implements NetCallBack<Commodity> {

    private static final String TAG = "CommodityDetailActivity";
    private Handler handler = new Handler();
    private Commodity commodity;
    private NetError error;
    private TextView publishtime,money,address,describe,phonenum,contactname,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.commodities_detail_customview);
        commodity = (Commodity) getIntent().getExtras().get("commodities");
        init();
        int id = getIntent().getIntExtra("id", -1);
        if (id != -1) {
            NetUtil.getInstance().getFiveItem(this);
        } else {
            // TODO if some error happen
        }

    }


    private void init(){
        publishtime =(TextView)findViewById(R.id.publishtimecomde_tv);
        money=(TextView)findViewById(R.id.moneycomd_tv);
        address=(TextView)findViewById(R.id.addresscomd_tv);
        describe=(TextView)findViewById(R.id.descricomd_tv);
        phonenum=(TextView)findViewById(R.id.phonenumcomd_tv);
        contactname=(TextView)findViewById(R.id.contactname_tv);
        name=(TextView)findViewById(R.id.namecomd_tv);
        publishtime.setText(commodity.getTime());
        money.setText(commodity.getPrice());
        address.setText(commodity.getPlace());
        describe.setText(commodity.getDescription());
        phonenum.setText(commodity.getUphone());
        contactname.setText(commodity.getUname());
        name.setText(commodity.getIname());
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
