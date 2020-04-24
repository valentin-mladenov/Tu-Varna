package com.vale.warehouses.ui.warehouses;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.MutableLiveData;

import com.vale.warehouses.service.model.SaleAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaleAgentMultiSelectionSpinner extends AppCompatSpinner
        implements DialogInterface.OnMultiChoiceClickListener {

    List<SaleAgent> saleAgents = null;
    private MutableLiveData<Set<SaleAgent>> selectedSaleAgents = new MutableLiveData<>();
    boolean[] selection = null;
    ArrayAdapter<String> adapter;

    public SaleAgentMultiSelectionSpinner(Context context) {
        super(context);

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);

        super.setAdapter(adapter);
    }

    public SaleAgentMultiSelectionSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selection != null && which < selection.length) {

            selection[which] = isChecked;

            adapter.clear();
            adapter.add(buildSelectedItemString());

            this.selectedSaleAgents.setValue(getSelectedItems());
        } else {
            throw new IllegalArgumentException("Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] itemNames = new String[saleAgents.size()];

        for (int i = 0; i < saleAgents.size(); i++) {
            itemNames[i] = saleAgents.get(i).getFullName();
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
        throw new RuntimeException("setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setSaleAgents(List<SaleAgent> saleAgents) {
        this.saleAgents = saleAgents;
        selection = new boolean[this.saleAgents.size()];
        adapter.clear();
        adapter.add("");
        Arrays.fill(selection, false);
    }

    public MutableLiveData<Set<SaleAgent>> getSelection() {
        return this.selectedSaleAgents;
    }

    public void setSelection(Set<SaleAgent> selection) {
        this.selectedSaleAgents.setValue(selection);
        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }

        for (SaleAgent sel : selection) {
            for (int j = 0; j < saleAgents.size(); ++j) {
                if (saleAgents.get(j).getFullName().equals(sel.getFullName())) {
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

        for (int i = 0; i < saleAgents.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append(", ");
                }

                foundOne = true;

                sb.append(saleAgents.get(i).getFullName());
            }
        }

        return sb.toString();
    }

    public Set<SaleAgent> getSelectedItems() {
        ArrayList<SaleAgent> selectedItems = new ArrayList<>();

        for (int i = 0; i < saleAgents.size(); ++i) {
            if (selection[i]) {
                selectedItems.add(saleAgents.get(i));
            }
        }

        return new HashSet<>(selectedItems);
    }
}
