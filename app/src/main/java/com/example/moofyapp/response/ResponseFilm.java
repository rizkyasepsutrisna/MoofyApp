package com.example.moofyapp.response;

import com.example.moofyapp.models.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFilm {
    @SerializedName("data")
    private List<Movie> movie;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public List<Movie> getMovies(){
        return movie;
    }

    public void setMovie(List<Movie> movie){
        this.movie = movie;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "data{" +
                "movies1 = '" + movie + '\'' +
                ", error = '" + error + '\'' +
                ", message = '" + message + '\'' +
                "}";
    }
}
