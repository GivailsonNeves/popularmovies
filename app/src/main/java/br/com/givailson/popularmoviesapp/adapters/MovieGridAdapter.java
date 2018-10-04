package br.com.givailson.popularmoviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.givailson.popularmoviesapp.R;
import br.com.givailson.popularmoviesapp.models.Movie;

public class MovieGridAdapter extends ArrayAdapter<Movie> {


    public MovieGridAdapter(Context context, List<Movie> movies) {
        super(context, 0,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.grid_item_movie, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        Movie movie = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.setMovie(movie);

        return convertView;
    }

    static class ViewHolder {

        TextView tvTitle;

        ViewHolder(View root) {
            tvTitle = root.findViewById(R.id.tv_title);
        }
        void setMovie(Movie movie) {
            tvTitle.setText(movie.getTitle());
        }
    }
}
