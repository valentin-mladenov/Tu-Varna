package com.vale.warehouses.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.app.Application;
import android.util.Patterns;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.vale.warehouses.data.model.Token;
import com.vale.warehouses.data.Result;
import com.vale.warehouses.data.model.LoggedInUser;
import com.vale.warehouses.R;
import com.vale.warehouses.service.AppRequestQueue;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    //private LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        //this.loginRepository = LoginRepository.getInstance(new LoginDataSource(application));
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "http://10.0.2.2:8080/auth/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        VolleyLog.wtf(response, "utf-8");
                        GsonBuilder builder = new GsonBuilder();

                        Gson gson = builder
                            .registerTypeAdapter(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
                            @Override
                            public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                    throws JsonParseException {
                                return OffsetDateTime.parse(json.getAsString());
                            }
                        }).create();

                        Token token = gson.fromJson(response, Token.class);

                        loginResult.setValue(new LoginResult(token));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplication(), R.string.no_network, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        AppRequestQueue.getInstance(getApplication()).getRequestQueue().add(stringRequest);

        // can be launched in a separate asynchronous job
//        Result<Token> result = loginRepository.login(username, password);
//
//        if (result instanceof Result.Success) {
//            Token data = ((Result.Success<Token>) result).getData();
//            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getUser().getUserName())));
//        } else {
//            loginResult.setValue(new LoginResult(R.string.login_failed));
//        }




    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
