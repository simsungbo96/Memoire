package com.sbsj.memosbsj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ItemWrittenlistBinding
import com.sbsj.memosbsj.viewmodel.MainViewModel

class WriteAdapter internal constructor(context: Context ,var viewModel : MainViewModel) :
    RecyclerView.Adapter<WriteAdapter.ViewHolder>() {


    private var writtenDatas = emptyList<WrittenData>() // Cached copy of words



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val writtenlistBinding = ItemWrittenlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(writtenlistBinding)
    }

    class ViewHolder(private val writtenlistBinding: ItemWrittenlistBinding) :RecyclerView.ViewHolder(writtenlistBinding.root){
        fun bind(writtenDatas: List<WrittenData>){
            writtenlistBinding.writtenListLlTvTitle.text = writtenDatas[adapterPosition].title
            writtenlistBinding.writtenListLlTvDate.text = writtenDatas[adapterPosition].date

        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(writtenDatas)
    }
    override fun getItemCount() = writtenDatas.size

    fun setWrittenData(writtenDatas : List<WrittenData>) {
        this.writtenDatas = writtenDatas
        notifyDataSetChanged()
    }
}

//
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