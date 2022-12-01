package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment{
    ImageView myImg, friendImg, myBigImg, frBigImg, heartImg;
    TextView date, oneDate, twoDate, threeDate, fourDate, fiveDate, firstDay;
    LinearLayout dateLayout;
    int year, month, dayofmon;

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
        year = 2022;
        month = 10;
        dayofmon = 15;

        Calendar calToday = Calendar.getInstance();
        Calendar calStart = new GregorianCalendar(year, month, dayofmon);
/*        날짜 받아오기 -> 1일째 언제인지 데이터 필요, 받아온 데이터 month에서 1 뺴줘야 함
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

        firstDay.setText(Integer.toString(year) + "." + Integer.toString(month+1) + "." + Integer.toString(dayofmon));
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
        myBigImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myBigImg.setVisibility(View.INVISIBLE);
            }
        });
        frBigImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
            public void onClick(View view) { dateLayout.setVisibility(View.INVISIBLE);}
        });

        return view;

    }
}