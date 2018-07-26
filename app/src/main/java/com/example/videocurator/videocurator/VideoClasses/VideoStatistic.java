package com.example.videocurator.videocurator.VideoClasses;

import com.google.gson.annotations.SerializedName;

public class VideoStatistic {
    @SerializedName("viewCount")
    private int viewCount;
    @SerializedName("likeCount")
    private int likeCount;

    public VideoStatistic(){}

    public VideoStatistic(int viewCount,int likeCount){
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
