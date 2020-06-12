package com.example.connected;

public class Profile {
    private String profileName;
    private String twitterName;


    public Profile (String profileName, String twitterName){
        this.profileName = profileName;
        this.twitterName = twitterName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }
}
