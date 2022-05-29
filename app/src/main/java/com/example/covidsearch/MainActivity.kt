package com.example.covidsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidsearch.adapter.CovidViewAdapter
import com.example.covidsearch.databinding.ActivityMainBinding
import com.example.covidsearch.network.CovidApi.Companion.TOKEN
import com.example.covidsearch.repository.CovidRepository
import com.example.covidsearch.viewmodel.CovidViewModel
import com.example.covidsearch.viewmodel.CovidViewModelFactory
import io.reactivex.Observable.create
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var covidViewModel: CovidViewModel

    private val covidAdapter: CovidViewAdapter by lazy {
        CovidViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CovidRepository()
        val viewModelFactory = CovidViewModelFactory(repository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        covidViewModel = ViewModelProvider(this, viewModelFactory).get(CovidViewModel::class.java)
        covidViewModel.getAll(TOKEN)
        initAdapter()
        observeCovidList()
//        search()
    }

    private fun initAdapter() {
        binding.re.adapter = covidAdapter
    }

    private fun observeCovidList() {
        covidViewModel.liveCovidVo.observe(this, Observer { covidList ->
            covidAdapter.setList(covidList)
        })
    }

    val observableTextQuery = Observable
        .create(/* observable 추가 */)
        .debounce(500, TimeUnit.MILLISECONDS)  //입력 후 0.5간 추가 입력이 없어야만 작동
        .subscribeOn(Schedulers.io())  //새로운 스레드에서 작업

    val observableTextQuery = Observable
        .create(ObservableOnSubscribe { emitter: ObservableEmitter<String>? ->
            movieNameET.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    emitter?.onNext(s.toString())
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })
        })
        .debounce(500, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())

    override fun onDestroy() {
        super.onDestroy()

    }

}