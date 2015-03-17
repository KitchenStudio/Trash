package com.example.xiner.trash.util;

import android.content.Context;
import android.util.Log;

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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by peng on 15-3-13.
 */
public class NetUtil {

    private static final String LOGIN_URL = "http://211.87.226.173/Green/user/logcheck";
    private static final String REGISTER_URL = "http://211.87.226.173/Green/user/reg";
    private static final String SECONDHAND_REALEASE_URL = "http://211.87.226.173/Green/items/output";
    private HttpPost httpRequest;
    private HttpResponse httpResponse;
    private Context context;

    public NetUtil(Context context) {
        this.context = context;
    }
//    public JSONArray GetResultFromNet(List<NameValuePair> params){
//        httpRequest = new HttpPost()
//    }

    public int LoginReq(JSONObject jsonObject) {
        //0表示用户 1表示商户 2表示无该用户 3表示密码错误
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<?> keys = jsonObject.keys();
        int status = -1;
        while (keys.hasNext()) {
            String key = keys.next().toString();
            try {
                params.add(new BasicNameValuePair(key, jsonObject.getString(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            httpRequest = new HttpPost(LOGIN_URL);
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            String jsonData = "";
            int result = httpResponse.getStatusLine().getStatusCode();
            if (result == 200) {
                InputStream is = httpResponse.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) {
                    jsonData += line + "\r\n";
                }
                jsonData = jsonData.trim();
                Log.i("登录测试", "登录数据：" + jsonData);
                status = Integer.parseInt(String.valueOf(jsonData.charAt(1)));
            } else {
                Log.d("netTest", "不成功");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
    }

    public int registerReq(JSONObject object) {
        int status = -1;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<?> keys = object.keys();

        while (keys.hasNext()) {
            String key = keys.next().toString();
            try {
                params.add(new BasicNameValuePair(key, object.getString(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        httpRequest = new HttpPost(REGISTER_URL);

        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            InputStream in = httpResponse.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String s = "";
            while ((line = br.readLine()) != null) {
                s += line;
            }
            s = s.trim();
           // status = Integer.parseInt(String.valueOf(s.charAt(0)));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void secondhandRealeaseReq(JSONObject jsonObject) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<?> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next().toString();
            try {
                params.add(new BasicNameValuePair(key, jsonObject.getString(key)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            httpRequest = new HttpPost(SECONDHAND_REALEASE_URL);
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            String jsonData = "";
            int result = httpResponse.getStatusLine().getStatusCode();
            if (result == 200) {
                InputStream is = httpResponse.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) {
                    jsonData += line + "\r\n";
                }
                jsonData = jsonData.trim();
                Log.i("二手商品发布测试", "测试数据：" + jsonData);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}