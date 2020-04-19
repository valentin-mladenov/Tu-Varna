package com.vale.warehouses.ui.users;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.Token;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.view_model.RoleViewModel;
import com.vale.warehouses.service.view_model.UserViewModel;

import java.util.List;
import java.util.Objects;

public class AddEditUserActivity extends AppCompatActivity {
    public static final String USER_ID = "USER_ID";
    private RoleViewModel roleViewModel;
    private UserViewModel userViewModel;
    private RoleMultiSelectionSpinner roleSpinner;
    private RecyclerView roleRecyclerView;
    private Token token;
    private User user;
    private EditText editTextUsername;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        token = (Token)getIntent().getExtras().get("TOKEN");

        roleSpinner = findViewById(R.id.role_spinner);

        roleViewModel = new ViewModelProvider(this).get(RoleViewModel.class);
        roleViewModel.getAllRoles(token.getId()).observe(this, new Observer<List<Role>>() {
            @Override
            public void onChanged(@Nullable List<Role> roles) {
//                final RoleAdapter roleAdapter = new RoleAdapter(user);
//                roleRecyclerView.setAdapter(roleAdapter);
//                roleAdapter.submitRoles(roles);

                roleSpinner.setRoles(roles);
            }
        });

//        roleRecyclerView = findViewById(R.id.edit_roles);
//        roleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        roleRecyclerView.setHasFixedSize(true);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextEmail = findViewById(R.id.edit_text_email);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        user = new User();
        if (intent.hasExtra(USER_ID)) {
            setTitle(getString(R.string.edit));

            userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.setToken(token);
            Long userId = intent.getExtras().getLong(USER_ID);

            userViewModel.getOne(userId).observe(this, new Observer<User>() {
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
        }



//        final RoleAdapter roleAdapter = new RoleAdapter(user);
//        roleRecyclerView.setAdapter(roleAdapter);

//        roleAdapter.setOnItemClickListener(new RoleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Role role) {
//
//            }
//        });

//        tracker = SelectionTracker.Builder<Long>(
//                "selection-1",
//                roleRecyclerView,
//                StableIdKeyProvider(roleRecyclerView),
//                MyLookup(roleRecyclerView),
//                StorageStrategy.createLongStorage()
//          ).withSelectionPredicate(
//                SelectionPredicates.createSelectAnything()
//        ).build();
    }
}
