package com.example.milkyway;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class settingChangeback extends AppCompatActivity {

    RadioGroup backRadio;
    Button setBack;
    FrameLayout fragment_home, fragment_recommendation, fragment_setting;
    LinearLayout setting_changeback, setting_changepw, setting_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_changeback);

        setBack = (Button) findViewById(R.id.setBack);
        setBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_home = (FrameLayout) findViewById(R.id.fragment_home);
                fragment_recommendation = (FrameLayout) findViewById(R.id.fragment_recommendation);
                fragment_setting = (FrameLayout) findViewById(R.id.fragment_setting);
                setting_changeback = (LinearLayout) findViewById(R.id.setting_changeback);
                setting_changepw = (LinearLayout) findViewById(R.id.setting_changepw);
                setting_connect = (LinearLayout) findViewById(R.id.setting_connect);
                backRadio = (RadioGroup) findViewById(R.id.backRadio);

/*
                수정필요
                switch (backRadio.getCheckedRadioButtonId()){
                    case R.id.setBackGray:
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                        fragment_recommendation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                        setting_changepw.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                        setting_changeback.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                        setting_connect.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_gray));
                    case R.id.setBackPink:
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        fragment_recommendation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_changepw.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_changeback.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_connect.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                    case R.id.setBackSky:
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                        fragment_recommendation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                        setting_changepw.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                        setting_changeback.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                        setting_connect.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_sky));
                    case R.id.setBackGreen:
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                        fragment_recommendation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                        setting_changepw.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                        setting_changeback.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                        setting_connect.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_green));
                    default:
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        fragment_recommendation.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        fragment_setting.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_changepw.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_changeback.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                        setting_connect.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_pink_main));
                }
*/
                Intent backtoMainIntent = new Intent(settingChangeback.this, MainActivity.class);
                settingChangeback.this.startActivity(backtoMainIntent);
            }
        });
    }
}
