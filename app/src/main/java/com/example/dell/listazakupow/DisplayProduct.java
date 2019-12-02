package com.example.dell.listazakupow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DisplayProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_product_layout);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("get_info");
        BackgroundTask backgroundTask2 = new BackgroundTask(this);
        backgroundTask2.execute("sum_info");


    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("get_info");
        BackgroundTask backgroundTask2 = new BackgroundTask(this);
        backgroundTask2.execute("sum_info");
    }
}
