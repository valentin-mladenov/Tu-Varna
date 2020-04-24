package com.vale.warehouses.ui.warehouses;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Category;
import com.vale.warehouses.service.model.Owner;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.Warehouse;
import com.vale.warehouses.service.model.WarehouseType;
import com.vale.warehouses.service.view_model.SaleAgentViewModel;
import com.vale.warehouses.service.view_model.WarehouseViewModel;
import com.vale.warehouses.ui.warehouses.SaleAgentMultiSelectionSpinner;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddEditWarehouseActivity extends AppCompatActivity {
    public static final String USER_ID = "USER_ID";

    private SaleAgentViewModel saleAgentViewModel;
    private WarehouseViewModel warehouseViewModel;
    private SaleAgentMultiSelectionSpinner saleAgentSpinner;
    private Warehouse warehouse;
    private TextInputLayout editTextAddress,
                            editTextWidth,
                            editTextHeight,
                            editTextLength;

    private Spinner editSpinnerType, editSpinnerCategory;

    private ArrayList<Category> categories;
    private ArrayList<WarehouseType> warehouseTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warehouse_activity_add_edit);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        categories = new ArrayList<>(Arrays.asList(Category.values()));
        warehouseTypes = new ArrayList<>(Arrays.asList(WarehouseType.values()));

        editTextAddress = findViewById(R.id.edit_text_address);
        editTextWidth = findViewById(R.id.edit_text_width);
        editTextHeight = findViewById(R.id.edit_text_height);
        editTextLength = findViewById(R.id.edit_text_length);

        editSpinnerType = findViewById(R.id.spinner_types);
        editSpinnerType.setAdapter(new ArrayAdapter<>(
            this, android.R.layout.simple_list_item_1, WarehouseType.values()));

        ArrayAdapter<Category> a = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Category.values());


        editSpinnerCategory = findViewById(R.id.spinner_category);
        editSpinnerCategory.setAdapter(new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, Category.values()));

        saleAgentSpinner = findViewById(R.id.sale_agent_spinner);
        saleAgentSpinner.getSelection().observe(this, new Observer<Set<SaleAgent>>() {
            @Override
            public void onChanged(Set<SaleAgent> agents) {
                warehouse.setSaleAgents(agents);
            }
        });

        warehouse = new Warehouse();

        final AddEditWarehouseActivity that = this;

        buildViewModels();

        saleAgentViewModel.getAllSaleAgents().observe(this, new Observer<List<SaleAgent>>() {
            @Override
            public void onChanged(@Nullable List<SaleAgent> saleAgents) {
                saleAgentSpinner.setSaleAgents(saleAgents);

                if (getIntent().hasExtra(USER_ID)) {
                    setTitle(getString(R.string.edit));

                    Long warehouseId = getIntent().getExtras().getLong(USER_ID);

                    warehouseViewModel.getOne(warehouseId).observe(that, new Observer<Warehouse>() {
                        @Override
                        public void onChanged(@Nullable Warehouse warehouseRes) {
//                            warehouse.set(warehouseRes.getEmail());
//                            warehouse.setId(warehouseRes.getId());
//                            warehouse.setWarehouseName(warehouseRes.getWarehouseName());
//                            warehouse.setSaleAgents(warehouseRes.getSaleAgents());
//                            warehouse.setRelatedSaleAgent(warehouseRes.getRelatedSaleAgent());
//                            warehouse.setRelatedOwner(warehouseRes.getRelatedOwner());
//                            warehouse.setRelatedTenant(warehouseRes.getRelatedTenant());
                            warehouse = warehouseRes;

                            // TODO price per month
                            editTextAddress.getEditText().setText(warehouse.getAddress());
                            editTextHeight.getEditText().setText(String.valueOf(warehouse.getHeight()));
                            editTextWidth.getEditText().setText(String.valueOf(warehouse.getWidth()));
                            editTextLength.getEditText().setText(String.valueOf(warehouse.getLength()));

                            editSpinnerCategory.setSelection(categories.indexOf(warehouse.getCategory()));
                            editSpinnerType.setSelection(warehouseTypes.indexOf(warehouse.getType()));

                            saleAgentSpinner.setSelection(warehouse.getSaleAgents());
                        }
                    });
                } else {
                    setTitle(getString(R.string.add));

                    saleAgentSpinner.setSelection(warehouse.getSaleAgents());
                }
            }
        });
    }

    private void saveWarehouse() {
        try {
            warehouse.setAddress(editTextAddress.getEditText().getText().toString());
            warehouse.setHeight(Double.parseDouble(editTextHeight.getEditText().getText().toString()));
            warehouse.setWidth(Double.parseDouble(editTextWidth.getEditText().getText().toString()));
            warehouse.setLength(Double.parseDouble(editTextLength.getEditText().getText().toString()));

            warehouse.setCategory(Category.valueOf(editSpinnerCategory.getSelectedItem().toString()));
            warehouse.setType(WarehouseType.valueOf(editSpinnerType.getSelectedItem().toString()));

            if (getIntent().hasExtra(USER_ID)) {
                warehouseViewModel.update(warehouse).observe(this, new Observer<Warehouse>() {
                    @Override
                    public void onChanged(Warehouse updatedWarehouse) {
                        Intent intent = new Intent();
                        intent.putExtras(getIntent());

                        setResult(RESULT_OK);

                        finish();
                    }
                });

                Toast.makeText(this, R.string.warehouse_updated, Toast.LENGTH_SHORT).show();
            } else {
                warehouseViewModel.insertData(warehouse).observe(this, new Observer<Warehouse>() {
                    @Override
                    public void onChanged(@Nullable Warehouse insertedWarehouse) {
                        assert insertedWarehouse != null;

                        Intent intent = new Intent();
                        intent.putExtras(getIntent());

                        setResult(RESULT_OK);

                        finish();
                    }
                });

                Toast.makeText(this, R.string.warehouse_created, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private void buildViewModels() {
        warehouseViewModel = new ViewModelProvider(this).get(WarehouseViewModel.class);

        saleAgentViewModel = new ViewModelProvider(this).get(SaleAgentViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                saveWarehouse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
