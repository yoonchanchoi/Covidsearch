package com.example.covidsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidsearch.model.CovidVO
import com.example.covidsearch.model.StateVO
import com.example.covidsearch.repository.CovidRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class CovidViewModel(private val repo: CovidRepository) : ViewModel() {

    private val _liveCovidVo: MutableLiveData<CovidVO> = MutableLiveData()
    private val _liveStateVo: MutableLiveData<StateVO> = MutableLiveData()
    private val _liveToast: MutableLiveData<String> = MutableLiveData()
    private val disposable = CompositeDisposable()
    private val _liveSearch: MutableLiveData<ArrayList<CovidVO?>> = MutableLiveData()

    val liveCovidVo: LiveData<CovidVO>
        get() = _liveCovidVo
    val liveStateVo: LiveData<StateVO>
        get() = _liveStateVo
    val liveToast: LiveData<String>
        get() = _liveToast

    fun search(text: String){
        repo.getCovidInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { response ->
                when (text) {
                    response.busan.countryName -> response.busan
                    response.seoul.countryName -> response.seoul
                    response.chungbuk.countryName -> response.chungbuk
                    response.chungnam.countryName -> response.chungnam
                    response.daegu.countryName -> response.daegu
                    response.ulsan.countryName -> response.ulsan
                    response.sejong.countryName -> response.sejong
                    response.daejeon.countryName -> response.daejeon
                    response.gwangju.countryName -> response.gwangju
                    response.gyeongbuk.countryName -> response.gyeongbuk
                    response.gyeonggi.countryName -> response.gyeonggi
                    response.gyeongnam.countryName -> response.gyeongnam
                    response.gangwon.countryName -> response.gangwon
                    response.incheon.countryName -> response.incheon
                    response.jeonnam.countryName -> response.jeonnam
                    response.quarantine.countryName -> response.quarantine
                    response.jeju.countryName -> response.jeju
                    response.jeonbuk.countryName -> response.jeonbuk
                    else -> response.korea
                }
            }
            .subscribeBy(
                onNext = { localCovidVo ->
                    _liveCovidVo.value = localCovidVo
                    _liveToast.value = "불러오기 성공"
                },
                onError = {
                    Log.d("실패실패..", "${it.localizedMessage.toString()}")
                    Log.d("실패실패..", "${it.message.toString()}")
                    _liveToast.value = it.localizedMessage
                }
            ).addTo(disposable)
//        val filterString = charSequence.toString()
//        val results = FilterResults()
//
//            //검색이 필요없을 경우를 위해 원본 배열을 복제
//        val filteredList: ArrayList<CovidVo> = ArrayList<CovidVo>()
//            //공백제외 아무런 값이 없을 경우 -> 원본 배열
//        if (filterString.trim { it <= ' ' }.isEmpty()) {
//            results.values = CovidVO
//            results.count = CovidVO.size
//
//            return results
//                //공백제외 2글자 이하인 경우 -> 이름으로만 검색
//        } else if (filterString.trim { it <= ' ' }.length <= 2) {
//            for (person in persons) {
//                if (person.name.contains(filterString)) {
//                    filteredList.add(person)
//                }
//            }
//        }
    }

//    inner class ItemFilter : Filter() {
//        override fun performFiltering(charSequence: CharSequence): FilterResults {
//            val filterString = charSequence.toString()
//            val results = FilterResults()
//            Log.d(TAG, "charSequence : $charSequence")
//
//            //검색이 필요없을 경우를 위해 원본 배열을 복제
//            val filteredList: ArrayList<Person> = ArrayList<Person>()
//            //공백제외 아무런 값이 없을 경우 -> 원본 배열
//            if (filterString.trim { it <= ' ' }.isEmpty()) {
//                results.values = persons
//                results.count = persons.size
//
//                return results
//                //공백제외 2글자 이하인 경우 -> 이름으로만 검색
//            } else if (filterString.trim { it <= ' ' }.length <= 2) {
//                for (person in persons) {
//                    if (person.name.contains(filterString)) {
//                        filteredList.add(person)
//                    }
//                }
//                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
//            } else {
//                for (person in persons) {
//                    if (person.name.contains(filterString) || person.phoneNumber.contains(filterString)) {
//                        filteredList.add(person)
//                    }
//                }
//            }
//            results.values = filteredList
//            results.count = filteredList.size
//
//            return results
//        }
//
//        //...
//    }


}