package com.thegun.jmd.cartitemforpos.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thegun.jmd.cartitemforpos.Adapter.AddItemCartAdapter;
import com.thegun.jmd.cartitemforpos.Model.AddItemCartModel;
import com.thegun.jmd.cartitemforpos.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {
  //@BindView(R.id.addCartRecyclerView)
  RecyclerView addCartRecyclerView;
  private AddItemCartAdapter addItemCartAdapter;
  public static ArrayList<AddItemCartModel> addItemCartArray = new ArrayList<>();
  Context context;
  private ArrayList<AddItemCartModel> temparraylist = new ArrayList<>();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.fragment_cart, container, false);
    context=getActivity();

    addCartRecyclerView=view.findViewById(R.id.addCartRecyclerView);
    addItemToCartMethod();// Inflate the layout for this fragment



    return view;
  }

  private void addItemToCartMethod() {
    for (int i = 0; i < addItemCartArray.size(); i++) {
      for (int j = i + 1; j < addItemCartArray.size(); j++) {
        if (addItemCartArray.get(i).getProductName().equals(addItemCartArray.get(j).getProductName())) {
          addItemCartArray.get(i).setQuantity(addItemCartArray.get(j).getQuantity());
          addItemCartArray.remove(j);
          j--;
          Log.d("remove", String.valueOf(addItemCartArray.size()));

        }
      }

    }
    temparraylist.addAll(addItemCartArray);
    //addItemCartArray.clear();


    addItemCartAdapter = new AddItemCartAdapter(temparraylist, context);
    LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    addCartRecyclerView.setLayoutManager(verticalLayoutManager);
    addCartRecyclerView.setAdapter(addItemCartAdapter);
    addItemCartAdapter.notifyDataSetChanged();



  }

}