package com.pr.f2w;

/**
 * Created by Arpit on 09-Jan-18.
 */

public class Card {
    private String name;
    private int thumbnail;

    public Card(){

    }

    public Card(String name, int thumbnail) {
        this.name = name;

        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
