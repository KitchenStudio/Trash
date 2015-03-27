package com.example.xiner.trash.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.xiner.trash.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by peng on 15-3-13.
 */
public class NetUtil {
    private static final String TAG = "NetUtil";
    private static final String HEAD = "http://211.87.226.219/Green/";
    private static final String LOGIN_URL = HEAD + "user/logcheck";
    private static final String REGISTER_URL = HEAD + "user/reg";
    private static final String SECONDHAND_REALEASE_URL = HEAD + "items/output";
    private static final String WASTE_REALEASE_URL = HEAD + "garbage/put";
    private static final String UPLOADPICTURE_URL = HEAD + "";
    private static final String GET_COMMODITY_URL = HEAD + "";
    private static final String GET_WASTE_URL = HEAD + "";

    private static NetUtil netUtil =null;
    private HttpPost httpRequest;
    private HttpResponse httpResponse;
    int serverResponseCode = 0;

    private NetUtil() {

    }

    public synchronized static NetUtil getInstance(){
        if(netUtil == null){
            netUtil = new NetUtil();
        }
        return netUtil;
    }


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
                Log.d(TAG, "登陆返回数据：" + jsonData);
                status = Integer.parseInt(String.valueOf(jsonData.charAt(1)));
            } else {
                Log.d(TAG, "网络似乎有问题。。。。");
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
            status = Integer.parseInt(String.valueOf(s.charAt(1)));
            Log.d(TAG, "注册测试，返回的结果为：" + status);

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
                Log.d(TAG, "二手商品发布测试，测试数据为：" + jsonData);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void wasteRealeaseReq(JSONObject jsonObject) {
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
            httpRequest = new HttpPost(WASTE_REALEASE_URL);
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
                Log.i(TAG, "废品发布测试，测试数据：" + jsonData);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void uploadInfo(JSONObject jsonObject) {
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
                Log.i("个人信息测试数据", "测试数据：" + jsonData);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public int uploadFile(String sourceFileUri) {


        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

            Log.e("uploadFile", "Source File not exist :");

            return 0;

        } else {
            try {

                // 打开URL连接
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(UPLOADPICTURE_URL);


                // 打开http连接
                conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true); // 允许输入
                conn.setDoOutput(true); // 允许输出
                conn.setUseCaches(false); //不使用缓存复制
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                conn.setRequestProperty("uploadfile", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploadfile\";filename=\""
                        + fileName + "\"" + lineEnd);


                dos.writeBytes(lineEnd);

                // 创建一个缓冲区
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // 读写文件.
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

//                // 上传文件之后上传字符串
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // 服务器的响应
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                //关闭流//
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {


                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

            }
            return serverResponseCode;

        }
    }

    public String commodityReq(int type) {
        String jsonData = "";
        switch (type) {
            case 0://请求五条的初始数据
                try {
                    httpRequest = new HttpPost(GET_COMMODITY_URL);
                    httpResponse = new DefaultHttpClient()
                            .execute(httpRequest);
                    int result = httpResponse.getStatusLine().getStatusCode();
                    if (result == 200) {
                        InputStream is = httpResponse.getEntity().getContent();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            jsonData += line + "\r\n";
                        }
                        jsonData = jsonData.trim();
                        Log.d(TAG, "Json测试,测试数据：" + jsonData);
                    } else {
                        Log.d(TAG, "获取二手商品数据失败，网络不通。。。。");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 1://请求所有的数据
                try {
                    httpRequest = new HttpPost(GET_COMMODITY_URL);
                    httpResponse = new DefaultHttpClient()
                            .execute(httpRequest);
                    int result = httpResponse.getStatusLine().getStatusCode();
                    if (result == 200) {
                        InputStream is = httpResponse.getEntity().getContent();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            jsonData += line + "\r\n";
                        }
                        jsonData = jsonData.trim();

                        Log.i("json", "测试数据：" + jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }


        return jsonData;
    }

    public String wasteReq(int type) {
        String jsonData = "";
        switch (type) {
            case 0://请求五条的初始数据
                try {
                    httpRequest = new HttpPost(GET_WASTE_URL);
                    httpResponse = new DefaultHttpClient()
                            .execute(httpRequest);
                    int result = httpResponse.getStatusLine().getStatusCode();
                    if (result == 200) {
                        InputStream is = httpResponse.getEntity().getContent();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            jsonData += line + "\r\n";
                        }
                        jsonData = jsonData.trim();
                        Log.i("json", "测试数据：" + jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 1://请求所有的数据
                try {
                    httpRequest = new HttpPost(GET_COMMODITY_URL);
                    httpResponse = new DefaultHttpClient()
                            .execute(httpRequest);
                    int result = httpResponse.getStatusLine().getStatusCode();
                    if (result == 200) {
                        InputStream is = httpResponse.getEntity().getContent();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            jsonData += line + "\r\n";
                        }
                        jsonData = jsonData.trim();

                        Log.i("json", "测试数据：" + jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
        return jsonData;
    }
}

//    public static List<String> getlistString(String key, String jsonString) {
//        List<String> list = new ArrayList<String>();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray(key);
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                String msg = jsonArray.getString(i);
//                list.add(msg);
//            }
//
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//
//        return list;
//    }
//
//    public static List<Map<String,Object>> getlistMap(String key, String jsonString){
//        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray(key);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                Map<String,Object> map=new HashMap<String, Object>();
//                Iterator<String> iterator=jsonObject2.keys();
//
//                while(iterator.hasNext()){
//                    String json_key=iterator.next();
//                    Object json_value=jsonObject2.get(json_key);
//                    if(json_value==null){
//                        json_value="";
//                    }
//                    map.put(json_key, json_value);
//                }
//                list.add(map);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }
//}