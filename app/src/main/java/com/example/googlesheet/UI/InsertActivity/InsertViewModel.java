package com.example.googlesheet.UI.InsertActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.googlesheet.Database.StockRepository;
import com.example.googlesheet.Pojo.ItemModel;

import java.util.List;



public class InsertViewModel extends AndroidViewModel {
    private StockRepository mStockRepository ;
    private LiveData<List<ItemModel>> allStock;
    public InsertViewModel(@NonNull Application application) {
        super(application);
        mStockRepository =  new StockRepository(application);
        allStock = mStockRepository.getAllStock();
    }
    //MutableLiveData<List<ItemModel>> itemLiveData =new MutableLiveData<>();
    public void insert(ItemModel itemModel ) {
        mStockRepository.insert(itemModel);
    }
    public void update(ItemModel itemModel) {
        mStockRepository.update(itemModel);
    }
    public  void delete(ItemModel ItemModel) {
        mStockRepository.delete(ItemModel);
    }

    public void deleteAllNotes() {
        mStockRepository.deleteAllStock();
    }
    public LiveData<List<ItemModel>> getAllNotes() {
        return allStock;
    }


    //
//        ItemModel itemModel = new ItemModel("2","3","9");
//        //setContentView(R.layout.activity_main);
//      Call<ItemModel> post= ApiClient.getINSTANCE().postData(itemModel);
//     post.enqueue( new Callback<ItemModel>() {
//         @Override
//         public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
//             Log.d( "responseeee"  , "" + response.body().getBarcode() );
//         }
//
//         @Override
//         public void onFailure(Call<ItemModel> call, Throwable t) {
//
//         }
//     } );


}
