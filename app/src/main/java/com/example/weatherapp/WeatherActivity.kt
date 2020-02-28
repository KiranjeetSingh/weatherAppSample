package com.example.weatherapp

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.WeatherList
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var weatherList = ArrayList<WeatherList>()
    var todoItmeAdapter: WeatherForecastItemAdapter?=null
    lateinit var binding: ActivityMainBinding
    val weatherViewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = weatherViewModel
        binding.executePendingBindings()
        handleObservers()
        setAdapter()
        weatherViewModel.getWeather()
    }

    private fun handleObservers(){
        weatherViewModel.weatherListData.observe(this, Observer {list->
            if(list.isNotEmpty()) {
                weatherList.addAll(list)
                todoItmeAdapter?.notifyDataSetChanged()
                binding.btnForecast.visibility = View.GONE
                binding.rv.visibility = View.VISIBLE
            }
        })

        weatherViewModel.errorData.observe(this, Observer {messg->
            Toast.makeText(this,messg,Toast.LENGTH_SHORT).show()
        })

    }


    private fun setAdapter() {
        todoItmeAdapter = WeatherForecastItemAdapter(
            this,
            weatherList
        )
        binding.rv?.apply {
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            addItemDecoration(DividerItemDecoration(this@WeatherActivity, DividerItemDecoration.VERTICAL))
            adapter = todoItmeAdapter
        }
    }
}
