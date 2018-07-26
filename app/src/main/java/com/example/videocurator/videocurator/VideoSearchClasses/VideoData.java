package com.example.videocurator.videocurator.VideoSearchClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoData implements Parcelable {
    @SerializedName("items")
    private List<VideoList> items;
    @SerializedName("nextPageToken")
    private String nextPageToken;

    protected VideoData(Parcel in) {
        this.items = in.createTypedArrayList(VideoList.CREATOR);
        this.nextPageToken = in.readString();
    }

    public static final Parcelable.Creator<VideoData> CREATOR = new Parcelable.Creator<VideoData>() {
        @Override
        public VideoData createFromParcel(Parcel in) {
            return new VideoData(in);
        }

        @Override
        public VideoData[] newArray(int size) {
            return new VideoData[size];
        }
    };

    public List<VideoList> getItems() {
        return items;
    }

    public void setItems(List<VideoList> items) {
        this.items = (List<VideoList>) items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.items);
        parcel.writeString(this.nextPageToken);
    }
}
