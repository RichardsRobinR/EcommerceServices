package com.example.krishnachaitanya.ecommerceservices;

/**
 * Created by Krishna Chaitanya on 25-08-2019.
 */

public class Product {
    String id,prodname,prodtype,web;

    public Product(){

    }

    public Product(String id, String prodname, String prodtype, String web) {
        this.id = id;
        this.prodname = prodname;
        this.prodtype = prodtype;
        this.web = web;
    }

    public String getId() {
        return id;
    }

    public String getProdname() {
        return prodname;
    }

    public String getProdtype() {
        return prodtype;
    }

    public String getWeb() {
        return web;
    }
}


