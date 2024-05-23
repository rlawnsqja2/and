package kr.ac.dmu.ai052301

import android.app.Application

class MyApplication constructor (): Application() {
    var gname: String = "홍길동"
    var intData: Int = 300

    init {
        INSTANCE = this
    }

    // java에서 static
    companion object { // data object
        lateinit var INSTANCE : MyApplication
    }
}
