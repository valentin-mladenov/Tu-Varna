package com.vale.banksystem.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class AccountRepository {


    private String DB_NAME = "db_account";

    private AppDatabase appDatabase;
    public AccountRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

     public void insertAccount(final Account account) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.accessInterface().addAccount(account);
                return null;
            }
        }.execute();
    }

    public void updateAccount(final Account account) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.accessInterface().updateAccount(account);
                return null;
            }
        }.execute();
    }

    public void deleteAccount(final int id) {
        final LiveData<Account> acc = getAccount(id);
        if(acc != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    appDatabase.accessInterface().deleteAccount(acc.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteAccount(final Account account) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.accessInterface().deleteAccount(account);
                return null;
            }
        }.execute();
    }

    public LiveData<Account> getAccount(int id) {
        return appDatabase.accessInterface().getAccount(id);
    }

    public LiveData<List<Account>> getAccounts() {
        return appDatabase.accessInterface().getAll();
    }
}

