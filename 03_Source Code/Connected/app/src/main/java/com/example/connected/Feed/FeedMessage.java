package com.example.connected.Feed;



public class FeedMessage {

    private String Author;
    private String Title;
    private String Description;
    private String publishedAt;

    public FeedMessage(String author, String title, String description, String publishedAt) {
        Author = author;
        Title = title;
        Description = description;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return publishedAt;
    }

    public void setDate(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
