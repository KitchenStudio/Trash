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

public class PublishWasteActivity extends ActionBarActivity {

    private EditText inameEt;
    private EditText descEt;
    private EditText unameEt;
    private EditText phoneEt;
    private EditText addressEt;
    private Button releaseBtn;
    private Spinner typeSp;
    ImageView picImage;
    GalleryAdapter adapter;
    ImageLoader imageLoader;
    ArrayList<CustomGallery> dataT;
    private Bitmap goodbitmap;
    GridView gridView;
    Main app;
    private final static String path = Environment.getExternalStorageDirectory() + "/trash/trash";// sd路径
    private String TAG = "Publc";
    String filename = "/trash";
    int filei;
    Dialog loadingDialog;
    NetUtil net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_waste);
        net = NetUtil.getInstance();
        initImageLoader();
        init();
    }
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
    private void init() {
        app = Main.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.whitearrow);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        inameEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        descEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        unameEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        phoneEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        addressEt = (EditText) findViewById(R.id.publish_waste_et_iname);
        releaseBtn = (Button) findViewById(R.id.publish_waste_btn_release);
        typeSp = (Spinner) findViewById(R.id.publish_waste_sp_type);
        picImage=(ImageView)findViewById(R.id.publictrash);
        ClickListener clickListener = new ClickListener();
        picImage.setOnClickListener(clickListener);
        releaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = LoadingDialog.createDialog(PublishWasteActivity.this, "正在上传，请稍后....");
                loadingDialog.show();
//                new wasteThread().start();
                new uploadpicThread().start();
            }
        });
        gridView = (GridView) findViewById(R.id.gridGallery);
        gridView.setFastScrollEnabled(true);
        adapter = new GalleryAdapter(getApplicationContext(), imageLoader);
        adapter.setActionMultiplePick(false);
        gridView.setAdapter(adapter);

    }


    private void picGet(final int selectcase,final int tookcase ){
        new android.app.AlertDialog.Builder(this)
                .setTitle("头像选择")
                .setNegativeButton("相册选取",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                                Intent intent1 = new Intent(
                                        Action.ACTION_MULTIPLE_PICK);

                                startActivityForResult(intent1, selectcase);
                            }
                        })
                .setPositiveButton("相机拍照",
                        new DialogInterface.OnClickListener() {
//                            final int a = diffcase;

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
    class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.publictrash:
                    picGet(2,1);
                    break;
            }
        }
    }

    class uploadpicThread extends Thread{
        @Override
        public void run() {
            uploadInfo();
            for (int i = 0; i < adapter.data.size(); i++) {
                int code = net.uploadFile(adapter.data.get(i).sdcardPath);
                if (code != 200) {
                    return;
                }

            }
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    loadingDialog.dismiss();
                    Toast.makeText(PublishWasteActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        picImage.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {

                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用

                        return;
                    }

                    if (data != null) {
                        Bundle extras = data.getExtras();
                        goodbitmap = extras.getParcelable("data");
                        if (goodbitmap != null) {
                            app.setPicToView(goodbitmap, path, filename+filei+".jpg");// 保存在SD卡中
                            dataT = new ArrayList<CustomGallery>();
                            CustomGallery customGallery = new CustomGallery();
                            customGallery.sdcardPath = path + filename+filei+".jpg";

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

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

//    private class wasteThread extends Thread {
    private void uploadInfo(){
         NetUtil net;
         JSONObject object;
         String iname, uname, desc, phone, place, catagory, time;
        //private double longitude,parallel;扩展使用的经纬度


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
                time = format.format(date).toString();
                object.put("g.time", time);
                net.wasteRealeaseReq(object);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

}