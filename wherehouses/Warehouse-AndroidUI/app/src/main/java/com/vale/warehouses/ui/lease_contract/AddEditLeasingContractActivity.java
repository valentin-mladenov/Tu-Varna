package com.vale.warehouses.ui.lease_contract;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.Category;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.view_model.LeasingContractViewModel;
import com.vale.warehouses.service.view_model.SaleAgentViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddEditLeasingContractActivity extends AppCompatActivity {
    public static final String LEASE_CONTRACT_ID = "LEASE_CONTRACT_ID";

    private SaleAgentViewModel saleAgentViewModel;
    private LeasingContractViewModel leaseContractViewModel;
    private LeasingContract leaseContract;
    private TextInputLayout editTextAddress,
                            editTextWidth,
                            editTextHeight,
                            editTextLength,
                            editTextPricePerMonth;

    private Spinner editSpinnerType, editSpinnerCategory;

    private ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leasing_contract_activity_add_edit);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        categories = new ArrayList<>(Arrays.asList(Category.values()));

        editTextAddress = findViewById(R.id.edit_text_address);
        editTextWidth = findViewById(R.id.edit_text_width);
        editTextHeight = findViewById(R.id.edit_text_height);
        editTextLength = findViewById(R.id.edit_text_length);
        editTextPricePerMonth = findViewById(R.id.edit_text_price_per_month);

        editSpinnerType = findViewById(R.id.spinner_types);
//        editSpinnerType.setAdapter(new ArrayAdapter<>(
//            this, android.R.layout.simple_list_item_1, LeasingContractType.values()));

        ArrayAdapter<Category> a = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Category.values());


        editSpinnerCategory = findViewById(R.id.spinner_category);
        editSpinnerCategory.setAdapter(new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, Category.values()));

        leaseContract = new LeasingContract();

        if (AppRequestQueue.getToken().getUser().getRelatedSaleAgent() != null) {
            editTextAddress.setEnabled(false);
            editTextWidth.setEnabled(false);
            editTextHeight.setEnabled(false);
            editTextLength.setEnabled(false);
            editTextPricePerMonth.setEnabled(false);
            editSpinnerType.setEnabled(false);
            editSpinnerCategory.setEnabled(false);
//            saleAgentSpinner.setEnabled(false);
            findViewById(R.id.save_item).setEnabled(false);
        }

//        saleAgentSpinner = findViewById(R.id.sale_agent_spinner);
//        saleAgentSpinner.getSelection().observe(this, new Observer<Set<SaleAgent>>() {
//            @Override
//            public void onChanged(Set<SaleAgent> agents) {
//                leaseContract.setSaleAgents(agents);
//            }
//        });


        final AddEditLeasingContractActivity that = this;

        buildViewModels();

        saleAgentViewModel.getAllSaleAgents().observe(this, new Observer<List<SaleAgent>>() {
            @Override
            public void onChanged(@Nullable List<SaleAgent> saleAgents) {
                //saleAgentSpinner.setSaleAgents(saleAgents);

                if (getIntent().hasExtra(LEASE_CONTRACT_ID)) {
                    setTitle(getString(R.string.edit));

                    Long leaseContractId = getIntent().getExtras().getLong(LEASE_CONTRACT_ID);

                    leaseContractViewModel.getOne(leaseContractId).observe(that, new Observer<LeasingContract>() {
                        @Override
                        public void onChanged(@Nullable LeasingContract leaseContractRes) {
                            // leaseContract = leaseContractRes;

//                            editTextAddress.getEditText().setText(leaseContract.getAddress());
//                            editTextHeight.getEditText().setText(String.valueOf(leaseContract.getHeight()));
//                            editTextWidth.getEditText().setText(String.valueOf(leaseContract.getWidth()));
//                            editTextLength.getEditText().setText(String.valueOf(leaseContract.getLength()));
//                            editTextPricePerMonth.getEditText().setText(String.valueOf(leaseContract.getPricePerMonth()));
//
//                            editSpinnerCategory.setSelection(categories.indexOf(leaseContract.getCategory()));
//                            editSpinnerType.setSelection(leaseContractTypes.indexOf(leaseContract.getType()));
//
//                            saleAgentSpinner.setSelection(leaseContract.getSaleAgents());
                        }
                    });
                } else {
                    setTitle(getString(R.string.add));
                    leaseContract.setOwner(AppRequestQueue.getToken().getUser().getRelatedOwner());
                }
            }
        });
    }

    private void saveLeasingContract() {
        try {
//            leaseContract.setAddress(editTextAddress.getEditText().getText().toString());
//            leaseContract.setHeight(Double.parseDouble(editTextHeight.getEditText().getText().toString()));
//            leaseContract.setWidth(Double.parseDouble(editTextWidth.getEditText().getText().toString()));
//            leaseContract.setLength(Double.parseDouble(editTextLength.getEditText().getText().toString()));
//            leaseContract.setPricePerMonth(new BigDecimal(editTextPricePerMonth.getEditText().getText().toString()));
//
//            leaseContract.setCategory(Category.valueOf(editSpinnerCategory.getSelectedItem().toString()));
//            leaseContract.setType(LeasingContractType.valueOf(editSpinnerType.getSelectedItem().toString()));

//            if (getIntent().hasExtra(LEASE_CONTRACT_ID)) {
//                leaseContractViewModel.update(leaseContract).observe(this, new Observer<LeasingContract>() {
//                    @Override
//                    public void onChanged(LeasingContract updatedLeasingContract) {
//                        Intent intent = new Intent();
//                        intent.putExtras(getIntent());
//
//                        setResult(RESULT_OK);
//
//                        finish();
//                    }
//                });
//
//                Toast.makeText(this, R.string.leaseContract_updated, Toast.LENGTH_SHORT).show();
//            } else {
//                leaseContractViewModel.insertData(leaseContract).observe(this, new Observer<LeasingContract>() {
//                    @Override
//                    public void onChanged(@Nullable LeasingContract insertedLeasingContract) {
//                        assert insertedLeasingContract != null;
//
//                        Intent intent = new Intent();
//                        intent.putExtras(getIntent());
//
//                        setResult(RESULT_OK);
//
//                        finish();
//                    }
//                });
//
//                Toast.makeText(this, R.string.leaseContract_created, Toast.LENGTH_SHORT).show();
//            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private void buildViewModels() {
        leaseContractViewModel = new ViewModelProvider(this).get(LeasingContractViewModel.class);

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
                saveLeasingContract();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
