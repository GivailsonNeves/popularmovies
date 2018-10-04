package br.com.givailson.popularmoviesapp.services;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MovieService extends AsyncTask<URL, Void, String> {

    private final static String BASE_MOVIE_API_PATH = "";
    MovieServiceCallBack msb;

    MovieService(MovieServiceCallBack msb) {
        this.msb = msb;
    }

    public String listPopularMoviews() {
        Uri uriBuilder = Uri.parse(BASE_MOVIE_API_PATH)
                .buildUpon()
                .build();

        try {
            URL url = new URL(uriBuilder.toString());
            return this.doInBackground(url);
        }catch (MalformedURLException me) {
            me.printStackTrace();
        }
        return null;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL servicePath = urls[0];
        String movieResults = null;
        try{
            movieResults = this.getResponseFromHttpUrl(servicePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieResults;
    }

    private String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.msb.onComplete(s);
    }

    interface MovieServiceCallBack {
        void onComplete(String s);
    }
}
