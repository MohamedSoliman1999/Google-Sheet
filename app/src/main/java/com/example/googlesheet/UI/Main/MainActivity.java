package com.example.googlesheet.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;
import com.example.googlesheet.NetWork.ApiClient;
import com.example.googlesheet.R;
import com.example.googlesheet.UI.InsertActivity.InsertActivity;
import com.example.googlesheet.UI.ShowActivity.ShowStockActivity;
//import com.example.googlesheet.databinding.ActivityListBinding;

public class MainActivity extends AppCompatActivity {
    Button insert_btn, show_btn;

    //    ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
//        binding=ActivityListBinding.inflate(getLayoutInflater());
//        View view=binding.getRoot();
        setContentView( R.layout.activity_main );

        initView();
        show_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showtIntent = new Intent( MainActivity.this, ShowStockActivity.class );
                startActivity( showtIntent );
            }
        } );


        insert_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertIntent = new Intent( MainActivity.this, InsertActivity.class );
                startActivity( insertIntent );

            }
        } );

    }

    private void initView() {
        insert_btn = findViewById( R.id.insert_btn );
        show_btn = findViewById( R.id.show_btn );

    }


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

       // call
//        Call<AllDataList> call= ApiClient.getINSTANCE().getAllData();
//        call.enqueue(new Callback<AllDataList>() {
//            @Override
//            public void onResponse(Call<AllDataList> call, Response<AllDataList> response) {
//                Log.e("#####",response.body().records.get(0).barcode.toString());
//            }
//
//            @Override
//            public void onFailure(Call<AllDataList> call, Throwable t) {
//                Log.e("Error",t.getMessage());
//            }
//        });

//    public void postInSheet(){
//
//    }
