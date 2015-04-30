package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;

public class SelectActivity extends ActionBarActivity implements AMapLocationListener{
    private static final String TAG = "SelectActivity";
    Button twohandButton;
    Button trashrecycleButton;
    private LocationManagerProxy mLocationManagerProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.select_customview);
        twohandButton =(Button)findViewById(R.id.twohandbutton);
        twohandButton.setOnClickListener(new twoHandListener());
        trashrecycleButton=(Button)findViewById(R.id.trash_recycle);
        trashrecycleButton.setOnClickListener(new trashListener());
        init();
    }

    /**
     * 初始化定位
     */
    private void init() {

        mLocationManagerProxy = LocationManagerProxy.getInstance(this);

        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60*1000, 15, this);

        mLocationManagerProxy.setGpsEnable(false);
    }
    @Override
    protected void onResume() {
        super.onResume();
        trashrecycleButton.setTextColor(getResources().getColor(R.color.grey));
        twohandButton.setTextColor(getResources().getColor(R.color.grey));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        String address = aMapLocation.getProvince()+aMapLocation.getCity();
        Log.v(TAG, aMapLocation.getProvince()+aMapLocation.getCity()+"citycitycity");
        Main.getInstance().getDataStore().edit().putString("location",address).commit();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    class twoHandListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            trashrecycleButton.setTextColor(getResources().getColor(R.color.grey));
            twohandButton.setTextColor(getResources().getColor(R.color.green));
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this,AllCommoditiesActivity.class);
            startActivity(intent);

        }
    }
    class trashListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            twohandButton.setTextColor(getResources().getColor(R.color.grey));
            trashrecycleButton.setTextColor(getResources().getColor(R.color.green));
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this,WasteActivity.class);
            startActivity(intent);
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
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
