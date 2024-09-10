package com.example.m02_layoutview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.m02_layoutview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTitle.setText(getString(R.string.title))
        //setContentView(R.layout.activity_main)

        //val textView = findViewById<TextView>(R.id.textTitle)
        //textView.text = getString(R.string.title)
        // textView.setText("동양 나눔 장터")
    }
}
