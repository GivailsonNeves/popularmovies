package br.com.givailson.popularmoviesapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        this.movie = getIntent().getParcelableExtra("movie");
        prepareMovie();
    }

    private void prepareMovie() {
        Log.i("yago", this.movie.getTitle());
    }
}
