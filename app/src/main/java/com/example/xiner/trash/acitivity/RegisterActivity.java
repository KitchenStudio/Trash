package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xiner.trash.R;
import com.example.xiner.trash.util.NetUtil;


import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends ActionBarActivity {

    private EditText userNameEt;
    private EditText passwordEt;
    private Button registerBtn;
    private Spinner userTypeSp;
    private NetUtil net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        userNameEt = (EditText) findViewById(R.id.register_et_username);
        passwordEt = (EditText) findViewById(R.id.register_et_password);
        registerBtn = (Button) findViewById(R.id.register_button);
        userTypeSp = (Spinner) findViewById(R.id.register_sp_usertype);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtn.setText("hello");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int status;
                        JSONObject json = new JSONObject();
                        net =NetUtil.getInstance();
                        try {
                            String type = userTypeSp.getSelectedItem().toString();
                            Log.d("type Test", type);
                            json.put("u.name", userNameEt.getText().toString());
                            //json.put("u.age", "123");
                            json.put("u.password", passwordEt.getText().toString());
                            json.put("u.phone", "15153162253");
                            if (type.equals("普通用户")) {
                                json.put("u.tag", "0");
                            } else {
                                json.put("u.tag", "1");
                            }
                            status = net.registerReq(json);
                            Message msg = new Message();
                            msg.what = status;
                            handler.sendMessage(msg);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(RegisterActivity.this, "对不起该用户已注册，请登陆", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(RegisterActivity.this, "恭喜您注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
            }
        }

    };

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
