package com.thegun.jmd.cartitemforpos.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxn769.Numpad;
import com.fxn769.TextGetListner;
import com.thegun.jmd.cartitemforpos.Fragment.CartFragment;
import com.thegun.jmd.cartitemforpos.Model.AddItemCartModel;
import com.thegun.jmd.cartitemforpos.Model.ProductModel;
import com.thegun.jmd.cartitemforpos.R;

import java.util.ArrayList;

import static com.thegun.jmd.cartitemforpos.Fragment.CartFragment.addItemCartArray;

/**
 * Created by Deependra Singh Patel on 24/4/19.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public static ArrayList<ProductModel> productsArray ;//= new ArrayList<>();
    Context context;
    private CallBackUs mCallBackus;


    public ProductAdapter(ArrayList<ProductModel> productsArray,Context context,CallBackUs mCallBackus) {
        this.productsArray= productsArray;
        this.context = context;
        this.mCallBackus = mCallBackus;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_adapter_layout, viewGroup, false);
        return new ProductAdapter.ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewHolder viewHolder, final int i) {
        //viewHolder.productImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.bag));
        viewHolder.productName.setText(productsArray.get(i).productName);
        viewHolder.productImage.setImageDrawable(ContextCompat.getDrawable(context, productsArray.get(i).imagePath));

        viewHolder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                final Dialog dialog12 = new Dialog(context);

                dialog12.setContentView(R.layout.dialog_custom_keyboard);
                dialog12.setTitle("Title...");


                final TextView edtTextQuantity= dialog12.findViewById(R.id.edt_txt_quantity);
                Numpad numpad = dialog12.findViewById(R.id.num);
                numpad.setTextLengthLimit(10);
                numpad.setBackgroundRes(R.drawable.custome_keyboard_grid_border);
                numpad.setOnTextChangeListner(new TextGetListner() {
                    @Override
                    public void onTextChange(String text, int digits_remaining) {
                        Log.d("input", (text) + "  " + digits_remaining);
                        edtTextQuantity.setText((text));
                    }
                });
                ImageView dialogQuantityClose= dialog12.findViewById(R.id.quantity_dialog_close);
                dialogQuantityClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog12.dismiss();
                    }
                });

                TextView dialogAddButton= dialog12.findViewById(R.id.dialog_add_quantity);
                dialogAddButton.setVisibility(View.VISIBLE);
                dialogAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCallBackus.addCartItemView();
                        productsArray.get(i).quantity=(edtTextQuantity.getText().toString());
                      

                        AddItemCartModel addItemCartModel = new AddItemCartModel();
                        addItemCartModel.setProductName(productsArray.get(i).getProductName());
                        addItemCartModel.setProductPrice(productsArray.get(i).getPrice());
                        addItemCartModel.setQuantity(productsArray.get(i).getQuantity());
                        addItemCartArray.add(addItemCartModel);
                        // AddItemCartAdapter.reports.add(addItemCartModel);
                        // holder.productImage.setSelected(true);


                        //}

                        CartFragment fragment2 = new CartFragment();
                        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.cart_fragment, fragment2);
                        fragmentTransaction.commit();


                        dialog12.dismiss();
                    }
                });


                dialog12.show();




            }
        });

    }

    @Override
    public int getItemCount() {
        return productsArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;

        ViewHolder(View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.android_gridview_image);
            productName=itemView.findViewById(R.id.android_gridview_text);
        }
    }
    public interface CallBackUs{
        void addCartItemView();
    }
}
