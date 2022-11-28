package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;
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

public class settingConnect extends AppCompatActivity {
    ImageView backConnect;
    FrameLayout codeLayout;
    LinearLayout dateLayout;
    Button insertCode, checkCode, enterDate;
    EditText yourCode, firstDate;
    TextView connectFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_connect);
        insertCode = (Button) findViewById(R.id.insertCode);
        checkCode = (Button) findViewById(R.id.checkCode);
        enterDate = (Button) findViewById(R.id.enterDate);
        yourCode = (EditText) findViewById(R.id.yourCode);
        firstDate = (EditText) findViewById(R.id.firstDate);
        codeLayout = (FrameLayout) findViewById(R.id.codeLayout);
        dateLayout = (LinearLayout) findViewById(R.id.dateLayout);
        connectFail = (TextView) findViewById(R.id.connectFail);

        insertCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeLayout.setVisibility(View.VISIBLE);
                checkCode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        맞는 코드가 존재한다면(yourCode)
                        if(true){
                            connectFail.setVisibility(View.GONE);
                            checkCode.setVisibility(View.GONE);
                            dateLayout.setVisibility(View.VISIBLE);
                        }
                        //틀리다면
                        else{
                            connectFail.setVisibility(View.VISIBLE);
                        }
                    }
                });
                enterDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        firstDate 날짜 저장
                        dateLayout.setVisibility(View.INVISIBLE);
                        codeLayout.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}