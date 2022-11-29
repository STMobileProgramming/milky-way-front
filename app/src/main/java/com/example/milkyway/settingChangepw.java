package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class settingChangepw extends AppCompatActivity {
    Button setPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_changepw);
        setPass = (Button) findViewById(R.id.setPass);
        setPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finishChangetoMainIntent = new Intent(settingChangepw.this, MainActivity.class);
                settingChangepw.this.startActivity(finishChangetoMainIntent);
            }
        });
    }
}
