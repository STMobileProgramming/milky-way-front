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
import android.widget.TextView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {
    ImageView myImg, friendImg, myBigImg, frBigImg;
    TextView date;

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
        myImg = (ImageView) view.findViewById(R.id.myImg);
        friendImg = (ImageView) view.findViewById(R.id.friendImg);
        myBigImg = (ImageView) view.findViewById(R.id.myBigImg);
        frBigImg = (ImageView) view.findViewById(R.id.frBigImg);
        Calendar calToday = Calendar.getInstance();
        Calendar calStart = new GregorianCalendar(2022, 10, 15);
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
        return view;

    }
}