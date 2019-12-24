package com.albert.openlibrary.backendCallers

import com.albert.openlibrary.beans.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the interface that connects
 * to the backend with the end-point
 * and search criteria to return
 * results to the user.
 */
interface GitHubService {
    @GET("search.json")
    fun listRepos(@Query("title") user :String) : Call<Book>
}