package com.example.googlesheet.NetWork;

import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
  //  @GET("macros/echo?user_content_key=-jPVeZkWJcduQHHXp_u4qNd4FzbddGBECxLzHrZc54_c7wlx0QdD_xia0IIuC9XLS3g3iyil2XyqCf4GDDWMFGkiefi1p1Aqm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnO43gfMK2_DJrqYBrWCjVQaE_IZW7vUcSnFlQIbDSMKsGjwLHZyKyBHS344KuYa5I0L0LNlw4jRx3bgPuCGuxiCdEy9KtHB5DA&lib=MnT7Gq2yBMqmLChS3NUMUMfTq_Sn2MCYG")

    @GET("macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec?action=read")
    Call<AllDataList> getAlldata();
  //  https://script.google.com/macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec?action=read


    @POST("macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec")
    public Call<ItemModel> postData(@Body ItemModel  itemModel);

//    @POST("macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec")
//    Call<ItemModel> postData(@Body JsonObject body);
//
//    @FormUrlEncoded
//    @POST("macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec")
//    Call<ItemModel> postDataItem(@Field("barcode") String barcode, @Field("qty") String qty);



}




