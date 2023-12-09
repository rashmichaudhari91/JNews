package com.rashmi.jnews.model;

import com.google.gson.annotations.SerializedName;


public class Source {

    @SerializedName("totalResults")
    private String name;

    @SerializedName("articles")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
