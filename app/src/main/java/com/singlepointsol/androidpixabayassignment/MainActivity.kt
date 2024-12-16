package com.singlepointsol.androidpixabayassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.singlepointsol.project.ApiService
import com.singlepointsol.project.Cars
import com.singlepointsol.project.CarsAdapter
import com.singlepointsol.project.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var flowersArrayList: ArrayList<Cars>
    lateinit var carsAdapter: CarsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        flowersArrayList = arrayListOf()
        carsAdapter = CarsAdapter(flowersArrayList)
        recyclerView.adapter = carsAdapter

        // Fetch flowers data from API
        fetchFlowersData()
    }

    // Coroutine scope to fetch data
    private fun fetchFlowersData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.getRetrofitInstance()
                    .create(ApiService::class.java)
                    .getFlowers() // Calling suspend function

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val carsList = response.body()?.hits ?: emptyList()
                        carsArrayList.clear()
                        carsArrayList.addAll(carsList)
                        carsAdapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}