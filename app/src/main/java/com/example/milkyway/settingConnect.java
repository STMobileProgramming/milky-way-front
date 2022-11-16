package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class settingConnect extends AppCompatActivity {
    ImageView backConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_connect);
        backConnect = (ImageView) findViewById(R.id.backConnect);
        backConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConnecttoMainIntent = new Intent(settingConnect.this, MainActivity.class);
                settingConnect.this.startActivity(ConnecttoMainIntent);
            }
        });
    }
}