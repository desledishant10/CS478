package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Initialize ImageView
        ImageView imageViewPoster = findViewById(R.id.imageViewPoster);

        // Retrieve the image URL and web page URL passed from MainActivity
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        final String webPageUrl = intent.getStringExtra("webPageUrl");

        // Use Picasso to load the image
        Picasso.get().load(imageUrl).into(imageViewPoster);

        // Set an OnClickListener to open the movie's official web page when the image is clicked
        imageViewPoster.setOnClickListener(view -> {
            // Intent to open web page
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setData(Uri.parse(webPageUrl));
            startActivity(webIntent);
        });
    }
}