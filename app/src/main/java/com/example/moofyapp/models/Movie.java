package com.example.moofyapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("id_movies")
    private String idMovies;

    @SerializedName("fiction")
    private String fiction;

    @SerializedName("adventure")
    private String adventure;

    @SerializedName("horror")
    private String horror;

    @SerializedName("action")
    private String action;

    @SerializedName("thriller")
    private String thriller;

    @SerializedName("comedy")
    private String comedy;

    @SerializedName("criminal")
    private String criminal;

    @SerializedName("fantasy")
    private String fantasy;

    @SerializedName("anime")
    private String anime;

    @SerializedName("sci-fi")
    private String sciFi;

    @SerializedName("mystery")
    private String mystery;

    @SerializedName("drama")
    private String drama;

    @SerializedName("romance")
    private String romance;

    @SerializedName("name")
    private String name;

    @SerializedName("dir")
    private String dir;

    @SerializedName("rdate")
    private String rdate;

    @SerializedName("description")
    private String description;

    @SerializedName("yt_link")
    private String ytLink;

    @SerializedName("rating")
    private String rating;


    public Movie(Parcel in) {
        idMovies = in.readString();
        fiction = in.readString();
        adventure = in.readString();
        horror = in.readString();
        action = in.readString();
        thriller = in.readString();
        comedy = in.readString();
        criminal = in.readString();
        fantasy = in.readString();
        anime = in.readString();
        sciFi = in.readString();
        mystery = in.readString();
        drama = in.readString();
        romance = in.readString();
        name = in.readString();
        dir = in.readString();
        rdate = in.readString();
        description = in.readString();
        ytLink = in.readString();
        rating = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idMovies);
        dest.writeString(fiction);
        dest.writeString(adventure);
        dest.writeString(horror);
        dest.writeString(action);
        dest.writeString(thriller);
        dest.writeString(comedy);
        dest.writeString(criminal);
        dest.writeString(fantasy);
        dest.writeString(anime);
        dest.writeString(sciFi);
        dest.writeString(mystery);
        dest.writeString(drama);
        dest.writeString(romance);
        dest.writeString(name);
        dest.writeString(dir);
        dest.writeString(rdate);
        dest.writeString(description);
        dest.writeString(ytLink);
        dest.writeString(rating);
    }

    public String getIdMovies() {
        return idMovies;
    }

    public String getFiction() {
        return fiction;
    }

    public String getAdventure() {
        return adventure;
    }

    public String getHorror() {
        return horror;
    }

    public String getAction() {
        return action;
    }

    public String getThriller() {
        return thriller;
    }

    public String getComedy() {
        return comedy;
    }

    public String getCriminal() {
        return criminal;
    }

    public String getFantasy() {
        return fantasy;
    }

    public String getAnime() {
        return anime;
    }

    public String getSciFi() {
        return sciFi;
    }

    public String getMystery() {
        return mystery;
    }

    public String getDrama() {
        return drama;
    }

    public String getRomance() {
        return romance;
    }

    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public String getRdate() {
        return rdate;
    }

    public String getDescription() {
        return description;
    }

    public String getYtLink() {
        return ytLink;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovies='" + idMovies + '\'' +
                ", fiction='" + fiction + '\'' +
                ", adventure='" + adventure + '\'' +
                ", horror='" + horror + '\'' +
                ", action='" + action + '\'' +
                ", thriller='" + thriller + '\'' +
                ", comedy='" + comedy + '\'' +
                ", criminal='" + criminal + '\'' +
                ", fantasy='" + fantasy + '\'' +
                ", anime='" + anime + '\'' +
                ", sciFi='" + sciFi + '\'' +
                ", mystery='" + mystery + '\'' +
                ", drama='" + drama + '\'' +
                ", romance='" + romance + '\'' +
                ", name='" + name + '\'' +
                ", dir='" + dir + '\'' +
                ", rdate='" + rdate + '\'' +
                ", description='" + description + '\'' +
                ", ytLink='" + ytLink + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
