package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import android.widget.VideoView;

import com.example.dialogcheckinternetconnection.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class LessonFragment extends Fragment {

    private String title, urlVideo, description;

    //    private PlayerView videoPlayer;
    //  private SimpleExoPlayer simpleExoPlayer;

    VideoView videoView;
    private static final String VIDEO_SAMPLE = "tacoma_narrows";

    public LessonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            //   title = getArguments().getString("title");
            urlVideo = getArguments().getString("urlVideo");
            description = getArguments().getString("description");
        }


        // videoPlayer = view.findViewById(R.id.course_vide);
        videoView = view.findViewById(R.id.course_vide);
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        videoView.setVideoURI(videoUri);
        MediaController controller = new MediaController(getContext());
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);

        // setUpPlayer(urlVideo);

    }

    private Uri getMedia(String mediaName) {
        return Uri.parse(urlVideo);

    }


    private void releasePlayer() {
        videoView.stopPlayback();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause();
        }

    }
    /*
    //            xml

    <com.google.android.exoplayer2.ui.PlayerView
        android:id="@+id/course_vide"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        />
     */


/*
    // +"/media/1.mp4"
    private void setUpPlayer(String url) {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
        videoPlayer.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "Dialog Check internet Connectio"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
    }

 */

}