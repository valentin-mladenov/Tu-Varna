package com.vale.warehouses.ui.warehouses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Warehouse;

import java.util.Locale;

import static java.lang.String.*;

public class WarehouseAdapter extends ListAdapter<Warehouse, WarehouseAdapter.WarehouseHolder> {
    private OnItemClickListener listener;

    private static final DiffUtil.ItemCallback<Warehouse> DIFF_CALLBACK = new DiffUtil.ItemCallback<Warehouse>() {
        @Override
        public boolean areItemsTheSame(Warehouse oldItem, Warehouse newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Warehouse oldItem, Warehouse newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getAddress().equals(newItem.getAddress()) &&
                    oldItem.getVolume() == newItem.getVolume();
        }
    };

    public WarehouseAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WarehouseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.warehouse_item, parent, false);
        return new WarehouseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseHolder holder, int position) {
        Warehouse current = getWarehouseAt(position);
        holder.textViewPricePerMonth.setText(String.valueOf(current.getPricePerMonth()));
        holder.textViewAddress.setText(current.getAddress());

        String addData = format(Locale.getDefault(),
                "%s, %s, %.2f m², %.2f m³",
                current.getCategory(),
                current.getType(),
                current.getArea(),
                current.getVolume()
        );
        holder.textViewAdditionalData.setText(addData);
    }


    public Warehouse getWarehouseAt(int position) {
        return getItem(position);
    }

    class WarehouseHolder extends RecyclerView.ViewHolder {
        private TextView textViewPricePerMonth, textViewAdditionalData, textViewAddress;

        public WarehouseHolder(View itemView) {
            super(itemView);
            textViewPricePerMonth = itemView.findViewById(R.id.text_view_price);
            textViewAdditionalData = itemView.findViewById(R.id.text_view_additional_data);
            textViewAddress = itemView.findViewById(R.id.text_view_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getWarehouseAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Warehouse Warehouse);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
