package com.sbsj.memosbsj.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.sbsj.memosbsj.activity.ReadActivity
import com.sbsj.memosbsj.activity.WriteActivity
import com.sbsj.memosbsj.adapter.WriteAdapter
import com.sbsj.memosbsj.data.WrittenData
import com.sbsj.memosbsj.databinding.FragmentEditBinding
import com.sbsj.memosbsj.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import java.text.SimpleDateFormat
import java.util.*
import android.widget.AdapterView.OnItemClickListener




@FragmentScoped
class EditFragment : Fragment() {

    lateinit var mWriteAdapter :WriteAdapter
    private var _binding: FragmentEditBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private var mListener: OnItemClickListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        activity?.let {

            binding.viewModel = viewModel
            binding.lifecycleOwner = this
        }
        mWriteAdapter = WriteAdapter(requireContext(),viewModel)

        binding.editRvWritten.apply {
            adapter =mWriteAdapter

            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.editFabWrite.setOnClickListener{
            moveWriteActivity()
        }
        Log.e("read", "viewModel.Repository : "+ viewModel.repository )
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allWrittenData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { writtenDatas ->
            writtenDatas?.let { mWriteAdapter.setWrittenData(it)}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun moveWriteActivity(){
        val intent = Intent(context, WriteActivity::class.java)
        startActivity(intent)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }
}