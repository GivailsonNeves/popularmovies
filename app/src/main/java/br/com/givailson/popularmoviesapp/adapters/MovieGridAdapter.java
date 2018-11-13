package br.com.givailson.popularmoviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
        private static final int IMAGE_DEFAULT_HEIGHT = 750;
        private static final int IMAGE_DEFAULT_WIDTH = 500;
        private final String imageBasePath;
        private Context context;
        private ImageView imageView;
        private int windowSize;

        ViewHolder(View root) {

            context = root.getContext();
            imageBasePath = context.getString(R.string.grid_poster_url);
            imageView = root.findViewById(R.id.iv_poster);

            windowSize = root.getResources().getDisplayMetrics().widthPixels;
            final int imageWidthSize = windowSize / 2;
            imageView.getLayoutParams().width = imageWidthSize;
            imageView.getLayoutParams().height = (int) ( IMAGE_DEFAULT_HEIGHT * ((float) imageWidthSize / IMAGE_DEFAULT_WIDTH) );
        }
        void setMovie(Movie movie) {
            String urlPoster = imageBasePath +
                    movie.getUriPhoto();
            Picasso.with(context).load(urlPoster).into(imageView);
        }
    }
}
