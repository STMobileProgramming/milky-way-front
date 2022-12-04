package com.example.milkyway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milkyway.api.RetrofitClient;
import com.example.milkyway.api.dto.DefaultResponseDto;
import com.example.milkyway.api.dto.TokenDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button registerBtn;
    EditText nameText, idText, passwordText, password2Text, emailText;
    Call<DefaultResponseDto> signupCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        nameText = findViewById(R.id.nameText);
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        password2Text = findViewById(R.id.password2Text);
        emailText = findViewById(R.id.emailText);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!passwordText.getText().toString().equals(password2Text.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호와 비밀번호 확인란이 일치하지않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                signupCall = RetrofitClient.getApiService().signup(nameText.getText().toString(), idText.getText().toString(), passwordText.getText().toString(), emailText.getText().toString());
                signupCall.enqueue(new Callback<DefaultResponseDto>() {
                    @Override
                    public void onResponse(Call<DefaultResponseDto> call, Response<DefaultResponseDto> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "입력이 올바른지 다시 확인해주세요.", Toast.LENGTH_LONG).show();
                        } else {
                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(loginIntent);
                        }
                    }
                    @Override
                    public void onFailure(Call<DefaultResponseDto> call, Throwable t) {
                    }
                });
            }
        });
    }
}