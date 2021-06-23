package com.example.moofyapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moofyapp.R;
import com.example.moofyapp.adapters.MovieAdapter;
import com.example.moofyapp.adapters.MovieItemClickListener;
import com.example.moofyapp.adapters.SliderPagerAdapter;
import com.example.moofyapp.models.Movie;
import com.example.moofyapp.models.Slide;
import com.example.moofyapp.response.ResponseFilm;
import com.example.moofyapp.utils.DataSource;
import com.example.moofyapp.utils.SharedPrefManager;
import com.example.moofyapp.utils.api.BaseApiServices;
import com.example.moofyapp.utils.api.UtilsApi;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Slide> lstSlides ;
    private List<Movie> movies = new ArrayList<>();
    private ViewPager sliderpager;
    private MovieAdapter adapterMovie;
    private TabLayout indicator;
    private RecyclerView MoviesRV,  moviesRvWeek ;
    private Context mContext;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sliderpager = findViewById(R.id.slider_pager) ;
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        moviesRvWeek = findViewById(R.id.rv_movies_week);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        adapterMovie = new MovieAdapter(this, movies);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        MoviesRV.setLayoutManager(mLayoutManager);
        MoviesRV.setItemAnimator(new DefaultItemAnimator());
        moviesRvWeek.setLayoutManager(mLayoutManager2);
        moviesRvWeek.setItemAnimator(new DefaultItemAnimator());

        getResultMovie();




        iniSlider();
//        iniPopularMovies();
//        iniWeekMovies();
    }

    private void getResultMovie(){
        mApiService.getMovie().enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                if (response.isSuccessful()){
                    final List<Movie> movies = response.body().getMovies();

                    MoviesRV.setAdapter(new MovieAdapter(mContext, movies));
                    moviesRvWeek.setAdapter(new MovieAdapter(mContext, movies));
                    adapterMovie.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void iniWeekMovies() {
//        // Recyclerview Setup
//        // ini data
//        MovieAdapter weekMovieAdapter = new MovieAdapter(this, DataSource.getWeekMovies(),this);
//        moviesRvWeek.setAdapter(weekMovieAdapter);
//        moviesRvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//    }

//    private void iniPopularMovies() {
//        // Recyclerview Setup
//        // ini data
//        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(),this);
//        MoviesRV.setAdapter(movieAdapter);
//        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//    }

    private void iniSlider() {
        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.aquaman,"Aquaman"));
        lstSlides.add(new Slide(R.drawable.birdsofprey,"Birds Of Prey"));
        lstSlides.add(new Slide(R.drawable.icarealot,"I Care A lot"));
        lstSlides.add(new Slide(R.drawable.knivesout,"Knives Out"));
        lstSlides.add(new Slide(R.drawable.fastfurious,"Fast Furious"));
        lstSlides.add(new Slide(R.drawable.deadpool,"Dead Pool"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlides);
        sliderpager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
    }



//    @Override
//    public void onMovieClick(Movie movie, ImageView movieImageView) {
//        // here we send movie information to detail activity
//        // also we ll create the transition animation between the two activity
//
//        Intent intent = new Intent(this,MovieDetailActivity.class);
//        // send movie information to deatilActivity
//        intent.putExtra("title",movie.getTitle());
//        intent.putExtra("imgURL",movie.getThumbnail());
//        intent.putExtra("drawable/imgCover",movie.getCoverPhoto());
//        // lets crezte the animation
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
//                movieImageView,"sharedName");
//
//        startActivity(intent,options.toBundle());
//
//
//
//        // i l make a simple test to see if the click works
//
//        Toast.makeText(this,"item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();
//        // it works great
//
//
//    }

    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<lstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }
}