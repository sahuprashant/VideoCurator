package com.example.videocurator.videocurator.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.videocurator.videocurator.ApiClient;
import com.example.videocurator.videocurator.Interface.ApiInterface;
import com.example.videocurator.videocurator.R;
import com.example.videocurator.videocurator.RecyclerAdapter;
import com.example.videocurator.videocurator.SharedPrefManager;
import com.example.videocurator.videocurator.UserDetail;
import com.example.videocurator.videocurator.VideoClasses.VideoDataList;
import com.example.videocurator.videocurator.VideoClasses.VideoLikes;
import com.example.videocurator.videocurator.VideoSearchClasses.VideoData;
import com.example.videocurator.videocurator.VideoSearchClasses.VideoList;
import com.google.api.client.util.Joiner;
import com.google.common.collect.ObjectArrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;
    public RecyclerAdapter vidlistadapter;
    Boolean scrolling = false;
    int currentitems,totalitems,scrolleditems;
    ProgressBar mProgressBar;
    public static String TOKEN = "";
    public List<VideoList> videoList;
    public List<VideoDataList> videodatalists,videodatalists1;
    public String TAG = "MainActivity";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    protected Handler handler;
    List<String> videoIds = new ArrayList<String>();
    int height,width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return;
        }
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        handler = new Handler();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mProgressBar = findViewById(R.id.progressbar);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        //vidlistadapter = new RecyclerAdapter(vidlist,this);
        //recyclerView.setAdapter(vidlistadapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentitems = linearLayoutManager.getChildCount();
                totalitems = linearLayoutManager.getItemCount();
                scrolleditems = linearLayoutManager.findFirstVisibleItemPosition();

                if (scrolling && (currentitems + scrolleditems == totalitems)){
                    //fetch data
                    scrolling = false;
                    fetchData();
                }

            }
        });

        Log.d(TAG,"starting adapter: ");
        startAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mainactivitymenu,menu);
        MenuItem  userdetail = menu.findItem(R.id.Userdetails);
        MenuItem logout = menu.findItem(R.id.logout);
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                finish();
                SharedPrefManager.getInstance(MainActivity.this).logout();
                return true;
            }
        });
        userdetail.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(MainActivity.this,UserDetail.class));
                return true;
            }
        });
        return true;
    }

    private void startAdapter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<VideoData> call = service.getVideos("");

        Log.d(TAG,"JSON FILE: "+service.toString());
        call.enqueue(new Callback<VideoData>() {
            @Override
            public void onResponse(Call<VideoData> call, Response<VideoData> response) {
                TOKEN = response.body().getNextPageToken();
                videoList = response.body().getItems();
                for (int i=0;i<10;i++){
                    videoIds.add(videoList.get(i).getId().getEtag());
                }
                Joiner join = Joiner.on(',');
                String videoId = join.join(videoIds);
                Log.d(TAG,"videoIds: "+videoList.get(0).getSnippet().getTitle()+"TOKEN : "+TOKEN);
                ApiClient ApiClient = new ApiClient();
                ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                Call<VideoLikes> callv = service.getVideoData(videoId);
                callv.enqueue(new Callback<VideoLikes>() {
                    @Override
                    public void onResponse(Call<VideoLikes> call, Response<VideoLikes> response) {
                        videodatalists = response.body().getItems();
                        videoIds.clear();
                        if (!(videodatalists == null)) {
                            vidlistadapter = new RecyclerAdapter(videodatalists, MainActivity.this, width);
                            recyclerView.setAdapter(vidlistadapter);
                            //vidlistadapter.notifyDataSetChanged();
                        }
                        Log.d(TAG,"videodatalist: "+videodatalists.get(1).getVideoDataSnippet().getTitle()+" "+videodatalists.get(0).getVideoStatistic().getLikeCount());
                    }

                    @Override
                    public void onFailure(Call<VideoLikes> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error in Fetching Data!!"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<VideoData> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error in Fetching Data!!"+t.getMessage().toString(),Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Error in Fetching data "+t.toString());
            }
        });

    }

    private void fetchData() {
        mProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreVideo(TOKEN);
                mProgressBar.setVisibility(View.GONE);
            }
        },3000);
    }

    private void loadMoreVideo(String tk){

        try{
            ApiClient ApiClient = new ApiClient();
            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            Log.d(TAG,"in try methond TOKEN: "+TOKEN);
            Call<VideoData> call = service.getVideos(tk);
            call.enqueue(new Callback<VideoData>() {
                @Override
                public void onResponse(Call<VideoData> call, Response<VideoData> response) {
                    TOKEN = response.body().getNextPageToken();

                    videoList = response.body().getItems();
                    for (int i=0;i<10;i++){
                        videoIds.add(videoList.get(i).getId().getEtag());
                    }
                    Joiner join = Joiner.on(',');
                    String videoId = join.join(videoIds);
                    Log.d(TAG,"videoId: "+videoIds);
                    ApiClient ApiClient = new ApiClient();
                    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
                    Call<VideoLikes> callv = service.getVideoData(videoId);
                    callv.enqueue(new Callback<VideoLikes>() {
                        @Override
                        public void onResponse(Call<VideoLikes> call, Response<VideoLikes> response) {
                            videodatalists1 = response.body().getItems();
                            //videodatalists.clear();
                            videoIds.clear();
                            videodatalists.addAll(videodatalists1);
                            /*for (int i=0;i<10;i++) {
                                videodatalists.add(videodatalists1.get(i));
                            }
                            for (int i=0;i<10;i++) {
                                videodatalists.add(response.body().getItems().get(i));
                            }*/
                            if (!(videodatalists == null)) {
                                vidlistadapter.notifyDataSetChanged();
                                Log.d(TAG,"videodatalist: "+videodatalists.get(0).getVideoDataSnippet().getTitle()+"videodatalist size: "+videodatalists.size());
                            }
                        }

                        @Override
                        public void onFailure(Call<VideoLikes> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Error in Fetching Data!!"+t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onFailure(Call<VideoData> call, Throwable t) {
                    Toast.makeText(MainActivity.this,"Error in Fetching Data!!"+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,"Error Fetching data! "+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

}