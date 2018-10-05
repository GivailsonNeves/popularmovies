package br.com.givailson.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.givailson.popularmoviesapp.fragments.GrideMovieFragment;

public class MainActivity extends AppCompatActivity {

    GrideMovieFragment gmf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gmf = (GrideMovieFragment) getSupportFragmentManager().findFragmentById(R.id.movies_fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        gmf.orderBy(item.getItemId());
        return false;
    }
}
