package com.example.dell.listazakupow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 14.01.2017.
 */

public class ProductAdapter extends ArrayAdapter {

    public static final String NAZWA = "nazwa";
    public static final String CENA = "cena";
    public static final String ILOSC = "ilosc";
    public static final String ID = "id";
    public static final String SUM = "sum";

    List list = new ArrayList();

    public ProductAdapter(Context context, int resource) {

        super(context, resource);
    }


    public void add(Product object){
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position){

        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder productHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_product_row, parent, false);
            productHolder = new ProductHolder();

            productHolder.tx_name = (TextView)row.findViewById(R.id.t_name);
            productHolder.tx_qty = (TextView)row.findViewById(R.id.t_qty);
            productHolder.tx_price = (TextView)row.findViewById(R.id.t_price);
            row.setTag(productHolder);
        }

        else
        {
            productHolder = (ProductHolder)row.getTag();
        }
        final Product product = (Product)getItem(position);

        productHolder.tx_name.setText(product.getName().toString());
        productHolder.tx_qty.setText(String.format("%.2f", product.getQty()));
        productHolder.tx_price.setText(String.format("%.2f", product.getPrice()));

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).
                        setMessage("Co chcesz zrobić?").
                        setPositiveButton("Anuluj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setNeutralButton("Edytuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("EDYTUJ", "Edytuj id");
                        Intent intent = new Intent(getContext(), Zapisywanie_info_produktow.class);
                        intent.putExtra(NAZWA, product.getName().toString());
                        intent.putExtra(CENA, product.getPrice());
                        intent.putExtra(ILOSC, product.getQty());
                        intent.putExtra(ID, product.getId());
                        getContext().startActivity(intent);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Usuń", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new BackgroundTask(getContext()).execute("delete_info", String.valueOf(product.getId()));
                        new BackgroundTask(getContext()).execute("sum_info");
                        Log.d("USUŃ", "OBSŁUGA USUWANIA"+position);
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                }).create();
                alertDialog.show();
            }
        });



        return row;
    }

    static class ProductHolder
    {
        TextView tx_name, tx_qty, tx_price;
    }
}
