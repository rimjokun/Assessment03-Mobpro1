package org.d3if3032.assessment03.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3032.assessment03.model.Anime
import org.d3if3032.assessment03.model.AnimeCreate
import org.d3if3032.assessment03.model.MessageResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
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
    @POST("posts/")
    suspend fun addAnime(
        @Body anime: AnimeCreate
    ): MessageResponse

    @GET("posts/")
    suspend fun getAllAnime(
        @Query("user_email") email: String,
    ): List<Anime>

    @DELETE("posts/{post_id}")
    suspend fun deleteAnime(
        @Path("post_id") id: Int,
        @Query("email") email: String
    ): MessageResponse
}


object Api {
    val userService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

}

enum class ApiStatus { LOADING, SUCCESS, FAILED }