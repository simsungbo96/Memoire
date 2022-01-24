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
import com.sbsj.memosbsj.viewmodel.MainViewModel

class WriteAdapter internal constructor(context: Context ,var viewModel : MainViewModel) :
    RecyclerView.Adapter<WriteAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var writtenDatas = emptyList<WrittenData>() // Cached copy of words


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.
        var txtDate: TextView = itemView.writtenList_ll_tv_title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_written_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val writtenData = writtenDatas[position]
        holder.txtTitle.text = writtenData.title
        holder.txtDate.text = writtenData.date
    }

    internal fun setWrittenData(writtenDatas : List<WrittenData>) {
        this.writtenDatas = writtenDatas
        notifyDataSetChanged()
    }
    override fun getItemCount() = writtenDatas.size
}


//class WriteAdapter(private val context: Context) : RecyclerView.Adapter<WriteAdapter.ViewHolder>() {
//
//    var datas = mutableListOf<WrittenData>()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_written_list,parent,false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = datas.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(datas[position])
//    }
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        private var txtTitle: TextView = itemView.findViewById(R.id.writtenList_ll_tv_title)
//        private var txtDate: TextView = itemView.findViewById(R.id.writtenList_ll_tv_date
//        )
//
//
//        fun bind(item: WrittenData) {
//            txtTitle.text = item.title
//            txtDate.text= item.date
//
//        }
//    }
//}