package com.vale.warehouses.service;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.vale.warehouses.R;
import com.vale.warehouses.service.model.Token;

import java.util.HashMap;
import java.util.Map;

public class AppRequestQueue {
    private static AppRequestQueue instance;
    private Context context;
    private RequestQueue requestQueue;
    private static Token token;
    public static final String TAG = AppRequestQueue.class.getSimpleName();

    private AppRequestQueue(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static synchronized AppRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new AppRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(context, R.string.no_network, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public Map<String, String> getHeaders() {
        Map<String, String> params = new HashMap<>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("Authorization", "Bearer " + token.getId());
        return params;
    }

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token tokenIn) {
        token = tokenIn;
    }
}
