package com.vale.warehouses.ui.users;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Owner;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.Tenant;
import com.vale.warehouses.service.model.Token;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.view_model.OwnerViewModel;
import com.vale.warehouses.service.view_model.RoleViewModel;
import com.vale.warehouses.service.view_model.SaleAgentViewModel;
import com.vale.warehouses.service.view_model.TenantViewModel;
import com.vale.warehouses.service.view_model.UserViewModel;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddEditUserActivity extends AppCompatActivity {
    public static final String USER_ID = "USER_ID";

    private RoleViewModel roleViewModel;
    private UserViewModel userViewModel;
    private OwnerViewModel ownerViewModel;
    private SaleAgentViewModel saleAgentViewModel;
    private TenantViewModel tenantViewModel;

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
            editTextUnique,
            editTextPhone;

    private RatingBar editRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        token = (Token)getIntent().getExtras().get("TOKEN");

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPasswordConfirm = findViewById(R.id.edit_text_password_confirm);
        editTextAddress = findViewById(R.id.edit_text_address);
        editTextFee = findViewById(R.id.edit_text_fee);
        editTextPhone = findViewById(R.id.edit_text_phone_number);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextUnique = findViewById(R.id.edit_text_unique);
        editRatingBar = findViewById(R.id.edit_text_rating);

        roleSpinner = findViewById(R.id.role_spinner);
        roleSpinner.getSelection().observe(this, new Observer<Set<Role>>() {
            @Override
            public void onChanged(Set<Role> roles) {
                user.setRoles(roles);
                hideData(roles);
            }
        });

        user = new User();
        user.setRoles(new HashSet<Role>());

        final AddEditUserActivity that = this;

        buildViewModels();

        roleViewModel.getAllRoles(token.getId()).observe(this, new Observer<List<Role>>() {
            @Override
            public void onChanged(@Nullable List<Role> roles) {
                roleSpinner.setRoles(roles);

                if (getIntent().hasExtra(USER_ID)) {
                    setTitle(getString(R.string.edit));
                    editTextUsername.setEnabled(false);

                    editTextPassword.setVisibility(View.INVISIBLE);
                    editTextPasswordConfirm.setVisibility(View.INVISIBLE);

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
                        }
                    });
                } else {
                    setTitle(getString(R.string.add));

                    roleSpinner.setSelection(user.getRoles());
                }
            }
        });
    }

    private void saveUser() {
        if(user.getRoles().isEmpty()) {
            Toast.makeText(this, R.string.user_role_missing, Toast.LENGTH_SHORT).show();

            return;
        }

//        if(user.getRoles().size() > 1) {
//            Toast.makeText(this, "Only one Role per User", Toast.LENGTH_SHORT).show();
//
//            return;
//        }

        try {
            if (getIntent().hasExtra(USER_ID)) {
                userViewModel.update(user).observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(User updatedUser) {
                        handleUserAction(updatedUser);

                        Intent intent = new Intent();
                        intent.putExtras(getIntent());

                        setResult(RESULT_OK);

                        finish();
                    }
                });

                Toast.makeText(this, R.string.user_updated, Toast.LENGTH_SHORT).show();
            } else {
                user.setUserName(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setConfirmPassword(editTextPasswordConfirm.getText().toString());

                userViewModel.insertData(user).observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User insertedUser) {
                        assert insertedUser != null;
                        handleUserAction(insertedUser);

                        Intent intent = new Intent();
                        intent.putExtras(getIntent());

                        setResult(RESULT_OK);

                        finish();
                    }
                });

                Toast.makeText(this, R.string.user_created, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private void hideData(Set<Role> roles) {
        boolean isAgent = false;
        for (Role role: roles) {
            if (role.getId() == 3) {
                isAgent = true;
            }
        }

        //Toggle
        if (isAgent) {
            editRatingBar.setVisibility(View.VISIBLE);
            editTextFee.setVisibility(View.VISIBLE);
        } else {
            editRatingBar.setVisibility(View.INVISIBLE);
            editTextFee.setVisibility(View.INVISIBLE);
        }
    }

    private void handleUserAction(User user) {
        boolean isNew = !getIntent().hasExtra(USER_ID);
        try {
            for (Role role: user.getRoles()) {
                switch ((int) role.getId()) {
                    case 2:
                        handleOwner(user, isNew);
                        break;
                    case 3:
                        handleSaleAgent(user, isNew);
                        break;
                    case 4:
                        handleTenant(user, isNew);
                        break;
                }
            }
        } catch (JSONException e) {
        }
    }

    private void handleOwner(User user, boolean isNew) throws JSONException {
        Owner owner = new Owner();

        owner.setAddress(editTextAddress.getText().toString());
        owner.setFirstName(editTextFirstName.getText().toString());
        owner.setLastName(editTextLastName.getText().toString());
        owner.setPhoneNumber(editTextPhone.getText().toString());
        owner.setUniqueCode(editTextUnique.getText().toString());
        owner.setUser(user);

        OwnerViewModel ownerViewModel = new ViewModelProvider(this).get(OwnerViewModel.class);
        ownerViewModel.setToken(token);

        if (isNew) {
            ownerViewModel.insertData(owner);
            Toast.makeText(this, R.string.owner_created, Toast.LENGTH_SHORT).show();

            return;
        }

        ownerViewModel.update(owner);
        Toast.makeText(this, R.string.owner_updated, Toast.LENGTH_SHORT).show();
    }


    private void handleTenant(User user, boolean isNew) throws JSONException {
        Tenant tenant = new Tenant();

        tenant.setAddress(editTextAddress.getText().toString());
        tenant.setFirstName(editTextFirstName.getText().toString());
        tenant.setLastName(editTextLastName.getText().toString());
        tenant.setPhoneNumber(editTextPhone.getText().toString());
        tenant.setUniqueCode(editTextUnique.getText().toString());
        tenant.setUser(user);

        if (isNew) {
            tenantViewModel.insertData(tenant);
            Toast.makeText(this, R.string.tenant_created, Toast.LENGTH_SHORT).show();

            return;
        }

        tenantViewModel.update(tenant);
        Toast.makeText(this, R.string.tenant_updated, Toast.LENGTH_SHORT).show();
    }

    private void handleSaleAgent(User user, boolean isNew) throws JSONException {
        SaleAgent agent = new SaleAgent();

        agent.setAddress(editTextAddress.getText().toString());
        agent.setFirstName(editTextFirstName.getText().toString());
        agent.setLastName(editTextLastName.getText().toString());
        agent.setPhoneNumber(editTextPhone.getText().toString());
        agent.setUniqueCode(editTextUnique.getText().toString());
        agent.setRating((int) editRatingBar.getRating());
        agent.setFee(new BigDecimal(editTextFee.getText().toString()));
        agent.setUser(user);

        if (isNew) {
            saleAgentViewModel.insertData(agent);
            Toast.makeText(this, R.string.sale_agent_cerated, Toast.LENGTH_SHORT).show();

            return;
        }

        saleAgentViewModel.update(agent);
        Toast.makeText(this, R.string.sale_agent_updated, Toast.LENGTH_SHORT).show();
    }

    private void buildViewModels() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setToken(token);

        roleViewModel = new ViewModelProvider(this).get(RoleViewModel.class);

        tenantViewModel = new ViewModelProvider(this).get(TenantViewModel.class);
        tenantViewModel.setToken(token);

        ownerViewModel = new ViewModelProvider(this).get(OwnerViewModel.class);
        ownerViewModel.setToken(token);

        saleAgentViewModel = new ViewModelProvider(this).get(SaleAgentViewModel.class);
        saleAgentViewModel.setToken(token);
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
