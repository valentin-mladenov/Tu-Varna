package com.vale.warehouses.ui.lease_request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vale.warehouses.R;
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.WarehouseType;
import com.vale.warehouses.service.view_model.LeaseRequestViewModel;

import java.util.Objects;

public class CreateLeaseRequest extends AppCompatActivity {
    private TextInputLayout
            editTextFirstName,
            editTextLastName,
            editTextAddress,
            editTextUnique,
            editTextPhone;

    private Spinner editSpinnerType;

    private LeaseRequestViewModel leaseRequestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lease_request_activity_create);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_back);

        editTextAddress = findViewById(R.id.edit_text_address);
        editTextPhone = findViewById(R.id.edit_text_phone_number);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextUnique = findViewById(R.id.edit_text_unique);

        editSpinnerType = findViewById(R.id.spinner_types);
        editSpinnerType.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, WarehouseType.values()));

        leaseRequestViewModel = new ViewModelProvider(this).get(LeaseRequestViewModel.class);

    }

    private void saveLeaseRequest() {
        if (editTextUnique.getEditText().getText().toString().trim().isEmpty() ) {
            Toast.makeText(this, R.string.warehouse_created, Toast.LENGTH_SHORT).show();
            return;
        }

        Tenant tenant = new Tenant();
        tenant.setPhoneNumber(editTextPhone.getEditText().getText().toString());
        tenant.setAddress(editTextAddress.getEditText().getText().toString());
        tenant.setUniqueCode(editTextUnique.getEditText().getText().toString());
        tenant.setLastName(editTextLastName.getEditText().getText().toString());
        tenant.setFirstName(editTextFirstName.getEditText().getText().toString());

        LeaseRequest leaseRequest = new LeaseRequest();
        leaseRequest.setWarehouseType(WarehouseType.valueOf(editSpinnerType.getSelectedItem().toString()));
        leaseRequest.setTenant(tenant);

        leaseRequestViewModel.insertData(leaseRequest).observe(this, leaseRequestRes -> {
            assert leaseRequestRes != null;

            Toast.makeText(this, R.string.warehouse_created, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtras(getIntent());

            setResult(RESULT_OK);

            finish();
        });
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
                saveLeaseRequest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
