package com.example.milkyway;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class SettingFragment extends Fragment {

    Button changepwBtn, connectBtn, setImgBtn, logoutBtn, disconnectBtn;
//    Button changeBackBtn;
    FrameLayout checkLogoutLayout, checkDisLayout;
    Button logoutYesBtn, logoutNoBtn, disYestBtn, disNoBtn;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        changepwBtn = (Button) view.findViewById(R.id.changepwBtn);             // 비밀번호 변경
        connectBtn = (Button) view.findViewById(R.id.connectBtn);               // 커플 연결
        setImgBtn = (Button) view.findViewById(R.id.setImgBtn);
//        changeBackBtn = (Button) view.findViewById(R.id.changeBackBtn);
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);                 // 로그아웃
        disconnectBtn = (Button) view.findViewById(R.id.disconnectBtn);         // 연결 끊기

/*        changeBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changebackIntent = new Intent(getActivity(), settingChangeback.class);
                startActivity(changebackIntent);
            }
        });*/

        changepwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changepwIntent = new Intent(getActivity(), settingChangepw.class);
                startActivity(changepwIntent);
            }
        });

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connectIntent = new Intent(getActivity(), settingConnect.class);
                startActivity(connectIntent);
            }
        });

        // logoutBtn, disconnectBtn layout 수정 필요
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogoutLayout = (FrameLayout) view.findViewById(R.id.checkLogoutLayout);
                logoutYesBtn = (Button) view.findViewById(R.id.logoutYesBtn);
                logoutNoBtn = (Button) view.findViewById(R.id.logoutNoBtn);
                checkLogoutLayout.setVisibility(View.VISIBLE);
                logoutYesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        로그아웃 과정 추가
                        checkLogoutLayout.setVisibility(View.INVISIBLE);
                        Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(logoutIntent);
                    }
                });
                logoutNoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkLogoutLayout.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        disconnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDisLayout = (FrameLayout) view.findViewById(R.id.checkDisLayout);
                disYestBtn = (Button) view.findViewById(R.id.disYesBtn);
                disNoBtn = (Button) view.findViewById(R.id.disNoBtn);
                checkDisLayout.setVisibility(View.VISIBLE);
                disYestBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        커플 연결 끊기 과정 추가 -> 회원가입 정보, 프로필 사진 외에 모두 삭제
//                        로그아웃 과정 추가
                        checkDisLayout.setVisibility(View.INVISIBLE);
                        Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(logoutIntent);
                    }
                });
                disNoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkDisLayout.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        return view;
    }
}