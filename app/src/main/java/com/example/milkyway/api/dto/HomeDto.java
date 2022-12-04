package com.example.milkyway.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeDto {

    @SerializedName("myProfile")
    @Expose
    private String myProfile;

    @SerializedName("coupleProfile")
    @Expose
    private String coupleProfile;

    @SerializedName("startDay")
    @Expose
    private String startDay;


    public String getMyProfile() {
        return myProfile;
    }

    public void setMyProfile(String myProfile) {
        this.myProfile = myProfile;
    }

    public String getCoupleProfile() {
        return coupleProfile;
    }

    public void setCoupleProfile(String coupleProfile) {
        this.coupleProfile = coupleProfile;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
}
