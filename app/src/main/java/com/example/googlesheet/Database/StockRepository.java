package com.example.googlesheet.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.googlesheet.Pojo.ItemModel;

import java.util.List;

public class StockRepository {

    private stockDao mStockDao ;
    private LiveData<List<ItemModel>> allStock;
    public StockRepository(Application application){
        StockDatabase stockDatabase = StockDatabase.getInstance(application);
        mStockDao = stockDatabase.mStockDao();
        allStock = mStockDao.GetAllItemsInStock();
    }

    public void insert(ItemModel item){
        new InsertItemAsyncTask(mStockDao).execute(item);

    }

    public void update(ItemModel item){
        new UpdateItemAsyncTask(mStockDao).execute(item);


    }
    public void delete(ItemModel item){
        new DeleteItemAsyncTask(mStockDao).execute(item);


    }

    public void deleteAllStock(){
        new DeleteAllItemAsyncTask(mStockDao).execute();

    }
    public LiveData<List<ItemModel>> getAllStock(){
        return allStock;
    }
private static class InsertItemAsyncTask extends AsyncTask<ItemModel ,Void ,Void> {
    private  stockDao mStockDao ;

    private InsertItemAsyncTask(stockDao stockDao){
        this.mStockDao = stockDao ;

    }

    @Override
    protected Void doInBackground(ItemModel... itemModels) {
        mStockDao.insert(itemModels[0]);
        return null;
    }
}
private static class UpdateItemAsyncTask extends AsyncTask<ItemModel ,Void ,Void>{
    private  stockDao mStockDao ;
    private UpdateItemAsyncTask(stockDao stockDao){
        this.mStockDao = stockDao ;

    }

    @Override
    protected Void doInBackground(ItemModel... itemModels) {
        mStockDao.update(itemModels[0]);
        return null;
    }
}
private static class DeleteItemAsyncTask extends AsyncTask<ItemModel ,Void ,Void>{
    private  stockDao mStockDao ;
    private DeleteItemAsyncTask(stockDao stockDao){
        this.mStockDao = stockDao ;

    }

    @Override
    protected Void doInBackground(ItemModel... itemModels) {
        mStockDao.delete(itemModels[0]);
        return null;
    }
}
private static class DeleteAllItemAsyncTask extends AsyncTask<Void ,Void ,Void>{
    private  stockDao mStockDao ;
    private DeleteAllItemAsyncTask(stockDao stockDao){
        this.mStockDao = stockDao ;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        mStockDao.deleteAllitems();
        return null;
    }
}


}
