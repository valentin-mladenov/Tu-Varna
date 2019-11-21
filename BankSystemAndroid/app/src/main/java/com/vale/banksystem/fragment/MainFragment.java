package com.vale.banksystem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;

import com.vale.banksystem.MainActivity;
import com.vale.banksystem.R;

public class MainFragment  extends Fragment {
    Button add, update, delete, view;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add = view.findViewById(R.id.add);
        //delete = view.findViewById(R.id.delete);
        update = view.findViewById(R.id.update);
        // this.view = view.findViewById(R.id.view);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new AddAccountFragment()).addToBackStack(null).commit();
            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.fm.beginTransaction().replace(R.id.output, new DeleteAccountFragment()).addToBackStack(null).commit();
//
//            }
//        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new UpdateAccountFragment()).addToBackStack(null).commit();

            }
        });

//        this.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.fm.beginTransaction().replace(R.id.output, new ReadAccountsFragment()).addToBackStack(null).commit();
//
//            }
//        });
    }
}