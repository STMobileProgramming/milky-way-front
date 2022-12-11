package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.Tokens;
import com.example.milkyway.api.dto.DateInfoDto;
import com.example.milkyway.api.dto.DefaultResponseDto;
import com.example.milkyway.api.dto.StringDto;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCalendar extends AppCompatActivity {

    ImageView deleteImg, editImg, editDateImg, detailImg;
    TextView detailDate;
    Button saveBtn, setDateBtn;
    EditText detailPlace, detailDescription;
    CalendarView editCalView;
    LinearLayout editDateLayout;
    String selectedDate;
    Uri uriImg;
    String imagePath;
    private final int REQUEST_CODE = 0;
    final int GALLERY = 101;
    Call<DateInfoDto> calendarCall;
    Call<String> fileUpload;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_detail);

///*        저장된 정보
//        detailImg -> 저장된 사진으로 변경
//        detailDate -> 저장된 날짜로 변경
//        detailPlace -> 저장된 장소로 변경
//        detailMemo -> 저장된 메모로 변경 */
//        deleteImg = (ImageView) findViewById(R.id.deleteImg);
//        editImg = (ImageView) findViewById(R.id.editImg);
//        detailImg = (ImageView) findViewById(R.id.detailImg);
//        editDateImg = (ImageView) findViewById(R.id.editDateImg);
//        detailDate = (TextView) findViewById(R.id.detailDate);
//        saveBtn = (Button) findViewById(R.id.saveBtn);
//        detailPlace = (EditText) findViewById(R.id.detailPlace);
//        detailMemo = (EditText) findViewById(R.id.detailMemo);
        detailImg = (ImageView) findViewById(R.id.detailImg);
        detailImg.setEnabled(false);
        detailDate = (TextView) findViewById(R.id.detailDate);
        detailPlace = (EditText) findViewById(R.id.detailPlace);
        detailDescription = (EditText) findViewById(R.id.detailMemo);

        editImg = (ImageView) findViewById(R.id.editImg);
        editDateImg = (ImageView) findViewById(R.id.editDateImg);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteImg = (ImageView) findViewById(R.id.deleteImg);

