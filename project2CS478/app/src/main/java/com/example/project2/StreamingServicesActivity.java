package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StreamingServicesActivity extends AppCompatActivity {

    private ListView listViewServices;
    private ArrayList<String> servicesList;
    private ArrayList<String> serviceUrls; // URLs corresponding to each service

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming_services);

        listViewServices = findViewById(R.id.listViewServices);

        // Initialize with example data.
        servicesList = new ArrayList<>();
        serviceUrls = new ArrayList<>();
        servicesList.add("Netflix");
        serviceUrls.add("https://www.netflix.com/");
        servicesList.add("Amazon Prime");
        serviceUrls.add("https://www.amazon.com/Amazon-Video/b?ie=UTF8&node=2858778011");

        // More services can be added here

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, servicesList);
        listViewServices.setAdapter(adapter);

        listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open the service's page for the movie
                String url = serviceUrls.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}