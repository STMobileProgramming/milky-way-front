package com.example.milkyway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.Tokens;
import com.example.milkyway.api.dto.DefaultResponseDto;
import com.example.milkyway.api.dto.TokenDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView registerBtn, loginFail;
    Button loginBtn;
    Call<TokenDto> tokenCall;

    EditText idText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (TextView) findViewById(R.id.registerBtn);
        loginFail = (TextView) findViewById(R.id.loginFail);
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tokenCall = RetrofitClient.getApiService().login(idText.getText().toString(), passwordText.getText().toString());
                tokenCall.enqueue(new Callback<TokenDto>() {
                    @Override
                    public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                        if (!response.isSuccessful()) {
                            Log.e("dd", response.message());
                            System.out.printf(String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 다시 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            /*
                            로그인 된 경우 Tokens에 accessToken과 refreshToken을 저장한다.
                            이 토큰들은 나중에 헤더에 넣어서 서버측에서 사용자를 인식할 수 있게한다.
                             */
                            Tokens.init(getApplicationContext());
                            Tokens.setAccessToken(response.body().getAccessToken());
                            Tokens.setRefreshToken(response.body().getRefreshToken());
                            Log.e("저장된 accessToken : ", Tokens.getAccessToken("nein"));

                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(mainIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenDto> call, Throwable t) {
                        Log.e("dd", t.getMessage());
                        Log.e("ss", t.getCause().getMessage());
                        Log.e("s", t.getLocalizedMessage());

                        loginFail.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

    }
}