package com.example.m09_sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m09_sqlite.databinding.ActivityLogBinding

class LogActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOK.setOnClickListener {
            val detail_info = binding.editTextText.text.toString().trim()
            val title = binding.editTextTitle.text.toString().trim()

            if(detail_info.isEmpty() || title.isEmpty()) {
                Toast.makeText(baseContext, "현장 진행 상황을 입력해 주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val sIntent = Intent()//this, MainActivity::class.java)
            sIntent.putExtra("title", title)
            sIntent.putExtra("detail", detail_info)

            setResult(RESULT_OK, sIntent)
            finish()
        }

        binding.buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    } // oncreate
}
