package com.vale.warehouses.ui.loggedin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.ui.lease_contract.LeaseContractListActivity;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.users.UserListActivity;
import com.vale.warehouses.ui.warehouses.WarehouseListActivity;

import java.util.ArrayList;
import java.util.Set;

public class LoggedInActivity extends AppCompatActivity {
    private Button usersButton;
    private Button warehousesButton;
    private Button warehouseContractsButton;
    private Button warehouseRequestsButton;
//    private Button usersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        usersButton = findViewById(R.id.users);
        warehousesButton = findViewById(R.id.warehouses);
        warehouseContractsButton = findViewById(R.id.warehouse_contracts);
        warehouseRequestsButton = findViewById(R.id.warehouse_requests);

        ArrayList<Role> roles  = new ArrayList<>(
                AppRequestQueue.getToken().getUser().getRoles());

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
            warehouseRequestsButton.setVisibility(View.VISIBLE);
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
