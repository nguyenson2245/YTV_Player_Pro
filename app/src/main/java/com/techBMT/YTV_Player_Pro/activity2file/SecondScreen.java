package com.techBMT.YTV_Player_Pro.activity2file;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.activity.MainActivityChinh;
import com.techBMT.YTV_Player_Pro.constant.SharedPref;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SecondScreen extends AppCompatActivity {

    Button btn_allow, btn_alter;

    private static final int REQUEST_PERMISSION_CODE = 10;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.techBMT.YTV_Player_Pro.R.layout.activity_second_screen);

        btn_allow = findViewById(R.id.btn_allow);
        btn_alter = findViewById(R.id.btn_Later);

        SharedPref.setFirstOpenApp(false,getApplicationContext());
        btn_allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissionFromLibrary();
            }
        });

        btn_alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondScreen.this, MainActivityChinh.class));
            }
        });
    }

    private void openSettingPermission() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

//    private void clickRequsetPermission() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return;
//        }
//
//        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
//                checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
//        ) {
//            Toast.makeText(this, "ĐÃ cấp", Toast.LENGTH_SHORT).show();
//        } else {
//            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE};
//            requestPermissions(permission, REQUEST_PERMISSION_CODE);
//        }
//    }

//    @SuppressLint("MissingSuperCall")
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == REQUEST_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    &&grantResults[1] == PackageManager.PERMISSION_GRANTED
//            ) {
//                Toast.makeText(this, "ĐÃ cấp", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "từ chối", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void requestPermissionFromLibrary(){
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {  // cho phép sd quyền
                        Toast.makeText(SecondScreen.this, "ĐÃ cấp", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) { // khi từ chối
//                        Toast.makeText(SecondScreen.this, "Khong được cấp", Toast.LENGTH_SHORT).show();
                    }
                }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                .setDeniedTitle("Open Setting permissions")
                .setDeniedMessage("Please turn on permissions at [Setting] > [Permission] > [Access to All Files] > [Allow]")
                .check();
    }


//    private void KiemTraCheck() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//            Uri uri = Uri.fromParts("package", getPackageName(), null);
//            intent.setData(uri);
//            startActivityForResult(intent, REQUEST_CODE_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//        } else {
//
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_MANAGE_ALL_FILES_ACCESS_PERMISSION) {
//            check();
//        }
//
//    }
//
//    private void check() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
//            File file = new File(Environment.getExternalStorageDirectory(), "ten_file.txt");
//            try {
//                FileInputStream inputStream = new FileInputStream(file);
//                InputStreamReader reader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                String line = bufferedReader.readLine();
//                while (line != null) {
//                    line = bufferedReader.readLine();
//                }
//                bufferedReader.close();
//                reader.close();
//                inputStream.close();
//            } catch (IOException e) {
//
//            }
//        } else {
//            Toast.makeText(this, "//Permission is not enabled, you cannot access the memory ", Toast.LENGTH_SHORT).show();
//        }
//    }
}