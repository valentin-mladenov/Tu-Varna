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
import com.vale.warehouses.service.model.SaleAgent;
import com.vale.warehouses.service.model.Token;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class SaleAgentViewModel extends AndroidViewModel {
    private AppRequestQueue requestQueue;
    private MutableLiveData<List<SaleAgent>> allSaleAgents;
    private MutableLiveData<SaleAgent> oneSaleAgent;
    private MutableLiveData<Boolean> deleteResult;
    private Token token;
    private String url = getApplication().getResources().getString(R.string.base_url) + "/api/saleAgent";

    public SaleAgentViewModel(@NonNull Application application) {
        super(application);

        requestQueue = AppRequestQueue.getInstance(application);
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public MutableLiveData<SaleAgent> getOne(Long saleAgentId) {
        oneSaleAgent = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url + "/" + saleAgentId,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneSaleAgent.setValue(gson.fromJson(response.toString(), SaleAgent.class));
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

        return oneSaleAgent;
    }

    public MutableLiveData<SaleAgent> insertData(SaleAgent saleAgent) throws JSONException {
        oneSaleAgent = new MutableLiveData<>();

        JSONObject requestBody = new JSONObject(new Gson().toJson(saleAgent));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST,
            url,
            requestBody,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneSaleAgent.setValue(gson.fromJson(response.toString(), SaleAgent.class));
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

        return oneSaleAgent;
    }

    public MutableLiveData<SaleAgent> update(SaleAgent saleAgent) throws JSONException {
        oneSaleAgent = new MutableLiveData<>();

        JSONObject requestBody = new JSONObject(new Gson().toJson(saleAgent));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url + "/" + saleAgent.getId(),
            requestBody,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    oneSaleAgent.setValue(new Gson().fromJson(response.toString(), SaleAgent.class));
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

        return oneSaleAgent;
    }

    public MutableLiveData<Boolean> delete(SaleAgent saleAgent) throws JSONException {
        deleteResult = new MutableLiveData<>();

        JSONObject requestBody = new JSONObject(new Gson().toJson(saleAgent));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.DELETE,
            url + "/" + saleAgent.getId(),
            requestBody,
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

    public MutableLiveData<List<SaleAgent>> getAllSaleAgents() {
        allSaleAgents = new MutableLiveData<>();

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

                    Type listType = new TypeToken<List<SaleAgent>>(){}.getType();
                    List<SaleAgent> saleAgents = gson.fromJson(response.toString(), listType);
                    allSaleAgents.setValue(saleAgents);
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

        return allSaleAgents;
    }
}
