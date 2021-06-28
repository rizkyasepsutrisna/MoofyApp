package com.example.moofyapp.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moofyapp.R;
import com.bumptech.glide.Glide;
import com.example.moofyapp.models.Movie;
import com.example.moofyapp.utils.SharedPrefManager;
import com.example.moofyapp.utils.api.BaseApiServices;
import com.example.moofyapp.utils.api.UtilsApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String DATA_MOVIE = "data_movie";
    private String link;
    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private Movie movie;
    private SharedPrefManager sharedPrefManager;
    private BaseApiServices mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        sharedPrefManager = new SharedPrefManager(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;
        movie = getIntent().getParcelableExtra(DATA_MOVIE);

        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        //int imageResourceId = getIntent().getExtras().getInt("imgURL");
        //int imagecover = getIntent().getExtras().getInt("drawable/imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        //Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
       // MovieThumbnailImg.setImageResource(imageResourceId);
        //MovieCoverImg = findViewById(R.id.imageView);
        //Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movie.getName());
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(movie.getDescription());
        // setup animation
        //MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        //yt link
        link = movie.getYtLink();
        // Ganti API sesuai ip address Masing"
        String urlPhoto = "http://182.158.1.108/moofy/gambar/"+ movie.getDir();
        Picasso.get().load(urlPhoto).into(MovieThumbnailImg);

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoviePlayerActivity.class);
                intent.putExtra("DATA_LINK", link);
                startActivity(intent);
            }
        });

    }


}
