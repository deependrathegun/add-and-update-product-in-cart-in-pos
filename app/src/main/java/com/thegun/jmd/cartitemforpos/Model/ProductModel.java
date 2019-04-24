package com.thegun.jmd.cartitemforpos.Model;

/**
 * Created by Deependra Singh Patel on 24/4/19.
 */
public class ProductModel {

        public String productName;
        public String price;
        public String quantity;
        public int imagePath;
        //    public int price;


    public ProductModel(String productName, String price, String quantity, int imagePath) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }
}
