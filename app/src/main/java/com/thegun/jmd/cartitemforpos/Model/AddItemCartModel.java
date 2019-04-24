package com.thegun.jmd.cartitemforpos.Model;

/**
 * Created by Deependra Singh Patel on 12/4/19.
 */
public class AddItemCartModel {
    String productName;
    String productPrice;
    String quantity;


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
