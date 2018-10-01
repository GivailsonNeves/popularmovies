package br.com.givailson.popularmoviesapp.models;

import java.util.Date;

public class Movie {
    public long id;
    private String title;
    private String uriPhoto;
    private String sinopse;
    private int rate;
    private Date releaseDate;

    public Movie() {}

    public Movie(Long id, String title, String uriPhoto, String sinopse, int rate, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.uriPhoto = uriPhoto;
        this.sinopse = sinopse;
        this.rate = rate;
        this.releaseDate = releaseDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUriPhoto(String uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public String getSinopse() {
        return sinopse;
    }

    public int getRate() {
        return rate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
