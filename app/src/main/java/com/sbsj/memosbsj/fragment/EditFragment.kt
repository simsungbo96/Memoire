package com.sbsj.memosbsj.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sbsj.memosbsj.R
import com.sbsj.memosbsj.activity.MainActivity
import com.sbsj.memosbsj.activity.WriteActivity
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.FragmentEditBinding
import com.sbsj.memosbsj.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {
    lateinit var fabWrite : FloatingActionButton
    lateinit var rvEdit : RecyclerView
    lateinit var mainActivity: MainActivity

    lateinit var writeAdapter: WriteAdapter
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentEditBinding

    val datas = mutableListOf<WrittenData>()

    fun newInstance() : EditFragment{
        return EditFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_edit,container,false)
        activity?.let {

            binding.viewModel =viewModel
            binding.lifecycleOwner = this
        }

        return binding.root



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity =context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mWriteAdapter = WriteAdapter(requireContext(),viewModel)
        binding.editRvWritten.apply {
            adapter = mWriteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.allWrittenData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { datas->datas?.let { mWriteAdapter.setWrittenData(it) } })
        fabWrite = view.findViewById(R.id.edit_fab_write)
//        writeAdapter = WriteAdapter(mainActivity)
//        rvEdit = view.findViewById(R.id.edit_rv_written)
//        rvEdit.adapter = writeAdapter
//        val layoutManager = LinearLayoutManager(mainActivity)
//        rvEdit.layoutManager = layoutManager
//        rvEdit.setHasFixedSize(true)
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




    }



}