package com.example.xiner.trash.acitivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.xiner.trash.R;
import com.example.xiner.trash.model.Waste;

public class WasteDetailActivity extends ActionBarActivity {
    private Waste waste;
    private TextView name,time,subject ,detail,contact,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waste_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        waste = (Waste) getIntent().getExtras().get("waste");
        name=(TextView)findViewById(R.id.waste_name);
        time=(TextView)findViewById(R.id.waste_time);
        detail=(TextView)findViewById(R.id.waste_detail);
        contact=(TextView)findViewById(R.id.waste_contact);
        phone=(TextView)findViewById(R.id.waste_phone);
        name.setText(waste.getGname());
        time.setText(waste.getTime());
        detail.setText(waste.getDescription());
        contact.setText(waste.getUname());
        phone.setText("联系电话:"+waste.getGphone());


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
        }

        return super.onOptionsItemSelected(item);
    }
}
