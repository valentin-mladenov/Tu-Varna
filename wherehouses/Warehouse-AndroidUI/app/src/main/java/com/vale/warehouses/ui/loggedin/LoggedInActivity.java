package com.vale.warehouses.ui.loggedin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.Token;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.users.UserListActivity;

import java.util.Objects;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
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

    public void openOwners(View view) {
    }

    public void openSaleAgents(View view) {
    }

    public void openWarehouses(View view) {
    }

    public void openUsers(View view) {
        Intent intent = new Intent(LoggedInActivity.this, UserListActivity.class);

        startActivity(intent);
    }
}
