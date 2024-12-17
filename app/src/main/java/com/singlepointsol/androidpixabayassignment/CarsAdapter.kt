package com.singlepointsol.androidpixabayassignment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarsAdapter(private val carsArrayList: ArrayList<Cars>) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImage: ImageView = itemView.findViewById(R.id.carslist_image)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val viewCount: TextView = itemView.findViewById(R.id.view_count)
        val likesCount: TextView = itemView.findViewById(R.id.likes_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cars_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = carsArrayList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carsArrayList[position]

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(car.carImage).into(holder.carImage)

        // Set the text for the user, views, downloads, and likes
        holder.userName.text = "User: ${car.user}"
        holder.viewCount.text = "Views: ${car.views}"
        holder.likesCount.text = "Likes: ${car.likes}"

        // Handle item click event to pass the data to the next activity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CarsActivity::class.java)
            intent.putExtra("carImage", car.carImage)
            intent.putExtra("user", car.user)
            intent.putExtra("views", car.views)
            intent.putExtra("likes", car.likes)
            holder.itemView.context.startActivity(intent)
        }
    }

}
