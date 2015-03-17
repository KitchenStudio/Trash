package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.xiner.trash.adapter.AllGoodAdapter;


public class AllGoodsActivity extends ActionBarActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayoutManager mLayoutManager;
    AllGoodAdapter allGoodAdapter;
    ImageView personImage;
    String [] time ={"时间","由近到远","由远到近"};
    String [] distance ={"距离","一千米之内","两千米之内","三千米之内","三千米之外"};
    String [] money ={"价格","一百元一下","两百元以下","两百元以上"};
    String [] select={"筛选","济南市","平板电脑","全部价格"};
    Spinner timeSpin,distanceSpin,moneySpin,selectSpin;
    ArrayAdapter<String> timeAda,distanceAda,moneyAda,selectAda;
    ImageView publicgoodImage;
    LinearLayout trashlinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);

        timeSpin=(Spinner)findViewById(R.id.time_spinner);
        distanceSpin=(Spinner)findViewById(R.id.distance_spinner);
        moneySpin=(Spinner)findViewById(R.id.money_spinner);
        selectSpin=(Spinner)findViewById(R.id.select_spinner);
        timeAda = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,time);
        distanceAda = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,distance);
        moneyAda = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,money);
        selectAda = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,select);
        timeSpin.setAdapter(timeAda);
        distanceSpin.setAdapter(distanceAda);
        moneySpin.setAdapter(moneyAda);
        selectSpin.setAdapter(selectAda);
        timeSpin.setOnItemSelectedListener(new timeSpiListener());
        distanceSpin.setOnItemSelectedListener(new distanceSpiListener());
        moneySpin.setOnItemSelectedListener(new moneySpiListener());
        selectSpin.setOnItemSelectedListener(new selectSpiListener());
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_allgood);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        allGoodAdapter = new AllGoodAdapter(this);
        mRecyclerView.setAdapter(allGoodAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.allgoods_customview);
        personImage =(ImageView)findViewById(R.id.person_image);
        personImage.setOnClickListener(new PersonListener());
        publicgoodImage=(ImageView)findViewById(R.id.publicgood);
        publicgoodImage.setOnClickListener(new publicgoodListener());
        trashlinear=(LinearLayout)findViewById(R.id.trash_linear);
        trashlinear.setOnClickListener(new trashListener());
    }

    class trashListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(AllGoodsActivity.this,WasteActivity.class);
            startActivity(intent);
        }
    }
    class publicgoodListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(AllGoodsActivity.this,PublishCommodityActivity.class);
            startActivity(intent);
        }
    }

    class PersonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(AllGoodsActivity.this,PersonActivity.class);
            startActivity(intent);
        }
    }

    class timeSpiListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AllGoodsActivity.this,time[position]+"已选中",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

    class moneySpiListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AllGoodsActivity.this,money[position]+"已选中",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    class distanceSpiListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AllGoodsActivity.this,distance[position]+"已选中",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    class selectSpiListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(AllGoodsActivity.this,select[position]+"已选中",Toast.LENGTH_SHORT).show();
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
