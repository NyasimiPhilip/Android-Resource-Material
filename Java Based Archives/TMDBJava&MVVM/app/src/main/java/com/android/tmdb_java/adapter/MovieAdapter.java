package com.android.tmdb_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tmdb_java.R;
import com.android.tmdb_java.databinding.MovieListItemBinding;
import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.view.MovieActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final ArrayList<Movie> movieArrayList;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        this.movieArrayList = new ArrayList<>();
    }

    public void updateMovies(List<Movie> movies) {
        movieArrayList.clear();
        movieArrayList.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.bind(movie);
        String imagePath = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();

        // Load image using Glide library
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.binding.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private final MovieListItemBinding binding;

        public MovieViewHolder(@NonNull MovieListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();

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
}
