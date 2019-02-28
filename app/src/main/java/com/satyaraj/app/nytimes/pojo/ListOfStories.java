package com.satyaraj.app.nytimes.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfStories {

    @SerializedName("results")
    List<Stories> stories;

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

}
