package com.example.lesson2feb

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class MyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("На какой день хотите посмотреть погоду?:")
            .setItems(R.array.fragments_array) { _, item ->
                dismissAllowingStateLoss()

//                val fragmentManager = requireActivity().supportFragmentManager
//                val transaction = fragmentManager.beginTransaction()
//                transaction.setReorderingAllowed(true)
                val destination = when (item) {
                    0 -> R.id.action_homeFragment_to_blankFragment
                    1 -> R.id.action_homeFragment_to_blankFragment2
                    2 -> R.id.action_homeFragment_to_blankFragment3
                    else -> TODO()
                }
                findNavController().navigate(destination)

//                transaction.addToBackStack(null)
//                transaction.commit()
            }
        return builder.create()
    }
}
