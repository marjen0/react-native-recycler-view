package com.reactlibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.reactlibrary.Model.Movie;

import java.util.ArrayList;
import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies;
    private ThemedReactContext mThemedReactContext;
    private int mRecyclerId;
    private static ClickListener clickListener;

    public MoviesAdapter(ThemedReactContext context, int recyclerId) {
        mThemedReactContext = context;
        mRecyclerId = recyclerId;
    }
    public ThemedReactContext getReactContext() {
        return  mThemedReactContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), mMovies, mThemedReactContext, mRecyclerId);
        }
    }

    public Movie getMovie(int index){
        return mMovies.get(index);
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        MoviesAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(int position, ArrayList<Movie> movies, ThemedReactContext themedReactContext, int recyclerId);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        ImageView image = holder.myImageView;
        new DownloadImageTask(image).execute(movie.getPosterSrc());
    }
    @Override
    public int getItemCount() {
        return mMovies.size();
    }
    public void setMovies(ArrayList<Movie> mMovies) {
        this.mMovies = mMovies;
    }
}