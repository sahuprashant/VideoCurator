package com.example.videocurator.videocurator.VideoClasses;

import com.google.gson.annotations.SerializedName;

public class VideoDataSnippetThumbnails {
    @SerializedName("high")
    private VideoDataThumbHigh videoDataThumbHigh;

    public VideoDataSnippetThumbnails(){}

    public VideoDataSnippetThumbnails(VideoDataThumbHigh videoDataThumbHigh){
        this.videoDataThumbHigh = videoDataThumbHigh;
    }

    public VideoDataThumbHigh getVideoDataThumbHigh() {
        return videoDataThumbHigh;
    }

    public void setVideoDataThumbHigh(VideoDataThumbHigh videoDataThumbHigh) {
        this.videoDataThumbHigh = videoDataThumbHigh;
    }
}
