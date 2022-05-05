package com.example.rxjavaretrofit.repository

import com.example.rxjavaretrofit.network.CovidRetrofit

class CovidRepository {

    fun getCovidInfo(serviceKey: String) =
        CovidRetrofit.covidApiService.getDocument(serviceKey)

}