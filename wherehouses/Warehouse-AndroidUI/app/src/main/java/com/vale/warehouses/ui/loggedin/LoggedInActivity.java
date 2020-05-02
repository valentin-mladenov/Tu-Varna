package com.vale.warehouses.ui.loggedin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.model.Warehouse;
import com.vale.warehouses.service.view_model.LeaseRequestViewModel;
import com.vale.warehouses.service.view_model.LeasingContractViewModel;
import com.vale.warehouses.service.view_model.SaleAgentViewModel;
import com.vale.warehouses.ui.lease_contract.AddEditLeasingContractActivity;
import com.vale.warehouses.ui.lease_contract.LeaseContractListActivity;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.users.UserListActivity;
import com.vale.warehouses.ui.warehouses.WarehouseListActivity;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoggedInActivity extends AppCompatActivity {
    private LoggedInActivity that = this;
    private DateTimeFormatter format;

    private Button usersButton, warehousesButton, makeAnotherSearchButton,
            warehouseContractsButton, searchContractsButton;
    private TextInputEditText editTextDateFrom, editTextDateTo;

    private RelativeLayout RelativeLayoutAboutToExpireLeases,
            RelativeLayoutLeaseRequests, RelativeLayoutCurrentlyLeasedWarehouses;

    private TextView textNotifications,
            BadgeCurrentlyLeasedWarehouses, BadgeAboutToExpireLeases, BadgeLeaseRequests;
    private ListView listViewReportData;

    private LeaseRequestViewModel leaseRequestViewModel;
    private LeasingContractViewModel leasingContractViewModel;
    private SaleAgentViewModel saleAgentViewModel;

    private Spinner saleAgentsSpinner;
    private OffsetDateTime dateFrom, dateTo;
    private SaleAgent selectedSaleAgent;

    private List<String> leaseRequests = new ArrayList<>();
    private List<LeasingContract> leasingContracts = new ArrayList<>();
    private List<Warehouse> warehouses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        ArrayList<Role> roles  = new ArrayList<>(
                AppRequestQueue.getToken().getUser().getRoles());

        leaseRequestViewModel = new ViewModelProvider(this).get(LeaseRequestViewModel.class);
        leasingContractViewModel = new ViewModelProvider(this).get(LeasingContractViewModel.class);
        saleAgentViewModel = new ViewModelProvider(this).get(SaleAgentViewModel.class);

        usersButton = findViewById(R.id.users);
        warehousesButton = findViewById(R.id.warehouses);
        warehouseContractsButton = findViewById(R.id.warehouse_contracts);
        searchContractsButton = findViewById(R.id.search_contracts);
        makeAnotherSearchButton = findViewById(R.id.make_another_search);

        textNotifications = findViewById(R.id.text_notifications);

        RelativeLayoutAboutToExpireLeases = findViewById(R.id.expired_leases_notifications);
        RelativeLayoutLeaseRequests = findViewById(R.id.lease_requests_notifications);
        RelativeLayoutCurrentlyLeasedWarehouses = findViewById(R.id.currently_leased_warehouses);

        BadgeAboutToExpireLeases = findViewById(R.id.badge_notification_expired_leases_notifications);
        BadgeCurrentlyLeasedWarehouses = findViewById(R.id.badge_notification_currently_leased_warehouses);
        BadgeLeaseRequests = findViewById(R.id.badge_notification_lease_requests_notifications);

        listViewReportData = findViewById(R.id.report_data);

        saleAgentsSpinner = findViewById(R.id.spinner_sale_agents);
        editTextDateFrom = findViewById(R.id.edit_text_date_from);
        editTextDateTo = findViewById(R.id.edit_text_date_to);

        int roleId = (int) roles.get(0).getId();
        if (roleId == RoleType.Admin.getValue()) {
            handleAdminUser();
        }

        if (roleId == RoleType.Owner.getValue()
                || roleId == RoleType.SaleAgent.getValue()
        ) {
            getEndingSoonContracts(roleId);
            warehousesButton.setVisibility(View.VISIBLE);
            warehouseContractsButton.setVisibility(View.VISIBLE);
        }

        if (roleId == RoleType.SaleAgent.getValue()) {
            getAllNotCompletedLeaseRequests();
        }

        if (roleId == RoleType.Owner.getValue()) {
            getAllCurrentlyLeasedWarehouses(roleId);
        }
    }

    private void handleAdminUser() {
        format = DateTimeFormatter.ofPattern(getString(R.string.date_format));
        editTextDateFrom.setOnClickListener(getClickListener(this, editTextDateFrom));
        editTextDateTo.setOnClickListener(getClickListener(this, editTextDateTo));

        usersButton.setVisibility(View.VISIBLE);
        findViewById(R.id.Label_spinner_sale_agents).setVisibility(View.VISIBLE);
        ((TextInputLayout)editTextDateFrom.getParent().getParent()).setVisibility(View.VISIBLE);
        ((TextInputLayout)editTextDateTo.getParent().getParent()).setVisibility(View.VISIBLE);
        saleAgentsSpinner.setVisibility(View.VISIBLE);
        searchContractsButton.setVisibility(View.VISIBLE);

        saleAgentViewModel.getAllSaleAgents().observe(this, saleAgents -> {
            saleAgentsSpinner.setAdapter(new ArrayAdapter<>(
                    that, android.R.layout.simple_list_item_1, saleAgents));
            saleAgentsSpinner.setSelection(0);
        });

        saleAgentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedSaleAgent = (SaleAgent)saleAgentsSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    private void getAllNotCompletedLeaseRequests() {
        leaseRequestViewModel.getAllNotCompleted(null)
        .observe(this, leaseRequests -> {

            assert leaseRequests != null;
            leaseRequests.forEach(lr -> this.leaseRequests.add(lr.toString()
                    + ", Tenant: "
                    + lr.getTenant().getFullName()
                    + " with phone number: "
                    + lr.getTenant().getPhoneNumber()));

            textNotifications.setVisibility(View.VISIBLE);
            RelativeLayoutLeaseRequests.setVisibility(View.VISIBLE);
            BadgeLeaseRequests.setText(
                    String.format(Locale.getDefault(), "%d", this.leaseRequests.size()));
        });
    }

    private void getAllCurrentlyLeasedWarehouses(int roleType) {
        leasingContractViewModel.getCurrentlyLeasedWarehouses(roleType)
        .observe(this, leasingContracts -> {
            assert leasingContracts != null;
            leasingContracts.forEach(lc -> this.warehouses.add(lc.getWarehouse()));

            textNotifications.setVisibility(View.VISIBLE);
            RelativeLayoutCurrentlyLeasedWarehouses.setVisibility(View.VISIBLE);
            BadgeCurrentlyLeasedWarehouses.setText(
                    String.format(Locale.getDefault(), "%d", this.warehouses.size()));
        });
    }

    private void getEndingSoonContracts(int roleType) {
        leasingContractViewModel.getEndingSoonContracts(roleType)
        .observe(this, leasingContracts -> {
            this.leasingContracts = leasingContracts;

            textNotifications.setVisibility(View.VISIBLE);
            RelativeLayoutAboutToExpireLeases.setVisibility(View.VISIBLE);
            BadgeAboutToExpireLeases.setText(
                    String.format(Locale.getDefault(), "%d", this.leasingContracts.size()));
        });
    }

    public void logout(View view) {
        String goodbye = getString(R.string.goodbye) + AppRequestQueue.getToken().getUser().getUserName();
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoggedInActivity.this, LoginActivity.class);
        startActivity(intent);

        setResult(Activity.RESULT_OK);

        //Complete and destroy LoggedIn activity once successful
        finish();
    }

    public void openLeasingContracts(View view) {
        Intent intent = new Intent(LoggedInActivity.this, LeaseContractListActivity.class);

        startActivity(intent);
    }

    public void openWarehouses(View view) {
        Intent intent = new Intent(LoggedInActivity.this, WarehouseListActivity.class);

        startActivity(intent);
    }

    public void openUsers(View view) {
        Intent intent = new Intent(LoggedInActivity.this, UserListActivity.class);

        startActivity(intent);
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
                logout(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener getClickListener(
            final LoggedInActivity that,
            final TextInputEditText editText
    ) {
        return v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(that,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        OffsetDateTime date = OffsetDateTime.of(
                                year1, monthOfYear + 1, 1,
                                0, 0, 0, 0,
                                ZoneOffset.UTC);

                        if (editText.getId() == R.id.edit_text_date_from) {
                            dateFrom = date;
                        }
                        else if (editText.getId() == R.id.edit_text_date_to) {
                            dateTo = date;
                        }

                        editText.setText(date.format(format));
                    }, year, month, day);
            datePickerDialog.show();
        };
    }

    public void searchContractsForSaleAgent(View view) {
        ((TextInputLayout)editTextDateFrom.getParent().getParent()).setVisibility(View.GONE);
        ((TextInputLayout)editTextDateTo.getParent().getParent()).setVisibility(View.GONE);
        saleAgentsSpinner.setEnabled(false);
        searchContractsButton.setVisibility(View.GONE);

        leasingContractViewModel.getAllLeasingContracts(RoleType.SaleAgent, dateFrom, dateTo, selectedSaleAgent.getId())
        .observe(this, leasingContracts -> {
            listViewReportData.setAdapter(new ArrayAdapter<>(
                    that, R.layout.activity_listview, leasingContracts));

            listViewReportData.setVisibility(View.VISIBLE);
            makeAnotherSearchButton.setVisibility(View.VISIBLE);
        });
    }

    public void makeAnotherSearch(View view) {
        ((TextInputLayout)editTextDateFrom.getParent().getParent()).setVisibility(View.VISIBLE);
        ((TextInputLayout)editTextDateTo.getParent().getParent()).setVisibility(View.VISIBLE);
        saleAgentsSpinner.setEnabled(true);
        searchContractsButton.setVisibility(View.VISIBLE);
        makeAnotherSearchButton.setVisibility(View.GONE);

        listViewReportData.setVisibility(View.GONE);
    }

    public void showExpiredLeaseContracts(View view) {
        showDialog(R.string.lease_contracts_about_to_expire,
                new ArrayAdapter<>(that, android.R.layout.simple_list_item_1, leasingContracts));

    }

    public void showNewLeaseContractsRequests(View view) {
        showDialog(R.string.new_lease_requests,
                new ArrayAdapter<>(that, android.R.layout.simple_list_item_1, leaseRequests));
    }

    public void showCurrentlyLeasedWarehouses(View view) {
        showDialog(R.string.currently_leased_warehouses,
                new ArrayAdapter<>(that, android.R.layout.simple_list_item_1, warehouses));
    }

    private void showDialog(int titleId, ArrayAdapter adapter) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.notifications, null);

        alertDialog.setView(convertView);
        alertDialog.setTitle(getApplication().getResources().getString(titleId));

        ListView notifications = convertView.findViewById(R.id.notifications);
        notifications.setAdapter(adapter);

        alertDialog.show();
    }
}
