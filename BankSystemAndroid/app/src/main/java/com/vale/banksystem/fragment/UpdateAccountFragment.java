package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

public class UpdateAccountFragment extends Fragment {
    TextInputLayout id, balance, interest, client_name;
    Button update_account, delete_account, back;
    private int accountId;

    public UpdateAccountFragment(int accountId) {
        this.accountId = accountId;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id = view.findViewById(R.id.acc_id);
        interest = view.findViewById(R.id.interest);
        balance = view.findViewById(R.id.balance);
        client_name = view.findViewById(R.id.client_name);
        update_account = view.findViewById(R.id.update);
        delete_account = view.findViewById(R.id.delete);
        back = view.findViewById(R.id.back);

        MainActivity.appDatabase.accessInterface().getAccount(this.accountId).observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
                if(account != null) {
                    id.getEditText().setText(String.format("%d", account.getId()));
                    balance.getEditText().setText(account.getBalance().toString());
                    interest.getEditText().setText(account.getInterest().toString());
                    client_name.getEditText().setText(account.getName());
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new ReadAccountsFragment()).addToBackStack(null).commit();
            }
        });

        update_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setId(Integer.parseInt(id.getEditText().getText().toString()));
                account.setBalance(Double.parseDouble(balance.getEditText().getText().toString()));
                account.setInterest(Double.parseDouble(interest.getEditText().getText().toString()));
                account.setName(client_name.getEditText().getText().toString());

                MainActivity.appDatabase.accessInterface().updateAccount(account);

                Toast.makeText(getActivity(), R.string.acc_updated, Toast.LENGTH_SHORT).show();

                MainActivity.fm.beginTransaction().replace(R.id.output, new ReadAccountsFragment()).addToBackStack(null).commit();
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setId(Integer.parseInt(id.getEditText().getText().toString()));
                account.setBalance(Double.parseDouble(balance.getEditText().getText().toString()));
                account.setInterest(Double.parseDouble(interest.getEditText().getText().toString()));
                account.setName(client_name.getEditText().getText().toString());

                MainActivity.appDatabase.accessInterface().deleteAccount(account);

                Toast.makeText(getActivity(), R.string.acc_deleted, Toast.LENGTH_SHORT).show();

                MainActivity.fm.beginTransaction().replace(R.id.output, new ReadAccountsFragment()).addToBackStack(null).commit();
            }
        });
    }
}
