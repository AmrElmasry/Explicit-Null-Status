package com.amrelmasry.processingplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amrelmasry.processly.annotations.IgnoreFields;


@IgnoreFields
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
