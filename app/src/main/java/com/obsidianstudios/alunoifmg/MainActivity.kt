package com.obsidianstudios.alunoifmg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Weather Icons Reference
        // http://erikflowers.github.io/weather-icons/

        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)

        Log.d("TAG", dayOfTheWeek)

    }
}
