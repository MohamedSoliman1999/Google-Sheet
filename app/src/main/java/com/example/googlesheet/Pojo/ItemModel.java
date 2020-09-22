package com.example.googlesheet.Pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "stock_table")
public class ItemModel {
    @PrimaryKey(autoGenerate = false)
    @NonNull
   @SerializedName("barcode")
   String barcode ;
   @SerializedName("qty")
   String qty;
   @SerializedName("time")
   String time;

   public ItemModel() {
   }

   public ItemModel(String barcode, String qty, String time) {
       this.barcode = barcode;
       this.qty = qty;
       this.time = time;
   }

   public String getBarcode() {
       return barcode;
   }

   public void setBarcode(String barcode) {
       this.barcode = barcode;
   }

   public String getQty() {
       return qty;
   }

   public void setQty(String qty) {
       this.qty = qty;
   }

   public String getTime() {
       return time;
   }

   public void setTime(String time) {
       this.time = time;
   }
}