//        intent로 selectedDate 받아야 함
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("date");
        String[] splitDate = selectedDate.split("-");
        String detailDateText = String.format("%d년 %d월 %d일", Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
        detailDate.setText(detailDateText);
//        int monthPlusOne = Integer.parseInt(splitDate[1]);
//        monthPlusOne++;
//        splitDate[1] = Integer.toString(monthPlusOne);
//        String dateMonthPlused = String.join("-", splitDate);


//        selectedDate = "2022-12-09";
        Log.e("server에 가는 날짜 형식 ", selectedDate);
        calendarCall = RetrofitClient.getApiService().getDateInfo("Bearer " + Tokens.getAccessToken("nein"), selectedDate);
        calendarCall.enqueue(new Callback<DateInfoDto>() {
            @Override
            public void onResponse(Call<DateInfoDto> call, Response<DateInfoDto> response) {
                if(response.body() != null){
//                    Log.e("date : ", response.body().getDate());
//                    Log.e("image : ", response.body().getImage());
//                    Log.e("place : ", response.body().getPlace());
//                    Log.e("description: ", response.body().getDescription());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(response.body().getImage() != null){
                                Thread uThread = new Thread() {
                                    @Override
                                    public void run(){
                                        try{
                                            URL url = new URL(response.body().getImage());
                                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                            conn.setDoInput(true);
                                            conn.connect();
                                            InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                            bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
                                        } catch (IOException e){
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                uThread.start(); // 작업 Thread 실행
                                try{
                                    uThread.join();
                                    detailImg.setImageBitmap(bitmap);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                            String[] splitDate = selectedDate.split("-");
                            detailDate.setText(detailDateText);
                            detailPlace.setText(response.body().getPlace());

                            if(response.body().getDescription() != null){
                                detailDescription.setText(response.body().getDescription());
                            } else{
                                detailDescription.setText("");
                            }
                        }
                    });
                }
                else {
                    String[] splitDate = selectedDate.split("-");
                    detailDate.setText(detailDateText);
                    detailPlace.setText("");
                    detailDescription.setText("");
                }
            }


            @Override
            public void onFailure(Call<DateInfoDto> call, Throwable t) {
            }
        });

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                데이터 삭제 과정 추가

//                일정 삭제하면 다시 캘린더로 돌아가도록

                Call<DefaultResponseDto> deleteCall = RetrofitClient.getApiService().deleteCalendar("Bearer " + Tokens.getAccessToken("nein"), selectedDate);
                deleteCall.enqueue(new Callback<DefaultResponseDto>() {
                    @Override
                    public void onResponse(Call<DefaultResponseDto> call, Response<DefaultResponseDto> response) {
                        Toast.makeText(getApplicationContext(), "캘린더 삭제가 완료되었습니다.", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<DefaultResponseDto> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "서버의 상태가 원활하지 않습니다.", Toast.LENGTH_SHORT);
                    }
                });
                Intent CalenderIntent = new Intent(DetailCalendar.this, MainActivity.class);
                DetailCalendar.this.startActivity(CalenderIntent);
            }
        });
        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailImg.setEnabled(true);
                deleteImg.setVisibility(View.INVISIBLE);
                editImg.setVisibility(View.INVISIBLE);
                editDateImg.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.VISIBLE);
                detailPlace.setEnabled(true);
                detailDescription.setEnabled(true);
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
                detailImg.setEnabled(false);

                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), new File(imagePath));
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", "image", fileBody);
                Call<StringDto> uploadCall = RetrofitClient.getApiService().fileUpload("Bearer " + Tokens.getAccessToken("nein"), filePart);
                uploadCall.enqueue(new Callback<StringDto>() {
                    @Override
                    public void onResponse(Call<StringDto> call, Response<StringDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("왜 successful이 아닐까.. ", response.message());
                        } else {

                            // 이미지 업로드가 잘 됐으면 할일 (post하기)
                            Call<DefaultResponseDto> postCalendarCall = RetrofitClient.getApiService()
                                    .postCalendar(
                                            "Bearer " + Tokens.getAccessToken("nein"),
                                                    response.body().getString(),
                                                    selectedDate,
                                                    detailPlace.toString(),
                                                    detailDescription.toString());
                            postCalendarCall.enqueue(new Callback<DefaultResponseDto>() {
                                @Override
                                public void onResponse(Call<DefaultResponseDto> call, Response<DefaultResponseDto> response) {
                                    if (!response.isSuccessful()) {
                                        Log.e("왜 successful이 아닐까.. 2 ", response.message());
                                    } else {
                                        Toast.makeText(getApplicationContext(), "저장이 완료되었습니다.", Toast.LENGTH_LONG);
                                    }
                                }

                                @Override
                                public void onFailure(Call<DefaultResponseDto> call, Throwable t) {
                                    Log.e("왜 calendar post api 통신이 안될까?????? ", t.getCause().toString());
                                }
                            });

//                            imageUrl = response.body().getString();
                        }
                    }

                    @Override
                    public void onFailure(Call<StringDto> call, Throwable t) {
                        Log.e("왜 image upload api 통신이 안될까?????? ", t.getCause().toString());
                    }
                });
                saveBtn.setVisibility(View.GONE);
                editDateImg.setVisibility(View.INVISIBLE);
                deleteImg.setVisibility(View.VISIBLE);
                editImg.setVisibility(View.VISIBLE);
                detailPlace.setEnabled(false);
                detailDescription.setEnabled(false);
            }
        });

        detailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY) {
            uriImg = data.getData();
            imagePath = uriImg.getPath();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uriImg);
                detailImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
