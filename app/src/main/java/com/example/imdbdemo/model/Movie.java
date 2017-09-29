package com.example.imdbdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dell on 29/9/17.
 */

public class Movie implements Parcelable {

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

    private int id;
    private String movieName;
    private int movieThumbnailPath;
    private String releaseYear;
    private String rating;
    private String webUrl;

    public Movie() {

    }

    protected Movie(Parcel in) {
        id = in.readInt();
        movieThumbnailPath = in.readInt();
        movieName = in.readString();
        releaseYear = in.readString();
        rating = in.readString();
        webUrl = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieThumbnailPath() {
        return movieThumbnailPath;
    }

    public void setMovieThumbnailPath(int movieThumbnailPath) {
        this.movieThumbnailPath = movieThumbnailPath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(movieThumbnailPath);
        parcel.writeString(movieName);
        parcel.writeString(releaseYear);
        parcel.writeString(rating);
        parcel.writeString(webUrl);
    }
}
