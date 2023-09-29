package com.techBMT.YTV_Player_Pro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.techBMT.YTV_Player_Pro.R

class Privacy_Fragment : Fragment() {
    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_privacy, container, false)

        textView = view.findViewById(R.id.tvPrivacyPolicy)

        val privacyPolicyText: String = resources.getString(R.string.privacy_policy_text)
        val formattedText = HtmlCompat.fromHtml(privacyPolicyText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        textView.text = formattedText

        return view;
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.home) {
            requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            requireActivity().onBackPressed()
            return true
        }
        return super.onContextItemSelected(item)
    }

}