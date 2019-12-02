package com.example.dell.listazakupow;

/**
 * Created by dell on 14.01.2017.
 */

public class Product {

    private String name;
    private double qty, price;
    private Integer id;

    public Product(Integer id, String name, double qty, double price) {
        this.setId(id);
        this.setName(name);
        this.setQty(qty);
        this.setPrice(price);
    }



    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getQty() {

        return qty;
    }

    public void setQty(double qty) {

        this.qty = qty;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}
