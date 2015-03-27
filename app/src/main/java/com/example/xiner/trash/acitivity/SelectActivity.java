package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.xiner.trash.R;

public class SelectActivity extends ActionBarActivity {
    Button twohandButton;
    Button trashrecycleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.select_customview);
        twohandButton =(Button)findViewById(R.id.twohandbutton);
        twohandButton.setOnClickListener(new twoHandListener());
        trashrecycleButton=(Button)findViewById(R.id.trash_recycle);
        trashrecycleButton.setOnClickListener(new trashListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        trashrecycleButton.setTextColor(getResources().getColor(R.color.grey));
        twohandButton.setTextColor(getResources().getColor(R.color.grey));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    class twoHandListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            trashrecycleButton.setTextColor(getResources().getColor(R.color.grey));
            twohandButton.setTextColor(getResources().getColor(R.color.green));
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this,AllCommoditiesActivity.class);
            startActivity(intent);

        }
    }
    class trashListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            twohandButton.setTextColor(getResources().getColor(R.color.grey));
            trashrecycleButton.setTextColor(getResources().getColor(R.color.green));
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this,WasteActivity.class);
            startActivity(intent);
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

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
