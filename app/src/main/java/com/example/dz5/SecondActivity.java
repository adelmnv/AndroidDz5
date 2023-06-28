package com.example.dz5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashSet;

public class SecondActivity extends AppCompatActivity {
    public static String PIZZA_NAME = "pizzaName";
    public static String PIZZA_SIZE = "pizzaSize";
    public static String PIZZA_ADDITIONS = "pizzaAdditions";
    public static String PIZZA_SUM = "sum";

    private RadioGroup rgSize;
    private CheckBox cbCheese;
    private CheckBox cbHam;
    private  CheckBox cbMashroom;
    private Button btnNext;
    private String pizzaSize = "Маленькая 20 см";
    private String pizzaName = "";

    private HashSet<String> pizzaAditions = new HashSet<>();
    private int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getPizzaIntent();
        initialize();
        setListeners();
    }

    private void initialize(){
        rgSize = findViewById(R.id.rgSize);
        cbCheese = findViewById(R.id.cbCheese);
        cbHam = findViewById(R.id.cbHam);
        cbMashroom = findViewById(R.id.cbMashroom);
        btnNext = findViewById(R.id.btnNext);
    }

    private void setListeners(){
        radioGroupOnClick();
        checkBoxOnClick();
        btnNext();
    }

    private void radioGroupOnClick(){
        rgSize.setOnCheckedChangeListener((group, checkedId)->{
            if(checkedId == R.id.rbSmall){
                pizzaSize = getResources().getString(R.string.sizeSmall);
            }
            else if(checkedId == R.id.rbMiddle){
                pizzaSize = getResources().getString(R.string.sizeMiddle);
            }
            else if(checkedId == R.id.rbLarge){
                pizzaSize = getResources().getString(R.string.sizeLarge);
            }
        });
    }

    private void getPizzaIntent(){
        Intent intent = getIntent();
        pizzaName = intent.getStringExtra(MainActivity.KEY_MESSAGE);
    }

    private void checkBoxOnClick(){
        cbMashroom.setOnCheckedChangeListener(this::checkBoxEvent);
        cbHam.setOnCheckedChangeListener(this::checkBoxEvent);
        cbCheese.setOnCheckedChangeListener(this::checkBoxEvent);
    }

    private void checkBoxEvent(CompoundButton compoundButton, boolean b) {
        String temp = compoundButton.getText().toString();
        if(b){
            pizzaAditions.add(temp);
        }
        else {
            pizzaAditions.remove(temp);
        }
    }

    private void btnNext(){
        btnNext.setOnClickListener(v-> sendIntent());
    }

    private void sendIntent(){
        setPizzaPrice();

        String temp = "";
        for(String x : pizzaAditions){
            temp += x+" ";
        }

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra(PIZZA_NAME,pizzaName);
        intent.putExtra(PIZZA_SIZE,pizzaSize);
        intent.putExtra(PIZZA_ADDITIONS,temp);
        intent.putExtra(PIZZA_SUM,String.valueOf(sum));

        startActivity(intent);
    }

    private void setPizzaPrice(){
        switch (pizzaName){
            case "Маргарита":
                sum = 2850;
                break;
            case "Пепперони":
                sum = 3450;
                break;
            case "Грибная":
                sum = 4050;
        }

        if(pizzaSize == getResources().getString(R.string.sizeMiddle))
            sum+=1200;
        else if (pizzaSize == getResources().getString(R.string.sizeLarge))
            sum+=1600;

        if (pizzaAditions.contains(getResources().getString(R.string.adCheese)))
            sum+=600;
        else if(pizzaAditions.contains(getResources().getString(R.string.adMashroom)))
            sum+=800;
        else if(pizzaAditions.contains(getResources().getString(R.string.adHam)))
            sum+=1000;
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