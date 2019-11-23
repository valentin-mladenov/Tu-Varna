package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vale.banksystem.AccountListAdapter;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

import java.util.List;

public class ReadAccountsFragment extends Fragment {
    private AccountListAdapter accListAdapter;
    private RecyclerView recyclerView;

    public ReadAccountsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_accounts, container, false);

        recyclerView = rootView.findViewById(R.id.account_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL));

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.appDatabase.accessInterface().getAll().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable List<Account> accounts) {
                if(accounts.size() > 0) {
                    if (accListAdapter == null) {
                        accListAdapter = new AccountListAdapter(accounts);
                    }

                    recyclerView.setAdapter(accListAdapter);
                }
            }
        });
    }
}
