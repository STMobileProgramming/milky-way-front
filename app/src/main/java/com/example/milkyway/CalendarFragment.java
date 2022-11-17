package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.milkyway.calendarDecorator.SaturdayDecorator;
import com.example.milkyway.calendarDecorator.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;

public class CalendarFragment extends Fragment {

    MaterialCalendarView calendarView;
    Intent intent;
    final int GALLERY = 101;

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

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Intent intent = new Intent(getActivity(), DetailCalendar.class);
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