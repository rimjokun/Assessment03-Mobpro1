package org.d3if3032.assessment03.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3032.assessment03.model.Anime
import org.d3if3032.assessment03.model.MessageResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://anime-log-iota.vercel.app/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface UserApi {
    @Multipart
    @POST("posts/")
    suspend fun addData(
        @Part("judul_anime") title: RequestBody,
        @Part("episode") type: RequestBody,
        @Part("musim") price: RequestBody,
        @Part("user_email") userEmail: RequestBody,
        @Part file: MultipartBody.Part
    ): Anime

    @GET("posts/")
    suspend fun getAllData(
        @Query("email") email: String,
    ): List<Anime>


    @DELETE("posts/{post_id}")
    suspend fun deleteData(
        @Path("post_id") id: Int,
        @Query("email") email: String
    ): MessageResponse
}


object Api {
    val userService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun getImageUrl(imageId: String): String{
        return BASE_URL + "posts/images/$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }