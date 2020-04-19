package com.vale.warehouses.ui.users;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Role;
import com.vale.warehouses.service.model.Token;
import com.vale.warehouses.service.model.User;
import com.vale.warehouses.service.view_model.RoleViewModel;
import com.vale.warehouses.service.view_model.UserViewModel;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;

    private UserViewModel userViewModel;
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        token = (Token)getIntent().getExtras().get("TOKEN");

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_user);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserListActivity.this, AddEditUserActivity.class);
                intent.putExtras(getIntent());
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.users_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final UserAdapter userAdapter = new UserAdapter();
        recyclerView.setAdapter(userAdapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setToken(token);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userAdapter.submitList(users);
            }
        });

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView,
//                                  @NonNull RecyclerView.ViewHolder viewHolder,
//                                  @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                userViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
//                Toast.makeText(UserListActivity.this, R.string.note_deleted, Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(UserListActivity.this, AddEditUserActivity.class);

                intent.putExtras(getIntent());
                intent.putExtra("USER_ID", user.getId());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
