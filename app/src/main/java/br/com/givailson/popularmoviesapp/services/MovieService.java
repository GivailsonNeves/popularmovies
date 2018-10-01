package br.com.givailson.popularmoviesapp.services;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class MovieService extends AsyncTask<URL, Void, String> {
    MovieServiceCallBack msb;

    MovieService(MovieServiceCallBack msb) {
        this.msb = msb;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL servicePath = urls[0];
        String movieResults = null;
        try{
            movieResults = NetworkUtils.getResponseFromHttpUrl(servicePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieResults;
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
