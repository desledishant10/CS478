package com.example.project2;

import java.util.List;

public class Movie {
    private String title;
    private String imageUrl;
    private String actors;
    private String webPageUrl;
    private String wikiPageUrl;
    private List<String> streamingServices;

    // Constructor
    public Movie(String title, String imageUrl, String actors, String webPageUrl, String wikiPageUrl, List<String> streamingServices) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.actors = actors;
        this.webPageUrl = webPageUrl;
        this.wikiPageUrl = wikiPageUrl;
        this.streamingServices = streamingServices;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getActors() {
        return actors;
    }

    public String getWebPageUrl() {
        return webPageUrl;
    }

    public String getWikiPageUrl() {
        return wikiPageUrl;
    }

    public List<String> getStreamingServices() {
        return streamingServices;
    }

    // Setters (if needed, otherwise can be omitted)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setWebPageUrl(String webPageUrl) {
        this.webPageUrl = webPageUrl;
    }

    public void setWikiPageUrl(String wikiPageUrl) {
        this.wikiPageUrl = wikiPageUrl;
    }

    public void setStreamingServices(List<String> streamingServices) {
        this.streamingServices = streamingServices;
    }
}