package com.example.googlesheet.UI.ShowActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googlesheet.Pojo.ItemModel;
import com.example.googlesheet.R;

import java.util.ArrayList;
import java.util.List;

class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {
    private ShowAdapter.OnItemClickListener listener;

    private static List<ItemModel> stockItemslist = new ArrayList<>();

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.stock_item, parent, false );
        return new ShowAdapter.ShowViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        ItemModel currentItem = stockItemslist.get( position );

           holder.time.setText(String.valueOf(currentItem.getTime()));
           holder.qty.setText(String.valueOf(currentItem.getQty()));
          holder.barcode.setText(String.valueOf(currentItem.getBarcode()));


    }


    @Override
    public int getItemCount() {
        return stockItemslist.size();
    }
    public void setStockItemslist(  List<ItemModel> itemslist) {
        this.stockItemslist = itemslist;
        notifyDataSetChanged();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView qty;
        private TextView barcode;

        public ShowViewHolder(@NonNull View itemView) {
            super( itemView );
            //status = itemView.findViewById( R.id.status_item);
            time = itemView.findViewById( R.id.time_item );
            qty = itemView.findViewById( R.id.qty_item );
            barcode = itemView.findViewById( R.id.item_barcode );
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick( stockItemslist.get( position ) );
                    }
                }
            } );
        }


    }

    public interface OnItemClickListener {
        void onItemClick(ItemModel itemModel);
    }
    public void setOnItemClickListener(ShowAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}

