package com.android.memo_file

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.memo_file.databinding.ActivityFilenameBinding

class FilenameActivity : AppCompatActivity() {

    lateinit var binding: ActivityFilenameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_filename)
        binding = ActivityFilenameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sharedpreference의 값 변경하기
        MyApplication.preferences.setString("MyKey", "FilenameActivity에서 값을 변경하다. ")
        val temp = MyApplication.preferences.getString("MyKey", "")
        Log.d("myCheck", "SharedPreference에서 MyKey의 값은 ${temp}")
        //
        // '확인' 버튼
        // 입력된 파일 이름을 가지고 mainActivity로 돌아가야 한다.
        binding.button4.setOnClickListener() {
            // Intent 객체 준비
            val returnIntent = Intent()
            // Intent 객체에 데이터 저장
            returnIntent.putExtra("fileNameToSave", binding.editText2.text.toString())
            // 돌아갈 준비로 결과 설정
            setResult(RESULT_OK, returnIntent)
            // 현재 Activity를 마치고, 현재 Activity를 호출했던 mainActivity로 돌아가기
            finish()

        }
    }
}
