package com.vale.banksystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.banksystem.DB.Account;

import java.util.List;

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
        Account account = getItem(position);

        holder.acc_id.getEditText().setText(String.format("%d", account.getId()));
        holder.balance.getEditText().setText(account.getBalance().toString());
        holder.interest.getEditText().setText(account.getInterest().toString());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public Account getItem(int position) {
        return accounts.get(position);
    }

//    public void addTasks(List<Account> newNotes) {
//        NoteDiffUtil noteDiffUtil = new NoteDiffUtil(notes, newNotes);
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(noteDiffUtil);
//        notes.clear();
//        notes.addAll(newNotes);
//        diffResult.dispatchUpdatesTo(this);
//    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextInputLayout acc_id, balance, interest;

        CustomViewHolder(View itemView) {
            super(itemView);

            acc_id = itemView.findViewById(R.id.acc_id);
            balance = itemView.findViewById(R.id.balance);
            interest = itemView.findViewById(R.id.interest);

        }
    }
}
