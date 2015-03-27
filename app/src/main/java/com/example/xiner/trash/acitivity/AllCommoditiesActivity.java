package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.util.JsonUtil;
import com.example.xiner.trash.util.NetUtil;

import java.util.ArrayList;


public class AllCommoditiesActivity extends ActionBarActivity {

    private Main app;
    private RecyclerView mRecyclerView;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout secondHandLinear;
    private LinearLayout wasteLinear;
    private LinearLayoutManager mLayoutManager;
    private AllCommoditiesAdapter allCommoditiesAdapter;
    private Spinner timeSpin, distanceSpin, moneySpin;
    private ImageView personImage;
    //private ImageView publishCommodityImage;
    private String[] time = {"时间", "由近到远", "由远到近"};
    private String[] distance = {"距离", "一千米之内", "两千米之内", "三千米之内", "三千米之外"};
    private String[] money = {"价格", "一百元一下", "两百元以下", "两百元以上"};

    private ArrayAdapter<String> timeAda, distanceAda, moneyAda;
    private NetUtil net;
    private JsonUtil json;
    private mHandler handler = new mHandler();

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
        net = NetUtil.getInstance();
        json = new JsonUtil();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Commodity> list = json.getCommodities(net.commodityReq(0));
                allCommoditiesAdapter.setCommodities(list);
                Message msg = new Message();
                msg.what = 11;
                handler.sendMessage(msg);
                //allCommoditiesAdapter.notifyDataSetChanged();
            }
        }).start();

    }

    private void init() {
        // SpinnerListener spListener = new SpinnerListener();
        timeSpin = (Spinner) findViewById(R.id.time_spinner);
        distanceSpin = (Spinner) findViewById(R.id.distance_spinner);
        moneySpin = (Spinner) findViewById(R.id.money_spinner);
        timeAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, time);
        distanceAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, distance);
        moneyAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, money);

        timeSpin.setAdapter(timeAda);
        distanceSpin.setAdapter(distanceAda);
        moneySpin.setAdapter(moneyAda);

//        timeSpin.setOnItemSelectedListener(spListener);
//        distanceSpin.setOnItemSelectedListener(spListener);
//        moneySpin.setOnItemSelectedListener(spListener);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_allCommodities);
        mRecyclerView.setHasFixedSize(true);
        allCommoditiesAdapter = new AllCommoditiesAdapter(this);
        mRecyclerView.setAdapter(allCommoditiesAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Listener listener = new Listener();
        personImage = (ImageView) findViewById(R.id.person_image);
        personImage.setOnClickListener(listener);
        //publishCommodityImage = (ImageView) findViewById(R.id.publicgood);
        //publishCommodityImage.setOnClickListener(new publicgoodListener());
        wasteLinear = (LinearLayout) findViewById(R.id.waste_linear);
        wasteLinear.setOnClickListener(listener);
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

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.commodity_linear:
                    if (!app.isLogin()) {
                        Login();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(AllCommoditiesActivity.this, PublishCommodityActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.person_image:
                    if (!app.isLogin()) {
                        Login();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(AllCommoditiesActivity.this, PersonActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.waste_linear:
                    if (!app.isLogin()) {
                        Login();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(AllCommoditiesActivity.this, WasteActivity.class);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }

    private class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 11:
                    allCommoditiesAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

//    private class netWork extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] params) {
//            net.commodityReq();
//            return null;
//        }
//    }


//    class DownImage extends AsyncTask {
//
//        private ImageView imageView;
//
//        public DownImage(ImageView imageView) {
//            this.imageView = imageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            String url = params[0];
//            Bitmap bitmap = null;
//            try {
//                //加载一个网络图片
//                InputStream is = new URL(url).openStream();
//                bitmap = BitmapFactory.decodeStream(is);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);
//        }
//    }


//    class SpinnerListener implements AdapterView.OnItemSelectedListener {
//
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            switch (parent.getId()) {
//                case R.id.time_spinner:
//                    Toast.makeText(AllCommoditiesActivity.this, time[position] + "已选中", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.money_spinner:
//                    Toast.makeText(AllCommoditiesActivity.this, money[position] + "已选中", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.distance_spinner:
//                    Toast.makeText(AllCommoditiesActivity.this, distance[position] + "已选中", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//        }
//    }

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
