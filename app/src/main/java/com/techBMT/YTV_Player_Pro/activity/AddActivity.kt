package com.techBMT.YTV_Player_Pro.activity

import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.activity2file.dataBase.MyDatabaseHelper

class AddActivity : AppCompatActivity() {

    lateinit var db: MyDatabaseHelper
    lateinit var title_input: EditText
    lateinit var author_input: EditText
    lateinit var pages_input: EditText
    lateinit var add_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        Anhxa();
        clickSuKien()
    }

    private fun clickSuKien() {

        add_button.setOnClickListener {
            val title = title_input.text.toString().trim()
            val author = author_input.text.toString().trim()
            val pages = pages_input.text.toString().trim()

            if (URLUtil.isValidUrl(author)) {
                val myDB = MyDatabaseHelper(this)
                myDB.addBook(title, author, pages)
                title_input.setText("")
                author_input.setText("")
                pages_input.setText("")

                val intent = Intent(this, ShowDLActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "URL is not valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Anhxa() {
        title_input = findViewById(R.id.title_input)
        author_input = findViewById(R.id.author_input)
        pages_input = findViewById(R.id.pages_input)
        add_button = findViewById<Button>(R.id.add_button)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.show()
    }


}