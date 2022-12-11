package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.Tokens;
import com.example.milkyway.api.dto.HomeDto;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class HomeFragment extends Fragment{
    ImageView myImg, friendImg, myBigImg, frBigImg, heartImg;
    TextView date, oneDate, twoDate, threeDate, fourDate, fiveDate, firstDay;
    LinearLayout dateLayout;
    int year, month, dayofmon;
    Call<HomeDto> homeCall;
    Bitmap bitmap;
    Handler handler;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        상대방 프로필 사진, 내 프로필 사진으로 변경해서 나타나도록 해야 함. 없을 경우 기본 이미지로
//        myImg, friendImg, myBigImg, frBigImg 수정

        myImg = (ImageView) view.findViewById(R.id.myImg);
        friendImg = (ImageView) view.findViewById(R.id.friendImg);
        myBigImg = (ImageView) view.findViewById(R.id.myBigImg);
        frBigImg = (ImageView) view.findViewById(R.id.frBigImg);
        heartImg = (ImageView) view.findViewById(R.id.heartImg);
        dateLayout = (LinearLayout) view.findViewById(R.id.dateLayout);
        oneDate = (TextView) view.findViewById(R.id.oneDate);
        twoDate = (TextView) view.findViewById(R.id.twoDate);
        threeDate = (TextView) view.findViewById(R.id.threeDate);
        fourDate = (TextView) view.findViewById(R.id.fourDate);
        fiveDate = (TextView) view.findViewById(R.id.fiveDate);
        firstDay = (TextView) view.findViewById(R.id.firstDay);

        Log.e("dddddd", Tokens.getAccessToken("nein"));
        homeCall = RetrofitClient.getApiService().getHome("Bearer " + Tokens.getAccessToken("nein"));
        homeCall.enqueue(new Callback<HomeDto>() {
            @Override
            public void onResponse(Call<HomeDto> call, Response<HomeDto> response) {
//                Log.e("myProfile : ", response.body().getMyProfile());
//                Log.e("coupleProfile : ", response.body().getCoupleProfile());
//                Log.e("startDay : ", response.body().getStartDay());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection;
                        InputStream inputStream;
                        if (response.body().getMyProfile() != null) {
                            Thread uThread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        URL url = new URL(response.body().getMyProfile());
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        conn.setDoInput(true);
                                        conn.connect();
                                        InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                        bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            uThread.start(); // 작업 Thread 실행
                            try {
                                uThread.join();
                                myImg.setImageBitmap(bitmap);
                                myBigImg.setImageBitmap(bitmap);
                                myImg.setBackground(null);
                                myBigImg.setBackground(null);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (response.body().getCoupleProfile() != null) {
                            Thread uThread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        URL url = new URL(response.body().getCoupleProfile());
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        conn.setDoInput(true);
                                        conn.connect();
                                        InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                        bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            uThread.start(); // 작업 Thread 실행
                            try {
                                uThread.join();
                                friendImg.setImageBitmap(bitmap);
                                frBigImg.setImageBitmap(bitmap);
                                frBigImg.setBackground(null);
                                friendImg.setBackground(null);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (response.body().getStartDay() != null) {
                            Thread uThread = new Thread() {
                                @Override
                                public void run() {
                                    String startDay = response.body().getStartDay();
                                    year = Integer.parseInt(startDay.substring(0, 4));
                                    Log.e("year : ", String.valueOf(year));
                                    month = Integer.parseInt(startDay.substring(5, 7));
                                    Log.e("month : ", String.valueOf(month));
                                    dayofmon = Integer.parseInt(startDay.substring(8, 10));
                                    Log.e("dayofmon : ", String.valueOf(dayofmon));
                                    month--;
                                }
                            };
                            uThread.start(); // 작업 Thread 실행
                            try{
                                uThread.join();
                                Calendar calToday = Calendar.getInstance();
                                Calendar calStart = new GregorianCalendar(year, month, dayofmon);
/*                              날짜 받아오기 -> 1일째 언제인지 데이터 필요, 받아온 데이터 month에서 1 뺴줘야 함
                              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                              Date dDay = new Date();
                              try{
                              dDay = df.parse("2022-11-14");
                              } catch (ParseException e){
                              e.printStackTrace();
                              }
                              calStart.setTime(dDay);*/
                                long IToday = calToday.getTimeInMillis() / (24*60*60*1000);
                                long IStart = calStart.getTimeInMillis() / (24*60*60*1000);
                                long day = IToday - IStart + 1;
                                String calDDay = Long.toString(day);
                                date = (TextView) view.findViewById(R.id.date);
                                date.setText("D+"+calDDay);

                                firstDay.setText(Integer.toString(year) + "." + Integer.toString(month + 1) + "." + Integer.toString(dayofmon));
                                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                                Calendar firstday = new GregorianCalendar(year, month, dayofmon);
                                Date afterdate = new Date();

                                firstday.add(Calendar.DATE, 99);
                                afterdate = firstday.getTime();
                                oneDate.setText(df.format(afterdate));

                                firstday.add(Calendar.DATE, 100);
                                afterdate = firstday.getTime();
                                twoDate.setText(df.format(afterdate));

                                firstday.add(Calendar.DATE, 100);
                                afterdate = firstday.getTime();
                                threeDate.setText(df.format(afterdate));

                                firstday.add(Calendar.DATE, 100);
                                afterdate = firstday.getTime();
                                fourDate.setText(df.format(afterdate));

                                firstday.add(Calendar.DATE, 100);
                                afterdate = firstday.getTime();
                                fiveDate.setText(df.format(afterdate));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            String startDay = response.body().getStartDay();
//                            year = Integer.parseInt(startDay.substring(0, 4));
//                            Log.e("year : ", String.valueOf(year));
//                            month = Integer.parseInt(startDay.substring(5, 7));
//                            Log.e("month : ", String.valueOf(month));
//                            dayofmon = Integer.parseInt(startDay.substring(8, 10));
//                            Log.e("dayofmon : ", String.valueOf(dayofmon));
//                            month--;
                        } else {
                            year = 2022;
                            month = 12;
                            dayofmon = 3;
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<HomeDto> call, Throwable t) {

            }
        });

        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBigImg.setVisibility(View.VISIBLE);
            }
        });
        friendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frBigImg.setVisibility(View.VISIBLE);
            }
        });
        myBigImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBigImg.setVisibility(View.INVISIBLE);
            }
        });
        frBigImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frBigImg.setVisibility(View.INVISIBLE);
            }
        });
        heartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                100일, 200일, 300일, 400일, 500일, 1주년 날짜 계산 코드 추가
                dateLayout.setVisibility(View.VISIBLE);
            }
        });
        dateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateLayout.setVisibility(View.INVISIBLE);
            }
        });
        return view;

    }
}