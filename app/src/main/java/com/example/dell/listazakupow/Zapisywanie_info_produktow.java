package com.example.dell.listazakupow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by dell on 14.01.2017.
 */

public class Zapisywanie_info_produktow extends Activity {
    EditText e_name, e_qty, e_price;
    String name, qty, price;
    private Intent intent;
    private Integer id;
    private String nazwa;
    private double cena,ilosc;




    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zapisywanie_info_produktow);

        e_name = (EditText)findViewById(R.id.d_name);
        if( e_name.getText().toString().length() == 0 )
            e_name.setError( "Podaj nazwę produktu!" );
        e_qty = (EditText)findViewById(R.id.d_qty);
        if( e_qty.getText().toString().length() == 0 )
            e_qty.setError( "Podaj ilość produktu!" );
        e_price = (EditText)findViewById(R.id.d_price);
        if( e_price.getText().toString().length() == 0 )
            e_price.setError( "Podaj cenę produktu!" );

        intent = getIntent();
        id=null;
        if(intent.hasExtra(ProductAdapter.ID)) {
            id = intent.getIntExtra(ProductAdapter.ID, 0);
            nazwa = intent.getStringExtra(ProductAdapter.NAZWA);
            cena = intent.getDoubleExtra(ProductAdapter.CENA, 0d);
            ilosc = intent.getDoubleExtra(ProductAdapter.ILOSC, 0d);

            e_name.setText(nazwa);
            e_qty.setText(String.valueOf(ilosc));
            e_price.setText(String.valueOf(cena));
        }





    }



    public void saveData(View view){


        name = e_name.getText().toString();
        qty = e_qty.getText().toString();
        price = e_price.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(this);
        if(id!=null) {
        String sID = String.valueOf(id);
        backgroundTask.execute("update_info", sID, name, qty,  price);
        } else
        backgroundTask.execute("add_info", name, qty,  price);
        finish();

        }
        }
