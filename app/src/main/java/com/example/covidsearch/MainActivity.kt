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

//    private fun search(){
//        val observableTextQuery = Observable
//            .create(ObservableOnSubscribe{ emitter: ObservableEmitter<String>? ->
//                binding.etSearch.addTextChangedListener(object : TextWatcher{
//                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun afterTextChanged(p0: Editable?) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//
//
//            })
//
//    }


    override fun onDestroy() {
        super.onDestroy()

    }

}