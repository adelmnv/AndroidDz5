package com.example.dz5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout llPizza1;
    private LinearLayout llPizza2;
    private LinearLayout llPizza3;
    private String selectedPizza = "";
    public static String KEY_MESSAGE = "pizza";
    public static String INFO_MESSAGE = "info";
    public static String DETAIL1_MESSAGE = "detail1";
    public static String DETAIL2_MESSAGE = "detail2";

    public static List<String> ADDRESS = Arrays.asList("г.Алматы Шевченко 132", "г.Алматы Абая 68");
    public static List<String> PHONE = Arrays.asList("+77781361188", "+77781341188");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setOnClick();
    }

    private void initialize(){
        llPizza1 = findViewById(R.id.llPizza1);
        llPizza2 = findViewById(R.id.llPizza2);
        llPizza3 = findViewById(R.id.llPizza3);
    }

    private void setOnClick(){
        llPizza1.setOnClickListener(this);
        llPizza2.setOnClickListener(this);
        llPizza3.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        LinearLayout ll = (LinearLayout) view;
        TextView pizzaName = (TextView) ll.getChildAt(0);
        selectedPizza = pizzaName.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(KEY_MESSAGE,selectedPizza);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.address) {
            createIntent(ADDRESS, item.getTitle().toString());
            return true;
        } else if (itemId == R.id.phone) {
            createIntent(PHONE, item.getTitle().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createIntent(List<String> l, String n){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(INFO_MESSAGE,n);
        intent.putExtra(DETAIL1_MESSAGE,l.get(0));
        intent.putExtra(DETAIL2_MESSAGE,l.get(1));
        startActivity(intent);
    }
}