package com.example.milkyway.api;

import android.service.autofill.UserData;

import com.example.milkyway.api.dto.DateInfoDto;
import com.example.milkyway.api.dto.DefaultResponseDto;
import com.example.milkyway.api.dto.HomeDto;
import com.example.milkyway.api.dto.MonthImagesDto;
import com.example.milkyway.api.dto.TokenDto;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {

    /*
    @Header의 토큰을 넣어줄 때는 Tokens의 getAccessToken("nein")을 사용합니다.
     */

    @POST("auth/login")
    @FormUrlEncoded
    Call<TokenDto> login(@Field("userId") String userId, @Field("password") String password);

    @POST("auth/signup")
    @FormUrlEncoded
    Call<DefaultResponseDto> signup(@Field("name") String name, @Field("userId") String userId, @Field("password") String password, @Field("email") String email);

    @GET("home")
    Call<HomeDto> getHome(@Header("Authorization") String token);

    @GET("calendar/{month}")
    Call<List<MonthImagesDto>> getCalendarImages(@Header("Authorization") String token, @Path("month") String month);

    @GET("calendar/date/{date}")
    Call<DateInfoDto> getDateInfo(@Header("Authorization") String token, @Path("date") String date);

    @POST("calendar")
    @FormUrlEncoded
    Call<DefaultResponseDto> postCalendar(@Header("Authorization") String token, @Field("image") String image, @Field("date") String date, @Field("place") String place, @Field("description") String description);

    @POST("couple")
    @FormUrlEncoded
    Call<DefaultResponseDto> makeCouple(@Header("Authorization") String token, @Field("code") String code, @Field("startDay") String startDay);

    @POST("member/password")
    Call<DefaultResponseDto> editPassword(@Header("Authorization") String token);

    @POST("couple/break")
    Call<DefaultResponseDto> breakOffCouple(@Header("Authorization") String token);

    @Multipart
    @POST("image/upload")
    Call<String> fileUpload(@Header("Authorization") String token, @Part MultipartBody.Part image);

    @PUT("member/profile")
    Call<DefaultResponseDto> editProfile(@Header("Authorization") String token, @Field("profileImg") String profileImg);
}
