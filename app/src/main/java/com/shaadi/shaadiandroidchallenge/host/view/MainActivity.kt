package com.shaadi.shaadiandroidchallenge.host.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shaadi.shaadiandroidchallenge.R
import com.shaadi.shaadiandroidchallenge.databinding.ActivityMainBinding
import com.shaadi.shaadiandroidchallenge.host.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), IHostView {

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    override val hostBinding: ActivityMainBinding
        get() = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)
    }
}