package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.DB.AppDatabase;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

public class AddAccountFragment  extends Fragment {
    EditText id, balance, interest;
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

        id = view.findViewById(R.id.id);
        interest = view.findViewById(R.id.interest);
        balance = view.findViewById(R.id.balance);
        add_account = view.findViewById(R.id.add_account);

        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setId(Integer.parseInt(id.getText().toString()));
                account.setBalance(Double.parseDouble(balance.getText().toString()));
                account.setInterest(Double.parseDouble(interest.getText().toString()));

                AppDatabase appDatabase = MainActivity.appDatabase;
                appDatabase.accessInterface().addAccount(account);
                Toast.makeText(getActivity(), "Account added successfully" , Toast.LENGTH_SHORT).show();

                id.setText("");
                balance.setText("");
                interest.setText("");
            }
        });
    }
}