package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.xiner.trash.R;
import com.example.xiner.trash.util.NetUtil;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RegisterActivity extends ActionBarActivity {

    private EditText userNameEt;
    private EditText passwordEt;
    private Button registerBtn;
    private Spinner userTypeSp;
    //private final String REGISTER_URL = "http://192.168.1.128/Green/user/reg";
    private final String REGISTER_URL = "http://211.87.226.182/Green/user/reg";
    private HttpPost httpRequest;
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
                        JSONObject json = new JSONObject();
                        net = new NetUtil(RegisterActivity.this);
                        try {
                            String type = userTypeSp.getSelectedItem().toString();
                            json.put("u.name", userNameEt.getText().toString());
                            //json.put("u.age", "123");
                            json.put("u.password", passwordEt.getText().toString());
                            json.put("u.phone", "15153162253");
                            if (type.equals("普通用户")) {
                                json.put("u.tag", "0");
                            } else {
                                json.put("u.tag", "1");
                            }
                            net.registerReq(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
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
