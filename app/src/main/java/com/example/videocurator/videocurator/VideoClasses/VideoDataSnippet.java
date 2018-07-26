package com.example.videocurator.videocurator.VideoClasses;

import com.google.gson.annotations.SerializedName;

public class VideoDataSnippet {
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnails")
    private VideoDataSnippetThumbnails videoDataSnippetThumbnails;

    public VideoDataSnippet(){}

    public VideoDataSnippet(String title,VideoDataSnippetThumbnails videoDataSnippetThumbnails){
        this.title = title;
        this.videoDataSnippetThumbnails = videoDataSnippetThumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoDataSnippetThumbnails getVideoDataSnippetThumbnails() {
        return videoDataSnippetThumbnails;
    }

    public void setVideoDataSnippetThumbnails(VideoDataSnippetThumbnails videoDataSnippetThumbnails) {
        this.videoDataSnippetThumbnails = videoDataSnippetThumbnails;
    }
}
