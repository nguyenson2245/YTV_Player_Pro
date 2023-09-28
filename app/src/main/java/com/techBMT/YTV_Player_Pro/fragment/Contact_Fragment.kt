package com.techBMT.YTV_Player_Pro.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.techBMT.YTV_Player_Pro.R


class Contact_Fragment : Fragment() {

    lateinit var email_id: EditText
    lateinit var email_subject: EditText
    lateinit var email_message: EditText

    lateinit var button_send: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_contact, container, false)

        email_id = view.findViewById(R.id.idMail)
        email_subject = view.findViewById(R.id.idSubject)
        email_message = view.findViewById(R.id.idMessage)
        button_send = view.findViewById(R.id.buttonone_senemail)

        button_send.setOnClickListener {
            val s1: String = email_id.text.toString()
            val s2: String = email_subject.text.toString()
            val s3: String = email_message.text.toString()

            val mail = arrayOf(s1)
            val intent: Intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            intent.putExtra(Intent.EXTRA_SUBJECT, s2)
            intent.putExtra(Intent.EXTRA_TEXT, s3)
            intent.setType("message/rfc822")
            startActivity(intent)
        }

        return view

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
}