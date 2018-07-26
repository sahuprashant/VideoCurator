package com.example.videocurator.videocurator.VideoSearchClasses;

import com.google.gson.annotations.SerializedName;

public class VideoItemSnippet {
    @SerializedName("title")
    private String title;

    public VideoItemSnippet(){}

    public VideoItemSnippet(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
