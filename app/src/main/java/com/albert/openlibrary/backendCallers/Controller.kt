package com.albert.openlibrary.backendCallers

import android.util.Log
import com.albert.openlibrary.OpenLibraryApplication
import com.albert.openlibrary.beans.Book
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class handles the data retrieval
 * from the backend.
 * The search criteria is passed by the
 * user looking for a book based off of a
 * book's title.
 */
class Controller : Callback<Book> {

    /**
     * This is the base URL based off of
     * which the data will be downloaded.
     */
    companion object {
        var BASE_URL ="http://openlibrary.org/"
        var ERROR_TAG = "Error"
    }

    /**
     * This is the method that calls the
     * backend to retrieve data, and share
     * with the user on the AutoCompleteTextView.
     * The searchItem is the text that is being
     * passed as the user types in characters
     * to search.
     */
   fun start(searchItem : String) {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val service = retrofit.create(GitHubService::class.java)
        val call = service.listRepos(searchItem)
        call.enqueue(this)
    }

    /**
     * This is the method that processes
     * the response and send to the intended
     * recipient.
     * The Bus used in the application is used
     * to send the data to the search_fragment
     * to show to the user.
     */
    override fun onResponse(call: Call<Book>?, response: Response<Book>?) {
        if (response!!.isSuccessful) {
            OpenLibraryApplication.getDefaultBus().post(response.body().docs)
        } else {
            Log.e(ERROR_TAG, response.errorBody().toString())
        }
    }

    /**
     * This method logs the error as
     * in case something breaks from
     * the backend.
     */
    override fun onFailure(call: Call<Book>?, t: Throwable?) {
        Log.e(ERROR_TAG, t.toString())
    }
}