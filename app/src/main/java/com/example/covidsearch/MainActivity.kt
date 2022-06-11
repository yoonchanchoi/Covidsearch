package com.example.covidsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
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

    private lateinit var binding: ActivityMainBinding
    private lateinit var covidViewModel: CovidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CovidRepository()
        val viewModelFactory = CovidViewModelFactory(repository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        covidViewModel = ViewModelProvider(this, viewModelFactory)[CovidViewModel::class.java]
        binding.btnSearch.setOnClickListener {
            covidViewModel.search(binding.etSearch.toString())
        }
        observeCovidList()
        observeToast()
    }

    private fun observeToast() {
        covidViewModel.liveToast.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeCovidList() {
        covidViewModel.liveCovidVo.observe(this, Observer { covidList ->
            Log.d("testCovidResponse", covidList.toString())
        })
    }
}