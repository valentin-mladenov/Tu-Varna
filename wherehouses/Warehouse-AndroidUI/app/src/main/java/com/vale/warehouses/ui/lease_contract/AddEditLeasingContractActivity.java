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
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.Warehouse;
import com.vale.warehouses.service.view_model.LeaseRequestViewModel;
import com.vale.warehouses.service.view_model.LeasingContractViewModel;
import com.vale.warehouses.service.view_model.SaleAgentViewModel;
import com.vale.warehouses.service.view_model.TenantViewModel;
import com.vale.warehouses.service.view_model.WarehouseViewModel;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddEditLeasingContractActivity extends AppCompatActivity {
    public static final String LEASE_CONTRACT_ID = "LEASE_CONTRACT_ID";

    private WarehouseViewModel warehouseViewModel;
    private LeasingContractViewModel leaseContractViewModel;
    private TenantViewModel tenantViewModel;
    private LeaseRequestViewModel leaseRequestViewModel;
    private LeasingContract leaseContract;
    private TextInputLayout editTextLeasedAt, editTextLeasedTill, editTextTotalPrice;

    private Spinner editSpinnerWarehouse, editSpinnerTenant, editSpinnerLeaseRequests;

    private ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leasing_contract_activity_add_edit);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        categories = new ArrayList<>(Arrays.asList(Category.values()));

        editTextLeasedAt = findViewById(R.id.edit_text_leased_at);
        editTextLeasedAt.addTextChangedListener();

        editTextLeasedTill = findViewById(R.id.edit_text_leased_till);
        editTextTotalPrice = findViewById(R.id.edit_text_total_price);

        editSpinnerWarehouse = findViewById(R.id.spinner_warehouses);
        editSpinnerWarehouse.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<Warehouse>()));

        editSpinnerTenant = findViewById(R.id.spinner_tenants);
        editSpinnerTenant.setAdapter(new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_2, new ArrayList<Tenant>()));

        editSpinnerLeaseRequests = findViewById(R.id.spinner_lease_requests);
        editSpinnerLeaseRequests.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_single_choice, new ArrayList<LeaseRequest>()));

        leaseContract = new LeasingContract();

        // LeaseRequest a = (LeaseRequest) editSpinnerLeaseRequests.getSelectedItem();


        final AddEditLeasingContractActivity that = this;

        buildViewModels();



        if (getIntent().hasExtra(LEASE_CONTRACT_ID)) {
            setTitle(getString(R.string.edit));

            Long leaseContractId = Objects.requireNonNull(getIntent().getExtras()).getLong(LEASE_CONTRACT_ID);

            leaseContractViewModel.getOne(leaseContractId).observe(that, new Observer<LeasingContract>() {
                @Override
                public void onChanged(@Nullable LeasingContract leaseContractRes) {
                    leaseContract = leaseContractRes;

                    editTextLeasedAt.getEditText().setText(leaseContract.getLeasedAt().toString());
                    editTextLeasedTill.getEditText().setText(leaseContract.getLeasedTill().toString());

                    String months = String.valueOf(ChronoUnit.MONTHS.between(leaseContract.getLeasedAt(), leaseContract.getLeasedTill()));
                    BigDecimal totalPrice = leaseContract.getWarehouse().getPricePerMonth().multiply(new BigDecimal(months));

                    editTextTotalPrice.getEditText().setText(totalPrice.toString());
                }
            });
        } else {
            setTitle(getString(R.string.add));
            leaseContract.setOwner(AppRequestQueue.getToken().getUser().getRelatedOwner());
        }
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

        leaseRequestViewModel = new ViewModelProvider(this).get(LeaseRequestViewModel.class);

        warehouseViewModel= new ViewModelProvider(this).get(WarehouseViewModel.class);

        tenantViewModel = new ViewModelProvider(this).get(TenantViewModel.class);
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
