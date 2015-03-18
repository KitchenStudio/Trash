package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;

public class MyInformationActivity extends ActionBarActivity {

    TextView editinfo, changepassword;
    TextView nickname, sex, phonecall, qqnumber;
    ImageView headImage;
    Main app;
    String filepath = Environment.getExternalStorageDirectory() + "/trash/head/head.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        app = Main.getInstance();
        init();
    }


    private void init() {
        InfoListener infoListener = new InfoListener();
        editinfo = (TextView) findViewById(R.id.editinformation);
        editinfo.setOnClickListener(infoListener);
        changepassword = (TextView) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(infoListener);
        headImage = (ImageView) findViewById(R.id.person_iv_head);
        nickname = (TextView) findViewById(R.id.information_nickname);
        sex = (TextView) findViewById(R.id.sex_text);
        phonecall = (TextView) findViewById(R.id.phonecall_text);
        qqnumber = (TextView) findViewById(R.id.qqnumber_text);
        nickname.setText(app.getDataStore().getString("nickname","未设置昵称"));
        sex.setText(app.getDataStore().getString("sex","未设置性别"));
        phonecall.setText(app.getDataStore().getString("phonecall","未设置电话号码"));
        qqnumber.setText(app.getDataStore().getString("qqnumber","未设置qq号码"));
        if (app.getDataStore().getBoolean("ifhead", false)) {
            Bitmap bitmap = app.getLoacalBitmap(filepath);
            headImage.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        nickname.setText(app.getDataStore().getString("nickname","未设置昵称"));
        sex.setText(app.getDataStore().getString("sex","未设置性别"));
        phonecall.setText(app.getDataStore().getString("phonecall","未设置电话号码"));
        qqnumber.setText(app.getDataStore().getString("qqnumber","未设置qq号码"));
        if (app.getDataStore().getBoolean("ifhead", false)) {
            Bitmap bitmap = app.getLoacalBitmap(filepath);
            headImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    class InfoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.editinformation:
                    intent = new Intent();
                    intent.setClass(MyInformationActivity.this, EditInfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.changepassword:
                    intent = new Intent();
                    intent.setClass(MyInformationActivity.this, EditInfoActivity.class);
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


        return super.onOptionsItemSelected(item);
    }
}
