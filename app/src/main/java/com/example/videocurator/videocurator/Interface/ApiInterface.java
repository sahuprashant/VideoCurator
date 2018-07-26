package com.example.videocurator.videocurator.Interface;

import com.example.videocurator.videocurator.VideoSearchClasses.VideoData;
import com.example.videocurator.videocurator.VideoClasses.VideoLikes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("search?part=snippet&q=Comedy&type=video&maxResults=10&key=AIzaSyAuqcaMwEKCI0KVwycYT3gLXn3ulA2O4io")
    Call<VideoData> getVideos(@Query("pageToken") String token);

    @GET("videos?part=snippet,statistics&key=AIzaSyAuqcaMwEKCI0KVwycYT3gLXn3ulA2O4io")
    Call<VideoLikes> getVideoData(@Query("id") String id);
}
