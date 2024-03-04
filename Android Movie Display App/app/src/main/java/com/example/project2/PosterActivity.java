package com.example.project2;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        ImageView posterImageView = findViewById(R.id.posterImageView);
        String imageUrl = getIntent().getStringExtra("imageUrl");

        Picasso.get().load(imageUrl).into(posterImageView);
    }
}
