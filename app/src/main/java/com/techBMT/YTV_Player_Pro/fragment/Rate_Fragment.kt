package com.techBMT.YTV_Player_Pro.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.techBMT.YTV_Player_Pro.R


class Rate_Fragment : Fragment() {

    lateinit var button_rateUss: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_rate, container, false)
        button_rateUss = view.findViewById(R.id.button_rateUss)

        button_rateUss.setOnClickListener {
            openAppRating()
        }
        return view
    }


    private fun openAppRating() {
        val packageName = requireContext().packageName
        val marketUri = Uri.parse("market://details?id=$packageName")
        val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)

        try {
            startActivity(marketIntent)
        } catch (e: ActivityNotFoundException) {
            val playStoreUri =
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val playStoreIntent = Intent(Intent.ACTION_VIEW, playStoreUri)
            startActivity(playStoreIntent)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.home) {
            requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            requireActivity().onBackPressed()
            requireActivity().supportFragmentManager.popBackStack()
            return true
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TAGRate", "onDestroyViewRate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAGRate", "onDestroyRate: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAGRate", "onPauseViewRate: ")
    }

}