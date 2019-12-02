package com.example.dell.listazakupow;

/**
 * Created by dell on 14.01.2017.
 */

public final class ProductContract {



    ProductContract(){}

    public static abstract class ProductEntry
    {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String QTY = "qty";
        public static final String PRICE = "price";
        public static final String SUM = "sum";


        public static final String TABLE_NAME = "product_table";



    }
}
