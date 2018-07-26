package com.example.videocurator.videocurator.VideoClasses;

import com.google.gson.annotations.SerializedName;

public class VideoDataList {
    @SerializedName("snippet")
    private VideoDataSnippet videoDataSnippet;
    @SerializedName("statistics")
    private VideoStatistic videoStatistic;
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VideoDataList(){}

    public VideoDataList(VideoDataSnippet videoDataSnippet,VideoStatistic videoStatistic,String id){
        this.videoDataSnippet = videoDataSnippet;
        this.videoStatistic = videoStatistic;
        this.id = id;
    }

    public VideoDataSnippet getVideoDataSnippet() {
        return videoDataSnippet;
    }

    public void setVideoDataSnippet(VideoDataSnippet videoDataSnippet) {
        this.videoDataSnippet = videoDataSnippet;
    }

    public VideoStatistic getVideoStatistic() {
        return videoStatistic;
    }

    public void setVideoStatistic(VideoStatistic videoStatistic) {
        this.videoStatistic = videoStatistic;
    }
}
