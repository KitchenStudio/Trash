package com.example.xiner.trash.acitivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xiner.trash.R;
import com.example.xiner.trash.adapter.GalleryAdapter;
import com.example.xiner.trash.main.Main;
import com.example.xiner.trash.model.CustomGallery;
import com.example.xiner.trash.util.Action;
import com.example.xiner.trash.util.LoadingDialog;
import com.example.xiner.trash.util.NetUtil;
import com.example.xiner.trash.util.ProgressListener;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PublishCommodityActivity extends ActionBarActivity {
    GalleryAdapter adapter;
    ArrayList<CustomGallery> dataT;

    ImageLoader imageLoader;
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
    private ImageView goodimage;
    private Bitmap goodbitmap;
    int filei;
    GridView gridView;
    Dialog loadingDialog;
    NetUtil net;
    Main app;
    private final static String path = Environment.getExternalStorageDirectory() + "/trash/good";// sd路径
    private String TAG = "Publc";
    String filename = "/good";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_commodity);
        net = NetUtil.getInstance();
        app = Main.getInstance();
        initImageLoader();
        init();

    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.whitearrow);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        goodimage = (ImageView) findViewById(R.id.addgoodpic);
        goodimage.setOnClickListener(new picListener());
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
                loadingDialog = LoadingDialog.createDialog(PublishCommodityActivity.this, "正在上传，请稍后....");
                loadingDialog.show();

                new picUploadThread().start();
            }
        });

        gridView = (GridView) findViewById(R.id.gridGallery);
        gridView.setFastScrollEnabled(true);
        adapter = new GalleryAdapter(getApplicationContext(), imageLoader);
        adapter.setActionMultiplePick(false);
        gridView.setAdapter(adapter);
    }

    /*
    * 初始化加载图画类库
    * */
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions).memoryCache(
                new WeakMemoryCache());

        ImageLoaderConfiguration config = builder.build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    loadingDialog.dismiss();
                    Toast.makeText(PublishCommodityActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };


    private class picUploadThread extends Thread {

        @Override
        public void run() {
            uploadInfo();
//            for (int i = 0; i < adapter.data.size(); i++) {
//
//                int code = net.uploadFile(adapter.data.get(i).sdcardPath);
//                if (code != 200) {
//                    return;
//                }
//
//            }
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);


        }
    }

    //    private class secondHandThread extends Thread {
    private void uploadInfo() {
        NetUtil net = null;
        JSONObject jsonObject = null;
        String iname, price, desc, uname, phone, qq, address, sort, recency;

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
            net = NetUtil.getInstance();
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
            jsonObject.put("i.time", time);
            //jsonObject.put("i.time",new Date(udate.getTime()));
            Log.d("DateTest", time);

            net.secondhandRealeaseReq(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        net.secondhandRealeaseReq(jsonObject);

    }

    class picListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            picGet(2, 1);
        }
    }

    private void picGet(final int selectcase, final int tookcase) {
        new android.app.AlertDialog.Builder(this)
                .setTitle("头像选择")
                .setNegativeButton("相册选取",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                Intent i = new Intent(
                                        Action.ACTION_MULTIPLE_PICK);
                                startActivityForResult(i, selectcase);
                            }
                        })
                .setPositiveButton("相机拍照",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                String status = Environment
                                        .getExternalStorageState();
                                if (status
                                        .equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
                                    Intent intent2 = new Intent(
                                            MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent2, tookcase);// 采用ForResult打开
                                }
                            }
                        }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        goodimage.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {

                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用

                        return;
                    }
                    Log.v(TAG, data + "good");
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        goodbitmap = extras.getParcelable("data");
                        if (goodbitmap != null) {
                            app.setPicToView(goodbitmap, path, filename + filei + ".jpg");// 保存在SD卡中
                            dataT = new ArrayList<CustomGallery>();
                            CustomGallery customGallery = new CustomGallery();
                            customGallery.sdcardPath = path + filename + filei + ".jpg";
                            Log.v(TAG, customGallery.sdcardPath + "sdPath");
                            dataT.add(customGallery);
                            adapter.addAll(dataT);
                            filei++;
                        }
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {

                    String[] all_path = data.getStringArrayExtra("all_path");

                    dataT = new ArrayList<CustomGallery>();

                    for (String string : all_path) {
                        CustomGallery item = new CustomGallery();
                        item.sdcardPath = string;
                        dataT.add(item);
                    }
                    adapter.addAll(dataT);

                }
                break;
            default:
                break;

        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapter.data.size()) {
                    picGet(2, 1);
                }
            }
        });
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
