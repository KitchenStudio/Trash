package com.example.xiner.trash.acitivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;

import java.io.File;


public class PubliccommodityActivity extends ActionBarActivity {

    ImageView goodpic;
    private Bitmap goodbitmap;
    private final static String path = Environment.getExternalStorageDirectory() + "/trash/good";// sd路径
    Main app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publiccommodity);
        app =Main.getInstance();

    }


    public void init(){
        PublicListener publicListener = new PublicListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        goodpic =(ImageView)findViewById(R.id.goodpic);
        goodpic.setOnClickListener(publicListener);
    }

    class  PublicListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.goodpic:
                    new android.app.AlertDialog.Builder(PubliccommodityActivity.this)
                            .setTitle("头像选择")
                            .setNegativeButton("相册选取",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            dialog.cancel();
                                            Intent intent1 = new Intent(
                                                    Intent.ACTION_PICK, null);
                                            intent1.setDataAndType(
                                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                    "image/*");
                                            startActivityForResult(intent1, 3);
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
                                                intent2.putExtra(
                                                        MediaStore.EXTRA_OUTPUT,
                                                        Uri.fromFile(new File(
                                                                Environment.getExternalStorageDirectory(),
                                                                "good.jpg")));
                                                startActivityForResult(intent2, 3);// 采用ForResult打开
                                            }
                                        }
                                    }).show();
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
//                    cropPhoto(data.getData());// 裁剪图片
//                    startActivityForResult(intent, 3);
                }

                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
//                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    goodbitmap = extras.getParcelable("data");
                    if (goodbitmap != null) {

                        app.setPicToView(goodbitmap, path);// 保存在SD卡中
                        goodpic.setImageBitmap(goodbitmap);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

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
        if (id==android.R.id.home){
            finish();
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
