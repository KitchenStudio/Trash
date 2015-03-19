package com.example.xiner.trash.acitivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiner.trash.R;

public class MyAddressActivity extends ActionBarActivity {

    TextView addresstext,streettext,nametext,phonecalltext;
    EditText addressedit,streetedit,nameedit,phonecalledit;
    LinearLayout buttonLinear;
    Button cancel,ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        init();
    }


    public void init(){
        TextListener textListener = new TextListener();
        addresstext =(TextView)findViewById(R.id.addresstext);
        addresstext.setOnClickListener(textListener);
        addressedit=(EditText)findViewById(R.id.addressedit);
        addressedit.setOnClickListener(textListener);
        streettext =(TextView)findViewById(R.id.streettext);
        streettext.setOnClickListener(textListener);
        streetedit=(EditText)findViewById(R.id.streetedit);
        streetedit.setOnClickListener(textListener);
        nametext=(TextView)findViewById(R.id.nametext);
        nametext.setOnClickListener(textListener);
        nameedit=(EditText)findViewById(R.id.nameedit);
        nameedit.setOnClickListener(textListener);
        phonecalltext=(TextView)findViewById(R.id.phonetext);
        phonecalltext.setOnClickListener(textListener);
        phonecalledit=(EditText)findViewById(R.id.phoneedit);
        phonecalledit.setOnClickListener(textListener);
        buttonLinear =(LinearLayout)findViewById(R.id.buttonlinear);
        buttonLinear.setOnClickListener(textListener);
        cancel=(Button)findViewById(R.id.canclebutton);
        cancel.setOnClickListener(textListener);
        ok=(Button)findViewById(R.id.okbutton);
        ok.setOnClickListener(textListener);

    }

    class TextListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addresstext:
                    addresstext.setVisibility(View.GONE);
                    addressedit.setVisibility(View.VISIBLE);

                    break;
                case R.id.addressedit:
                    break;
                case R.id.streettext:
                    streettext.setVisibility(View.GONE);
                    streetedit.setVisibility(View.VISIBLE);
                    break;
                case R.id.streetedit:
                    break;
                case R.id.nametext:
                    nametext.setVisibility(View.GONE);
                    nameedit.setVisibility(View.VISIBLE);
                    break;
                case R.id.nameedit:
                    break;
                case R.id.phonetext:
                    phonecalltext.setVisibility(View.GONE);
                    phonecalledit.setVisibility(View.VISIBLE);

                    break;
                case R.id.phoneedit:
                    break;
                case R.id.canclebutton:
                    finish();
                    break;
                case  R.id.okbutton:

                    addressedit.setVisibility(View.GONE);
                    addresstext.setVisibility(View.VISIBLE);
                    addresstext.setText(addressedit.getText());
                    streetedit.setVisibility(View.GONE);
                    streettext.setVisibility(View.VISIBLE);
                    streettext.setText(streetedit.getText());
                    nameedit.setVisibility(View.GONE);
                    nametext.setVisibility(View.VISIBLE);
                    nametext.setText(nameedit.getText());
                    phonecalledit.setVisibility(View.GONE);
                    phonecalltext.setVisibility(View.VISIBLE);
                    phonecalltext.setText(phonecalledit.getText());
                    break;

            }
            buttonLinear.setVisibility(View.VISIBLE);

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
        if (id==android.R.id.home){
            finish();
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
