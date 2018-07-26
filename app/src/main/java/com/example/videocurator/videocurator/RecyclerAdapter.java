package com.example.videocurator.videocurator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.videocurator.videocurator.Activity.MainActivity;
import com.example.videocurator.videocurator.VideoClasses.VideoDataList;
import com.example.videocurator.videocurator.VideoClasses.VideoDataSnippet;
import com.example.videocurator.videocurator.VideoSearchClasses.VideoItemSnippet;
import com.example.videocurator.videocurator.VideoSearchClasses.VideoList;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<VideoDataList> vidlist;
    private int width;
    private String TAG = "RecyclerAdapter";


    public RecyclerAdapter(List<VideoDataList> videoData, MainActivity mainActivity,int width){
        Log.d(TAG,"Data recieved: "+videoData.get(0).getId());
        this.vidlist = videoData;
        this.mContext = mainActivity;
        this.width = width;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vid_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoDataSnippet snippet = vidlist.get(position).getVideoDataSnippet();
       // VideoItemSnippet snippet = vidlist.get(position).getSnippet();

        holder.vidtitle.setText(snippet.getTitle());
        Log.d(TAG,"Video Title"+snippet.getTitle()+" "+snippet.getVideoDataSnippetThumbnails().getVideoDataThumbHigh());
        holder.vidlikes.setText(String.valueOf(vidlist.get(position).getVideoStatistic().getViewCount()));
        Uri myUri = Uri.parse(String.valueOf(snippet.getVideoDataSnippetThumbnails().getVideoDataThumbHigh().getUrl()));
        Glide.with(mContext).load(myUri).into(holder.vidthumb);
        holder.vidthumb.requestLayout();
        holder.vidthumb.getLayoutParams().height = width-100;
        holder.vidthumb.getLayoutParams().width = width-50;

    }

    @Override
    public int getItemCount() {
        return vidlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView vidthumb;
        TextView vidtitle;
        TextView vidlikes;
        Button vidlike;
        Button vidcomment;

        public MyViewHolder(View itemView) {
            super(itemView);
            vidthumb = itemView.findViewById(R.id.vidthumbnail);
            vidtitle = itemView.findViewById(R.id.vidtitle);
            vidlikes = itemView.findViewById(R.id.vidlikes);
            vidlike = itemView.findViewById(R.id.vidlikebutton);
            vidcomment = itemView.findViewById(R.id.vidcommentbutton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        String vidid = vidlist.get(pos).getId();
                        String url = "https://www.youtube.com/watch?v="+vidid;
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
