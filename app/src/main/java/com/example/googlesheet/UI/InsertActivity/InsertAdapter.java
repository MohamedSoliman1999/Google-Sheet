package com.example.googlesheet.UI.InsertActivity;

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


class InsertAdapter extends RecyclerView.Adapter<InsertAdapter.InsertViewHolder>{
    private OnItemClickListener listener;

    private static List<ItemModel> stockItemslist = new ArrayList<>();

    @NonNull
    @Override
    public InsertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_item, parent, false);
        return new InsertViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InsertViewHolder holder, int position) {
        ItemModel currentItem = stockItemslist.get(position);

//        holder.status.setText(String.valueOf(currentItem.isSyncStatus()));
        holder.time.setText(String.valueOf(currentItem.getTime()));
//        holder.name.setText(currentItem.getItemName());
        holder.qty.setText(String.valueOf(currentItem.getQty()));
        holder.barcode.setText(String.valueOf(currentItem.getBarcode()));


    }

    @Override
    public int getItemCount() {
        return stockItemslist.size();
    }

    public void setStockItemslist(List<ItemModel> itemslist) {
        this.stockItemslist = itemslist;
        notifyDataSetChanged();
    }
    public static ItemModel getItemStockAt(int position) {
        return stockItemslist.get(position);
    }
    class InsertViewHolder extends RecyclerView.ViewHolder{
        private TextView status;
        private TextView time;
        private TextView qty;
        private TextView name;
        private TextView barcode;


        public InsertViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById( R.id.status_item);
            time = itemView.findViewById(R.id.time_item);
            qty = itemView.findViewById(R.id.qty_item);
            //name = itemView.findViewById(R.id.name_item);
            barcode = itemView.findViewById(R.id.item_barcode);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(stockItemslist.get(position));
                    }
                }
            });
        }


    }
    public interface OnItemClickListener {
        void onItemClick(ItemModel itemModel);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}