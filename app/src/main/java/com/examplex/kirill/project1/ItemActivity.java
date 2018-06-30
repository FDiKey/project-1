package com.examplex.kirill.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv;
    private String temp;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent i = getIntent();
        temp = i.getStringExtra("nickname");
        tv = (TextView) findViewById(R.id.itemText);
        tv.setText(temp);

        btn = (Button) findViewById(R.id.item_back);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.item_back:{
                this.finish();
                break;
            }
        }
    }
}
