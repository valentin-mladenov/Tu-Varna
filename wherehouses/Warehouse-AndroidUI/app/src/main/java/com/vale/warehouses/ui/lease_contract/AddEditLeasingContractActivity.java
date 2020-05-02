package com.vale.warehouses.ui.lease_contract;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.model.Warehouse;
import com.vale.warehouses.service.view_model.LeaseRequestViewModel;
import com.vale.warehouses.service.view_model.LeasingContractViewModel;
import com.vale.warehouses.service.view_model.TenantViewModel;
import com.vale.warehouses.service.view_model.WarehouseViewModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddEditLeasingContractActivity extends AppCompatActivity {
    public static final String LEASE_CONTRACT_ID = "LEASE_CONTRACT_ID";
    private DateTimeFormatter format;
    private AddEditLeasingContractActivity that = this;
    private WarehouseViewModel warehouseViewModel;
    private LeasingContractViewModel leaseContractViewModel;
    private TenantViewModel tenantViewModel;
    private LeaseRequestViewModel leaseRequestViewModel;
    private LeasingContract leaseContract;
    private TextInputEditText editTextLeasedAt, editTextLeasedTill;
    private TextInputLayout editTextTotalPrice, editTextMonths;
    private TextView textLeaseRequest;

    private Spinner editSpinnerWarehouse, editSpinnerTenant, editSpinnerLeaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leasing_contract_activity_add_edit);
        format = DateTimeFormatter.ofPattern(getString(R.string.date_format));

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        editTextLeasedAt = findViewById(R.id.edit_text_leased_at);
        editTextLeasedAt.setOnClickListener(getClickListener(this, editTextLeasedAt));

        editTextLeasedTill = findViewById(R.id.edit_text_leased_till);
        editTextLeasedTill.setOnClickListener(getClickListener(this, editTextLeasedTill));

        editTextTotalPrice = findViewById(R.id.edit_text_total_price);
        editTextMonths = findViewById(R.id.edit_text_total_months);

        editSpinnerWarehouse = findViewById(R.id.spinner_warehouses);
        editSpinnerWarehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Warehouse warehouse = (Warehouse)editSpinnerWarehouse.getSelectedItem();

                leaseContract.setWarehouse(warehouse);
                calculateTotalMonthsAndPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        editSpinnerTenant = findViewById(R.id.spinner_tenants);
        editSpinnerTenant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Tenant tenant = (Tenant)editSpinnerTenant.getSelectedItem();

                leaseContract.setTenant(tenant);
                getLeaseRequests(that, tenant.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        editSpinnerLeaseRequests = findViewById(R.id.spinner_lease_requests);
        editSpinnerLeaseRequests.setVisibility(View.GONE);
        editSpinnerLeaseRequests.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                LeaseRequest leaseRequest = (LeaseRequest)editSpinnerLeaseRequests.getSelectedItem();

                leaseContract.setLeaseRequest(leaseRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        textLeaseRequest = findViewById(R.id.text_lease_requests);
        textLeaseRequest.setVisibility(View.GONE);

        leaseContract = new LeasingContract();

        buildViewModels();

        if (getIntent().hasExtra(LEASE_CONTRACT_ID)) {
            setTitle(getString(R.string.edit));

            Long leaseContractId = Objects.requireNonNull(getIntent().getExtras()).getLong(LEASE_CONTRACT_ID);

            leaseContractViewModel.getOne(leaseContractId).observe(this, leaseContractRes -> {
                leaseContract = leaseContractRes;

                editTextLeasedAt.setText(leaseContract.getLeasedAt().format(format));
                editTextLeasedTill.setText(leaseContract.getLeasedTill().format(format));

                calculateTotalMonthsAndPrice();

                List<Warehouse> warehouses = new ArrayList<>();
                warehouses.add(leaseContract.getWarehouse());

                editSpinnerWarehouse.setAdapter(new ArrayAdapter<>(
                        that, android.R.layout.simple_list_item_1, warehouses));
                editSpinnerWarehouse.setSelection(0);
                editSpinnerWarehouse.setEnabled(false);

                List<Tenant> tenants = new ArrayList<>();
                tenants.add(leaseContract.getTenant());

                editSpinnerTenant.setAdapter(new ArrayAdapter<>(
                        that, android.R.layout.simple_list_item_1, tenants));
                editSpinnerTenant.setSelection(0);
                editSpinnerTenant.setEnabled(false);

                if (leaseContract.getLeaseRequest() != null) {
                    List<LeaseRequest> leaseRequests = new ArrayList<>();
                    leaseRequests.add(leaseContract.getLeaseRequest());

                    editSpinnerLeaseRequests.setAdapter(new ArrayAdapter<>(
                            that, android.R.layout.simple_list_item_1, leaseRequests));

                    editSpinnerLeaseRequests.setSelection(0);
                    editSpinnerLeaseRequests.setEnabled(false);
                    editSpinnerLeaseRequests.setVisibility(View.VISIBLE);
                    textLeaseRequest.setVisibility(View.VISIBLE);
                }
                else {
                    getLeaseRequests(that, leaseContract.getTenant().getId());
                }

                if (AppRequestQueue.getToken().getUser().getRelatedOwner() != null) {
                    setTitle(getString(R.string.inspect));

                    editTextLeasedAt.setEnabled(false);
                    editTextLeasedTill.setEnabled(false);
                }
            });
        } else {
            setTitle(getString(R.string.add));

            getTenants(this);
            getWarehouses(this);
        }
    }

    private void getWarehouses(final AddEditLeasingContractActivity that) {
        User loggedUser = AppRequestQueue.getToken().getUser();
        int rolePosition = (int) new ArrayList<>(loggedUser.getRoles()).get(0).getId();
        RoleType roleType = RoleType.values()[rolePosition - 1];

        warehouseViewModel.getAllWarehouses(roleType).observe(this, new Observer<List<Warehouse>>() {
            @Override
            public void onChanged(@Nullable List<Warehouse> warehouses) {
                editSpinnerWarehouse.setAdapter(new ArrayAdapter<>(
                        that, android.R.layout.simple_list_item_1, warehouses));

                leaseContract.setWarehouse(warehouses.get(0));
                leaseContract.setOwner(warehouses.get(0).getOwner());
            }
        });
    }

    private void getTenants(final AddEditLeasingContractActivity that) {
        User loggedUser = AppRequestQueue.getToken().getUser();
        int rolePosition = (int) new ArrayList<>(loggedUser.getRoles()).get(0).getId();
        RoleType roleType = RoleType.values()[rolePosition - 1];

        tenantViewModel.getAllTenants().observe(this, new Observer<List<Tenant>>() {
            @Override
            public void onChanged(@Nullable List<Tenant> tenants) {
                editSpinnerTenant.setAdapter(new ArrayAdapter<>(
                        that, android.R.layout.simple_list_item_1, tenants));

                leaseContract.setTenant(tenants.get(0));
            }
        });
    }

    private void getLeaseRequests(final AddEditLeasingContractActivity that, long id) {
        leaseRequestViewModel.getAllNotCompleted(id).observe(this, new Observer<List<LeaseRequest>>() {
            @Override
            public void onChanged(@Nullable List<LeaseRequest> leaseRequests) {
                editSpinnerLeaseRequests.setAdapter(new ArrayAdapter<>(
                        that, android.R.layout.simple_list_item_1, leaseRequests));

                if (leaseRequests.size() > 0) {
                    editSpinnerLeaseRequests.setVisibility(View.VISIBLE);
                    textLeaseRequest.setVisibility(View.VISIBLE);
                }
                else {
                    editSpinnerLeaseRequests.setVisibility(View.GONE);
                    textLeaseRequest.setVisibility(View.GONE);
                }
            }
        });
    }

    private View.OnClickListener getClickListener(
            final AddEditLeasingContractActivity that,
            final TextInputEditText editText
    ) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(that,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                OffsetDateTime date = OffsetDateTime.of(
                                        year, monthOfYear + 1, 1,
                                        0, 0, 0, 0,
                                        ZoneOffset.UTC);

                                if (editText.getId() == R.id.edit_text_leased_till) {
                                    leaseContract.setLeasedTill(date);
                                }
                                else if (editText.getId() == R.id.edit_text_leased_at) {
                                    leaseContract.setLeasedAt(date);
                                }

                                editText.setText(date.format(format));

                                calculateTotalMonthsAndPrice();
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        };
    }

    private void calculateTotalMonthsAndPrice() {
        if (leaseContract.getLeasedAt() == null || leaseContract.getLeasedTill() == null) {
            return;
        }

        long months = ChronoUnit.MONTHS.between(
            leaseContract.getLeasedAt(), leaseContract.getLeasedTill());

        String monthsStr = String.valueOf(months);
        BigDecimal totalPrice = leaseContract
                .getWarehouse()
                .getPricePerMonth()
                .multiply(new BigDecimal(monthsStr));

        editTextMonths.getEditText().setText(monthsStr);
        editTextTotalPrice.getEditText().setText(
                String.format(Locale.getDefault(),"%.2f", totalPrice));
    }

    private void saveLeasingContract() {
        try {
            if (leaseContract.getLeasedAt() == null || leaseContract.getLeasedTill() == null) {
                Toast.makeText(this, R.string.mandatory_dates, Toast.LENGTH_LONG).show();
                return;
            }

            LocalDate leasedAtLocal = leaseContract.getLeasedAt().toLocalDate();
            LocalDate leasedTillLocal = leaseContract.getLeasedTill().toLocalDate();

            if (leasedAtLocal.compareTo(leasedTillLocal) >= 0) {
                Toast.makeText(this, R.string.leased_at_less_than_leased_till, Toast.LENGTH_LONG).show();
                return;
            }

            leaseContract.setOwner(leaseContract.getWarehouse().getOwner());
            leaseContract.setSaleAgent(AppRequestQueue.getToken().getUser().getRelatedSaleAgent());

            if (getIntent().hasExtra(LEASE_CONTRACT_ID)) {
                leaseContractViewModel.update(leaseContract).observe(this, new Observer<LeasingContract>() {
                    @Override
                    public void onChanged(LeasingContract updatedLeasingContract) {
                        finishExecution(R.string.leaseContract_updated);
                    }
                });
            } else {
                leaseContractViewModel.insertData(leaseContract).observe(this, new Observer<LeasingContract>() {
                    @Override
                    public void onChanged(@Nullable LeasingContract insertedLeasingContract) {
                        finishExecution(R.string.leaseContract_created);
                    }
                });
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private void finishExecution(int message) {
        Intent intent = new Intent();
        intent.putExtras(getIntent());

        Toast.makeText(that, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);

        finish();
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

        if (AppRequestQueue.getToken().getUser().getRelatedOwner() != null) {
            menu.getItem(0).setEnabled(false);
            menu.getItem(0).setVisible(false);
        }

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
