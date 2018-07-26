package com.example.videocurator.videocurator.VideoSearchClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.services.youtube.model.VideoStatistics;
import com.google.gson.annotations.SerializedName;

public class VideoList implements Parcelable {
    @SerializedName("kind")
    private String kind;
    @SerializedName("etag")
    private String etag;
    @SerializedName("id")
    private VideoItemId id;
    @SerializedName("snippet")
    private VideoItemSnippet snippet;


    public VideoItemSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(VideoItemSnippet snippet) {
        this.snippet = snippet;
    }

    protected VideoList(Parcel in) {
        kind = in.readString();
        etag = in.readString();
    }

    public static final Creator<VideoList> CREATOR = new Creator<VideoList>() {
        @Override
        public VideoList createFromParcel(Parcel in) {
            return new VideoList(in);
        }

        @Override
        public VideoList[] newArray(int size) {
            return new VideoList[size];
        }
    };

    public VideoItemId getId() {
        return id;
    }

    public void setId(VideoItemId id) {
        this.id = id;
    }

    public VideoList(String kind, String etag, VideoItemId id, VideoItemSnippet snippet){
        this.kind = kind;
        this.etag = etag;
        this.id = id;
        this.snippet = snippet;
    }

    public VideoList(){}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kind);
        parcel.writeString(etag);
    }
}
