package com.example.imdbdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imdbdemo.db.SqliteDbHelper;
import com.example.imdbdemo.helper.Helper;
import com.example.imdbdemo.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        TopRatedMoviesFragment.OnDetailsFragmentOpen, MovieDetailsFragment.OnWebViewUrlChanged {

    final String APP_FIRST_LAUNCH = "appFirstLaunch";
    @BindView(R.id.ll_splash)
    LinearLayout llSplash;
    @BindView(R.id.iv_back)
    ImageView toolbarBack;
    @BindView(R.id.tv_title)
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        llSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                llSplash.setVisibility(View.GONE);
            }
        }, 2000);

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean isFirstStart = getPrefs.getBoolean(APP_FIRST_LAUNCH, true);
        if (isFirstStart) {
            SqliteDbHelper sqliteDbHelper = SqliteDbHelper.getInstance(getApplicationContext());
            ArrayList<Movie> arrayList = Helper.getAllTopRatedMovies();
            for (Movie movie : arrayList) {
                sqliteDbHelper.insertMovie(movie);
            }

            SharedPreferences.Editor e = getPrefs.edit();
            e.putBoolean(APP_FIRST_LAUNCH, false);
            e.apply();
        }

        toolbarTitle.setText(R.string.top_rated_movies);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.ll_container, new TopRatedMoviesFragment()).commit();

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarBack.setVisibility(View.GONE);
                toolbarTitle.setText(R.string.top_rated_movies);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_container, new TopRatedMoviesFragment()).commit();
            }
        });
    }

    @Override
    public void openFragment(ArrayList<Movie> movies, int pos) {
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("movies", movies);
        bundle.putInt("position", pos);
        movieDetailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ll_container, movieDetailsFragment).commit();
        toolbarBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (toolbarBack.getVisibility() == View.VISIBLE) {
            toolbarBack.performClick();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void changeToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }
}
