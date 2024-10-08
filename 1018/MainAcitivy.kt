package com.example.m04_eventintent

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m04_eventintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroupKind.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                var msg = ""
                when(p1){
                    R.id.radioButtonC -> msg = "의류"
                    R.id.radioButtonB -> msg = "도서"
                    R.id.radioButtonE -> msg = "가전"
                    R.id.radioButtonT -> msg = "기타"

                }
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonOK.setOnClickListener {
            val kindInfo = when(binding.radioGroupKind.checkedRadioButtonId) {
                R.id.radioButtonC ->  "의류"
                R.id.radioButtonB ->  "도서"
                R.id.radioButtonE ->  "가전"
                R.id.radioButtonT ->  "기타"
                else -> "오류"
            }
            val saleInfo = binding.checkBoxPrice.isChecked.toString()

            val detailInfo = binding.editTextText.text.toString().trim()

            if(detailInfo.isEmpty()){ // == true
                Toast.makeText(baseContext, R.string.detail_hint, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intentS = Intent(baseContext, SaleActivity::class.java)
            intentS.putExtra("kInfo", kindInfo)
            intentS.putExtra("dInfo", detailInfo)
            if(saleInfo == "true"){
                intentS.putExtra("sInfo", "가능")
            }
            startActivity(intentS)
        }
    }        // oncreate
}
