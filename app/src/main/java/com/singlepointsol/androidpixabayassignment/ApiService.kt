package com.singlepointsol.androidpixabayassignment

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("https://pixabay.com/api/?key={ KEY }&q=cars&image_type=photo")
    suspend fun getCars(): Response<CarResponse>
}
