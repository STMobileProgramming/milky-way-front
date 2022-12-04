package com.example.milkyway;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.IOException;

public class SettingFragment extends Fragment {

    Button changepwBtn, connectBtn, setImgBtn, logoutBtn, disconnectBtn;
    FrameLayout checkLogoutLayout, checkDisLayout;
    Button logoutYesBtn, logoutNoBtn, disYestBtn, disNoBtn;
    final int GALLERY = 101;
    private final int REQUEST_CODE = 0;

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
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);                 // 로그아웃
        disconnectBtn = (Button) view.findViewById(R.id.disconnectBtn);         // 연결 끊기
        checkDisLayout = (FrameLayout) view.findViewById(R.id.checkDisLayout);
        disYestBtn = (Button) view.findViewById(R.id.disYesBtn);
        disNoBtn = (Button) view.findViewById(R.id.disNoBtn);
        checkLogoutLayout = (FrameLayout) view.findViewById(R.id.checkLogoutLayout);
        logoutYesBtn = (Button) view.findViewById(R.id.logoutYesBtn);
        logoutNoBtn = (Button) view.findViewById(R.id.logoutNoBtn);

        setImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // 위의 Activity를 실행한 이후 이벤트를 정의
                startActivityForResult(intent, GALLERY);
            }
        });

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

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY) {
            Uri image = data.getData();
            try {
//                선택한 이미지 저장
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}