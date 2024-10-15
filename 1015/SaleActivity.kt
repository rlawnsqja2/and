package com.example.m05_intent

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.m05_intent.databinding.ActivitySaleBinding

class SaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentR = getIntent() // val rcIntent = intent
        val kindInfo = intentR.getStringExtra("kInfo")
        val detailInfo = intentR.getStringExtra("dInfo")
        val priceInfo = intentR.getBooleanExtra("pInfo", true)
        var priceNego = ""
        if(priceInfo)
            priceNego = "가능"
        else
            priceNego = "불가"

        binding.textViewInfo.text = "상품 : ${kindInfo}\n가격협상 : ${priceNego}\n상세정보 : ${detailInfo}"

        val place = resources.getStringArray(R.array.places)
        var adapter = ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, place)
        binding.spinner.adapter = adapter

        binding.buttonOk.setOnClickListener {
            val intentSS = Intent()
            intentSS.putExtra("placeInfo", binding.spinner.selectedItemPosition)
            setResult(RESULT_OK, intentSS)
            finish()
        } // 등록 버튼

        binding.buttonCancel.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        } // 취소 버튼

        binding.buttonEdit.setOnClickListener{
            setResult(RESULT_FIRST_USER + 100)
            finish()
        } // 수정 버튼
    }
}
