package com.capstonebangkit.siboeah

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("gambar.json")
    fun postGambar(@Body uploadResponse: UploadResponse): Call<Void>


    @Multipart
    @POST("Upload")
    fun postImage(@Part image: MultipartBody.Part): Call<Void>
}


