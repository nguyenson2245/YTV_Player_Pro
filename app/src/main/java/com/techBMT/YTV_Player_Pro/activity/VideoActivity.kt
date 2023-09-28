package com.techBMT.YTV_Player_Pro.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.techBMT.YTV_Player_Pro.R


class VideoActivity : AppCompatActivity() {

    private lateinit var videoView: com.devbrackets.android.exomedia.ui.widget.VideoView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(R.layout.activity_video)
        supportActionBar?.hide()

        val videoUrl = intent.getStringExtra("videoUrl")
        videoView = findViewById(R.id.video_view) as com.devbrackets.android.exomedia.ui.widget.VideoView
        videoView.setMedia(Uri.parse(videoUrl))
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}