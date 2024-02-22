package com.example.lesson17.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.lesson17.R

class SlideFragment : Fragment() {

    companion object {
        private const val ARG_ID = "id"
        fun newInstance(id: Int) = SlideFragment().apply {
            arguments = bundleOf(
                ARG_ID to id
            )
        }
    }

    private val viewModel: SlideViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setId(arguments?.getInt(ARG_ID) ?: 0)

        val slideDesc = view.findViewById<TextView>(R.id.sliderDesc)
        viewModel.description.observe(viewLifecycleOwner){
            slideDesc.setText(it)
        }
    }
}