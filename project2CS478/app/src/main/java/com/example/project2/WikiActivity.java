package com.example.project2;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WikiActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        webView = findViewById(R.id.webViewWiki);

        // Enable JavaScript (if needed)
        webView.getSettings().setJavaScriptEnabled(true);

        // Ensure links and redirects open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());

        // Retrieve the Wikipedia URL passed from MainActivity or the appropriate adapter
        String wikiUrl = getIntent().getStringExtra("wikiPageUrl");

        // Load the Wikipedia page
        webView.loadUrl(wikiUrl);
    }
}