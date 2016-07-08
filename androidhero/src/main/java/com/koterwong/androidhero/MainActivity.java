package com.koterwong.androidhero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){

        }
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,new MainFragment(),this.toString())
                .commit();
    }
}
