package com.example.dell.listazakupow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addProduct(View view){

        startActivity(new Intent(this, Zapisywanie_info_produktow.class));
    }

    public void displayProducts(View view)
    {
        startActivity(new Intent(this, DisplayProduct.class));
    }
}
