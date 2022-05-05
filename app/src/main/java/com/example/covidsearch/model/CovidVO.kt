package com.example.rxjavaretrofit.model

import com.google.gson.annotations.SerializedName

data class CovidVO(

    @SerializedName("countryName")
    var countryName: String,

    @SerializedName("newCase")
    var newCase: String,

    @SerializedName("totalCase")
    var totalCase: String,

    @SerializedName("recovered")
    var recovered: String,

    @SerializedName("death")
    var death: String,

    @SerializedName("percentage")
    var percentage: String,

    @SerializedName("newFcase")
    var newFcase: String,

    @SerializedName("newCcase")
    var newCcase: String
)
