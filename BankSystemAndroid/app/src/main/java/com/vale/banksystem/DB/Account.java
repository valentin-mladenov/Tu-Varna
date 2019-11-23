package com.vale.banksystem.DB;

import androidx.room.*;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "account")
public class Account implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull private int id;

    @ColumnInfo(name = "balance")
    private Double Balance;

    @ColumnInfo(name = "interest")
    private Double Interest;


    @ColumnInfo(name = "name")
    private String Name;

    public String getName() { return Name; }

    public void setName(String name) { this.Name = name; }

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
