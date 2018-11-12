package br.com.givailson.popularmoviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {

    public long id;
    private String title;
    private String uriPhoto;
    private String sinopse;
    private double rate;
    private String releaseDate;

    public Movie () {}

    public Movie(long id, String title, String uriPhoto, String sinopse, double rate, String releaseDate) {
        this.id = id;
        this.title = title;
        this.uriPhoto = uriPhoto;
        this.sinopse = sinopse;
        this.rate = rate;
        this.releaseDate = releaseDate;
    }

    public Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        uriPhoto = in.readString();
        sinopse = in.readString();
        rate = in.readInt();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(uriPhoto);
        dest.writeString(sinopse);
        dest.writeDouble(rate);
        dest.writeString(releaseDate);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static Movie fromJSONObject(JSONObject jsonObject) throws JSONException {

        return new Movie(
            jsonObject.getLong("id"),
            jsonObject.getString("title"),
            jsonObject.getString("poster_path"),
            jsonObject.getString("overview"),
            jsonObject.getDouble("vote_average"),
            jsonObject.getString("release_date")
        );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public void setUriPhoto(String uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
