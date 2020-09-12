package com.example.Leaderboard.model;

public class LeaderBoard {
    public String mName;
    public String mCountry;
    public String mHours;
    public String mBadgeUrl;

    public LeaderBoard(String name, String country, String hours, String badgeUrl) {
        this.mName = name;
        this.mCountry = country;
        this.mHours = hours;
        this.mBadgeUrl = badgeUrl;
    }
}
