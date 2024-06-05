package com.music.app;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String album;
    private int year;

    public Song(int id, String title, String artist, String album, int year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public int getYear() {
        return year;
    }
}
