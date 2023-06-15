package com.capstonebangkit.siboeah

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("imageUrl")
    val imageUrl: String?
)
