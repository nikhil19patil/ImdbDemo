package com.example.imdbdemo.helper;

import com.example.imdbdemo.model.Movie;

import java.util.ArrayList;

/**
 * Created by dell on 29/9/17.
 */

public class Helper {

    public static ArrayList<Movie> getAllTopRatedMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setMovieName("The Shawshank Redemption");
        movie.setReleaseYear("1994");
        movie.setRating("9.3");
        movie.setMovieThumbnailPath(1);
        movie.setWebUrl("http://m.imdb.com/title/tt0111161/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_1");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("The Godfather");
        movie.setReleaseYear("1972");
        movie.setRating("9.2");
        movie.setMovieThumbnailPath(2);
        movie.setWebUrl("http://m.imdb.com/title/tt0068646/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_2");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("The Godfather: Part II");
        movie.setReleaseYear("1974");
        movie.setRating("9.0");
        movie.setMovieThumbnailPath(3);
        movie.setWebUrl("http://m.imdb.com/title/tt0071562/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_3");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("The Dark Night");
        movie.setReleaseYear("2008");
        movie.setRating("9.0");
        movie.setMovieThumbnailPath(4);
        movie.setWebUrl("http://m.imdb.com/title/tt0468569/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_4");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("12 Angry Men");
        movie.setReleaseYear("1957");
        movie.setRating("8.9");
        movie.setMovieThumbnailPath(5);
        movie.setWebUrl("http://m.imdb.com/title/tt0050083/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_5");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("Schindler's List");
        movie.setReleaseYear("1993");
        movie.setRating("8.9");
        movie.setMovieThumbnailPath(6);
        movie.setWebUrl("http://m.imdb.com/title/tt0108052/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_6");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("Pulp Fiction");
        movie.setReleaseYear("1994");
        movie.setRating("8.9");
        movie.setMovieThumbnailPath(7);
        movie.setWebUrl("http://m.imdb.com/title/tt0110912/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_7");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("The Lord of the Rings: The Return of the King");
        movie.setReleaseYear("2003");
        movie.setRating("8.9");
        movie.setMovieThumbnailPath(8);
        movie.setWebUrl("http://m.imdb.com/title/tt0167260/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_8");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("The Good, the Bad and the Ugly");
        movie.setReleaseYear("1966");
        movie.setRating("8.9");
        movie.setMovieThumbnailPath(9);
        movie.setWebUrl("http://m.imdb.com/title/tt0060196/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_9");
        movies.add(movie);

        movie = new Movie();
        movie.setMovieName("Fight Club");
        movie.setReleaseYear("1999");
        movie.setRating("8.8");
        movie.setMovieThumbnailPath(10);
        movie.setWebUrl("http://m.imdb.com/title/tt0137523/?pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=2398042122&pf_rd_r=1FJRJBZ3RCCCMKDWK42A&pf_rd_s=top-1&pf_rd_t=15506&pf_rd_i=top&ref_=m_chttp_tt_10");
        movies.add(movie);

        return movies;
    }
}
