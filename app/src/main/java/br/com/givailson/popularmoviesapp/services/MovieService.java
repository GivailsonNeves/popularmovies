package br.com.givailson.popularmoviesapp.services;


import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.givailson.popularmoviesapp.models.Movie;

public class MovieService extends AsyncTask<String, Void, String> {

    private final static String BASE_MOVIE_API_PATH = "api.themoviedb.org";
    private final static String APP_KEY = "841b5aca9f29628b2f94a63dc8c0e672";
    private MovieServiceCallBack msb;
    private List<Movie> listMovies;

    public MovieService(MovieServiceCallBack msb) {
        this.msb = msb;
    }

    @Override
    protected String doInBackground(String... urls) {
        String servicePath = urls[0];
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https")
            .authority(BASE_MOVIE_API_PATH)
            .appendPath("3")
            .appendPath("movie")
            .appendPath(servicePath)
            .appendQueryParameter("api_key", APP_KEY);

        try{

            this.getResponseFromHttpUrl(new URL(uriBuilder.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return servicePath;
    }

    private void getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                listMovies = new ArrayList<Movie>();

                String res = scanner.next();

                JSONObject mainLevel = new JSONObject(res);

                JSONArray results = mainLevel.getJSONArray("results");

                for ( int i = 0; i < results.length(); i++) {
                    listMovies.add(Movie.fromJSONObject(results.getJSONObject(i)));
                }
            }

        } catch (JSONException je) {
          je.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.msb.onComplete(listMovies);
    }

    public interface MovieServiceCallBack {
        void onComplete(List<Movie> movies);
    }
}
