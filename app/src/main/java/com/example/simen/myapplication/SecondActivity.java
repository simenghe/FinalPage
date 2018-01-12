package com.example.simen.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent().hasExtra("BigCane")){
            TextView tv=(TextView)findViewById(R.id.txtStats);
            tv.setText(getIntent().getExtras().getString("BigCane"));
        }
    }
}
