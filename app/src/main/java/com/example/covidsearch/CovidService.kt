package com.example.covidsearch

import com.example.covidsearch.model.StateVO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService{
    @GET("/korea/country/new/")
    fun getDocument(@Query("serviceKey") serviceKey: String): Observable<StateVO>
}