package com.example.project2;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MoviesAdapter moviesAdapter; // Declare as a class member
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView moviesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeMovieList();
        setupRecyclerView();
    }

    private void initializeMovieList() {
        // Mock data for demonstration
        movieList.add(new Movie("Stranger Than Fiction", "https://upload.wikimedia.org/wikipedia/en/f/ff/Stranger_Than_Fiction_%282006_movie_poster%29.jpg", "Will Ferrell, Emma Thompson, Maggie Gyllenhaal, Dustin Hoffman", "https://www.sonypictures.com/movies/strangerthanfiction", "https://en.wikipedia.org/wiki/Stranger_than_Fiction_(2006_film)", Arrays.asList("Amazon Prime", "Netflix")));
        movieList.add(new Movie("Inception", "https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg", "Leonardo DiCaprio, Joseph Gordon-Levitt", "https://www.warnerbros.com/movies/inception", "https://en.wikipedia.org/wiki/Inception", Arrays.asList("HBO Max", "Disney+")));
        movieList.add(new Movie("Interstellar", "https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg", "Matthew McConaughey, Anne Hathaway, Jessica Chastain", "https://www.paramountpictures.com/movies/interstellar", "https://en.wikipedia.org/wiki/Interstellar_(film)", Arrays.asList("Netflix", "Disney+")));
        movieList.add(new Movie("Peaky Blinders (TV series)", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e8/Peaky_Blinders_titlecard.jpg/330px-Peaky_Blinders_titlecard.jpg", "Cillian Murphy, Sophie Rundle, Paul Anderson", "https://peakyblinders.tv/", "https://en.wikipedia.org/wiki/Peaky_Blinders_(TV_series)", Arrays.asList("Netflix", "Disney+")));
    }

    private void setupRecyclerView() {
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesAdapter = new MoviesAdapter(this, movieList, position -> {
            // On short click, open the movie's official web page
            Movie selectedMovie = movieList.get(position);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedMovie.getWebPageUrl()));
            startActivity(browserIntent);
        }, position -> {
            // On long click, show options dialog
            showOptionsDialog(position);
            return true;
        });

        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    private void showOptionsDialog(int position) {
        Movie selectedMovie = movieList.get(position);
        CharSequence[] options = {"Full Poster", "Wikipedia", "Streaming Services"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0: // Full Poster
                            Intent posterIntent = new Intent(MainActivity.this, PosterActivity.class);
                            posterIntent.putExtra("imageUrl", selectedMovie.getImageUrl());
                            startActivity(posterIntent);
                            break;
                        case 1: // Wikipedia
                            Intent wikiIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedMovie.getWikiPageUrl()));
                            startActivity(wikiIntent);
                            break;
                        case 2: // Streaming Services
                            Intent servicesIntent = new Intent(MainActivity.this, StreamingServicesActivity.class);
                            servicesIntent.putExtra("servicesList", new ArrayList<>(selectedMovie.getStreamingServices()));
                            startActivity(servicesIntent);
                            break;
                    }
                });
        builder.show();
    }
}
