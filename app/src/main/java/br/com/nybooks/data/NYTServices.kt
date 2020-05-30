package br.com.nybooks.data

import br.com.nybooks.data.response.BooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {
    @GET("lists.json")
    fun listBooks(
        @Query("api-key") apiKey: String = "gEo8naYWfxMWKrYjNurXAlM9VKd9AbzG",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BooksResponse>
}