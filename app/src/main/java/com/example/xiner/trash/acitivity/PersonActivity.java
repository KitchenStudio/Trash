package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiner.trash.R;


public class PersonActivity extends ActionBarActivity {

    private Intent intent;
    private LinearLayout settingLinearLayout, addressLinear, collectionLinear, myreleaseLinear;
    private TextView nameTv;
    private TextView secondHandReleaseTv;
    private TextView wasteOrderTv;
    private TextView collectionTv;
    private TextView addressTv;
    private TextView infoTv;
    private TextView historyTv;
    private TextView settingsTv;
    private TextView secondHandTradeTv;
    private TextView wasteRecycleTv;
    private LinearLayout trashrecycle,twohan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        init();

    }

    private void init() {
        PersonListener listener = new PersonListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.person_customview);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //初始化组件
        nameTv = (TextView) findViewById(R.id.person_tv_name);
        nameTv.setText(name);
        secondHandReleaseTv = (TextView) findViewById(R.id.person_tv_secondhand_release);
        wasteOrderTv = (TextView) findViewById(R.id.person_tv_waste_order);
        collectionTv = (TextView) findViewById(R.id.person_tv_collection);
        addressTv = (TextView) findViewById(R.id.person_tv_address);
        infoTv = (TextView) findViewById(R.id.person_tv_info);
        historyTv = (TextView) findViewById(R.id.person_tv_history);
        settingsTv = (TextView) findViewById(R.id.person_tv_settings);
        secondHandTradeTv = (TextView) findViewById(R.id.person_tv_secondhand_trade);
        wasteRecycleTv = (TextView) findViewById(R.id.person_tv_waste_recycle);
        trashrecycle =(LinearLayout)findViewById(R.id.person_ll_trash);
        twohan=(LinearLayout)findViewById(R.id.person_ll_secondhand);

        //添加监听事件
        secondHandReleaseTv.setOnClickListener(listener);
        wasteOrderTv.setOnClickListener(listener);
        collectionTv.setOnClickListener(listener);
        addressTv.setOnClickListener(listener);
        infoTv.setOnClickListener(listener);
        historyTv.setOnClickListener(listener);
        settingsTv.setOnClickListener(listener);
        secondHandTradeTv.setOnClickListener(listener);
        wasteRecycleTv.setOnClickListener(listener);
        trashrecycle.setOnClickListener(listener);
        twohan.setOnClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    private class PersonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.person_tv_secondhand_release:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, FragmentTabsPager.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_waste_order:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, FragmentTabsPager.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_collection://ok
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, MyCollectionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_address://ok
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, MyAddressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_info:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, MyInformationActivity.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_history:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, SettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.person_tv_settings://ok
                    intent = new Intent();
                    intent.setClass(PersonActivity.this, SettingActivity.class);
                    startActivity(intent);
                    break;
//                case R.id.person_tv_secondhand_trade://ok
//                    intent = new Intent();
//                    intent.setClass(PersonActivity.this, PublishCommodityActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.person_tv_waste_recycle://ok
//                    intent = new Intent();
//                    intent.setClass(PersonActivity.this, PublishWasteActivity.class);
//                    startActivity(intent);
//                    break;
                case R.id.person_ll_secondhand:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this,AllCommoditiesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.person_ll_trash:
                    intent = new Intent();
                    intent.setClass(PersonActivity.this,WasteActivity.class);
                    startActivity(intent);
                    break;

            }
        }
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
