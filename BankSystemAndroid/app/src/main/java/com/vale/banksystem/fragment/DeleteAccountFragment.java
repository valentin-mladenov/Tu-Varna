package com.vale.banksystem.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import android.view.*;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.vale.banksystem.DB.Account;
import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

public class DeleteAccountFragment extends Fragment {
    EditText editText;
    Button delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.account_id);
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account acc = new Account();
                acc.setId(Integer.parseInt(editText.getText().toString()));
                MainActivity.appDatabase.accessInterface().deleteAccount(acc);
                Toast.makeText(getActivity(), "Data deleted successfully" , Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });
    }
}