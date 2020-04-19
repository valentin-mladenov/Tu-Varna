package com.vale.warehouses.ui.users;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;

import com.vale.warehouses.service.model.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleMultiSelectionSpinner extends AppCompatSpinner
        implements DialogInterface.OnMultiChoiceClickListener {

    List<Role> roles = null;
    boolean[] selection = null;
    ArrayAdapter adapter;

    public RoleMultiSelectionSpinner(Context context) {
        super(context);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    public RoleMultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selection != null && which < selection.length) {
            selection[which] = isChecked;

            adapter.clear();
            adapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException("Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] itemNames = new String[roles.size()];

        for (int i = 0; i < roles.size(); i++) {
            itemNames[i] = roles.get(i).getName();
        }

        builder.setMultiChoiceItems(itemNames, selection, this);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                // Do nothing
            }
        });

        builder.show();

        return true;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
        selection = new boolean[this.roles.size()];
        adapter.clear();
        adapter.add("");
        Arrays.fill(selection, false);
    }

    public void setSelection(Set<Role> selection) {
        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }

        for (Role sel : selection) {
            for (int j = 0; j < roles.size(); ++j) {
                if (roles.get(j).getName().equals(sel.getName())) {
                    this.selection[j] = true;
                }
            }
        }

        adapter.clear();
        adapter.add(buildSelectedItemString());
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < roles.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }

                foundOne = true;

                sb.append(roles.get(i).getName());
            }
        }

        return sb.toString();
    }

    public Set<Role> getSelectedItems() {
        ArrayList<Role> selectedItems = new ArrayList<>();

        for (int i = 0; i < roles.size(); ++i) {
            if (selection[i]) {
                selectedItems.add(roles.get(i));
            }
        }

        return new HashSet<>(selectedItems);
    }
}
