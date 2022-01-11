package com.marcpetit.chalkboardtechtest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcpetit.chalkboardtechtest.R
import com.marcpetit.chalkboardtechtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingView: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingView.root)
    }
}