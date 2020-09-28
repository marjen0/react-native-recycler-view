package com.reactlibrary;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.reactlibrary.Model.Movie;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;


public class MyModuleManager extends ViewGroupManager<RecyclerView> implements MoviesAdapter.ClickListener {

    private static final String REACT_CLASS = "MoviesRecyclerList";
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private ThemedReactContext mReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RecyclerView createViewInstance(ThemedReactContext reactContext) {
        mReactContext = reactContext;
        recyclerView = new RecyclerView(reactContext);
        //recyclerView.setId(RecyclerView.generateViewId());
        LinearLayoutManager layout = new LinearLayoutManager(reactContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);

        return recyclerView;
    }
    @ReactProp(name = "movies")
    public void setMovies(RecyclerView view, @NonNull ReadableArray movies) {
        ArrayList<Movie> parsedMovies = DataParser.parse(movies);
        mAdapter = new MoviesAdapter(mReactContext, recyclerView.getId());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setMovies(parsedMovies);
    }
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onPress", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPress")))
                .build();
    }
    @Override
    public void onItemClick(int position, ArrayList<Movie> movies, ThemedReactContext themedReactContext, int recyclerId) {
        Log.d("onItemClick", "onItemClick positiossssssssssssssn: " + position + " movie: " + mAdapter.getMovie(position).getTitle());
        Log.d("RECYCLER_ID", String.valueOf(recyclerId));
        Log.d("MOVIE", movies.get(position).getTitle());
        WritableMap event = Arguments.createMap();
        Movie movie = movies.get(position);
        event.putString("movie", movie.toJSON());
        themedReactContext.getJSModule(RCTEventEmitter.class).receiveEvent(recyclerId, "onPress", event);
    }

}