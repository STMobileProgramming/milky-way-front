package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.milkyway.api.dto.DefaultResponseDto;

import retrofit2.Call;

public class settingChangepw extends AppCompatActivity {
    Button setPass;
    EditText oriPass, newPass, checkNewPass;
    Call<DefaultResponseDto> editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_changepw);
        setPass = (Button) findViewById(R.id.setPass);
        oriPass = (EditText) findViewById(R.id.oriPass);
        newPass = (EditText) findViewById(R.id.newPass);
        checkNewPass = (EditText) findViewById(R.id.checkNewPass);
        setPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent finishChangetoMainIntent = new Intent(settingChangepw.this, MainActivity.class);
//                settingChangepw.this.startActivity(finishChangetoMainIntent);
                Log.e("비밀번호", newPass.getText().toString() + checkNewPass.getText().toString());
                if(!newPass.getText().toString().equals(checkNewPass.getText().toString())) Toast.makeText(getApplicationContext(), "새로운 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                else{
                    Toast.makeText(getApplicationContext(), "비밀번호 변경이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    Intent finishChangetoMainIntent = new Intent(settingChangepw.this, MainActivity.class);
                    settingChangepw.this.startActivity(finishChangetoMainIntent);
                }
            }
        });
    }
}
