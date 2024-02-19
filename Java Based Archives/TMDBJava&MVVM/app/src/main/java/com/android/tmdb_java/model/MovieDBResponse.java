package com.android.tmdb_java.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

// MovieDBResponse class represents the response from the movie database API
public class MovieDBResponse implements Parcelable {

    // Instance variables representing various attributes of the response
    @SerializedName("page")
    @Expose
    private Integer page; // Current page number

    @SerializedName("total_results")
    @Expose
    private Integer totalMovies; // Total number of movies

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages; // Total number of pages

    @SerializedName("results")
    @Expose
    private List<Movie> movies = null; // List of movies returned in the response

    // Parcelable.Creator implementation for deserialization
    public final static Parcelable.Creator<MovieDBResponse> CREATOR = new Creator<MovieDBResponse>() {
        @SuppressWarnings({"unchecked"})
        public MovieDBResponse createFromParcel(Parcel in) {
            return new MovieDBResponse(in);
        }
        public MovieDBResponse[] newArray(int size) {
            return (new MovieDBResponse[size]);
        }
    };

    // Constructor for deserialization
    protected MovieDBResponse(Parcel in) {
        this.page = in.readInt(); // Read page number from Parcel
        this.totalMovies = in.readInt(); // Read total number of movies from Parcel
        this.totalPages = in.readInt(); // Read total number of pages from Parcel
        in.readList(this.movies, Movie.class.getClassLoader()); // Read list of movies from Parcel
    }

    // Empty constructor
    public MovieDBResponse() {
    }

    // Getters and setters for page, totalMovies, totalPages, and movies
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // Methods for writing and reading to/from a Parcel
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page); // Write page number to Parcel
        dest.writeInt(totalMovies); // Write total number of movies to Parcel
        dest.writeInt(totalPages); // Write total number of pages to Parcel
        dest.writeList(movies); // Write list of movies to Parcel
    }

    public int describeContents() {
        return 0;
    }
}
