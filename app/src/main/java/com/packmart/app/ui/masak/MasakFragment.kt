package com.packmart.app.ui.masak

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.packmart.app.databinding.FragmentMasakBinding
import com.packmart.app.ui.home.MainActivity

class MasakFragment : Fragment() {

    private val parent: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentMasakBinding
    private val viewModel: MasakViewModel by lazy { MasakViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMasakBinding.inflate(inflater, container, false).apply {
            viewModel = this@MasakFragment.viewModel
            lifecycleOwner = this@MasakFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner){

        }
        viewModel.actionState.observe(viewLifecycleOwner){
            if(it.isConsumed){
                Log.i("ActionState", "isConsumed")
            }else if (!it.isSuccess){
                Toast.makeText(parent, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        binding.recyclerView.adapter = MasakAdapter(parent)
        viewModel.listMasak()
    }

}