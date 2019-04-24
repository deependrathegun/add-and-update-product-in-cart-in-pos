package com.thegun.jmd.cartitemforpos.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxn769.Numpad;
import com.fxn769.TextGetListner;
import com.thegun.jmd.cartitemforpos.Model.AddItemCartModel;
import com.thegun.jmd.cartitemforpos.Model.ProductModel;
import com.thegun.jmd.cartitemforpos.R;

import java.util.ArrayList;

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
                /*Intent intent = new Intent(context,AddItemCartAdapter.class);
                intent.putExtra("name",productsArray.get(position).getProductName());
                intent.putExtra("price",productsArray.get(position).getPrice());
                context.startActivity(intent);*/
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
                        if (viewHolder.productImage.isSelected() == true){
                            //Toast.makeText(context, "Already added in cart", Toast.LENGTH_SHORT).show();
                            for (int j=0;j<AddItemCartAdapter.reports.size();j++) {
                                if (productsArray.get(i).productName.equals(AddItemCartAdapter.reports.get(j).getProductName())) {
                                    AddItemCartModel addItemCartModel = new AddItemCartModel();
                                    addItemCartModel.setProductName(productsArray.get(i).productName);
                                    addItemCartModel.setProductPrice(productsArray.get(i).price);
                                    addItemCartModel.setQuantity(productsArray.get(i).quantity);
                                    AddItemCartAdapter.reports.set(i,addItemCartModel);
                                }
                            }
                            //holder.productImage.setSelected(true);
                        }
                /*if (AddItemCartAdapter.reports.size()!=-1) {
                    holder.productImage.setClickable(false);
                }*/
                        else {
                            AddItemCartModel addItemCartModel = new AddItemCartModel();
                            addItemCartModel.setProductName(productsArray.get(i).productName);
                            addItemCartModel.setProductPrice(productsArray.get(i).price);
                            addItemCartModel.setQuantity(productsArray.get(i).quantity);
                            AddItemCartAdapter.reports.add(addItemCartModel);
                            viewHolder.productImage.setSelected(true);
                        }


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
