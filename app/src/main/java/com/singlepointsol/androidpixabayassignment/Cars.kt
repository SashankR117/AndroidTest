package com.singlepointsol.project

import com.google.gson.annotations.SerializedName

data class Cars(
    @SerializedName("webformatURL")
    val carImage: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("likes")
    val likes: Int
)

// Wrapper class for the entire response
data class CarResponse(
    @SerializedName("hits")
    val hits: List<Cars>
)
