package com.thegun.jmd.cartitemforpos.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn769.Numpad;
import com.fxn769.TextGetListner;
import com.thegun.jmd.cartitemforpos.MainActivity;
import com.thegun.jmd.cartitemforpos.Model.AddItemCartModel;
import com.thegun.jmd.cartitemforpos.R;

import java.util.ArrayList;

/**
 * Created by Deependra Singh Patel on 12/4/19.
 */
public class AddItemCartAdapter extends RecyclerView.Adapter<AddItemCartAdapter.ViewHolder> {
    public static ArrayList<AddItemCartModel> reports = new ArrayList<>();
    Context context;
   // public static CartItems cartItems = new CartItems();


    public AddItemCartAdapter(ArrayList<AddItemCartModel> tReports, Context context) {
        this.context = context;
        this.reports = tReports;
    }

    @Override
    public AddItemCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_layout, parent, false);
        return new AddItemCartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AddItemCartAdapter.ViewHolder holder, final int position) {
        AddItemCartModel addItemCartModel = new AddItemCartModel();

                    holder.itemName.setText(reports.get(position).getProductName());
                    holder.itemPrice.setText(reports.get(position).getProductPrice());
                    holder.quantity.setText(reports.get(position).getQuantity());
                   /* if (Integer.parseInt(holder.quantity.getText().toString()) > Integer.parseInt(reports.get(position).getQuantity())) {
                        cartItems.noOfItems= (cartItems.noOfItems)+(Integer.parseInt(reports.get(position).getQuantity())-Integer.parseInt(holder.quantity.getText().toString()));
                        NewHomeFragment.itemInCart.setText(String.valueOf(cartItems.noOfItems));

                    } else if ((Integer.parseInt(holder.quantity.getText().toString()) < Integer.parseInt(reports.get(position).getQuantity()))){
                        cartItems.noOfItems = (cartItems.noOfItems) + Integer.parseInt(reports.get(position).getQuantity()) + Integer.parseInt(holder.quantity.getText().toString());
                        NewHomeFragment.itemInCart.setText(String.valueOf(cartItems.noOfItems));
                    } else if (Integer.parseInt(reports.get(position).getQuantity())>0){
                        NewHomeFragment.itemInCart.setText(String.valueOf(cartItems.noOfItems));

                    }*/
        double sumQuantity = 0;
        double sumSubTotal=0;
        for(int i = 0; i < reports.size(); i++) {
            sumQuantity += Integer.parseInt(reports.get(i).getQuantity());
            sumSubTotal+= (Integer.parseInt(reports.get(i).getProductPrice())* Integer.parseInt(reports.get(i).getQuantity()));
        }
        MainActivity.itemInCart.setText(String.valueOf(sumQuantity));
        MainActivity.subTotal.setText(String.valueOf(sumSubTotal));


        holder.quantity.setOnClickListener(new View.OnClickListener() {
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

                TextView dialogUpdateButton= dialog12.findViewById(R.id.dialog_update_quantity);
                dialogUpdateButton.setVisibility(View.VISIBLE);
                dialogUpdateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reports.get(position).setQuantity(edtTextQuantity.getText().toString());
                            Toast.makeText(context, "Already added in cart", Toast.LENGTH_SHORT).show();
                           // for (int i=0;i<AddItemCartAdapter.reports.size();i++) {
                                //if (HomeProductAdapter.productsArray.get(position).getProductName().equals(AddItemCartAdapter.reports.get(i).getProductName())) {
                                    AddItemCartModel addItemCartModel = new AddItemCartModel();
                                    addItemCartModel.setProductName(reports.get(position).getProductName());
                                    addItemCartModel.setProductPrice(reports.get(position).getProductPrice());
                                    addItemCartModel.setQuantity(reports.get(position).getQuantity());
                                    AddItemCartAdapter.reports.set(position,addItemCartModel);
                        holder.quantity.setText(reports.get(position).getQuantity());

                        //}
                            //}
                        double sumQuantity = 0;
                        double sumSubTotal=0;
                        for(int i = 0; i < reports.size(); i++) {
                            sumQuantity += Integer.parseInt(reports.get(i).getQuantity());
                            sumSubTotal+= (Integer.parseInt(reports.get(i).getProductPrice())* Integer.parseInt(reports.get(i).getQuantity()));
                        }
                        MainActivity.itemInCart.setText(String.valueOf(sumQuantity));
                        MainActivity.subTotal.setText(String.valueOf(sumSubTotal));

                        dialog12.dismiss();
                        //holder.productImage.setSelected(true);
                        }
                /*if (AddItemCartAdapter.reports.size()!=-1) {
                    holder.productImage.setClickable(false);
                }*/



                });


                dialog12.show();

            }
        });




    }

    @Override
    public int getItemCount() {
        Log.d("sizeproductr", String.valueOf(reports.size()));

        return reports.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName,itemPrice, quantity;
        ImageView removeSingleItemFromCart;


        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemPrice = (TextView)itemView.findViewById(R.id.itemPrice);
            quantity = (TextView)itemView.findViewById(R.id.multi);

        }
    }
}


