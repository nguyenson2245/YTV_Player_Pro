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

    private void requestPermissionFromLibrary(){
        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(SecondScreen.this, "ĐÃ cấp", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) { // khi từ chối
                    }
                }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                .setDeniedTitle("Open Setting permissions")
                .setDeniedMessage("Please turn on permissions at [Setting] > [Permission] > [Access to All Files] > [Allow]")
                .check();
    }

}