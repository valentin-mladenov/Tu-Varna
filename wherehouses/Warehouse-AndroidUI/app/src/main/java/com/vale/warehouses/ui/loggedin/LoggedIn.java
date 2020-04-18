package com.vale.warehouses.ui.loggedin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.ui.login.LoginActivity;

public class LoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
    }

    public void logout(View view) {
        String goodbye = getString(R.string.goodbye) + getIntent().getExtras().get("LOGGED_IN_USER");
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoggedIn.this, LoginActivity.class);
        startActivity(intent);

        setResult(Activity.RESULT_OK);

        //Complete and destroy LoggedIn activity once successful
        finish();
    }
}
