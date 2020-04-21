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

import com.google.android.material.textfield.TextInputLayout;
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
    private TextInputLayout editTextUsername,
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
    private TextView textViewRating;

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
        textViewRating = findViewById(R.id.text_view_rating);

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
                    roleSpinner.setEnabled(false);

                    editTextPassword.setVisibility(View.GONE);
                    editTextPasswordConfirm.setVisibility(View.GONE);

                    Long userId = getIntent().getExtras().getLong(USER_ID);

                    userViewModel.getOne(userId).observe(that, new Observer<User>() {
                        @Override
                        public void onChanged(@Nullable User userRes) {
                            user.setEmail(userRes.getEmail());
                            user.setId(userRes.getId());
                            user.setUserName(userRes.getUserName());
                            user.setRoles(userRes.getRoles());
                            user.setRelatedSaleAgent(userRes.getRelatedSaleAgent());
                            user.setRelatedOwner(userRes.getRelatedOwner());
                            user.setRelatedTenant(userRes.getRelatedTenant());

                            editTextUsername.getEditText().setText(user.getUserName());
                            editTextEmail.getEditText().setText(user.getEmail());

                            roleSpinner.setSelection(user.getRoles());

                            addUserDataToUi();
                        }
                    });
                } else {
                    setTitle(getString(R.string.add));

                    roleSpinner.setSelection(user.getRoles());
                }
            }
        });
    }

    private void addUserDataToUi() {
        for (Role role: user.getRoles()) {
            switch ((int) role.getId()) {
                case 2:
                    addOwnerData();
                    break;
                case 3:
                    addSaleAgentData();
                    break;
                case 4:
                    addTenantData();
                    break;
            }
        }
    }

    private void addOwnerData() {
        Owner owner = user.getRelatedOwner();

        editTextUnique.getEditText().setText(owner.getUniqueCode());
        editTextPhone.getEditText().setText(owner.getPhoneNumber());
        editTextLastName.getEditText().setText(owner.getLastName());
        editTextFirstName.getEditText().setText(owner.getFirstName());
        editTextAddress.getEditText().setText(owner.getAddress());
    }

    private void addTenantData() {
        Tenant tenant = user.getRelatedTenant();

        editTextUnique.getEditText().setText(tenant.getUniqueCode());
        editTextPhone.getEditText().setText(tenant.getPhoneNumber());
        editTextLastName.getEditText().setText(tenant.getLastName());
        editTextFirstName.getEditText().setText(tenant.getFirstName());
        editTextAddress.getEditText().setText(tenant.getAddress());
    }

    private void addSaleAgentData() {
        SaleAgent agent = user.getRelatedSaleAgent();

        editTextUnique.getEditText().setText(agent.getUniqueCode());
        editTextPhone.getEditText().setText(agent.getPhoneNumber());
        editTextLastName.getEditText().setText(agent.getLastName());
        editTextFirstName.getEditText().setText(agent.getFirstName());
        editTextAddress.getEditText().setText(agent.getAddress());
        editTextFee.getEditText().setText(String.valueOf(agent.getFee()));
        editRatingBar.setRating(agent.getRating());
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
            user.setEmail(editTextEmail.getEditText().getText().toString());
            handleUserRelatedData();

            if (getIntent().hasExtra(USER_ID)) {
                userViewModel.update(user).observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(User updatedUser) {

                        Intent intent = new Intent();
                        intent.putExtras(getIntent());

                        setResult(RESULT_OK);

                        finish();
                    }
                });

                Toast.makeText(this, R.string.user_updated, Toast.LENGTH_SHORT).show();
            } else {
                user.setUserName(editTextUsername.getEditText().getText().toString());
                user.setPassword(editTextPassword.getEditText().getText().toString());
                user.setConfirmPassword(editTextPasswordConfirm.getEditText().getText().toString());

                userViewModel.insertData(user).observe(this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User insertedUser) {
                        assert insertedUser != null;

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
            editTextFee.setVisibility(View.VISIBLE);
            editRatingBar.setVisibility(View.VISIBLE);
            textViewRating.setVisibility(View.VISIBLE);
        } else {
            editTextFee.setVisibility(View.GONE);
            editRatingBar.setVisibility(View.GONE);
            textViewRating.setVisibility(View.GONE);
        }
    }

    private void handleUserRelatedData() {
        for (Role role: user.getRoles()) {
            switch ((int) role.getId()) {
                case 2:
                    handleOwner();
                    break;
                case 3:
                    handleSaleAgent();
                    break;
                case 4:
                    handleTenant();
                    break;
            }
        }
    }

    private void handleOwner() {
        Owner owner = new Owner();

        owner.setAddress(editTextAddress.getEditText().getText().toString());
        owner.setFirstName(editTextFirstName.getEditText().getText().toString());
        owner.setLastName(editTextLastName.getEditText().getText().toString());
        owner.setPhoneNumber(editTextPhone.getEditText().getText().toString());
        owner.setUniqueCode(editTextUnique.getEditText().getText().toString());

        if(getIntent().hasExtra(USER_ID)) {
            owner.setId(user.getRelatedOwner().getId());
        }

        user.setRelatedOwner(owner);
    }

    private void handleTenant() {
        Tenant tenant = new Tenant();

        tenant.setAddress(editTextAddress.getEditText().getText().toString());
        tenant.setFirstName(editTextFirstName.getEditText().getText().toString());
        tenant.setLastName(editTextLastName.getEditText().getText().toString());
        tenant.setPhoneNumber(editTextPhone.getEditText().getText().toString());
        tenant.setUniqueCode(editTextUnique.getEditText().getText().toString());

        if(getIntent().hasExtra(USER_ID)) {
            tenant.setId(user.getRelatedTenant().getId());
        }

        user.setRelatedTenant(tenant);
    }

    private void handleSaleAgent() {
        SaleAgent agent = new SaleAgent();

        agent.setAddress(editTextAddress.getEditText().getText().toString());
        agent.setFirstName(editTextFirstName.getEditText().getText().toString());
        agent.setLastName(editTextLastName.getEditText().getText().toString());
        agent.setPhoneNumber(editTextPhone.getEditText().getText().toString());
        agent.setUniqueCode(editTextUnique.getEditText().getText().toString());
        agent.setRating((int) editRatingBar.getRating());
        agent.setFee(Double.parseDouble(editTextFee.getEditText().getText().toString()));

        if(getIntent().hasExtra(USER_ID)) {
            agent.setId(user.getRelatedSaleAgent().getId());
        }

        user.setRelatedSaleAgent(agent);
    }

    private void buildViewModels() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setToken(token);

        roleViewModel = new ViewModelProvider(this).get(RoleViewModel.class);
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
