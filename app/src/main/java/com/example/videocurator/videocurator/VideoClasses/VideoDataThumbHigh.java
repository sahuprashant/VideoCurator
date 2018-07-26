package com.example.videocurator.videocurator.VideoClasses;

import com.google.gson.annotations.SerializedName;

public class VideoDataThumbHigh {
    @SerializedName("url")
    private String url;

    public VideoDataThumbHigh(){}

    public VideoDataThumbHigh(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
