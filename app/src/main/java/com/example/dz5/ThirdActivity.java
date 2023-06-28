package com.example.dz5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvSize;
    private TextView tvAdditions;
    private TextView tvSum;
    private EditText etClientName;
    private EditText etClientPhone;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initialize();
        getFullIntent();
        btnOrder.setOnClickListener(this::placeOrder);
    }

    private void placeOrder(View view) {
        String order = "Заказ на имя: "+etClientName.getText().toString()+" создан. Ожидайте звонок";
        Toast.makeText(this,order,Toast.LENGTH_LONG).show();
    }

    private void initialize(){
        tvName = findViewById(R.id.tvName);
        tvSize = findViewById(R.id.tvSize);
        tvAdditions = findViewById(R.id.tvAdditions);
        tvSum = findViewById(R.id.tvSum);
        etClientName = findViewById(R.id.etClientName);
        etClientPhone = findViewById(R.id.etClientPhone);
        btnOrder = findViewById(R.id.btnOrder);
    }

    private void getFullIntent(){
        Intent intent = getIntent();
        tvName.setText(tvName.getText()+ intent.getStringExtra(SecondActivity.PIZZA_NAME));
        tvSize.setText(tvSize.getText()+intent.getStringExtra(SecondActivity.PIZZA_SIZE));
        tvSum.setText(tvSum.getText()+intent.getStringExtra(SecondActivity.PIZZA_SUM));
        tvAdditions.setText(tvAdditions.getText()+intent.getStringExtra(SecondActivity.PIZZA_ADDITIONS));
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
            Toast.makeText(this, "address", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.phone) {
            Toast.makeText(this, "phone", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}