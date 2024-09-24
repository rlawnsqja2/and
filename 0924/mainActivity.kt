package com.example.m03_viewevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.m03_viewevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 이름이 있는 클래스 방법
        // 첫 번째 단계 : 클래스 정의 (인터페이스 상속받아)
        class ImgClikcListener : View.OnClickListener{
            override fun onClick(p0: View?) {
                val toast = Toast.makeText(baseContext, "이미지 클릭됨", Toast.LENGTH_LONG)
                toast.show()
            }
        }
        // 두번째 단계 : 객체 생성
        val iClickListener = ImgClikcListener()

        // 세번째 단계 : 이벤트 소스에게 이벤트 처리 객체 알려주기
        binding.imageView.setOnClickListener(iClickListener)

        // 익명 클래스 방법 1 : 이름 있는 클래스의 첫번째 단계와 두번째 단계를 동시에 실행
        val bCL = object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(baseContext, /*getString(R.string.no_image)*/R.string.no_image, Toast.LENGTH_SHORT).show()
                Log.i("kimjunbeom", "버튼 클릭됨 : ${(p0 as TextView).text}")
            }

        }

        // 세번째 단계(익명클래스 2단계)
        binding.button.setOnClickListener(bCL)

    }  // oncreate
}   // MainActivity
