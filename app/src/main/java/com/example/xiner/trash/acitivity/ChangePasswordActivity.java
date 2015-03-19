package com.example.xiner.trash.acitivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiner.trash.R;
import com.example.xiner.trash.main.Main;

public class ChangePasswordActivity extends ActionBarActivity {

    EditText usedpassword,newpassword,newpasswordagain;
    Button saveButton;
    Main app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        app = Main.getInstance();
        init();
    }



    private void init(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        saveListener savelistener = new saveListener();
        usedpassword =(EditText)findViewById(R.id.usedpassword);
        newpassword=(EditText)findViewById(R.id.newpassword);
        newpasswordagain=(EditText)findViewById(R.id.newpasswordagain);
        saveButton=(Button)findViewById(R.id.savepassword);
        saveButton.setOnClickListener(savelistener);

    }

    class saveListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String userpass = app.getDataStore().getString("password","");
            if (!usedpassword.getText().toString().equals(userpass)){
                Toast.makeText(ChangePasswordActivity.this,"原密码不正确",Toast.LENGTH_SHORT).show();
            }else if (!newpassword.getText().toString().equals(newpasswordagain.getText().toString())){
                Toast.makeText(ChangePasswordActivity.this,"两次输入的密码不一样",Toast.LENGTH_SHORT).show();
            }else if (newpassword.getText().toString().equals("")||newpasswordagain.getText().toString().equals("")||usedpassword.getText().toString().equals("")){
                Toast.makeText(ChangePasswordActivity.this,"请将信息填写完整",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ChangePasswordActivity.this,"可以",Toast.LENGTH_SHORT).show();
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
        if (android.R.id.home == item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
