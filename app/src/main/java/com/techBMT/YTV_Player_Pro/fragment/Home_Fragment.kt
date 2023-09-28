package com.techBMT.YTV_Player_Pro.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techBMT.YTV_Player_Pro.activity.ShowDLActivity
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.activity2file.MainActivity_File
import com.techBMT.YTV_Player_Pro.databinding.FragmentHomeBinding

class Home_Fragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.layoutclickRelativeLayout.setOnClickListener {
            startActivity(Intent(context, ShowDLActivity::class.java))
        }

        binding.layoutclickConstraintLayoutFile.setOnClickListener {
            startActivity(Intent(context, MainActivity_File::class.java))
        }

    }

}