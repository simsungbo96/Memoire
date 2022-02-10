package com.sbsj.memosbsj.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.activity.ReadActivity
import com.sbsj.memosbsj.activity.WriteActivity
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.ItemWrittenlistBinding
import com.sbsj.memosbsj.viewmodel.MainViewModel

class WriteAdapter internal constructor(var context: Context, var viewModel : MainViewModel) :
    RecyclerView.Adapter<WriteAdapter.ViewHolder>() {
    init {
        context = context
    }

    private var writtenDatas = emptyList<WrittenData>() // Cached copy of words



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val writtenlistBinding = ItemWrittenlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(writtenlistBinding)
    }

    class ViewHolder(private val writtenlistBinding: ItemWrittenlistBinding) :RecyclerView.ViewHolder(writtenlistBinding.root){
        val delete  = writtenlistBinding.writtenListLlBtnDelete
        val LI = writtenlistBinding.writtenListLl
        fun bind(writtenDatas: List<WrittenData>){
            writtenlistBinding.writtenListLlTvTitle.text = writtenDatas[adapterPosition].title
            writtenlistBinding.writtenListLlTvDate.text = writtenDatas[adapterPosition].date

        }


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(writtenDatas)

        holder.LI.setOnClickListener {
            val intent = Intent(context, ReadActivity::class.java)
            intent.putExtra("order",writtenDatas[position].order)
            context.startActivity(intent)
        }
        holder.delete.setOnClickListener {

            AlertDialog.Builder(context)
                .setTitle("삭제 알림")
                .setMessage("정말로 삭제하시겠습니까?\n삭제를 할 경우에 기존데이터는 \n복구 할 수 없습니다.")
                .setPositiveButton(
                    "확인"
                ) { dialog, which ->
                    viewModel.delete(writtenDatas[position].order)
                }
                .setNegativeButton(
                    "취소"
                ) { dialog, which -> }
                .create()
                .show()

            Log.e("TAG", "position: $position")
            notifyDataSetChanged()
        }
    }
    override fun getItemCount() = writtenDatas.size



    fun setWrittenData(writtenDatas : List<WrittenData>) {
        this.writtenDatas = writtenDatas
        notifyDataSetChanged()
    }


}

