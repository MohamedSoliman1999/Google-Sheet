package com.example.googlesheet.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.googlesheet.Pojo.ItemModel;

import java.util.List;
@Dao
interface stockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemModel item);
    @Delete
    void delete(ItemModel item);
    @Update
    void update(ItemModel item);
    @Query("DELETE FROM stock_table")
    void deleteAllitems();

    @Query("SELECT * FROM stock_table ORDER BY time")
    LiveData<List<ItemModel>> GetAllItemsInStock();

}
