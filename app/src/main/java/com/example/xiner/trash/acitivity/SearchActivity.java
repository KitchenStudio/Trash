package com.example.xiner.trash.acitivity;

import android.app.Dialog;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.adapter.AllCommoditiesAdapter;
import com.example.xiner.trash.model.Commodity;
import com.example.xiner.trash.util.JsonUtil;
import com.example.xiner.trash.util.LoadingDialog;
import com.example.xiner.trash.util.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends ActionBarActivity {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private Button searchButton;
    private EditText searchEdit;
    private AllCommoditiesAdapter allCommoditiesAdapter;
    private NetUtil net;
    private JsonUtil json;
    private ImageView searchBack;
   private mHandler handler = new mHandler();
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_search_customview);
        searchButton=(Button)findViewById(R.id.search_button);
        ClickListener clickListener = new ClickListener();
        searchButton.setOnClickListener(clickListener);
        searchEdit=(EditText)findViewById(R.id.seach_edittext);
        searchBack =(ImageView)findViewById(R.id.search_back_arrow);
        searchBack.setOnClickListener(clickListener);
        linearLayoutManager = new LinearLayoutManager(this );
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        allCommoditiesAdapter = new AllCommoditiesAdapter(this);
        recyclerView.setAdapter(allCommoditiesAdapter);
        net = NetUtil.getInstance();
        json = new JsonUtil();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ClickListener implements View.OnClickListener{
        private static final String TAG = "ClickListener";
        JSONObject searchObject;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.search_button:
                    dialog = LoadingDialog.createDialog(SearchActivity.this,"正在搜索。。。");
                    dialog.show();
                    new Thread(new Runnable() {
                    @Override
                    public void run() {
                        searchObject = new JSONObject();
                        try {
                            searchObject.put("i.iname", searchEdit.getText().toString());
                            ArrayList<Commodity> list = json.getCommodities(net.search(searchObject));
                            Log.v(TAG, list.size() + "listsizelistsize");
                            allCommoditiesAdapter.getCommodities().clear();
                            allCommoditiesAdapter.setCommodities(list);
                            Message msg = new Message();
                            msg.what = 11;
                            handler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                    ArrayList<Commodity> list = json.getCommodities(net.search());
//                    allCommoditiesAdapter.getCommodities().clear();
//                    allCommoditiesAdapter.setCommodities(list);
//                    Message msg = new Message();
//                    msg.what = 11;
//                    handler.sendMessage(msg);
                    }
                }).start();
                    break;
                case R.id.search_back_arrow:
                    finish();
                    break;
            }
        }
    }

    private class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 11:
                    allCommoditiesAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    break;
            }
        }
    }
}
