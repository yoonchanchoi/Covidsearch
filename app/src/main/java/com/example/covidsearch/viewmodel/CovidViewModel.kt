package com.example.rxjavaretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaretrofit.model.CovidVO
import com.example.rxjavaretrofit.model.StateVO
import com.example.rxjavaretrofit.repository.CovidRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class CovidViewModel(private val repo: CovidRepository) : ViewModel() {

    private val _liveCovidVo: MutableLiveData<ArrayList<CovidVO?>> = MutableLiveData()
    private val _liveStateVo: MutableLiveData<StateVO> = MutableLiveData()
    private val _liveToast: MutableLiveData<String> = MutableLiveData()
    private val disposable = CompositeDisposable()


    val liveCovidVo: LiveData<ArrayList<CovidVO?>>
        get() = _liveCovidVo
    val liveStateVo: LiveData<StateVO>
        get() = _liveStateVo
    val liveToast: LiveData<String>
        get() = _liveToast

    fun getAll(key: String) {
        repo.getCovidInfo(key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //여기에서 라이브데이터에 받은 데이터를 넣어줘야될것으로 보이는 맞나?
                //맞으면 어떻게 넣어줘야됨?
                val regionList = arrayListOf(
                    it?.korea,
                    it?.seoul,
                    it?.busan,
                    it?.incheon,
                    it?.gwangju,
                    it?.jeonbuk,
                    it?.chungbuk,
                    it?.jeonnam,
                    it?.gyeongbuk,
                    it?.daegu,
                    it?.ulsan,
                    it?.daejeon,
                    it?.sejong,
                    it?.chungnam,
                    it?.gyeonggi,
                    it?.gyeongnam,
                    it?.gangwon,
                    it?.jeju,
                    it?.quarantine
                )
                Log.d("성공성공!", regionList.toString())
                _liveCovidVo.postValue(regionList)

            }, {
                Log.d("실패실패..", "${it.localizedMessage.toString()}")
                Log.d("실패실패..", "${it.message.toString()}")
                _liveToast.postValue(it.localizedMessage)
            }).addTo(disposable)
    }

}