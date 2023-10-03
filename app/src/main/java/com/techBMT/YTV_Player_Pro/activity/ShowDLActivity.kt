package com.techBMT.YTV_Player_Pro.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.adapter_tv.CustomAdapter
import com.techBMT.YTV_Player_Pro.dataBase.MyDatabaseHelper

class ShowDLActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var add_button: FloatingActionButton
    lateinit var empty_imageview: ImageView
    lateinit var no_data: TextView

    lateinit var myDB: MyDatabaseHelper

    var book_id: ArrayList<String> = ArrayList()
    var book_title: ArrayList<String> = ArrayList()
    var book_author: ArrayList<String> = ArrayList()
    var book_pages: ArrayList<String> = ArrayList()

    lateinit var customAdapter: CustomAdapter

    private var dataChangedFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.show_dl_activity_1)

        FloatingActionButton()
        AnhXA()

        add_button?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        })

        myDB = MyDatabaseHelper(this)
        book_id = java.util.ArrayList()
        book_title = java.util.ArrayList()
        book_author = java.util.ArrayList()
        book_pages = java.util.ArrayList()

        storeDataInArrays()

        customAdapter = CustomAdapter(this, this, book_id, book_title, book_author, book_pages)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)

        recyclerView.setAdapter(customAdapter)
        customAdapter.notifyDataSetChanged()


    }

    private fun AnhXA() {
        recyclerView = findViewById(R.id.recyclerView)
        add_button = findViewById(R.id.add_button)
        empty_imageview = findViewById(R.id.empty_imageview)
        no_data = findViewById(R.id.no_data)
    }

    fun storeDataInArrays() {
        val cursor = myDB!!.readAllData()
        if (cursor!!.count == 0) {
            empty_imageview!!.visibility = View.VISIBLE
            no_data!!.visibility = View.VISIBLE
        } else {
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0))
                book_title.add(cursor.getString(1))
                book_author.add(cursor.getString(2))
                book_pages.add(cursor.getString(3))
            }
            empty_imageview!!.visibility = View.GONE
            no_data!!.visibility = View.GONE
        }
    }


    override fun onResume() {
        super.onResume()
        supportActionBar?.show()
    }

    private fun FloatingActionButton() {
        add_button = findViewById(R.id.add_button)
        add_button.setOnClickListener {

            val intent = Intent(this, AddActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.outapp2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.deleter) {
            confirmDialog()
        } else if (item.getItemId() == R.id.outAPP) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton("Yes") { dialogInterface, i ->
            val myDB = MyDatabaseHelper(this)
            myDB.deleteAllData()
//            val intent = Intent(this, ShowDLActivity::class.java)
//            startActivity(intent)
//            finish()
            recyclerView.invalidate()

            book_id.clear()
            book_title.clear()
            book_author.clear()
            book_pages.clear()

            storeDataInArrays()

            customAdapter.notifyDataSetChanged()
            Log.d("TAG", "confirmDialog: "  + i)



        }
        builder.setNegativeButton("No") { dialogInterface, i ->
            // Do nothing
        }
        builder.create().show()
    }

}

