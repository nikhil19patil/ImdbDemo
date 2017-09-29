package com.example.imdbdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imdbdemo.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 11/9/17.
 */

public class MovieDetailsFragment extends Fragment {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tv_prev)
    TextView tvPrev;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    ArrayList<Movie> movies;
    int position;
    OnWebViewUrlChanged onWebViewUrlChanged;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWebViewUrlChanged = (OnWebViewUrlChanged) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_details,
                container, false);
        ButterKnife.bind(this, view);

        try {
            movies = getArguments().getParcelableArrayList("movies");
            position = getArguments().getInt("position");
        } catch (Exception e) {
            e.printStackTrace();
        }

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(movies.get(position).getWebUrl());
        onWebViewUrlChanged.changeToolbarTitle(movies.get(position).getMovieName());
        if (position == 0) {
            tvPrev.setTextColor(ActivityCompat.getColor(getActivity(), R.color.grey));
        } else if (position == movies.size() - 1) {
            tvNext.setTextColor(ActivityCompat.getColor(getActivity(), R.color.grey));
        }

        tvPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position - 1 >= 0) {
                    position -= 1;
                    webView.loadUrl(movies.get(position).getWebUrl());
                    onWebViewUrlChanged.changeToolbarTitle(movies.get(position).getMovieName());
                } else {
                    Toast.makeText(getActivity(), "Not any previous info to load",
                            Toast.LENGTH_SHORT).show();
                }

                tvNext.setTextColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));

                if (position == 0) {
                    tvPrev.setTextColor(ActivityCompat.getColor(getActivity(), R.color.grey));
                } else {
                    tvPrev.setTextColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));
                }
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position + 1 < movies.size()) {
                    position += 1;
                    webView.loadUrl(movies.get(position).getWebUrl());
                    onWebViewUrlChanged.changeToolbarTitle(movies.get(position).getMovieName());
                } else {
                    Toast.makeText(getActivity(), "Not any next info to load",
                            Toast.LENGTH_SHORT).show();
                }

                tvPrev.setTextColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));

                if (position == movies.size() - 1) {
                    tvNext.setTextColor(ActivityCompat.getColor(getActivity(), R.color.grey));
                } else {
                    tvNext.setTextColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));
                }
            }
        });

        return view;
    }

    public interface OnWebViewUrlChanged {
        void changeToolbarTitle(String title);
    }
}