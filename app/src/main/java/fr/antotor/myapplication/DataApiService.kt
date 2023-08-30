package fr.antotor.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DataApiService {
    @GET("todos")
    fun getTodos(): Call<List<Todos>>

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: DataApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DataApiService::class.java)
    }
}