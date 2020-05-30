package br.com.nybooks.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BooksResponse(
    @Json(name = "results")
    val bookResults: List<BookResultResponse>
)