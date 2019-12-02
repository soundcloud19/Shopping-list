package com.example.dell.listazakupow;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell on 14.01.2017.
 */

public class BackgroundTask extends AsyncTask<String, Product, String> {

    Context ctx;
    ProductAdapter productAdapter;
    Activity activity;
    ListView listView;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        DbOperations dbOperations = new DbOperations(ctx);
        if (method.equals("add_info")) {

            String Name = params[1];
            double qty = Double.parseDouble(params[2]);
            double price = Double.parseDouble(params[3]);
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.addInformations(db, Name, qty, price);
            return "Dodano produkt do listy zakupów";
        } else if (method.equals("get_info")) {
            listView = (ListView) activity.findViewById(R.id.display_listview);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listView.setClickable(true);
                }
            });
            SQLiteDatabase db = dbOperations.getReadableDatabase();
            Cursor cursor = dbOperations.getInformations(db);
            productAdapter = new ProductAdapter(ctx, R.layout.display_product_row);
            String name;
            double qty, price, sum;
            Integer id;
            while (cursor.moveToNext()) {
                id = cursor.getInt(cursor.getColumnIndex(ProductContract.ProductEntry.ID));
                name = cursor.getString(cursor.getColumnIndex(ProductContract.ProductEntry.NAME));
                qty = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.QTY));
                price = cursor.getDouble(cursor.getColumnIndex(ProductContract.ProductEntry.PRICE));
                Product product = new Product(id, name, qty, price);
                publishProgress(product);
            }
            return "get_info";
        } else if(method.equals("update_info")) {
            int id = Integer.parseInt(params[1]);
            String Name = params[2];
            double qty = Double.parseDouble(params[3]);
            double price = Double.parseDouble(params[4]);
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.updateInformations(db, id, Name, qty, price);
            return "Zaktualizowano produkt z listy zakupów";
        } else if(method.equals("delete_info")) {
            int id = Integer.parseInt(params[1]);
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.delInformations(db, id);
            return "Usunięto produkt z listy zakupów";
        } else if(method.equals("sum_info")) {
            SQLiteDatabase db = dbOperations.getWritableDatabase();
            final double sum = dbOperations.sumProduct(db);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView) activity.findViewById(R.id.sum)).setText(String.format("%.2f", sum));
                }
            });
            return null;
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Product... values) {

        productAdapter.add(values[0]);


    }

    @Override
    protected void onPostExecute(String result) {

        if(result==null) {}
        else if (result.equals("get_info"))
        {
            listView.setAdapter(productAdapter);
        }
        else
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}
