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

public class PublishWasteActivity extends ActionBarActivity {

    private EditText inameEt;
    private EditText descEt;
    private EditText unameEt;
    private EditText phoneEt;
    private EditText addressEt;
    private Button releaseBtn;
    private Spinner typeSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_waste);
        init();
    }

    private void init() {
        inameEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        descEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        unameEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        phoneEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        addressEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        releaseBtn = (Button) findViewById(R.id.publish_waste_btn_release);
        typeSp = (Spinner) findViewById(R.id.publish_waste_sp_type);
        releaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new wasteThread().start();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publictrash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class wasteThread extends Thread {
        private NetUtil net;
        private JSONObject object;
        private String iname, uname, desc, phone, place, catagory, time;
        //private double longitude,parallel;扩展使用的经纬度

        @Override
        public void run() {
            iname = inameEt.getText().toString();
            uname = unameEt.getText().toString();
            desc = descEt.getText().toString();
            phone = phoneEt.getText().toString();
            place = addressEt.getText().toString();
            catagory = typeSp.getSelectedItem().toString();

            net = NetUtil.getInstance();
            object = new JSONObject();

            try {
                object.put("g.uname", uname);
                object.put("g.gname", iname);
                object.put("g.description", desc);
                object.put("g.gphone", phone);
                object.put("g.place", place);
                object.put("g.catagory", catagory);

                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date).toString();
                object.put("g.time", time);
                net.wasteRealeaseReq(object);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}