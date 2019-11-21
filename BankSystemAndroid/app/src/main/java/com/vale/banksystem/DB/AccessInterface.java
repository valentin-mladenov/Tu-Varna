package com.vale.banksystem.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccessInterface {
    @Insert
    public void addAccount(Account account);

    @Query("Select * from account")
    public LiveData<List<Account>> getAll();

    @Update
    public void updateAccount(Account account);

    @Delete
    public void deleteAccount(Account account);

    @Query("SELECT * FROM Account WHERE id =:accId")
    LiveData<Account> getAccount(int accId);
}
