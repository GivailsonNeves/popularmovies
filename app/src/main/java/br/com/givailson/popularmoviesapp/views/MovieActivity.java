package br.com.givailson.popularmoviesapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.models.Movie;
import br.com.givailson.popularmoviesapp.utils.Util;

public class MovieActivity extends AppCompatActivity {

    private final String basePathImage = "http://image.tmdb.org/t/p/w500/";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        this.movie = getIntent().getParcelableExtra("movie");
        prepareMovie();
    }

    private void prepareMovie() {
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvReview = findViewById(R.id.tv_review);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        ImageView ivPoster = findViewById(R.id.iv_movie_poster);
        StringBuilder urlImage = new StringBuilder(basePathImage)
                .append(movie.getUriPhoto());

        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(Util.datoToBRString(movie.getReleaseDate()));
        tvReview.setText(movie.getSinopse());
        Picasso.with(this).load(urlImage.toString()).into(ivPoster);

    }
}
