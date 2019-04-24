package com.thegun.jmd.cartitemforpos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.thegun.jmd.cartitemforpos.Adapter.AddItemCartAdapter;
import com.thegun.jmd.cartitemforpos.Adapter.ProductAdapter;
import com.thegun.jmd.cartitemforpos.Model.AddItemCartModel;
import com.thegun.jmd.cartitemforpos.Model.ProductModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductAdapter.CallBackUs {

    ArrayList<ProductModel> arrayList = new ArrayList<>();
    ProductAdapter productAdapter;
    RecyclerView productRecyclerView;
    RecyclerView addCartRecyclerView;
    public static TextView itemInCart;
    public static TextView subTotal;
    private AddItemCartAdapter addItemCartAdapter;
    ArrayList<AddItemCartModel> addItemCartArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProduct();
        productAdapter = new ProductAdapter(arrayList, this,this);
        productRecyclerView = findViewById(R.id.product_recycler_view);
        addCartRecyclerView = findViewById(R.id.cart_recycler_view);
        itemInCart = findViewById(R.id.txt_item_in_cart);
        subTotal = findViewById(R.id.txt_view_subtotal);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(productAdapter);

    }

    private void addItemToCartMethod() {
        addItemCartAdapter = new AddItemCartAdapter(addItemCartArray, this);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        addCartRecyclerView.setLayoutManager(verticalLayoutManager);
        addCartRecyclerView.setAdapter(addItemCartAdapter);
        addItemCartAdapter.notifyDataSetChanged();


    }


    private void addProduct() {
        ProductModel productModel = new ProductModel("Bag", "1200", "10", R.drawable.bag);
         arrayList.add(productModel);
        ProductModel productModel1 = new ProductModel("Shoe", "3200", "10", R.drawable.shoe);
        arrayList.add(productModel1);
        ProductModel productModel2 = new ProductModel("Springroll", "120", "10", R.drawable.springrolls);
        arrayList.add(productModel2);
        ProductModel productModel3 = new ProductModel("Shoe1", "1000", "10", R.drawable.shoe);
        arrayList.add(productModel3);
        ProductModel productModel4 = new ProductModel("Springroll1", "100", "10", R.drawable.springrolls);
        arrayList.add(productModel4);
        ProductModel productModel5 = new ProductModel("bag1", "1100", "10", R.drawable.bag);
        arrayList.add(productModel5);
    }

    @Override
    public void addCartItemView() {
        addItemToCartMethod();

    }
}
