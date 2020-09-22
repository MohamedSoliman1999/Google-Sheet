package com.example.googlesheet.UI.ShowActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.googlesheet.NetWork.ApiClient;
import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;
import com.example.googlesheet.R;
import com.example.googlesheet.UI.InsertActivity.InsertViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowStockActivity extends AppCompatActivity {
    ShowStockViewModel mShowStockViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_stock );
        RecyclerView recyclerView = findViewById(R.id.show_RecyclerView);
        final ShowAdapter adapter = new ShowAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        mShowStockViewModel = new ViewModelProvider( this ).get( ShowStockViewModel.class );
        mShowStockViewModel.getStockItems().observe( this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(List<ItemModel> itemModels) {
                adapter.setStockItemslist(itemModels);

            }
        } );




//        // call
//        Call<AllDataList> call= ApiClient.getINSTANCE().getAllData();
//        call.enqueue(new Callback<AllDataList>() {
//            @Override
//            public void onResponse(Call<AllDataList> call, Response<AllDataList> response) {
//                Log.e("#####",response.body().records.get(0).getBarcode().toString());
//            }
//
//            @Override
//            public void onFailure(Call<AllDataList> call, Throwable t) {
//                Log.e("Error",t.getMessage());
//            }
//        });








    }
}