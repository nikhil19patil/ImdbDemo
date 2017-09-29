package com.example.imdbdemo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imdbdemo.db.SqliteDbHelper;
import com.example.imdbdemo.model.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 11/9/17.
 */

public class TopRatedMoviesFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    OnDetailsFragmentOpen onDetailsFragmentOpen;

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onDetailsFragmentOpen = (OnDetailsFragmentOpen) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_rated_movies,
                container, false);
        ButterKnife.bind(this, view);

        try {
            ArrayList<Movie> results = SqliteDbHelper.getInstance(getActivity()).getMovies();
            if (null != results && results.size() != 0) {

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                myRecyclerViewAdapter = new MyRecyclerViewAdapter(getActivity(),
                        results);
                recyclerView.setAdapter(myRecyclerViewAdapter);
                etSearch.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        myRecyclerViewAdapter.getFilter().filter(s);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private int getPath(int thumbnailPathIndex) {
        int resId = R.mipmap.one;
        switch (thumbnailPathIndex) {
            case 1:
                resId = R.mipmap.one;
                break;
            case 2:
                resId = R.mipmap.two;
                break;
            case 3:
                resId = R.mipmap.three;
                break;
            case 4:
                resId = R.mipmap.four;
                break;
            case 5:
                resId = R.mipmap.five;
                break;
            case 6:
                resId = R.mipmap.six;
                break;
            case 7:
                resId = R.mipmap.seven;
                break;
            case 8:
                resId = R.mipmap.eight;
                break;
            case 9:
                resId = R.mipmap.nine;
                break;
            case 10:
                resId = R.mipmap.ten;
                break;
        }
        return resId;
    }

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Movie> tempList = new ArrayList<>();
        ArrayList<Movie> originalList = new ArrayList<>();

        MyRecyclerViewAdapter(Context context, ArrayList<Movie> results) {
            this.context = context;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (results != null) {
                this.tempList = results;
                this.originalList = results;
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Movie movie = tempList.get(position);

            holder.movieName.setText(fromHtml("<b>" + movie.getMovieName() + "</b>&nbsp;&nbsp;(" +
                    movie.getReleaseYear() + ")"));
            holder.movieRating.setText(movie.getRating());
            Glide.with(holder.movieThumbnail.getContext())
                    .load(getPath(movie.getMovieThumbnailPath()))
                    .into(holder.movieThumbnail);
            holder.list_item.setTag(position);
            holder.list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) view.getTag();
                    onDetailsFragmentOpen.openFragment(originalList, pos);
                }
            });
        }

        Filter getFilter() {
            return new Filter() {
                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    tempList = (ArrayList<Movie>) results.values;
                    MyRecyclerViewAdapter.this.notifyDataSetChanged();
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    ArrayList<Movie> filteredResults;
                    if (constraint.length() == 0) {
                        filteredResults = originalList;
                    } else {
                        filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                    }

                    FilterResults results = new FilterResults();
                    results.values = filteredResults;

                    return results;
                }
            };
        }

        ArrayList<Movie> getFilteredResults(String constraint) {
            ArrayList<Movie> results = new ArrayList<>();

            for (Movie item : originalList) {
                if (item.getMovieName().toLowerCase().contains(constraint)) {
                    results.add(item);
                }
            }
            return results;
        }


        @Override
        public int getItemCount() {
            return tempList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item)
        LinearLayout list_item;
        @BindView(R.id.iv_movie_thumbnail)
        ImageView movieThumbnail;
        @BindView(R.id.tv_movie_name)
        TextView movieName;
        @BindView(R.id.tv_rating)
        TextView movieRating;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDetailsFragmentOpen {
        void openFragment(ArrayList<Movie> movies, int position);
    }
}