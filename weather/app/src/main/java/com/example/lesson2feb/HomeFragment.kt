package com.example.lesson2feb

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.Locale

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

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

        view.findViewById<ImageButton>(R.id.imageButton).setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_blankFragment)
        }
        view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_blankFragment2)
        }
        view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_blankFragment3)
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