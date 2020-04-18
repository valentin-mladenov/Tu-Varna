package com.vale.warehouses.data.model;

import android.app.Application;
import android.app.DownloadManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private RequestQueue requestQueue;
    private MutableLiveData<List<User>> allUsers;
    private User oneUser;
    private String baseUrl = "http://localhost:8080/api/user";

    public UserViewModel(@NonNull Application application) {
        super(application);
        requestQueue = AppRequestQueue.getInstance(application).getRequestQueue();
    }

    public User getOne(String userId) {
        oneUser = null;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                baseUrl + userId,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        VolleyLog.wtf(response.toString(), "utf-8");
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();

                        oneUser = gson.fromJson(response.toString(), User.class);
                    }
                }, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.add(jsonObjectRequest);

        return oneUser;
    }

//    public void insert(User user) {
//        service.insert(user);
//    }
//
//    public void update(User user) {
//        service.update(user);
//    }
//
//    public void delete(User user) {
//        service.delete(user);
//    }
//
    public MutableLiveData<List<User>> getAllUsers() {
        allUsers = new MutableLiveData<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                baseUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        VolleyLog.wtf(response.toString(), "utf-8");
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();

                        Type listType = new TypeToken<List<User>>(){}.getType();
                        List<User> users = gson.fromJson(response.toString(), listType);
                        allUsers.setValue(users);
                    }
                }, errorListener) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.add(jsonArrayRequest);

        return allUsers;
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplication(), R.string.no_network, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };
}
