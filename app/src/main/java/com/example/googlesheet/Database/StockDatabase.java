package com.example.googlesheet.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.googlesheet.Pojo.ItemModel;


@Database(entities = { ItemModel.class }, version = 1 )
public abstract class  StockDatabase extends RoomDatabase {

    public static StockDatabase instance ;
    public abstract stockDao mStockDao() ;

    public static synchronized StockDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StockDatabase.class ," stock_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallBack).build();
        }
        return instance;
    }
    private static  RoomDatabase.Callback roomCallBack =  new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();


        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private stockDao mStockDao;
        private PopulateDbAsyncTask(StockDatabase db) {
            mStockDao = db.mStockDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mStockDao.insert(new ItemModel( "Description 2", "4", ""));
            mStockDao.insert(new ItemModel( "Description 1", "2", ""));
            mStockDao.insert(new ItemModel( "Description 12", "2", ""));
            return null;
        }
    }

}
