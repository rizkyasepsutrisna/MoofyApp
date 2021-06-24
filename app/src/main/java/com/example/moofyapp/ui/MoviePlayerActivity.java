package com.example.moofyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.moofyapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MoviePlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ProgressBar progressBar;
    private ImageView btFullScreen;
    private SimpleExoPlayer simpleExoPlayer;
    private boolean flag = false;
    private String links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);
        Bundle extras = getIntent().getExtras();
        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullScreen = findViewById(R.id.bt_fullscreen);
        links = extras.getString("DATA_LINK");

        //Buat Activity FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Video Url
        Uri videoUrl = Uri.parse(links);

        //Install Load Control
        LoadControl loadControl = new DefaultLoadControl();

        //Install Band width meter
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        //Install Track Selector
        TrackSelector trackSelector = new DefaultTrackSelector(new
                AdaptiveTrackSelection.Factory(bandwidthMeter));

        //Install Simple Exoplayer
        SimpleExoPlayer simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(MoviePlayerActivity.this,
                trackSelector, loadControl);

        //Install data source Factory
        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        //Install exractors Factory
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        //Install Media Source
        MediaSource mediaSource = new ExtractorMediaSource(videoUrl
                , factory, extractorsFactory, null, null);

        //Set Player
        playerView.setPlayer(simpleExoPlayer);

        //Keep Screen On
        playerView.setKeepScreenOn(true);

        //Prepare Media
        simpleExoPlayer.prepare(mediaSource);

        //Play Video when Ready
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                //Cek Kondisi
                if (playbackState == Player.STATE_BUFFERING) {
                    //Ketika Buffring
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    //Ketika Ready
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });


        btFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cek Kondisi
                if (flag) {
                    btFullScreen.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_fullscreen));
                    //set Potrait Orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //set flag issue
                    flag = false;
                } else {
                    btFullScreen.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_fullscreen_exit));
                    //set Potrait Orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //set flag issue
                    flag = true;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Stop Video when Ready
        simpleExoPlayer.setPlayWhenReady(false);
        //Get Playback State
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //play Video when Ready
        simpleExoPlayer.setPlayWhenReady(true);
        //get Playback State
        simpleExoPlayer.getPlaybackState();
    }
}