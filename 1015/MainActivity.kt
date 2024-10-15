package com.example.m05_intent



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.m05_intent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        class ImgOnClickListener : View.OnClickListener {
            override fun onClick(v: View?) {
                val toast = Toast.makeText(baseContext, R.string.no_image, Toast.LENGTH_LONG)
                toast.show()
            }
        }

        val iCL = ImgOnClickListener()
        binding.imageView.setOnClickListener(iCL)

        class TextOnClickListener : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("ekpark", "The title is clicked.")
            }
        }

        val tCL = TextOnClickListener()
        binding.textTitle.setOnClickListener(tCL)

        binding.radioGroupKind.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                var msg = when(checkedId) {
                    R.id.radioButtonC -> "의류"
                    R.id.radioButtonB -> "도서"
                    R.id.radioButtonE -> "가전제품"
                    R.id.radioButtonT -> "기타"
                    else -> "선택 오류"
                }
                Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
            }
        })
        // input : Intent, output : ActivityResult
        val contract = ActivityResultContracts.StartActivityForResult()
        val callback = object : ActivityResultCallback<ActivityResult>{
            override fun onActivityResult(result: ActivityResult) {
                // SaleActivity에서 넘겨준 데이터를 처리
                if(result.resultCode == RESULT_OK){
                    val intentRS = result.data
                    val placePosition = intentRS?.getIntExtra("placeInfo", 0)
                    val placeArray = arrayOf("학교 정문앞", "3호관 정문", "학생 식당 앞", "도서관 앞", "Dream cafe", "오류")
                    Toast.makeText(baseContext, placeArray[placePosition!!], Toast.LENGTH_LONG).show()
                    binding.radioButtonC.isChecked = true
                    binding.checkBoxPrice.isChecked = false
                    binding.editTextText.text.clear() // binding.editTextText.setText("")
                } else if(result.resultCode == RESULT_CANCELED){
                    Toast.makeText(baseContext, "취소되었습니다.", Toast.LENGTH_LONG).show()
                    binding.radioButtonC.isChecked = true
                    binding.checkBoxPrice.isChecked = false
                    binding.editTextText.text.clear()
                } else if(result.resultCode == RESULT_FIRST_USER + 100){
                    Toast.makeText(baseContext, "수정해주세요.", Toast.LENGTH_LONG).show()
                }
            }
        }
        val launcher = registerForActivityResult(contract, callback)

        binding.buttonOK.setOnClickListener {
            val detailInfo = binding.editTextText.text.toString().trim()

            if(detailInfo.isEmpty()) {
                Toast.makeText(baseContext, R.string.detail_hint, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var kindInfo = when (binding.radioGroupKind.checkedRadioButtonId) {
                R.id.radioButtonC -> "의류"
                R.id.radioButtonB -> "도서"
                R.id.radioButtonE -> "가전"
                R.id.radioButtonT -> "기타"
                else -> "오류"
            }

            val intentS = Intent(baseContext, SaleActivity::class.java)
            intentS.putExtra("kInfo", kindInfo)
            intentS.putExtra("dInfo", detailInfo)
            intentS.putExtra("pInfo", binding.checkBoxPrice.isChecked)
            launcher.launch(intentS)
            //startActivity(intentS)
        }

        binding.textViewLink.setOnClickListener{
            val intentA = Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-2941-1345"))
            startActivity(intentA)
        }
    } // oncreate
}
