package com.vale.warehouses.service.view_model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
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
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.service.model.Warehouse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class WarehouseViewModel extends AndroidViewModel {
    private AppRequestQueue requestQueue;
    private MutableLiveData<List<Warehouse>> allWarehouses;
    private MutableLiveData<Warehouse> oneWarehouse;
    private String url = getApplication().getResources().getString(R.string.base_url) + "/api/warehouse";
    private MutableLiveData<Boolean> deleteResult;

    public WarehouseViewModel(@NonNull Application application) {
        super(application);

        requestQueue = AppRequestQueue.getInstance(application);
    }

    public MutableLiveData<Warehouse> getOne(Long warehouseId) {
        oneWarehouse = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url + "/" + warehouseId,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneWarehouse.setValue(gson.fromJson(response.toString(), Warehouse.class));
                }
            },
            requestQueue.getErrorListener()) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return requestQueue.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.getRequestQueue().add(jsonObjectRequest);

        return oneWarehouse;
    }

    public MutableLiveData<Warehouse> insertData(Warehouse warehouse) {
        oneWarehouse = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST,
            url,
            getJsonObject(warehouse),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneWarehouse.setValue(gson.fromJson(response.toString(), Warehouse.class));
                }
            },
            requestQueue.getErrorListener()) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return requestQueue.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.getRequestQueue().add(jsonObjectRequest);

        return oneWarehouse;
    }

    public MutableLiveData<Warehouse> update(Warehouse warehouse) {
        oneWarehouse = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url + "/" + warehouse.getId(),
            getJsonObject(warehouse),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneWarehouse.setValue(gson.fromJson(response.toString(), Warehouse.class));
                }
            },
            requestQueue.getErrorListener()) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = requestQueue.getHeaders();
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;
            }


            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.getRequestQueue().add(jsonObjectRequest);

        return oneWarehouse;
    }

    private JSONObject getJsonObject(Warehouse warehouse) {
        JSONObject requestBody = null;
        try {
            requestBody = new JSONObject(new Gson().toJson(warehouse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

    public MutableLiveData<Boolean> delete(Warehouse warehouse) {
        deleteResult = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url + "/" + warehouse.getId(),
            getJsonObject(warehouse),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    deleteResult.setValue(true);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    deleteResult.setValue(false);
                    if (error instanceof NetworkError) {
                        Toast.makeText(getApplication(), R.string.no_network, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return requestQueue.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.getRequestQueue().add(jsonObjectRequest);

        return deleteResult;
    }

    public MutableLiveData<List<Warehouse>> getAllWarehouses(RoleType roleType) {
        allWarehouses = new MutableLiveData<>();

        String url = "";

        if (roleType.getValue() == RoleType.Admin.getValue()) {
            url = this.url;
        }
        else if (roleType.getValue() == RoleType.Owner.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedOwner().getId();
            url = this.url + "/forOwner/" + id;
        }
        else if (roleType.getValue() == RoleType.SaleAgent.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedSaleAgent().getId();
            url = this.url + "/forSaleAgent/" + id;
        }

        getAll(url);

        return allWarehouses;
    }

    private void getAll(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        VolleyLog.wtf(response.toString(), "utf-8");
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();

                        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
                        List<Warehouse> warehouses = gson.fromJson(response.toString(), listType);
                        allWarehouses.setValue(warehouses);
                    }
                },
                requestQueue.getErrorListener()) {
            //This is for Headers If You Needed

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return requestQueue.getHeaders();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        requestQueue.getRequestQueue().add(jsonArrayRequest);
    }

}
