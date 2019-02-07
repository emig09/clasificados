package com.gudino.clasificados_ml.api

import com.gudino.clasificados_ml.model.Classified
import com.gudino.clasificados_ml.model.MLResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassifiedsApi {

    @GET("/sites/MLA/search")
    fun getSearchResponse(@Query("q") searchQuery: String): Call<MLResponse>

    @GET("/items/{id}")
    fun getClassifiedDetail(@Path("id") classifiedId: String): Call<Classified>
}