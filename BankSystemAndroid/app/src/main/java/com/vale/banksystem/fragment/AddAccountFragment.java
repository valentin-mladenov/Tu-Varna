package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.DB.AppDatabase;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

public class AddAccountFragment  extends Fragment {
    TextInputLayout balance, interest, client_name;
    Button add_account;

    public AddAccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        interest = view.findViewById(R.id.interest);
        balance = view.findViewById(R.id.balance);
        client_name = view.findViewById(R.id.client_name);
        add_account = view.findViewById(R.id.add_account);

        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setName(client_name.getEditText().getText().toString());
                account.setBalance(Double.parseDouble(balance.getEditText().getText().toString()));
                account.setInterest(Double.parseDouble(interest.getEditText().getText().toString()));

                MainActivity.appDatabase.accessInterface().addAccount(account);
                Toast.makeText(getActivity(), R.string.acc_added , Toast.LENGTH_SHORT).show();

                MainActivity.fm.beginTransaction().replace(R.id.frame, new MainFragment()).addToBackStack(null).commit();
            }
        });
    }
}