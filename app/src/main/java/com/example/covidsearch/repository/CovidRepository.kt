package com.example.covidsearch.repository

import com.example.covidsearch.network.CovidRetrofit

class CovidRepository {

    fun getCovidInfo(serviceKey: String) =
        CovidRetrofit.covidApiService.getDocument(serviceKey)

}