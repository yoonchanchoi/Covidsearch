package com.example.rxjavaretrofit.model

import com.google.gson.annotations.SerializedName

data class StateVO(
    @SerializedName("korea")
    var korea: CovidVO,

    @SerializedName("seoul")
    var seoul: CovidVO,

    @SerializedName("busan")
    var busan: CovidVO,

    @SerializedName("daegu")
    var daegu: CovidVO,

    @SerializedName("incheon")
    var incheon: CovidVO,

    @SerializedName("gwangju")
    var gwangju: CovidVO,

    @SerializedName("daejeon")
    var daejeon: CovidVO,

    @SerializedName("ulsan")
    var ulsan: CovidVO,

    @SerializedName("sejong")
    var sejong: CovidVO,

    @SerializedName("gyeonggi")
    var gyeonggi: CovidVO,

    @SerializedName("gangwon")
    var gangwon: CovidVO,

    @SerializedName("chungbuk")
    var chungbuk: CovidVO,

    @SerializedName("chungnam")
    var chungnam: CovidVO,

    @SerializedName("jeonbuk")
    var jeonbuk: CovidVO,

    @SerializedName("jeonnam")
    var jeonnam: CovidVO,

    @SerializedName("gyeongbuk")
    var gyeongbuk: CovidVO,

    @SerializedName("gyeongnam")
    var gyeongnam: CovidVO,

    @SerializedName("jeju")
    var jeju: CovidVO,

    @SerializedName("quarantine")
    var quarantine: CovidVO
)
