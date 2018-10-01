package br.com.givailson.popularmoviesapp.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

import br.com.givailson.popularmoviesapp.MainActivity;
import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.models.Movie;

public class MovieGridAdapter implements ListAdapter {

    private List<Movie> movies;
    private Context context;

    public MovieGridAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return this.movies.size();
    }

    @Override
    public Object getItem(int position) {
        return this.movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.movies.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.grid_item_movie, null);

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.movies.size() == 0;
    }
}
