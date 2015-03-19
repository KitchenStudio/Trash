package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xiner.trash.R;
import com.example.xiner.trash.adapter.AllCommoditiesAdapter;
import com.example.xiner.trash.main.Main;


public class AllCommoditiesActivity extends ActionBarActivity {

    private Main app;
    private RecyclerView mRecyclerView;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout wasteLinear;
    private LinearLayoutManager mLayoutManager;
    private AllCommoditiesAdapter allCommoditiesAdapter;
    private Spinner timeSpin, distanceSpin, moneySpin, selectSpin;
    private ImageView personImage;
    private ImageView publishCommodityImage;
    private String[] time = {"时间", "由近到远", "由远到近"};
    private String[] distance = {"距离", "一千米之内", "两千米之内", "三千米之内", "三千米之外"};
    private String[] money = {"价格", "一百元一下", "两百元以下", "两百元以上"};

    private ArrayAdapter<String> timeAda, distanceAda, moneyAda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_commodities);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.commodity_customview);
        app = Main.getInstance();
        init();


    }

    private void init() {
        SpinnerListener spListener = new SpinnerListener();
        timeSpin = (Spinner) findViewById(R.id.time_spinner);
        distanceSpin = (Spinner) findViewById(R.id.distance_spinner);
        moneySpin = (Spinner) findViewById(R.id.money_spinner);

        timeAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, time);
        distanceAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, distance);
        moneyAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, money);

        timeSpin.setAdapter(timeAda);
        distanceSpin.setAdapter(distanceAda);
        moneySpin.setAdapter(moneyAda);

        timeSpin.setOnItemSelectedListener(spListener);
        distanceSpin.setOnItemSelectedListener(spListener);
        moneySpin.setOnItemSelectedListener(spListener);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_allgood);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        allCommoditiesAdapter = new AllCommoditiesAdapter(this);
        mRecyclerView.setAdapter(allCommoditiesAdapter);

        personImage = (ImageView) findViewById(R.id.person_image);
        personImage.setOnClickListener(new PersonListener());
        publishCommodityImage = (ImageView) findViewById(R.id.publicgoodallgood);
        publishCommodityImage.setOnClickListener(new publicgoodListener());
        wasteLinear = (LinearLayout) findViewById(R.id.trash_linear);
        wasteLinear.setOnClickListener(new trashListener());
    }

    private void Login() {
        try {
            Toast.makeText(AllCommoditiesActivity.this, "你尚未登陆，请登陆", Toast.LENGTH_LONG).show();
            Thread.sleep(1000);
            Intent intent = new Intent(AllCommoditiesActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class trashListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!app.isLogin()) {
                Login();
            } else {
                Intent intent = new Intent();
                intent.setClass(AllCommoditiesActivity.this, WasteActivity.class);
                startActivity(intent);
            }
        }
    }

    class publicgoodListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (!app.isLogin()) {
                Login();
            } else {
                Intent intent = new Intent();
                intent.setClass(AllCommoditiesActivity.this, PublishCommodityActivity.class);
                startActivity(intent);
            }
        }
    }

    class PersonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!app.isLogin()) {
                Login();
            } else {
                Intent intent = new Intent();
                intent.setClass(AllCommoditiesActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        }
    }

    class SpinnerListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.time_spinner:
                    Toast.makeText(AllCommoditiesActivity.this, time[position] + "已选中", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.money_spinner:
                    Toast.makeText(AllCommoditiesActivity.this, money[position] + "已选中", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.distance_spinner:
                    Toast.makeText(AllCommoditiesActivity.this, distance[position] + "已选中", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

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
}
