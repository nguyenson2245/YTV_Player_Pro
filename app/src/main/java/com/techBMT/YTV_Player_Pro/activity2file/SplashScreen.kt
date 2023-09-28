package com.techBMT.YTV_Player_Pro.activity2file

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.activity.MainActivityChinh
import com.techBMT.YTV_Player_Pro.constant.SharedPref

class SplashScreen : AppCompatActivity() {

    lateinit var imgView: ImageView
    lateinit var textView: TextView
    lateinit var animation: Animation
    val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imgView = findViewById(R.id.imgLogo)
        textView = findViewById(R.id.textViewLoGo)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        animation = AnimationUtils.loadAnimation(this, R.anim.move_top_animation)
        imgView.startAnimation(animation)
        textView.startAnimation(animation)

        Handler().postDelayed({
            if (SharedPref.isFirstOpenApp(this)) {
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivityChinh::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)

    }
}