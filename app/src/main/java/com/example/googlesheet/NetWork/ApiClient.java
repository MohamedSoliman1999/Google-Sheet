package com.example.googlesheet.NetWork;

import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class ApiClient {
    private static String BASE_URL="https://script.google.com/";
        //http://script.google.com/";
   // https://script.google.com/macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec?action=read

    private static ApiInterface apiInterface;
    private static ApiClient INSTANCE;

    private ApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //for RX java
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static synchronized ApiClient getINSTANCE() {
        if(INSTANCE==null) {
            INSTANCE=new ApiClient();
        }
        return INSTANCE;
    }
    public Call<AllDataList> getAllData() {
        return apiInterface.getAlldata();
    }
    public Call<ItemModel> postData(ItemModel item) {
      return apiInterface.postData(item);
    }
}
