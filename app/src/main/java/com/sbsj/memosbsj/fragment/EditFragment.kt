package com.sbsj.memosbsj.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.activity.MainActivity
import com.sbsj.memosbsj.activity.WriteActivity
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.WrittenData


class EditFragment : Fragment() {
    lateinit var fabWrite : FloatingActionButton
    lateinit var rvEdit : RecyclerView
    lateinit var mainActivity: MainActivity

    lateinit var writeAdapter: WriteAdapter

    val datas = mutableListOf<WrittenData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootview = inflater.inflate(R.layout.fragment_edit, container, false)
        writeAdapter = WriteAdapter(mainActivity)
        rvEdit = rootview.findViewById(R.id.edit_rv_written)
        rvEdit.adapter = writeAdapter
        val layoutManager = LinearLayoutManager(mainActivity)
        rvEdit.layoutManager = layoutManager
        rvEdit.setHasFixedSize(true)


        return rootview



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity =context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabWrite = view.findViewById(R.id.edit_fab_write)



        fabWrite.setOnClickListener {
            moveWriteActivity()
        }



    }

    fun moveWriteActivity(){
        val intent = Intent(context, WriteActivity::class.java)
        startActivityForResult(intent, 12)
    }

     override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12) {

            var textTitle = data?.getStringExtra("title")
            datas.apply {

                add(WrittenData(title = textTitle))
                writeAdapter.datas = datas

            }

        }
    }

    fun refreshAdapter()
    {
        writeAdapter.notifyDataSetChanged()
    }



}