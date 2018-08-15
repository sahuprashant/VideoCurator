# VideoCurator
## A short Demo of using Youtube API in your app and fetching video details in your app

This Demo uses the endless scrolling of RecyclerView to show the feed of Youtube videos on specific topic. Following steps have been
followed while fetching the videos from Youtube:
- First I have to generate the API key from [Google Console](https://console.developers.google.com/)
- Using that API key and my search query in URL 'https://www.googleapis.com/youtube/v3/search' I made http request using Retrofit library to fetch the video details.
- After getting the details in json format I converted it into object format again using Retrofit library.
- These [parameters](https://developers.google.com/youtube/v3/docs/search/list#request) can be queried using above URL.
- To get the details(e.g. likeCount,viewCount,etc.) of each video following URL can be used 'https://www.googleapis.com/youtube/v3/videos' with the ID of particular video

Details of each video was then binded in each Item of Viewholder.
