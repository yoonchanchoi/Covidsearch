package com.example.covidsearch.repository

import com.example.covidsearch.network.CovidRetrofit

class CovidRepository {

    fun getCovidInfo(serviceKey: String = "IxEUeDafZwCP8FptGrd3TcKL4zoOJNiYH") =
        CovidRetrofit.covidApiService.getDocument(serviceKey)

}