package com.techBMT.YTV_Player_Pro.activity2file;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techBMT.YTV_Player_Pro.R;
import com.techBMT.YTV_Player_Pro.activity.MainActivityChinh;
import com.techBMT.YTV_Player_Pro.constant.SharedPref;

public class IntroActivity extends AppCompatActivity {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

         findViewById(R.id.viewStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.setFirstOpenApp(false,getApplicationContext());
                Intent intent = new Intent(IntroActivity.this, SecondScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}