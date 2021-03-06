package com.example.xiner.trash.acitivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;
import com.example.xiner.trash.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {
    private Main app;
    private Button loginButton;
    private EditText nameEt;
    private EditText passwordET;
    private TextView registerTv;
    private ProgressDialog progressDialog;
    private Intent intent;

    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = Main.getInstance();
        init();
    }

    private void init() {
        loginListener listener = new loginListener();
        loginButton = (Button) findViewById(R.id.loginbutton);
        nameEt = (EditText) findViewById(R.id.login_et_name);
        passwordET = (EditText) findViewById(R.id.login_et_password);
        registerTv = (TextView) findViewById(R.id.login_tv_register);
        loginButton.setOnClickListener(listener);

        registerTv.setOnClickListener(listener);
    }

    private void showMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isConnected() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    private class loginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginbutton:

//                    String name = nameEt.getText().toString();
//                    String passwd = passwordET.getText().toString();
//                    if (name == "" || passwd == "") {
//                        showMessage("姓名或密码不能为空！");
//                    } else if (isConnected()) {
//                        app.clear();
//                        progressDialog = ProgressDialog.show(LoginActivity.this,
//                                "", "登录中……");
//                        new LoginThread(name, passwd).start();
//                    } else {
//                        showMessage("网络无连接，请重试！");
//                    }
//                    intent = new Intent();
//                    intent.setClass(LoginActivity.this, PersonActivity.class);
//                    startActivity(intent);

                    name = nameEt.getText().toString();
                    pwd = passwordET.getText().toString();
                    if (name.equals("") || pwd.equals("")) {
                        showMessage("姓名或密码不能为空！");
                    } else if (isConnected()) {
                        app.clear();
//                        progressDialog = ProgressDialog.show(LoginActivity.this,
//                                "", "登录中……");
                        new LoginThread().start();
                    } else {
                        showMessage("网络无连接，请重试！");
                    }

                    break;
                case R.id.login_tv_register:
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //0表示用户 1表示商户 2表示无该用户 3表示密码错误
                case 0://用户成功登陆
                    //app.clear();
                    app.getDataStore().edit().putBoolean("login", true)
                            .commit();
                    app.getDataStore().edit().putString("stuid", name).commit();
                    app.getDataStore().edit().putString("password", pwd).commit();
                    app.setLogin(true);
                    //progressDialog.dismiss();
                    intent = new Intent();
                    intent.setClass(LoginActivity.this, AllCommoditiesActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    break;
                case 1://商户成功登陆
                    break;
                case 2:
                    showMessage("对不起，没有该用户！");
                    break;
                case 3:
                    showMessage("对不起，密码不正确，请重新输入！");
                    break;
            }
        }
    };

    private class LoginThread extends Thread {
        private NetUtil net;
        private JSONObject loginData;
        private int status; // 登录状态

        @Override
        public void run() {
            NetUtil net = NetUtil.getInstance();
            loginData = new JSONObject();
            try {
                loginData.put("u.name", name);
                loginData.put("u.password", pwd);
                status = net.LoginReq(loginData);
                Message msg = new Message();
                msg.what = status;
                handler.sendMessage(msg);
                Log.d("StatusTest", status + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
        //int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}

