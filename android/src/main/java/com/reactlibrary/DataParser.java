package com.reactlibrary;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.reactlibrary.Model.Movie;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public static ArrayList<Movie> parse(ReadableArray readableArray) {
        ArrayList<Movie> parsedMovies = new ArrayList<Movie>();
        for (int i = 0; i<readableArray.size(); i++) {
            ReadableMap movieMap = readableArray.getMap(i);
            Movie movie = new Movie();
            movie.setTitle(movieMap.getString("title"));
            movie.setId(movieMap.getString("_id"));
            movie.setPosterSrc(movieMap.getString("posterSrc"));
            parsedMovies.add(movie);
        }
        return  parsedMovies;
    }
}
