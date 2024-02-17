package com.android.tmdb_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tmdb_java.R;
import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.view.MovieActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;

    // Constructor to initialize context and movieArrayList
    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Bind data to ViewHolder
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.rate.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));

        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();

        // Load image using Glide library
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    // ViewHolder class to hold and manage views for each item
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView movieTitle, rate;
        public ImageView movieImage;

        // Constructor to initialize views and set onClickListener
        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.ivMovie);
            rate = itemView.findViewById(R.id.tvRating);
            movieTitle = itemView.findViewById(R.id.tvTitle);

            // Set onClickListener to handle item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the position of the clicked item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the selected movie
                        Movie selectedMovie = movieArrayList.get(position);
                        // Create an Intent to open MovieActivity and pass selected movie as extra
                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        // Start the activity
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
