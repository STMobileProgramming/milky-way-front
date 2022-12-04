package com.example.milkyway.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenDto {

    @SerializedName("grantType")
    @Expose
    private String grantType;

    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    @SerializedName("accessTokenExpiresIn")
    @Expose
    private Long accessTokenExpiresIn;





    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
