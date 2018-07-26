package com.example.videocurator.videocurator.VideoSearchClasses;

import com.google.gson.annotations.SerializedName;

public class VideoItemId {
    @SerializedName("kind")
    private String kind;
    @SerializedName("videoId")
    private String etag;

    public VideoItemId(){

    }

    public VideoItemId(String kind,String etag){
        this.kind = kind;
        this.etag = etag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
