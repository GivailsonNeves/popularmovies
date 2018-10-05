package br.com.givailson.popularmoviesapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.adapters.MovieGridAdapter;
import br.com.givailson.popularmoviesapp.models.Movie;
import br.com.givailson.popularmoviesapp.services.MovieService;
import br.com.givailson.popularmoviesapp.views.MovieActivity;

public class GrideMovieFragment extends Fragment implements AdapterView.OnItemClickListener, MovieService.MovieServiceCallBack {

    private MovieGridAdapter movieGridAdapter;
    private GridView moviesGridView;

    private List<Movie> movies = new ArrayList<Movie>();

    public GrideMovieFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_grid_movie, container, false);
        moviesGridView = rootView.findViewById(R.id.gv_movies_list);
        movieGridAdapter = new MovieGridAdapter(getActivity(), this.movies);
        moviesGridView.setAdapter(movieGridAdapter);
        moviesGridView.setOnItemClickListener(this);

        new MovieService(this).execute("popular");

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intentMovie = new Intent(this.getActivity(), MovieActivity.class);
        intentMovie.putExtra("movie", this.movies.get(position));
        startActivity(intentMovie);
    }

    @Override
    public void onComplete(List<Movie> movies) {
        this.movies = movies;
        movieGridAdapter = new MovieGridAdapter(getActivity(), this.movies);
        moviesGridView.setAdapter(movieGridAdapter);
    }

    public void orderBy(int itemId) {
        if(itemId == R.id.mn_popularity) {
            new MovieService(this).execute("popular");
        }else if(itemId == R.id.mn_rate) {
            new MovieService(this).execute("top_rated");
        }
    }
}
