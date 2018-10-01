package br.com.givailson.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.com.givailson.popularmoviesapp.adapters.MovieGridAdapter;
import br.com.givailson.popularmoviesapp.models.Movie;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView moviesGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMovieGrid();
    }

    private void prepareMovieGrid() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());
        movieList.add(new Movie());

        moviesGrid = findViewById(R.id.gv_movies_list);
        moviesGrid.setAdapter(new MovieGridAdapter(this, movieList));
        moviesGrid.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
