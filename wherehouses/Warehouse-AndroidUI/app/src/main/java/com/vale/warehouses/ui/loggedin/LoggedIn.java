package com.vale.warehouses.ui.loggedin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.data.model.Token;
import com.vale.warehouses.ui.login.LoginActivity;
import com.vale.warehouses.ui.users.UserListActivity;

public class LoggedIn extends AppCompatActivity {
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        token = (Token)getIntent().getExtras().get("TOKEN");
    }

    public void logout(View view) {
        String goodbye = getString(R.string.goodbye) + token.getUser().getUserName();
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoggedIn.this, LoginActivity.class);
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
        Intent intent = new Intent(LoggedIn.this, UserListActivity.class);

        intent.putExtras(getIntent());

        startActivity(intent);

         setResult(Activity.RESULT_OK);

         // Complete and destroy LoggedIn activity once successful
         finish();
    }
}
