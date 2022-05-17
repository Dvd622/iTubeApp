package com.example.itube;

public class YoutubeURL {
    int id;
    String user;
    String youtubeURL;

    public YoutubeURL(String user, String youtubeURL) {
        this.user = user;
        this.youtubeURL = youtubeURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}
