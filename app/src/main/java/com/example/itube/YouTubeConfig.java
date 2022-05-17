package com.example.itube;

public class YouTubeConfig {

    public YouTubeConfig() {

    }

    private static final String API_KEY = BuildConfig.API_KEY;
    public static String getApiKey() {
        return API_KEY;
    }
}
