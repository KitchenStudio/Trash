package com.example.xiner.trash.acitivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.xiner.trash.R;
import com.example.xiner.trash.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PublishCommodityActivity extends ActionBarActivity {

    private EditText inameEt;
    private EditText priceEt;
    private EditText descEt;
    private EditText unameEt;
    private EditText phoneEt;
    private EditText qqEt;
    private EditText addressEt;
    private Spinner commoditySortSp;
    private Spinner recencySp;
    private Button publishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_commodity);
        init();

    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        inameEt = (EditText) findViewById(R.id.publish_commodity_et_iname);
        priceEt = (EditText) findViewById(R.id.publish_commodity_et_price);
        descEt = (EditText) findViewById(R.id.publish_commodity_et_desc);
        unameEt = (EditText) findViewById(R.id.publish_commodity_et_uname);
        phoneEt = (EditText) findViewById(R.id.publish_commodity_et_phone);
        qqEt = (EditText) findViewById(R.id.publish_commodity_et_qq);
        addressEt = (EditText) findViewById(R.id.publish_commodity_et_address);
        commoditySortSp = (Spinner) findViewById(R.id.publish_commodity_sp_sort);
        recencySp = (Spinner) findViewById(R.id.publish_commodity_sp_recency);
        publishBtn = (Button) findViewById(R.id.publish_commodity_btn_publish);
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new secondHandThread().start();
            }
        });
    }

    private class secondHandThread extends Thread {
        private NetUtil net;
        private JSONObject jsonObject;
        private String iname, price, desc, uname, phone, qq, address, sort, recency;

        @Override
        public void run() {
            iname = inameEt.getText().toString();
            price = priceEt.getText().toString();
            desc = descEt.getText().toString();
            uname = unameEt.getText().toString();
            phone = phoneEt.getText().toString();
            qq = qqEt.getText().toString();
            address = addressEt.getText().toString();
            sort = commoditySortSp.getSelectedItem().toString();
            recency = recencySp.getSelectedItem().toString();
            //Log.d("DataTest",iname+price+desc+uname+phone+qq+address+sort+recency);
            try {
                net = new NetUtil(PublishCommodityActivity.this);
                jsonObject = new JSONObject();

                jsonObject.put("i.iname", iname);
                jsonObject.put("i.price", price);
                jsonObject.put("i.description", desc);
                jsonObject.put("i.uname", uname);
                jsonObject.put("i.uphone", phone);
                jsonObject.put("i.qq", qq);
                jsonObject.put("i.place", address);
                jsonObject.put("i.catagory", sort);
                jsonObject.put("i.old", recency);
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date).toString();
                jsonObject.put("i.time",time);
                //jsonObject.put("i.time",new Date(udate.getTime()));
                Log.d("DateTest",time);

                net.secondhandRealeaseReq(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            net.secondhandRealeaseReq(jsonObject);
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
}
