package com.example.videocurator.videocurator.VideoClasses;

import com.example.videocurator.videocurator.VideoSearchClasses.VideoList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoLikes {
    @SerializedName("items")
    private List<VideoDataList> items;

    public VideoLikes(){}

    public VideoLikes(List<VideoDataList> items){
        this.items = items;
    }

    public List<VideoDataList> getItems() {
        return items;
    }

    public void setItems(List<VideoDataList> items) {
        this.items = items;
    }
}
