package com.pr.f2w;

/**
 * Created by Arpit on 14-Jan-18.
 */

public class User {
    private String email;
    private int voted;

    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }
}
