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
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;
import com.vale.warehouses.service.model.LeaseRequest;
import com.vale.warehouses.service.model.RoleType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public class LeaseRequestViewModel extends AndroidViewModel {
    private AppRequestQueue requestQueue;
    private MutableLiveData<List<LeaseRequest>> allLeaseRequests;
    private MutableLiveData<LeaseRequest> oneLeaseRequest;
    private String url = getApplication().getResources().getString(R.string.base_url) + "/api/leaseRequest";
    private MutableLiveData<Boolean> deleteResult;

    public LeaseRequestViewModel(@NonNull Application application) {
        super(application);

        requestQueue = AppRequestQueue.getInstance(application);
    }

    public MutableLiveData<LeaseRequest> getOne(Long leaseContractId) {
        oneLeaseRequest = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            url + "/" + leaseContractId,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeaseRequest.setValue(gson.fromJson(response.toString(), LeaseRequest.class));
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

        return oneLeaseRequest;
    }

    public MutableLiveData<LeaseRequest> insertData(LeaseRequest leaseContract) {
        oneLeaseRequest = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST,
            url,
            getJsonObject(leaseContract),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeaseRequest.setValue(gson.fromJson(response.toString(), LeaseRequest.class));
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

        return oneLeaseRequest;
    }

    public MutableLiveData<LeaseRequest> update(LeaseRequest leaseContract) {
        oneLeaseRequest = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
            url + "/" + leaseContract.getId(),
            getJsonObject(leaseContract),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    Gson gson = buildGson();

                    oneLeaseRequest.setValue(gson.fromJson(response.toString(), LeaseRequest.class));
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

        return oneLeaseRequest;
    }

    private JSONObject getJsonObject(LeaseRequest leaseContract) {
        JSONObject requestBody = null;
        try {
            requestBody = new JSONObject(new Gson().toJson(leaseContract));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

    public MutableLiveData<Boolean> delete(LeaseRequest leaseContract) {
        deleteResult = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.PUT,
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

    public MutableLiveData<List<LeaseRequest>> getAllLeaseRequests(RoleType roleType) {
        allLeaseRequests = new MutableLiveData<>();

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

        return allLeaseRequests;
    }

    public MutableLiveData<List<LeaseRequest>> getAllNotCompleted(Long id) {
        allLeaseRequests = new MutableLiveData<>();

        String url = (id == null )
                ? this.url + "/notCompeted"
                : this.url + "/notCompeted/" + id;

        getAll(url);

        return allLeaseRequests;
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

                    Gson gson = buildGson();

                    Type listType = new TypeToken<List<LeaseRequest>>(){}.getType();
                    List<LeaseRequest> leaseContracts = gson.fromJson(response.toString(), listType);
                    allLeaseRequests.setValue(leaseContracts);
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
