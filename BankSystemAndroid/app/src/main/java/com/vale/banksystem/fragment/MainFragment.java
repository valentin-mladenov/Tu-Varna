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
    Button add, back;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add = view.findViewById(R.id.add);
        back = view.findViewById(R.id.back);

        add.setVisibility(View.VISIBLE);
        back.setVisibility(View.GONE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new AddAccountFragment()).addToBackStack(null).commit();
                add.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.output, new ReadAccountsFragment()).addToBackStack(null).commit();
                add.setVisibility(View.VISIBLE);
                back.setVisibility(View.GONE);

            }
        });
    }

    public void toggleAddButton() {
        add.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}