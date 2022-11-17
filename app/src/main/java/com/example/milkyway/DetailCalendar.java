package com.example.milkyway;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DetailCalendar extends AppCompatActivity {

    ImageView deleteImg, editImg, editDateImg, detailImg;
    TextView detailDate;
    Button saveBtn, setDateBtn;
    EditText detailPlace, detailMemo;
    CalendarView editCalView;
    LinearLayout editDateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_detail);

/*        저장된 정보
        detailImg -> 저장된 사진으로 변경
        detailDate -> 저장된 날짜로 변경
        detailPlace -> 저장된 장소로 변경
        detailMemo -> 저장된 메모로 변경 */
        deleteImg = (ImageView) findViewById(R.id.deleteImg);
        editImg = (ImageView) findViewById(R.id.editImg);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        editDateImg = (ImageView) findViewById(R.id.editDateImg);
        detailDate = (TextView) findViewById(R.id.detailDate);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        detailPlace = (EditText) findViewById(R.id.detailPlace);
        detailMemo = (EditText) findViewById(R.id.detailMemo);

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                데이터 삭제 과정 추가

//                일정 삭제하면 다시 캘린더로 돌아가도록
                Intent CalenderIntent = new Intent(DetailCalendar.this, MainActivity.class);
                DetailCalendar.this.startActivity(CalenderIntent);
            }
        });
        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImg.setVisibility(View.INVISIBLE);
                editImg.setVisibility(View.INVISIBLE);
                editDateImg.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                detailPlace.setEnabled(true);
                detailMemo.setEnabled(true);
            }
        });

//        ImageView 변경하는 과정(saveBtn이 VISIBLE 상태여야 가능) 추가

//        editDateImg 클릭 -> 캘린더 나타남. 날짜 선택 -> detailDate 변경(yyyy+"년 "+MM+"월 "+dd+"일) 추가
        editDateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDateLayout = (LinearLayout) findViewById(R.id.editDateLayout);
                editCalView = (CalendarView) findViewById(R.id.editCalView);
                setDateBtn = (Button) findViewById(R.id.setDateBtn);
                editDateLayout.setVisibility(View.VISIBLE);
                editCalView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int mon, int day) {
                        mon+=1;
                        detailDate.setText(String.format("%d년 %d월 %d일", year, mon, day));
                    }
                });
                setDateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editDateLayout.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtn.setVisibility(View.GONE);
                editDateImg.setVisibility(View.INVISIBLE);
                deleteImg.setVisibility(View.VISIBLE);
                editImg.setVisibility(View.VISIBLE);
                detailPlace.setEnabled(false);
                detailMemo.setEnabled(false);
            }
        });

    }
}
