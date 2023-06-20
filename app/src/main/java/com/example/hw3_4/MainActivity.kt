package com.example.hw3_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw3_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fl_mine, ContinentsFragment()).commit()
    }
}