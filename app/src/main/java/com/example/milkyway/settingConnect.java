package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.Tokens;
import com.example.milkyway.api.dto.DefaultResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class settingConnect extends AppCompatActivity {
    ImageView backConnect;
    FrameLayout codeLayout;
    LinearLayout dateLayout;
    Button insertCode, connectButton, enterDate;
    EditText yourCode, firstDate;
    TextView connectFail;
    TextView coupleCode;
    Call<String> codeCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_connect);
        insertCode = (Button) findViewById(R.id.insertCode);
//        checkCode = (Button) findViewById(R.id.checkCode);
//        connectButton = (Button) findViewById(R.id.connectButton);
        enterDate = findViewById(R.id.enterDate);
        yourCode = (EditText) findViewById(R.id.yourCode);
        firstDate = (EditText) findViewById(R.id.firstDate);
        codeLayout = (FrameLayout) findViewById(R.id.codeLayout);
        dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
        connectFail = (TextView) findViewById(R.id.connectFail);
        coupleCode = (TextView) findViewById(R.id.coupleCode);

        codeCall = RetrofitClient.getApiService().getCode("Bearer " + Tokens.getAccessToken("nein"));
        codeCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.e("code call is not successful ", response.message());
                } else {
                    coupleCode.setText(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("code call api is not working ", t.getCause().toString());
            }
        });

        insertCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeLayout.setVisibility(View.VISIBLE);
            }
        });

//        enterDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dateLayout.setVisibility(View.INVISIBLE);
//                codeLayout.setVisibility(View.GONE);
////                Intent settingIntent = new Intent(getApplicationContext(), SettingFragment.class);
////                sta(settingIntent);
//            }
//        });

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponseDto> connectCall = RetrofitClient.getApiService().makeCouple("Bearer " + Tokens.getAccessToken("nein"), yourCode.getText().toString(), firstDate.getText().toString());
                connectCall.enqueue(new Callback<DefaultResponseDto>() {
                    @Override
                    public void onResponse(Call<DefaultResponseDto> call, Response<DefaultResponseDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("couple connect failed ", response.message());
                        } else {
                            dateLayout.setVisibility(View.INVISIBLE);
                            codeLayout.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponseDto> call, Throwable t) {
                        Log.e("couple connect API failed ", t.getCause().toString());
                    }
                });
//                codeLayout.setVisibility(View.VISIBLE);
//                checkCode.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        맞는 코드가 존재한다면(yourCode)
//                        if(true){
//                            connectFail.setVisibility(View.GONE);
//                            checkCode.setVisibility(View.GONE);
//                            dateLayout.setVisibility(View.VISIBLE);
//                        }
//                        //틀리다면
//                        else{
//                            connectFail.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//                enterDate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        firstDate 날짜 저장
//                        dateLayout.setVisibility(View.INVISIBLE);
//                        codeLayout.setVisibility(View.GONE);
//                    }
//                });
            }
        });
    }
}