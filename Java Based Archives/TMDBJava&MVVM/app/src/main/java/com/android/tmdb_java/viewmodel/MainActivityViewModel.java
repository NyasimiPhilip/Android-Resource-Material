package com.android.tmdb_java.viewmodel;

import android.app.Application;

import com.android.tmdb_java.model.Movie;
import com.android.tmdb_java.model.MovieDataSource;
import com.android.tmdb_java.model.MovieDataSourceFactory;
import com.android.tmdb_java.model.MovieRepository;
import com.android.tmdb_java.service.MovieDataService;
import com.android.tmdb_java.service.RetrofitInstance;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;

    LiveData<MovieDataSource> movieDataSourceLiveData; // LiveData for the MovieDataSource
    private Executor executor; // Executor for background tasks
    private LiveData<PagedList<Movie>> moviesPagedList; // LiveData for the PagedList of movies

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepository(application); // Initializing MovieRepository

        MovieDataService movieDataService= RetrofitInstance.getService(); // Getting MovieDataService from RetrofitInstance
        MovieDataSourceFactory factory=new MovieDataSourceFactory(movieDataService,application); // Creating MovieDataSourceFactory
        movieDataSourceLiveData=factory.getMutableLiveData(); // Getting MutableLiveData from MovieDataSourceFactory

        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(true) // Enabling placeholders in the PagedList
                .setInitialLoadSizeHint(10) // Setting the initial load size hint
                .setPageSize(20) // Setting the page size
                .setPrefetchDistance(4) // Setting the prefetch distance
                .build(); // Building the PagedList.Config

        executor= Executors.newFixedThreadPool(5); // Creating an Executor with a fixed thread pool of size 5

        moviesPagedList = (new LivePagedListBuilder<Long,Movie>(factory,config)) // Creating LivePagedListBuilder with MovieDataSourceFactory and PagedList.Config
                .setFetchExecutor(executor) // Setting fetch executor
                .build(); // Building the LiveData of PagedList
    }

    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMutableLiveData(); // Returning LiveData from MovieRepository
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList; // Returning LiveData of PagedList of movies
    }
}
