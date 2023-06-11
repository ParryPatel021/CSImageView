package com.parrypatel.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.parrypatel.csimageview.CSImageView
import com.parrypatel.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.csImage.setOnCheckedChangeListener(object : CSImageView.OnCheckedChangeListener {
            override fun onCheckedChanged(csImageView: CSImageView?, isChecked: Boolean) {
                Log.e("Checked", "" + isChecked)
            }
        })

    }
}