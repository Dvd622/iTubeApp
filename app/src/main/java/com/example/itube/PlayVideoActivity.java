package com.example.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youtubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String url, videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Log.d("PlayVideoActivity", "onCreate: Starting.");
        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        Intent intentReceive = getIntent();
        url = intentReceive.getStringExtra("url");

        videoID = url;

        int vPosition = url.indexOf("v=");
        if (vPosition != -1) {
            videoID = url.split("v=")[1];
            int ampersandPosition = videoID.indexOf("&");
            if (ampersandPosition != -1) {
                videoID = videoID.substring(0, ampersandPosition);
            }
        }

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("PlayVideoActivity", "onCreate: Done initializing");
                youTubePlayer.loadVideo(videoID);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("PlayVideoActivity", "onCreate: Failed to initialize");
            }
        };
        Log.d("PlayVideoActivity", "onCreate: Initializing YouTube Player");
        youtubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);
    }
}
