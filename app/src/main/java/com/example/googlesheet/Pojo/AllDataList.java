package com.example.googlesheet.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class AllDataList{
    @SerializedName("records")
    public List<ItemModel> records = null;
}

