package com.satyaraj.app.nytimes.pojo;

import com.google.gson.annotations.SerializedName;

public class Multimedia {
    @SerializedName("url")
    public String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
