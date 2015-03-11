package com.example.xiner.trash.acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.xiner.trash.R;

public class EditGoodActivity extends ActionBarActivity {

    ImageView editgood,deletegood,contactgood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_good);
        editgood=(ImageView)findViewById(R.id.edit_good);
        editgood.setOnClickListener(new editListener());
        deletegood=(ImageView)findViewById(R.id.delete_good);
        contactgood=(ImageView)findViewById(R.id.contact_good);
    }


   class editListener implements View.OnClickListener{

       @Override
       public void onClick(View v) {
           Intent intent = new Intent();
           intent.setClass(EditGoodActivity.this,PubliccommodityActivity.class);
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



        return super.onOptionsItemSelected(item);
    }
}
