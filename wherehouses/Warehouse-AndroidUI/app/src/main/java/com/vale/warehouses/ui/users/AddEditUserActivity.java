package com.vale.warehouses.ui.users;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.Token;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.view_model.RoleViewModel;
import com.vale.warehouses.service.view_model.UserViewModel;

import org.json.JSONException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddEditUserActivity extends AppCompatActivity {
    public static final String USER_ID = "USER_ID";
    private RoleViewModel roleViewModel;
    private UserViewModel userViewModel;
    private RoleMultiSelectionSpinner roleSpinner;
    private Token token;
    private User user;
    private EditText editTextUsername,
            editTextEmail,
            editTextPassword,
            editTextPasswordConfirm,
            editTextFirstName,
            editTextLastName,
            editTextAddress,
            editTextFee,
            editTextRating,
            editTextUnique,
            editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        token = (Token)getIntent().getExtras().get("TOKEN");

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        roleSpinner = findViewById(R.id.role_spinner);
        editTextUsername = findViewById(R.id.edit_text_username);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPasswordConfirm = findViewById(R.id.edit_text_password_confirm);
        editTextAddress = findViewById(R.id.edit_text_address);
        editTextFee = findViewById(R.id.edit_text_fee);
        editTextPhone = findViewById(R.id.edit_text_phone_number);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextRating = findViewById(R.id.edit_text_rating);
        editTextUnique = findViewById(R.id.edit_text_unique);

        user = new User();
        user.setRoles(new HashSet<Role>());

        final AddEditUserActivity that = this;

        roleViewModel = new ViewModelProvider(that).get(RoleViewModel.class);
        roleViewModel.getAllRoles(token.getId()).observe(this, new Observer<List<Role>>() {
            @Override
            public void onChanged(@Nullable List<Role> roles) {
                roleSpinner.setRoles(roles);

                if (getIntent().hasExtra(USER_ID)) {
                    setTitle(getString(R.string.edit));
                    editTextUsername.setEnabled(false);

                    editTextPassword.setVisibility(View.INVISIBLE);
                    editTextPasswordConfirm.setVisibility(View.INVISIBLE);

                    userViewModel = new ViewModelProvider(that).get(UserViewModel.class);
                    userViewModel.setToken(token);
                    Long userId = getIntent().getExtras().getLong(USER_ID);

                    userViewModel.getOne(userId).observe(that, new Observer<User>() {
                        @Override
                        public void onChanged(@Nullable User userRes) {
                            user.setEmail(userRes.getEmail());
                            user.setId(userRes.getId());
                            user.setUserName(userRes.getUserName());
                            user.setRoles(userRes.getRoles());

                            editTextUsername.setText(user.getUserName());
                            editTextEmail.setText(user.getEmail());

                            roleSpinner.setSelection(user.getRoles());

                            roleSpinner.getSelection().observe(that, new Observer<Set<Role>>() {
                                @Override
                                public void onChanged(Set<Role> roles) {
                                    user.setRoles(roles);
                                    hideData(roles);
                                }
                            });
                        }
                    });
                } else {
                    setTitle(getString(R.string.add));
                }
            }
        });
    }

    private void saveUser() {
        if(user.getRoles().isEmpty()) {
            Toast.makeText(this, R.string.user_role_missing, Toast.LENGTH_SHORT).show();

            return;
        }

        if(user.getRoles().size() > 1) {
            Toast.makeText(this, "Only one Role per User", Toast.LENGTH_SHORT).show();

            return;
        }

        try {
            if (getIntent().hasExtra(USER_ID)) {
                userViewModel.update(user);
                Toast.makeText(this, R.string.user_updated, Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.insert(user);
                Toast.makeText(this, R.string.user_created, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {

        }

        Intent intent = new Intent();
        intent.putExtras(getIntent());

        setResult(RESULT_OK);

        finish();
    }

    public void hideData(Set<Role> roles) {
        boolean isAgent = false;
        for (Role role: roles) {
            if (role.getId() == 3) {
                isAgent = true;
            }
        }

        //Toggle
        if (isAgent) {
            editTextRating.setVisibility(View.VISIBLE);
            editTextFee.setVisibility(View.VISIBLE);
        } else {
            editTextRating.setVisibility(View.INVISIBLE);
            editTextFee.setVisibility(View.INVISIBLE);
        }
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
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
