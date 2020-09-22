package com.example.googlesheet.UI.ShowActivity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.googlesheet.NetWork.ApiClient;
import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class ShowStockViewModel extends ViewModel {
//    MutableLiveData<List<ItemModel>> getStockMutableLiveData = new MutableLiveData<>();
//    MutableLiveData<String> stock = new MutableLiveData<>();
     private MutableLiveData<List<ItemModel>> stock_items;
     public LiveData<List<ItemModel>> getStockItems() {
         if (stock_items == null) {
             stock_items = new MutableLiveData<List<ItemModel>>();
             getStock();
         }
         return stock_items;
     }

    public void getStock() {

        Call<AllDataList> call = ApiClient.getINSTANCE().getAllData();
        call.enqueue( new Callback<AllDataList>() {
            @Override
            public void onResponse(Call<AllDataList> call, Response<AllDataList> response) {
                stock_items.setValue(response.body().records);
                Log.e( "#####", response.body().records.get( 0 ).getBarcode().toString() );
            }

            @Override
            public void onFailure(Call<AllDataList> call, Throwable t) {
                Log.e( "Error", t.getMessage() );
            }
        } );


    }
}
