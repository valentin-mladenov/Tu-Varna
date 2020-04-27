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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.LeasingContract;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.RoleType;
import com.vale.warehouses.service.model.Warehouse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class LeasingContractViewModel extends AndroidViewModel {
    private AppRequestQueue requestQueue;
    private MutableLiveData<List<LeasingContract>> allLeasingContracts;
    private MutableLiveData<List<LeasingContract>> endingSoonLeasingContracts;
    private MutableLiveData<LeasingContract> oneLeasingContract;
    private String url = getApplication().getResources().getString(R.string.base_url) + "/api/leasingContract";
    private MutableLiveData<Boolean> deleteResult;

    public LeasingContractViewModel(@NonNull Application application) {
        super(application);

        requestQueue = AppRequestQueue.getInstance(application);
    }

    public MutableLiveData<LeasingContract> getOne(Long leaseContractId) {
        oneLeasingContract = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url + "/" + leaseContractId,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeasingContract.setValue(gson.fromJson(response.toString(), LeasingContract.class));
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

        return oneLeasingContract;
    }

    public MutableLiveData<LeasingContract> insertData(LeasingContract leaseContract) {
        oneLeasingContract = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST,
            url,
            getJsonObject(leaseContract),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeasingContract.setValue(gson.fromJson(response.toString(), LeasingContract.class));
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

        return oneLeasingContract;
    }

    public MutableLiveData<LeasingContract> update(LeasingContract leaseContract) {
        oneLeasingContract = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url + "/" + leaseContract.getId(),
            getJsonObject(leaseContract),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeasingContract.setValue(gson.fromJson(response.toString(), LeasingContract.class));
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

        return oneLeasingContract;
    }

    public MutableLiveData<Boolean> delete(LeasingContract leaseContract) {
        deleteResult = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.DELETE,
            url + "/" + leaseContract.getId(),
            getJsonObject(leaseContract),
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

    public MutableLiveData<List<LeasingContract>> getAllLeasingContracts(
            RoleType roleType, OffsetDateTime fromDate, OffsetDateTime toDate
    ) {
        allLeasingContracts = new MutableLiveData<>();

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

        getAll(url, allLeasingContracts);

        return allLeasingContracts;
    }


    public MutableLiveData<List<LeasingContract>> getCurrentlyLeasedWarehouses(
            int roleType
    ) {
        allLeasingContracts = new MutableLiveData<>();

        String url = "";

        if (roleType == RoleType.Admin.getValue()) {
            url = this.url;
        }
        else if (roleType == RoleType.Owner.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedOwner().getId();
            url = this.url + "/currentlyLeased/forOwner/" + id;
        }
        else if (roleType == RoleType.SaleAgent.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedSaleAgent().getId();
            url = this.url + "/currentlyLeased/forSaleAgent/" + id;
        }

        getAll(url, allLeasingContracts);

        return allLeasingContracts;
    }

    public MutableLiveData<List<LeasingContract>> getEndingSoonContracts(
            int roleType
    ) {
        endingSoonLeasingContracts = new MutableLiveData<>();

        String url = "";

        if (roleType == RoleType.Admin.getValue()) {
            url = this.url;
        }
        else if (roleType == RoleType.Owner.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedOwner().getId();
            url = this.url + "/endingSoon/forOwner/" + id;
        }
        else if (roleType == RoleType.SaleAgent.getValue()) {
            long id = AppRequestQueue.getToken().getUser().getRelatedSaleAgent().getId();
            url = this.url + "/endingSoon/forSaleAgent/" + id;
        }

        getAll(url, endingSoonLeasingContracts);

        return endingSoonLeasingContracts;
    }

    private void getAll(String url, MutableLiveData<List<LeasingContract>> leasingContracts) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    Type listType = new TypeToken<List<LeasingContract>>(){}.getType();
                    List<LeasingContract> leaseContracts = gson.fromJson(response.toString(), listType);
                    leasingContracts.setValue(leaseContracts);
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

    private JSONObject getJsonObject(LeasingContract leaseContract) {
        JSONObject requestBody = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder
                    .registerTypeAdapter(OffsetDateTime.class, new JsonSerializer() {
                        @Override
                        public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
                            OffsetDateTime date = (OffsetDateTime) src;
                            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_INSTANT));
                        }
                    }).create();
            requestBody = new JSONObject(gson.toJson(leaseContract));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

    private Gson buildGson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .registerTypeAdapter(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
                    @Override
                    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {
                        return OffsetDateTime.parse(json.getAsString());
                    }
                }).create();

        return gson;
    }
}
