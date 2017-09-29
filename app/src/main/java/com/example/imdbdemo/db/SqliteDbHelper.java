package com.example.imdbdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.imdbdemo.model.Movie;

import java.util.ArrayList;

/**
 * Created by dell on 10/9/17.
 */

public class SqliteDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "imdbdemo";

    //table names
    private static final String TABLE_MOVIES = "movies";

    // results table columns
    private static final String ID = "id";
    private static final String MOVIE_NAME = "moviename";
    private static final String RELEASE_YEAR = "releaseyear";
    private static final String RATING = "rating";
    private static final String WEB_URL = "weburl";
    private static final String MOVIE_THUMBNAIL = "thumbnail";

    private static final String CREATE_TABLE_MOVIES = "create table " + TABLE_MOVIES + "(" +
            ID + " INTEGER PRIMARY KEY, " + MOVIE_NAME + " TEXT, " + MOVIE_THUMBNAIL + " INTEGER, "
            + RELEASE_YEAR + " TEXT, " + RATING + " TEXT, " + WEB_URL + " TEXT);";
    private static SqliteDbHelper sqliteDbHelper;
    private static int VERSION = 1;

    private SqliteDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static SqliteDbHelper getInstance(Context context) {
        if (sqliteDbHelper == null) {
            sqliteDbHelper = new SqliteDbHelper(context);
        }
        return sqliteDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void insertMovie(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, movie.getMovieName());
        contentValues.put(RELEASE_YEAR, movie.getReleaseYear());
        contentValues.put(RATING, movie.getRating());
        contentValues.put(WEB_URL, movie.getWebUrl());
        contentValues.put(MOVIE_THUMBNAIL, movie.getMovieThumbnailPath());
        getReadableDatabase().insert(TABLE_MOVIES, null, contentValues);
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> results = null;
        String queryStr = "select * from " + TABLE_MOVIES;
        Cursor cursor = getReadableDatabase().rawQuery(queryStr, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                results = new ArrayList<>();
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    movie.setMovieName(cursor.getString(cursor.getColumnIndex(MOVIE_NAME)));
                    movie.setReleaseYear(cursor.getString(cursor.getColumnIndex(RELEASE_YEAR)));
                    movie.setRating(cursor.getString(cursor.getColumnIndex(RATING)));
                    movie.setWebUrl(cursor.getString(cursor.getColumnIndex(WEB_URL)));
                    movie.setMovieThumbnailPath(cursor.getInt(cursor.getColumnIndex(MOVIE_THUMBNAIL)));
                    results.add(movie);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return results;
    }

    /*public ArrayList<Movie> getMoviesMatchingQuery(String searchQuery) {
        ArrayList<Movie> results = new ArrayList<>();
        String queryStr = "select * from " + TABLE_MOVIES + " where " + MOVIE_NAME +
                " LIKE %'" + searchQuery + "%'";
        Cursor cursor = getReadableDatabase().rawQuery(queryStr, null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Movie movie = new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    movie.setMovieName(cursor.getString(cursor.getColumnIndex(MOVIE_NAME)));
                    movie.setReleaseYear(cursor.getString(cursor.getColumnIndex(RELEASE_YEAR)));
                    movie.setRating(cursor.getString(cursor.getColumnIndex(RATING)));
                    movie.setWebUrl(cursor.getString(cursor.getColumnIndex(WEB_URL)));
                    results.add(movie);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        return results;
    }*/
}
