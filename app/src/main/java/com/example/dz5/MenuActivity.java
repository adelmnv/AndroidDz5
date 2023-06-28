package com.example.dz5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView tvHeading;
    private  TextView tvFirst;
    private TextView tvSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
        getRes();
    }

    private void initialize(){
        tvHeading = findViewById(R.id.tvHeading);
        tvFirst = findViewById(R.id.tvFirst);
        tvSecond = findViewById(R.id.tvSecond);
    }

    private void getRes(){
        Intent intent = getIntent();
        tvHeading.setText(intent.getStringExtra(MainActivity.INFO_MESSAGE));
        tvFirst.setText(intent.getStringExtra(MainActivity.DETAIL1_MESSAGE));
        tvSecond.setText(intent.getStringExtra(MainActivity.DETAIL2_MESSAGE));
    }
}