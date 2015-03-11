package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiner.trash.R;


public class PersonActivity extends ActionBarActivity {

    LinearLayout settingLinearLayout,addressLinear,collectionLinear,myreleaseLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.person_customview);
        settingLinearLayout =(LinearLayout)findViewById(R.id.setting_linear);
        settingLinearLayout.setOnClickListener(new linearListener());
        addressLinear =(LinearLayout)findViewById(R.id.manageaddid);
        addressLinear.setOnClickListener(new AddressListener());
        collectionLinear=(LinearLayout)findViewById(R.id.collection_linear);
        collectionLinear.setOnClickListener(new collectionLinear());
        myreleaseLinear=(LinearLayout)findViewById(R.id.myrelease_linear);
        myreleaseLinear.setOnClickListener(new myreleaseListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    class myreleaseListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(PersonActivity.this,FragmentTabsPager.class);
            startActivity(intent);
        }
    }

    class collectionLinear implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(PersonActivity.this,MyCollectionActivity.class);
            startActivity(intent);
        }
    }
    class linearListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(PersonActivity.this,SettingActivity.class);
            startActivity(intent);
        }
    }

    class AddressListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(PersonActivity.this,MyAddressActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
