package com.vale.warehouses.ui.lease_contract;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.LeasingContract;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static java.lang.String.format;

public class LeaseContractAdapter extends ListAdapter<LeasingContract, LeaseContractAdapter.LeasingContractHolder> {
    private OnItemClickListener listener;

    private static final DiffUtil.ItemCallback<LeasingContract> DIFF_CALLBACK = new DiffUtil.ItemCallback<LeasingContract>() {
        @Override
        public boolean areItemsTheSame(LeasingContract oldItem, LeasingContract newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(LeasingContract oldItem, LeasingContract newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getWarehouse().getAddress().equals(newItem.getWarehouse().getAddress()) &&
                    oldItem.getWarehouse().getVolume() == newItem.getWarehouse().getVolume();
        }
    };

    public LeaseContractAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public LeasingContractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leasing_contract_item, parent, false);
        return new LeasingContractHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeasingContractHolder holder, int position) {
        LeasingContract current = getLeasingContractAt(position);
        holder.textViewPricePerMonth.setText(String.valueOf(current.getWarehouse().getPricePerMonth()));
        holder.textViewAddress.setText(current.getWarehouse().getAddress());

        String months = String.valueOf(ChronoUnit.MONTHS.between(current.getLeasedAt(), current.getLeasedTill()));

        BigDecimal totalPrice = current.getWarehouse().getPricePerMonth().multiply(new BigDecimal(months));

        String addData = format(Locale.getDefault(),
                "Months leased %s, till %s, total price %.2f",
                months,
                current.getLeasedTill().format(DateTimeFormatter.ofPattern("dd MMM YYYY")),
                totalPrice
        );
        holder.textViewAdditionalData.setText(addData);
    }


    public LeasingContract getLeasingContractAt(int position) {
        return getItem(position);
    }

    class LeasingContractHolder extends RecyclerView.ViewHolder {
        private TextView textViewPricePerMonth, textViewAdditionalData, textViewAddress;

        public LeasingContractHolder(View itemView) {
            super(itemView);
            textViewPricePerMonth = itemView.findViewById(R.id.text_view_price);
            textViewAdditionalData = itemView.findViewById(R.id.text_view_additional_data);
            textViewAddress = itemView.findViewById(R.id.text_view_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getLeasingContractAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LeasingContract LeasingContract);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
