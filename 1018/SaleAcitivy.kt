package com.example.m04_eventintent

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.m04_eventintent.databinding.ActivitySaleBinding

class SaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sale)
        val sBinding = ActivitySaleBinding.inflate(layoutInflater)
        setContentView(sBinding.root)

        val intentR = getIntent() // intentR = intent // intent
        val kInfo = intentR.getStringExtra("kInfo")
        val dInfo = intentR.getStringExtra("dInfo")
        val sInfo = intentR.getStringExtra("sInfo")

        sBinding.textViewInfo.text = "상품 : ${kInfo}\n가격협상 : ${sInfo}\n상세정보 : ${dInfo}"

        val place = resources.getStringArray(R.array.places)
        val sAdapter = ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, place)
        sBinding.spinner.adapter = sAdapter

        /*sBinding.buttonCancel.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }
        })*/
        /*sBinding.buttonCancel.setOnClickListener { p0: View? ->
                Log.d("김준범", "버튼 클릭 : ${p0?.id}")
                finish()
            }*/
        sBinding.buttonCancel.setOnClickListener {
            Log.d("김준범", "버튼 클릭 : ${it.id}")
            finish()
        }
    } // oncreate
}
