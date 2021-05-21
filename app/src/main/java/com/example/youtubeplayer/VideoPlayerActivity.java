package com.example.youtubeplayer;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoPlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubePlayerView;
    String videoString;
    YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        youtubePlayerView = findViewById(R.id.videoView);
        Intent intent = getIntent();
        videoString = intent.getStringExtra("VIDEO_URL");
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("SUCCESS", " Successfully initialized.");

                //regex stuff is from here: https://stackoverflow.com/questions/7730328/getting-the-youtube-id-from-a-link
                String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(videoString);

                if(matcher.find()){
                    youTubePlayer.loadVideo( matcher.group());
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("FAILURE", "Failed to initialize.");
            }
        };

        youtubePlayerView.initialize(YoutubeConfig.getApiKey(), onInitializedListener);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}