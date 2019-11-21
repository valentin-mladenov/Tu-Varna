package com.vale.banksystem.DB;

import androidx.room.*;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "account")
public class Account implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull private int id;

    @ColumnInfo(name = "balance")
    private Double Balance;

    @ColumnInfo(name = "interest")
    private Double Interest;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public Double getInterest() {
        return Interest;
    }

    public void setInterest(Double interest) {
        Interest = interest;
    }
}
