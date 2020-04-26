package com.vale.warehouses.ui.loggedin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.service.view_model.LeaseRequestViewModel;
import com.vale.warehouses.service.view_model.LeasingContractViewModel;
import com.vale.warehouses.ui.lease_contract.LeaseContractListActivity;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.users.UserListActivity;
import com.vale.warehouses.ui.warehouses.WarehouseListActivity;

import java.util.ArrayList;
import java.util.List;

public class LoggedInActivity extends AppCompatActivity {
    private LoggedInActivity that = this;

    private Button usersButton, warehousesButton, warehouseContractsButton;

    private TextView textNotifications;
    private ListView listViewLeaseRequests, listViewAboutToExpireLeases;

    private LeaseRequestViewModel leaseRequestViewModel;
    private LeasingContractViewModel leasingContractViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        ArrayList<Role> roles  = new ArrayList<>(
                AppRequestQueue.getToken().getUser().getRoles());

        leaseRequestViewModel = new ViewModelProvider(this).get(LeaseRequestViewModel.class);
        leasingContractViewModel = new ViewModelProvider(this).get(LeasingContractViewModel.class);

        usersButton = findViewById(R.id.users);
        warehousesButton = findViewById(R.id.warehouses);
        warehouseContractsButton = findViewById(R.id.warehouse_contracts);
        textNotifications = findViewById(R.id.text_notifications);

        listViewLeaseRequests = findViewById(R.id.lease_requests_notifications);
        leaseRequestViewModel.getAllNotCompleted(null).observe(this, new Observer<List<LeaseRequest>>() {
            @Override
            public void onChanged(@Nullable List<LeaseRequest> leaseRequests) {
                ArrayList<String> leaseRequestsStr = new ArrayList<>();

                assert leaseRequests != null;
                leaseRequests.forEach(lr -> leaseRequestsStr.add(lr.toString() + ", " + lr.getTenant().getFullName()));

                listViewLeaseRequests.setAdapter(new ArrayAdapter<String>(
                        that, R.layout.activity_listview, leaseRequestsStr));

                if (leaseRequests.size() > 0) {
                    listViewLeaseRequests.setVisibility(View.VISIBLE);
                    textNotifications.setVisibility(View.VISIBLE);
                }
            }
        });;

        listViewAboutToExpireLeases = findViewById(R.id.expired_leases_notifications);
        leasingContractViewModel.getEndingSoonContracts(RoleType.SaleAgent).observe(this, leasingContracts -> {
            ArrayList<String> leasingContractsStr = new ArrayList<>();

            assert leasingContracts != null;
            leasingContracts.forEach(lc -> leasingContractsStr.add(lc.toString() + ", " + lc.getTenant().getFullName()));

            listViewAboutToExpireLeases.setAdapter(new ArrayAdapter<String>(
                    that, R.layout.activity_listview, leasingContractsStr));

            if (leasingContracts.size() > 0) {
                listViewAboutToExpireLeases.setVisibility(View.VISIBLE);
                textNotifications.setVisibility(View.VISIBLE);
            }
        });;

        int roleId = (int) roles.get(0).getId();
        if (roleId == RoleType.Admin.getValue()) {
            usersButton.setVisibility(View.VISIBLE);
        }

        if (roleId == RoleType.Owner.getValue()
                || roleId == RoleType.SaleAgent.getValue()
        ) {
            warehousesButton.setVisibility(View.VISIBLE);
            warehouseContractsButton.setVisibility(View.VISIBLE);
        }

        if (roleId == RoleType.SaleAgent.getValue()) {
            textNotifications.setVisibility(View.VISIBLE);
            listViewLeaseRequests.setVisibility(View.VISIBLE);
            listViewAboutToExpireLeases.setVisibility(View.VISIBLE);
        }
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

    public void openWarehouseRequests(View view) {


    }
}
