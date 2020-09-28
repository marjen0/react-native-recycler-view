package com.reactlibrary.Model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Movie {
    private String mId;
    private String mTitle;
    private String mPosterSrc;

    public Movie() {}
    public Movie(String id,String title, String posterSrc) {
        mId = id;
        mTitle = title;
        mPosterSrc = posterSrc;
    }
    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }
    public String getTitle() {
        return  mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getPosterSrc() {
        return mPosterSrc;
    }
    public void setPosterSrc(String posterSrc) {
        mPosterSrc = posterSrc;
    }
    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}