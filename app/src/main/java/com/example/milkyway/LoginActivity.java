package com.example.milkyway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView registerBtn, loginFail;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (TextView) findViewById(R.id.registerBtn);
        loginFail = (TextView) findViewById(R.id.loginFail);
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
//                로그인 된다면
                if(true){
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(mainIntent);
                }
//                로그인 실패
                else{
                    loginFail.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}