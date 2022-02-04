package com.example.test;

public class Message {
    private String sender;
    private String description;
    private boolean isread;
    private boolean fav;

    public Message(){}

    public Message(String sender, String description, boolean isread, boolean fav) {
        this.sender=sender;
        this.description=description;
        this.isread=isread;
        this.fav=fav;

    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getfav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

}
