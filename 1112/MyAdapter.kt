package com.example.m09_sqlite

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m09_sqlite.databinding.ItemRecyclerviewBinding
import kotlin.concurrent.thread

class MyViewHolder(val adapter: MyAdapter, val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
    var id:Long = 0

    init {
        itemView.setOnLongClickListener {
            adapter.delItem(adapterPosition)
            true
        }

        binding.imageViewInfo.setOnClickListener {
           // 상세정보 보기
            val item = adapter.datas.get(adapterPosition)
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("상세정보")
            builder.setMessage(item.detail)
            builder.setPositiveButton("Ok", null)
            builder.show()
        }
    }

    fun viewBind(pos:Int) {

        id = adapter.datas[pos].id.toLong()!!
        with(binding) {
            // 이미지 바인딩

            itemTitle.text = adapter.datas[pos].title
            itemName.text = adapter.datas[pos].name
        }
    }
}

class MyAdapter(val dao:WorkLogDao): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val datas = mutableListOf<WorkLog>()

    override fun getItemCount(): Int{
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(this, ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).viewBind(position)
    }

    fun addItem(item: WorkLog) {
        // 항목 추가
        datas.add(item)

        thread {
            dao.insertWorklog(item)
        }
        notifyDataSetChanged()
    }

    fun delItem(pos:Int) {
       // 항목 삭제
        val item = datas.get(pos)

        thread {
            dao.deleteWorklog(item)
        }
        datas.removeAt(pos)
        notifyDataSetChanged()
    }
}
