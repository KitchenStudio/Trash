package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.adapter.WasteAdapter;

public class WasteActivity extends ActionBarActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    WasteAdapter trashAdapter;
    ImageView publictrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_waste);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        trashAdapter = new WasteAdapter(this);
        mRecyclerView.setAdapter(trashAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.waste_customview);
        publictrash = (ImageView) findViewById(R.id.publicgoodallgood);
        ClickListener clickListener = new ClickListener();
        publictrash.setOnClickListener(clickListener);
    }


    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.publicgoodallgood:
                    Intent intent = new Intent();
                    intent.setClass(WasteActivity.this, PublishWasteActivity.class
                    );
                    startActivity(intent);
                    break;
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
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
