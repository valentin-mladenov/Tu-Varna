package com.vale.banksystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.fragment.UpdateAccountFragment;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.CustomViewHolder> {
    private List<Account> accounts;
    public AccountListAdapter(List<Account> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_list_item, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final Account account = getItem(position);

        Objects.requireNonNull(holder.acc_id.getEditText()).setText(String.format(Locale.getDefault(), "%d", account.getId()));
        Objects.requireNonNull(holder.balance.getEditText()).setText(String.format(Locale.getDefault(), "%1$,.2f", account.getBalance()));
        Objects.requireNonNull(holder.interest.getEditText()).setText(String.format(Locale.getDefault(), "%1$,.2f", account.getInterest()));
        Objects.requireNonNull(holder.client_name.getEditText()).setText(account.getName());

        holder.unlockedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment(account.getId())).addToBackStack(null).commit();

            }
        });

        holder.acc_id.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment(account.getId())).addToBackStack(null).commit();
            }
        });

        holder.client_name.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment(account.getId())).addToBackStack(null).commit();
            }
        });

        holder.balance.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment(account.getId())).addToBackStack(null).commit();

            }
        });

        holder.interest.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment(account.getId())).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public Account getItem(int position) {
        return accounts.get(position);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextInputLayout acc_id, balance, interest, client_name;
        private FrameLayout unlockedView;

        CustomViewHolder(View itemView) {
            super(itemView);

            acc_id = itemView.findViewById(R.id.acc_id);
            balance = itemView.findViewById(R.id.balance);
            interest = itemView.findViewById(R.id.interest);
            client_name = itemView.findViewById(R.id.client_name);
            unlockedView = itemView.findViewById(R.id.view_unlocked);
        }
    }
}
