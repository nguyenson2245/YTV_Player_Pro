package com.techBMT.YTV_Player_Pro.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.dataBase.MyDatabaseHelper

class UpdateActivity : AppCompatActivity() {

    lateinit var title_input: EditText
    lateinit var author_input: EditText
    lateinit var pages_input: EditText
    lateinit var update_button: Button
    lateinit var delete_button: Button

    lateinit var id: String
    lateinit var title: String
    lateinit var author: String
    lateinit var pages: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        title_input = findViewById(R.id.title_input2)
        author_input = findViewById(R.id.author_input2)
        pages_input = findViewById(R.id.pages_input2)
        update_button = findViewById(R.id.update_button)
        delete_button = findViewById(R.id.delete_button)

        getAndSetIntentData()

        val ab = supportActionBar
        if (ab != null) {
            ab.title = title
        }

        update_button.setOnClickListener {
            update()
        }

        delete_button.setOnClickListener {
            confirmDialog()
        }

    }

    private fun update() {
        val myDB = MyDatabaseHelper(this)
        title = title_input.text.toString().trim()
        author = author_input.text.toString().trim()
        pages = pages_input.text.toString().trim()
        myDB.updateData(id, title, author, pages)

        val intent = Intent(this, ShowDLActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    fun getAndSetIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("title") &&
            intent.hasExtra("author") && intent.hasExtra("pages")
        ) {
            id = intent.getStringExtra("id")!!
            title = intent.getStringExtra("title")!!
            author = intent.getStringExtra("author")!!
            pages = intent.getStringExtra("pages")!!

            title_input.setText(title)
            author_input.setText(author)
            pages_input.setText(pages)

            Log.d("stev", "$title $author $pages")
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        var book_id: ArrayList<String> = ArrayList()
        var book_title: ArrayList<String> = ArrayList()
        var book_author: ArrayList<String> = ArrayList()
        var book_pages: ArrayList<String> = ArrayList()

        builder.setTitle("Delete $title ?")
        builder.setMessage("Are you sure you want to delete $title ?")

        builder.setPositiveButton("Yes") { dialogInterface, i ->
            val myDB = MyDatabaseHelper(this)
            myDB.deleteOneRow(id)
            update()

            finish()
        }
        builder.setNegativeButton("No") { dialogInterface, i -> }
        builder.create().show()
    }
}
