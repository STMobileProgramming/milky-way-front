package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.Tokens;
import com.example.milkyway.api.dto.DateInfoDto;
import com.example.milkyway.api.dto.MonthImagesDto;
import com.example.milkyway.api.dto.TokenDto;
import com.example.milkyway.calendarDecorator.DayImageViewDecorator;
import com.example.milkyway.calendarDecorator.SaturdayDecorator;
import com.example.milkyway.calendarDecorator.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {

    MaterialCalendarView calendarView;
    Intent intent;
    final int GALLERY = 101;
    Call<List<MonthImagesDto>> tokenCall;
    Bitmap x;
    List<DayImageViewDecorator> dayViews = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);

        calendarView.addDecorators(new SundayDecorator(), new SaturdayDecorator());

        tokenCall = RetrofitClient.getApiService().getCalendarImages("Bearer " + Tokens.getAccessToken("nein"), LocalDate.now().toString().substring(0, 7));
        tokenCall.enqueue(new Callback<List<MonthImagesDto>>() {
            @Override
            public void onResponse(Call<List<MonthImagesDto>> call, Response<List<MonthImagesDto>> response) {
                if (!response.isSuccessful()) {
                    Log.e("calendar 안불러와진 이유: ", response.message());
                    Log.e("calendar 안불러와진 이유: ", Integer.toString(response.code()));
                } else {
                    for (int i = 0; i < response.body().size(); i++) {
                        MonthImagesDto dto = response.body().get(i);
                        Log.e("response date", dto.getDate());
                        int year = Integer.parseInt(dto.getDate().substring(0, 4));
                        int month = Integer.parseInt(dto.getDate().substring(5, 7));
                        month--;
                        int dayofMonth = Integer.parseInt(dto.getDate().substring(8, 10));
                        CalendarDay day = CalendarDay.from(year, month, dayofMonth);
                        Log.e("calendarDay", day.toString());
                        /*
                        val utilDate = Date()
                        val formatType = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        println(formatType.format(utilDate))
                         */
//                        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        new Thread(()->{
                            try{
                                Log.e("image url", dto.getImage());
                                HttpURLConnection connection = (HttpURLConnection) new URL(dto.getImage()).openConnection();
                                connection.setDoInput(true);
                                connection.connect();
                                InputStream input = connection.getInputStream();
                                x = BitmapFactory.decodeStream(input);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        calendarView.addDecorator(new DayImageViewDecorator(day, new BitmapDrawable(x)));
                                    }
                                });

//                                saveImage(x, time);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }).start();

//                        BitmapDrawable bitmapDrawable = new BitmapDrawable(compressBitmap(x));
//                        Log.e("bitmap drawable", bitmapDrawable.toString());
////                        Log.e("bitmap drawable", bitmapDrawable.getBitmap().toString());
//                        dayViews.add(new DayImageViewDecorator(day, bitmapDrawable));
//                        DayImageViewDecorator dayView = new DayImageViewDecorator(day, new BitmapDrawable(x));
//                        calendarView.addDecorator(dayView);
                    }
                    calendarView.addDecorators(dayViews);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new Thread(() -> {
//                                for (int i = 0; i < response.body().size(); i++) {
//
//                                    MonthImagesDto dto = response.body().get(i);
//                                    Log.e("response date", dto.getDate());
//                                    CalendarDay day = CalendarDay.from(Integer.parseInt(dto.getDate().substring(0,4)), Integer.parseInt(dto.getDate().substring(5,7)), Integer.parseInt(dto.getDate().substring(8,10)));
//                                    try{
//                                        HttpURLConnection connection = (HttpURLConnection) new URL(dto.getImage()).openConnection();
//                                        connection.connect();
//                                        InputStream input = connection.getInputStream();
//                                        Bitmap x = BitmapFactory.decodeStream(input);
//                                        DayImageViewDecorator dayView = new DayImageViewDecorator(day, new BitmapDrawable(x));
//                                        calendarView.addDecorator(dayView);
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                    }
//                                }
//                            }).start();
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<List<MonthImagesDto>> call, Throwable t) {
                Log.e("dd", t.getMessage());
                Log.e("ss", t.getCause().getMessage());
                Log.e("s", t.getLocalizedMessage());
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Intent intent = new Intent(getActivity(), DetailCalendar.class);
                intent.putExtra("date", new SimpleDateFormat("yyyy-MM-dd").format(date.getDate())); //데이터 넣기
                startActivity(intent);
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//                startActivityForResult(intent, GALLERY);

//                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                intent.setType("image/*");
//                startActivityForResult(intent, GALLERY);
//                calendarView.
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY) {
            Uri image = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}