package com.sbsj.memosbsj.adapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.data.DataPage
import com.sbsj.memosbsj.adapter.ViewPagerAdapter.ViewHolderPage





class ViewPagerAdapter(dataList: ArrayList<DataPage>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolderPage>() {
    var item = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderPage((parent))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        holder.title.text = item[position].title

    }
    inner class ViewHolderPage(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)){
            val title = itemView.findViewById<TextView>(R.id.tv_title)


    }

}