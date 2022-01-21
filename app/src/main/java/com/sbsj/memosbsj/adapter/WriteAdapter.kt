package com.sbsj.memosbsj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.fragment.EditFragment

class WriteAdapter(private val context: Context) : RecyclerView.Adapter<WriteAdapter.ViewHolder>() {

    var datas = mutableListOf<WrittenData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_written_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var txtTitle: TextView = itemView.findViewById(R.id.tv_title)


        fun bind(item: WrittenData) {
            txtTitle.text = item.title

        }
    }
}