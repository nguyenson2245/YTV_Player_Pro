package com.techBMT.YTV_Player_Pro.adapter_tv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techBMT.YTV_Player_Pro.R
import com.techBMT.YTV_Player_Pro.activity.UpdateActivity
import com.techBMT.YTV_Player_Pro.activity.VideoActivity

class CustomAdapter(
    private val activity: Activity,
    private val context: Context,
    private val book_id: ArrayList<String>,

    private val book_title: ArrayList<String>,
    private val book_author: ArrayList<String>,
    private val book_pages: ArrayList<String>


) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

//    fun setData(bookIdList: ArrayList<String>, bookTitleList: ArrayList<String>, bookAuthorList: ArrayList<String>, bookPagesList: ArrayList<String>) {
//        book_id = bookIdList
//        book_title = bookTitleList
//        book_author = bookAuthorList
//        book_pages = bookPagesList
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.my_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.book_title_txt.text = book_title[position]
        holder.book_url_txt.text = book_author[position]


        holder.mainLayout.setOnClickListener {
            val videoUrl: String = book_author[position]
            val intent: Intent = Intent(holder.itemView.context, VideoActivity::class.java)
            intent.putExtra("videoUrl", videoUrl);
            holder.itemView.getContext().startActivity(intent);
        }

        holder.click_image.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", book_id[position])
            intent.putExtra("title", book_title[position])
            intent.putExtra("author", book_author[position])
            intent.putExtra("pages", book_pages[position])
            activity.startActivityForResult(intent, 1)
        }

    }

    override fun getItemCount(): Int {
        return book_id.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val book_title_txt: TextView = itemView.findViewById(R.id.book_title_txt)
        val book_url_txt: TextView = itemView.findViewById(R.id.book_author_txt)
        val click_image: ImageView = itemView.findViewById(R.id.click_image)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.thongtinshowLinnerlayout)

        init {
            val translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim)
            mainLayout.animation = translate_anim
        }
    }
}