package com.example.lesson2feb

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

//    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            MyDialog().show(childFragmentManager, "1")
        }
        view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
            MyDialog().show(childFragmentManager, "2")
        }
        view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener {
            MyDialog().show(childFragmentManager, "3")
        }


//        val editT = view.findViewById<EditText>(R.id.editTextHome)
//        view.findViewById<Button>(R.id.button2)
//            .setOnClickListener {
//                val action = HomeFragmentDirections.actionHomeFragmentToBlankFragment(
//                    editTextH = editT.text.toString()
//                )
//                findNavController().navigate(action)
//                findNavController().navigate(
//                    R.id.action_homeFragment_to_blankFragment,
//                    bundleOf("editTextH" to editT.text.toString())
//                )
//            }

    }

}