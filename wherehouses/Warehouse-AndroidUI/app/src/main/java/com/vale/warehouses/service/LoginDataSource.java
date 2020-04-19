//package com.vale.warehouses.service;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.StringRequest;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.vale.warehouses.data.Result;
//import com.vale.warehouses.data.model.Token;
//
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Class that handles authentication w/ login credentials and retrieves user information.
// */
//public class LoginDataSource {
//    private AppRequestQueue requestQueue;
//    // private String baseUrl = getResources().getString(R.string.base_url);
//    private String baseUrl = "http://10.0.2.2:8080/auth";
//    private Token token;
//
//    public LoginDataSource(@NonNull Application application) {
//        requestQueue = AppRequestQueue.getInstance(application);
//    }
//
//
//    public Result<Token> login(final String username, final String password) {
//        try {
//            StringRequest stringRequest = new StringRequest(
//                    Request.Method.POST,
//                    baseUrl + "/login",
//                    new Response.Listener<String >() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            VolleyLog.wtf(response, "utf-8");
//                            GsonBuilder builder = new GsonBuilder();
//                            Gson gson = builder.create();
//
//                            token = gson.fromJson(response, Token.class);
//                        }
//                    }, requestQueue.getErrorListener()) {
//
//                @Override
//                public String getBodyContentType() {
//                    return "application/x-www-form-urlencoded; charset=UTF-8";
//                }
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("username", username);
//                    params.put("password", password);
//                    return params;
//                }
//
//                @Override
//                public Priority getPriority() {
//                    return Priority.IMMEDIATE;
//                }
//            };
//
//            requestQueue.getRequestQueue().add(stringRequest);
//
//            // TODO: handle loggedInUser authentication
////            LoggedInUser fakeUser =
////                    new LoggedInUser(
////                            java.util.UUID.randomUUID().toString(),
////                            "Jane Doe");
//            return new Result.Success<>(token);
//        } catch (Exception e) {
//            return new Result.Error(new IOException("Error logging in", e));
//        }
//    }
//
//    public void logout(String token) {
//        // TODO: revoke authentication
//    }
//}
