package br.com.givailson.popularmoviesapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
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
    private int selectedFilter;
    private View vNodata;
    private View vLoading;
    private Button btnReload;

    public GrideMovieFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = prepareUI(inflater, container);

        this.orderBy(R.id.mn_popularity);

        return rootView;
    }

    @NonNull
    private View prepareUI(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_grid_movie, container, false);
        moviesGridView = rootView.findViewById(R.id.gv_movies_list);
        movieGridAdapter = new MovieGridAdapter(getActivity(), this.movies);
        moviesGridView.setAdapter(movieGridAdapter);
        moviesGridView.setOnItemClickListener(this);

        vNodata = rootView.findViewById(R.id.v_nodata);
        vLoading = rootView.findViewById(R.id.v_loading);
        btnReload = rootView.findViewById(R.id.btn_reload);

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderBy(selectedFilter);
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("movies", (ArrayList<Movie>) this.movies);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            this.movies = savedInstanceState.getParcelableArrayList("movies");
            mountList();
        }
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
        mountList();
        vLoading.setVisibility(View.GONE);
        moviesGridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String errorMessage) {
        this.showErrorMessage(errorMessage);
    }

    private void mountList() {
        movieGridAdapter = new MovieGridAdapter(getActivity(), this.movies);
        moviesGridView.setAdapter(movieGridAdapter);
    }

    public void orderBy(int itemId) {
        selectedFilter = itemId;
        if (itemId == R.id.mn_popularity) {
            loadMovies("popular");
        } else if (itemId == R.id.mn_rate) {
            loadMovies("top_rated");
        }
    }

    private void loadMovies(String filterString) {
        if (isConnected()) {
            showLoading();
            new MovieService(this, getContext()).execute(filterString);
        } else {
            this.showErrorMessage(getString(R.string.connection_fail));
        }
    }

    private void showLoading() {
        vLoading.setVisibility(View.VISIBLE);
        vNodata.setVisibility(View.GONE);
        moviesGridView.setVisibility(View.GONE);
    }

    private void showErrorMessage(String message) {
        vLoading.setVisibility(View.GONE);
        vNodata.setVisibility(View.VISIBLE);
        Toast.makeText(this.getContext(),message, Toast.LENGTH_LONG)
                .show();
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getContext()
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnected();
    }
}
