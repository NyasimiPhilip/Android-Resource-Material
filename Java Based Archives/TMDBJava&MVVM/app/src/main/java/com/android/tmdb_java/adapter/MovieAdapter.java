package com.android.tmdb_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tmdb_java.R;
import com.android.tmdb_java.databinding.MovieListItemBinding;
import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.view.MovieActivity;
import com.bumptech.glide.Glide;

// Adapter for displaying movies in a RecyclerView
public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    // Method to create ViewHolder
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the movie list item layout using DataBinding
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(movieListItemBinding); // Return the ViewHolder
    }

    // Method to bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position); // Get the movie at the specified position
        if (movie != null) {
            String imagePath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();

            holder.movieListItemBinding.setMovie(movie); // Bind movie data to the ViewHolder using DataBinding
            Glide.with(holder.itemView.getContext()) // Load movie image using Glide library
                    .load(imagePath)
                    .placeholder(R.drawable.loading) // Placeholder image while loading
                    .into(holder.movieListItemBinding.ivMovie); // Set image to ImageView
        }
    }

    // ViewHolder class for movie items
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            // Handle item click
            movieListItemBinding.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie selectedMovie = getItem(position); // Get the selected movie
                    // Create an intent to open MovieActivity and pass the selected movie
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", selectedMovie);
                    context.startActivity(intent); // Start MovieActivity
                }
            });
        }
    }
}


/*public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final ArrayList<Movie> movieArrayList;
    private Context context;

    // Constructor to initialize the adapter
    public MovieAdapter(Context context) {
        this.context = context;
        this.movieArrayList = new ArrayList<>();
    }

    // Method to update the movie list
    public void updateMovies(List<Movie> movies) {
        movieArrayList.clear();
        movieArrayList.addAll(movies);
        notifyDataSetChanged();
    }

    // Inflating the layout for each item in the RecyclerView
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(binding);
    }

    // Binding data to the views for each item in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.bind(movie); // Binding movie data to the ViewHolder
        String imagePath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();

        // Load image using Glide library
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.binding.ivMovie);
    }

    // Return the total number of items in the data set
    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    // ViewHolder class to hold references to the views for each item in the RecyclerView
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieListItemBinding binding;

        // Constructor to initialize the ViewHolder
        public MovieViewHolder(@NonNull MovieListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Method to bind movie data to the ViewHolder
        public void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings(); // Ensures the pending bindings are executed immediately

            // Set OnClickListener to handle item click
            binding.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie selectedMovie = movieArrayList.get(position);
                    Intent intent = new Intent(view.getContext(), MovieActivity.class);
                    intent.putExtra("movie", selectedMovie);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}*/
