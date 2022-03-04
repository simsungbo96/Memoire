package com.sbsj.memosbsj.adapter
import android.view.LayoutInflater
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.data.DataPage




class ViewPagerAdapter(dataList: ArrayList<DataPage>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolderPage>() {
    var item = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderPage((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        holder.title.text = item[position].title

    }
    inner class ViewHolderPage(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)){
            val title: TextView = itemView.findViewById<TextView>(R.id.tv_title)


    }

}