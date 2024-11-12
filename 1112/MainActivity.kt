package com.example.m09_sqlite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.m09_sqlite.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var launcher : ActivityResultLauncher<Intent>? = null
    lateinit var adapter:MyAdapter
    var dbId = 0 // max id
    //
    lateinit var roomDb:WorkLogDatabase
    lateinit var workLogDao:WorkLogDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //room 객체 초기화
        roomDb = Room.databaseBuilder(applicationContext, WorkLogDatabase::class.java, "worklog_t").build()
        workLogDao = roomDb.worklogsDao()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(workLogDao)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        readDB()

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object: ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if(result.resultCode == RESULT_OK) {
                        val rIntent = result.data
                        val title = rIntent?.getStringExtra("title")
                        val detail = rIntent?.getStringExtra("detail")

                        //response 처리
                        val item = WorkLog()
                        item.id = ++dbId
                        item.title = title!!
                        item.detail = detail!!
                        item.name = "jbkim"

                        adapter.addItem(item)
                    } else {
                        Toast.makeText(baseContext, "취소되었습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            })
    } // oncreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_add -> {
                val sIntent = Intent(baseContext, LogActivity::class.java)
                //sIntent.putExtra("selectedItem", -1)

                launcher?.launch(sIntent)
            }
            R.id.menu_close-> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun readDB() {
        //
        thread {
            val datas = workLogDao.getAllWorklogs()

            for(item in datas) {
                adapter.datas.add(item)

                if(dbId < item.id)
                    dbId = item.id
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        if(roomDb != null)
            roomDb.close()
        super.onDestroy()
    }
}
