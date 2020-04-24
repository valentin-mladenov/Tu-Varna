package com.vale.warehouses.ui.warehouses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.Warehouse;
import com.vale.warehouses.service.view_model.WarehouseViewModel;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.warehouses.AddEditWarehouseActivity;
import com.vale.warehouses.ui.warehouses.WarehouseAdapter;

import java.util.List;
import java.util.Objects;

public class WarehouseListActivity extends AppCompatActivity {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    private WarehouseAdapter warehouseAdapter;
    private WarehouseViewModel warehouseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warehouse_main_activity);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_back);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_warehouse);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WarehouseListActivity.this, AddEditWarehouseActivity.class);
                intent.putExtras(getIntent());
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.warehouses_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        warehouseAdapter = new WarehouseAdapter();
        recyclerView.setAdapter(warehouseAdapter);

        warehouseViewModel = new ViewModelProvider(this).get(WarehouseViewModel.class);
        getAllWarehouses();

        warehouseAdapter.setOnItemClickListener(new WarehouseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Warehouse warehouse) {
                Intent intent = new Intent(WarehouseListActivity.this, AddEditWarehouseActivity.class);

                intent.putExtra("USER_ID", warehouse.getId());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getAllWarehouses();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_now:
                logoutNow();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutNow() {
        String goodbye = getString(R.string.goodbye) + AppRequestQueue.getToken().getUser().getEmail();
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(WarehouseListActivity.this, LoginActivity.class);
        startActivity(intent);

        setResult(Activity.RESULT_OK);

        //Complete and destroy LoggedIn activity once successful
        finish();
    }

    private void getAllWarehouses() {
        warehouseViewModel.getAllWarehouses().observe(this, new Observer<List<Warehouse>>() {
            @Override
            public void onChanged(@Nullable List<Warehouse> warehouses) {
                warehouseAdapter.submitList(warehouses);
            }
        });
    }
}
