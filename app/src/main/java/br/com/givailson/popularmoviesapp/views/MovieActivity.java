package br.com.givailson.popularmoviesapp.views;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.models.Movie;


public class MovieActivity extends AppCompatActivity {

    private final String basePathImage = "http://image.tmdb.org/t/p/w500/";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        prepareActionBar();
        prepareMovie();
    }

    private void prepareActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void prepareMovie() {

        this.movie = getIntent().getParcelableExtra("movie");

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvReview = findViewById(R.id.tv_review);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        ImageView ivPoster = findViewById(R.id.iv_movie_poster);

        if(this.movie != null) {

            StringBuilder urlImage = new StringBuilder(basePathImage)
                    .append(movie.getUriPhoto());

            tvTitle.setText(movie.getTitle());
            tvReleaseDate.setText(movie.getReleaseDate());
            tvReview.setText(movie.getSinopse());
            Picasso.with(this).load(urlImage.toString()).into(ivPoster);
        } else {
            tvTitle.setText(R.string.erro_movie_view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
