package com.example.dell.listazakupow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dell on 14.01.2017.
 */

public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "product_info_5.db";
    private static final String CREATE_QUERY = "create table " + ProductContract.ProductEntry.TABLE_NAME +
            "("+ProductContract.ProductEntry.ID + " integer primary key autoincrement," + ProductContract.ProductEntry.NAME + " text," +
            ProductContract.ProductEntry.QTY + " real," + ProductContract.ProductEntry.PRICE + " real);";
    private static final String SUM = "SELECT SUM(QTY * PRICE) as sum FROM "+ProductContract.ProductEntry.TABLE_NAME;


    DbOperations(Context ctx){

        super(ctx, DB_NAME, null, DB_VERSION);
        Log.d("Operacje na baziedanych", "Utworzono bazę danych");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
            Log.d("Operacje nabazie danych", "Utworzono tabelę w bazie danych"+CREATE_QUERY);
    }

    public void addInformations(SQLiteDatabase db, String name, double qty, double price){

        ContentValues contentValues = new ContentValues();

        contentValues.put(ProductContract.ProductEntry.NAME, name);
        contentValues.put(ProductContract.ProductEntry.QTY, qty);
        contentValues.put(ProductContract.ProductEntry.PRICE, price);
        db.insert(ProductContract.ProductEntry.TABLE_NAME, null, contentValues);
        Log.d("Operacje na baziedanych", "Dodano produkt do listy zakupów");
    }

    public void updateInformations(SQLiteDatabase db, Integer id, String name, double qty, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductContract.ProductEntry.NAME, name);
        contentValues.put(ProductContract.ProductEntry.QTY, qty);
        contentValues.put(ProductContract.ProductEntry.PRICE, price);
        db.update(ProductContract.ProductEntry.TABLE_NAME, contentValues, "id="+id, null);
        Log.d("Operacje na baziedanych", "Dodano produkt do listy zakupów");
    }

    public void delInformations(SQLiteDatabase db, Integer id){
        db.delete(ProductContract.ProductEntry.TABLE_NAME, "id="+id, null);
        Log.d("Operacje na baziedanych", "Dodano produkt do listy zakupów");
    }

    public Cursor getInformations(SQLiteDatabase db)
    {
        String[] projections = {ProductContract.ProductEntry.ID,ProductContract.ProductEntry.NAME,
            ProductContract.ProductEntry.QTY, ProductContract.ProductEntry.PRICE};
        Cursor cursor = db.query(ProductContract.ProductEntry.TABLE_NAME, projections,

                null, null, null, null, null, null);

        return cursor;
    }

    public double sumProduct(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(SUM, null);
        if(cursor.moveToNext()) return cursor.getDouble(0);
        return 0d;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
