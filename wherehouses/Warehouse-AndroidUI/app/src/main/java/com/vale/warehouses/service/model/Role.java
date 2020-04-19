package com.vale.warehouses.service.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

import java.io.Serializable;

public class Role extends JSONObject implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Override
//    public boolean equals(@Nullable Role obj) {
//        return super.equals(obj);
//    }
}
