
package com.singlepointsol.project

import android.annotation.SuppressLint
import  android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.singlepointsol.androidpixabayassignment.R

class CarsActivity : AppCompatActivity() {
    lateinit var flowerImageView: ImageView
    lateinit var userName: TextView
    lateinit var viewCount: TextView
    lateinit var downloadCount: TextView
    lateinit var likesCount: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars)

        carsImageView = findViewById(R.id.cars_imageView)
        userName = findViewById(R.id.user_name)
        viewCount = findViewById(R.id.view_count)
        downloadCount = findViewById(R.id.download_count)
        likesCount = findViewById(R.id.likes_count)

        val imageUrl = intent.getStringExtra("flowerImage")
        val user = intent.getStringExtra("user")
        val views = intent.getIntExtra("views", 0)
        val downloads = intent.getIntExtra("downloads", 0)
        val likes = intent.getIntExtra("likes", 0)

        Glide.with(this).load(imageUrl).into(carsImageView)

        userName.text = "User: $user"
        viewCount.text = "Views: $views"
        likesCount.text = "Likes: $likes"
    }
}
