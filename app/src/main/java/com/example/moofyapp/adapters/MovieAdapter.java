package com.example.moofyapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moofyapp.R;
import com.example.moofyapp.models.Movie;
import com.example.moofyapp.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    Context context ;
    List<Movie> movies;



    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new MovieHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieholder, int i) {
        movieholder.bind(movies.get(i));
        movieholder.TvTitle.setText(movies.get(i).getName());
        movieholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie1 = movies.get(i);
                Intent moveData = new Intent(context, MovieDetailActivity.class);
                moveData.putExtra("data_movie", movie1);
                context.startActivity(moveData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        final TextView TvTitle;
        final ImageView ImgMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);

        }
        void bind(Movie movie){
            String urlPhoto = "http://182.158.1.108/moofy/gambar/"+ movie.getDir();
            Picasso.get().load(urlPhoto).into(ImgMovie);
        }
    }
    public void setData(ArrayList<Movie> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }
}
