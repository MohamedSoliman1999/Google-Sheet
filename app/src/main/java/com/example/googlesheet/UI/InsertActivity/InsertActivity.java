package com.example.googlesheet.UI.InsertActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.example.googlesheet.NetWork.ApiClient;
import com.example.googlesheet.Pojo.AllDataList;
import com.example.googlesheet.Pojo.ItemModel;
import com.example.googlesheet.R;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    InsertViewModel mInsertViewModel;
    InsertAdapter insertadapter;
    EditText name_edit_txt, barcode_edit_txt, qty_edit_txt, time_edit_txt;
    Button saveBtn ,uploadBtn;
    private RecyclerView.ViewHolder viewHolder;
    public static String barCode;
    public static String qty;
    int Itemid=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert );
                barcode_edit_txt = findViewById(R.id.barcode_editTxt);
                qty_edit_txt = findViewById(R.id.qty_editTxt);
                saveBtn = findViewById(R.id.insertToData_btn);
                uploadBtn = findViewById(R.id.uploadData_btn);

                RecyclerView recyclerView = findViewById(R.id.insertRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                insertadapter = new InsertAdapter();
                recyclerView.setAdapter(insertadapter);

                mInsertViewModel = ViewModelProviders.of(this).get(InsertViewModel.class);
               mInsertViewModel.getAllNotes().observe(this, new Observer<List<ItemModel>>() {
@Override
public void onChanged(@Nullable List<ItemModel> itemList) {
        //update RecyclerView
        insertadapter.setStockItemslist(itemList);
        }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
       @Override
        public void onClick(View v) {
        saveToDatabase();
        }
        });
        uploadBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        } );



        new ItemTouchHelper( new ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
         @Override
         public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
        }

       @Override
      public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
             Itemid=viewHolder.getAdapterPosition();
        openDialog();
//        mInsertViewModel.delete(InsertAdapter.getItemStockAt(viewHolder.getAdapterPosition()));
//        Toast.makeText(InsertActivity.this, "item deleted", Toast.LENGTH_SHORT).show();
        }
        }).attachToRecyclerView(recyclerView);

        }


public void saveToDatabase() {
        String barcode = barcode_edit_txt.getText().toString();
        String qty = qty_edit_txt.getText().toString();
//        int qtyvalue = 0;
//        if (!"".equals(qty)) {
//            qtyvalue = Integer.parseInt(qty);
//        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String currentTimeStamp = timestamp.toString();
        ItemModel itemModel = new ItemModel(currentTimeStamp ,qty,barcode  );
        Log.v("mmmmmmm", itemModel.toString());
    Log.v("9999999999999", itemModel.getBarcode().toString());


        if (barcode.isEmpty() || qty.isEmpty()) {

        //if (barcode.trim().isEmpty() || qty.trim().isEmpty()) {
        Toast.makeText(this, "Please insert  the product  Barcode and  Qty", Toast.LENGTH_SHORT).show();

        barcode_edit_txt.getText().clear();
        qty_edit_txt.getText().clear();

        }
        if (barcode != null && qty != null) {
        // ItemModel itemModel = new ItemModel(barcode, qtyvalue, currentTimeStamp, status);

        mInsertViewModel.insert(itemModel);
        Toast.makeText(this, "Item saved ", Toast.LENGTH_SHORT).show();
        barcode_edit_txt.getText().clear();
        qty_edit_txt.getText().clear(); //or you can use editText.setText("");


        }



        }
public void openDialog() {
new TTFancyGifDialog.Builder(InsertActivity.this)
            .setTitle(getString(R.string.dialog_txt_title))
            .setMessage(getString(R.string.dialog_txt_message))
            .setPositiveBtnText(getString(R.string.dialog_txt_ok))
            .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText(getString(R.string.dialog_txt_no))
            .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.warning)    //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
        @Override
        public void OnClick() {
            //startActivity(new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            try {
                mInsertViewModel.delete(InsertAdapter.getItemStockAt(Itemid));
            }catch (Exception e){Log.e("######",e.getMessage());}
        //Toast.makeText(InsertActivity.this, "item deleted", Toast.LENGTH_SHORT).show();
            
            
        }
    })
            .OnNegativeClicked(new TTFancyGifDialogListener() {
        @Override
        public void OnClick() {
            //return to main page
            startActivity(new Intent(InsertActivity.this, InsertActivity.class));
            return;
        }
    })
            .build();
   }

   public void uploadData() {
       barCode=barcode_edit_txt.getText().toString();
       qty=qty_edit_txt.getText().toString();
       if(qty.isEmpty()||barCode.isEmpty()){
           Toast.makeText(getApplicationContext(),"Please fill all field",Toast.LENGTH_LONG).show();
       }else{
           new SendRequest().execute();
           Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
       }



/*
            Call<ItemModel> call = ApiClient.getINSTANCE().postData(itemModel);
            call.enqueue( new Callback<ItemModel>() {
                @Override
                public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                    progressDialog.dismiss();
              Log.d( "responseeee"  , "" + response.body().toString() );
              Log.d( "responseeee"  , "" + response.body().getBarcode() );

                }

                @Override
                public void onFailure(Call<ItemModel> call, Throwable t) {

                }
            } );*/


        }


@Override
public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.insert, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
        case R.id.delete_all_items:
        mInsertViewModel.deleteAllNotes();
        Toast.makeText(this, "All items deleted", Toast.LENGTH_SHORT).show();
        return true;
        case R.id.uplaod:
                uploadData();

                default:
        return super.onOptionsItemSelected(item);

        }
        }

}
class SendRequest extends AsyncTask<String, Void, String> {

    protected void onPreExecute(){}
    protected String doInBackground(String... arg0) {

        try{

            URL url = new URL("https://script.google.com/macros/s/AKfycbwZy9u_eNqGwjMZTmC5-JiPsN1hZ9ut1v6gkVZRomCXVZwMUMSr/exec");
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("qty",InsertActivity.qty);
            postDataParams.put("barcode",InsertActivity.barCode);
            Log.e("params",postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}