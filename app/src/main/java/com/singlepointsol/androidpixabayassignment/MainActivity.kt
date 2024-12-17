package com.singlepointsol.androidpixabayassignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.singlepointsol.androidpixabayassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var carsArrayList: ArrayList<Cars>
    private lateinit var carsAdapter: CarsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        carsArrayList = arrayListOf()
        carsAdapter = CarsAdapter(carsArrayList)
        recyclerView.adapter = carsAdapter

        // Fetch cars data from API
        fetchCarsData()
    }

    // Coroutine scope to fetch data
    @SuppressLint("NotifyDataSetChanged")
    private fun fetchCarsData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.getRetrofitInstance()
                    .create(ApiService::class.java)
                    .getCars()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val carsList = response.body()?.hits ?: emptyList()
                        carsArrayList.clear()
                        carsArrayList.addAll(carsList)
                        carsAdapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

