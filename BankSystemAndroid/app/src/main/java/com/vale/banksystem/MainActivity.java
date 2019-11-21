package com.vale.banksystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vale.banksystem.DB.AppDatabase;
import com.vale.banksystem.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    public static AppDatabase appDatabase;
    public static FragmentManager fm;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.getInstance(this);

        fm = getSupportFragmentManager();

        if (findViewById(R.id.frame) != null) {
            if (savedInstanceState != null) {
                return;
            }

            fm.beginTransaction().add(R.id.frame, new MainFragment()).commit();
        }
    }
}
