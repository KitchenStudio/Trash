package com.example.xiner.trash.acitivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;
import com.example.xiner.trash.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EditInfoActivity extends ActionBarActivity {
    private static final String TAG = "EditInfoActivity";
    NetUtil netUtil;
    Main app;
    ImageView head;
    Bitmap headbitmap;
    ProgressDialog prograss;
    Button saveButton;
    EditText nickname, phonecall, qqnumber;
    private final static String path = Environment.getExternalStorageDirectory() + "/trash/head";// sd路径
    NetUtil net = new NetUtil(EditInfoActivity.this);
    String filepath = Environment.getExternalStorageDirectory() + "/trash/head/head.jpg";
    RadioGroup group;
    RadioButton man, woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        app = Main.getInstance();
        init();

    }


    private void init() {
        EditListener editListener = new EditListener();
        head = (ImageView) findViewById(R.id.person_edit_head);
        head.setOnClickListener(editListener);
        nickname = (EditText) findViewById(R.id.nickname_edit);
        phonecall = (EditText) findViewById(R.id.telephone_edit);
        qqnumber = (EditText) findViewById(R.id.qqnumber_edit);
        saveButton = (Button) findViewById(R.id.save_edit);
        saveButton.setOnClickListener(editListener);
        group = (RadioGroup) findViewById(R.id.group);
        group.setOnCheckedChangeListener(new SexListener());
        man = (RadioButton) findViewById(R.id.man_radio);
        woman = (RadioButton) findViewById(R.id.woman_radio);
        nickname.setText(app.getDataStore().getString("nickname", "未设置昵称"));
        if (app.getDataStore().getString("sex","男").equals("男")){
            man.setChecked(true);
        }else{
            woman.setChecked(true);
        }

        phonecall.setText(app.getDataStore().getString("phonecall", "未设置电话号码"));
        qqnumber.setText(app.getDataStore().getString("qqnumber", "未设置qq号码"));
        if (app.getDataStore().getBoolean("ifhead", false)) {
            Bitmap bitmap = app.getLoacalBitmap(filepath);
            head.setImageBitmap(bitmap);
        }

    }

    class SexListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.woman_radio) {
                app.getDataStore().edit().putString("sex", "女").commit();
            } else {
                app.getDataStore().edit().putString("sex", "男").commit();
            }
        }
    }

    class EditListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.person_edit_head:
                    new android.app.AlertDialog.Builder(EditInfoActivity.this)
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
                                            startActivityForResult(intent1, 1);
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
                                                                "head.jpg")));
                                                startActivityForResult(intent2, 2);// 采用ForResult打开
                                            }
                                        }
                                    }).show();
                    break;


                case R.id.save_edit:
                    app.getDataStore().edit().putString("nickname", nickname.getText().toString()).commit();
                    app.getDataStore().edit().putString("phonecall", phonecall.getText().toString()).commit();
                    app.getDataStore().edit().putString("qqnumber", qqnumber.getText().toString()).commit();
                    if (app.getDataStore().getBoolean("ifheadstore", false)) {
                        app.getDataStore().edit().putBoolean("ifhead", true).commit();
                    }
//                    prograss  = new ProgressDialog();
//                    prograss.
                    new uploadHeadThread().start();

//                    new uploadInfo().start();

                    break;
            }
        }
    }

    class uploadHeadThread extends Thread {
        @Override
        public void run() {
            Log.v(TAG, "upload");
            int code = net.uploadFile(path + "/head.jpg");
            if (code == 200) {
                finish();
            }
        }
    }

    class uploadInfo extends Thread {
        @Override
        public void run() {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("i.nickname", nickname.getText().toString());
                jsonObject.put("i.phonecall", phonecall.getText().toString());
                jsonObject.put("i.qqnumber", qqnumber.getText().toString());
                net.uploadInfo(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    headbitmap = extras.getParcelable("data");
                    if (headbitmap != null) {
                        app.setPicToView(headbitmap, path);// 保存在SD卡中
                        head.setImageBitmap(headbitmap);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
    }

    //保存头像
//    private void setPicToView(Bitmap mBitmap) {
//        Log.v(TAG,"保存头像");
//        String sdStatus = Environment.getExternalStorageState();
//        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//            return;
//        }
//        FileOutputStream b = null;
//        File file = new File(path);
//        file.mkdirs();// 创建文件夹
//        String fileName = path + "/head.jpg";// 图片名字
//        try {
//            b = new FileOutputStream(fileName);
//            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 关闭流
//                b.flush();
//                b.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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


        return super.onOptionsItemSelected(item);
    }
}
