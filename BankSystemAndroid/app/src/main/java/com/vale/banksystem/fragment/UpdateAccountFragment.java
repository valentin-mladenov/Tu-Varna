package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

import java.util.List;
import java.util.UUID;

public class UpdateAccountFragment extends Fragment {
    TextInputLayout id, balance, interest;
    Button update_account, delete_account;

    public UpdateAccountFragment() {
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
        update_account = view.findViewById(R.id.update);

        MainActivity.appDatabase.accessInterface().getAccount(1).observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
//                id = view.findViewById(R.id.id);
//                interest = view.findViewById(R.id.interest);
//                balance = view.findViewById(R.id.balance);
//                update_account = view.findViewById(R.id.update);

                if(account != null) {
                    id.getEditText().setText(String.format("%d", account.getId()));
                    balance.getEditText().setText(account.getBalance().toString());
                    interest.getEditText().setText(account.getInterest().toString());
                }
            }
        });

        update_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                account.setId(Integer.parseInt(id.getEditText().getText().toString()));
                account.setBalance(Double.parseDouble(balance.getEditText().getText().toString()));
                account.setInterest(Double.parseDouble(interest.getEditText().getText().toString()));

                MainActivity.appDatabase.accessInterface().updateAccount(account);

                Toast.makeText(getActivity(), "data updated successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
