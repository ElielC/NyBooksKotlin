package br.com.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nybooks.data.ApiService
import br.com.nybooks.data.model.Book
import br.com.nybooks.data.response.BooksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {
    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        ApiService.service.listBooks().enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    val books = mutableListOf<Book>()

                    response.body()?.bookResults?.forEach { bookResult ->
                        val bookDetail = bookResult.bookDetails.first()
                        val book = Book(
                            bookDetail.title,
                            bookDetail.author,
                            bookDetail.description
                        )

                        books.add(book)
                    }

                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {

            }
        })
    }

}