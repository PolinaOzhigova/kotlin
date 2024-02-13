package com.example.lesson2feb

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("На какой день хотите посмотреть погоду?:")
            .setItems(R.array.fragments_array) { _, item ->
                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                when (item) {
                    0 -> transaction.replace(android.R.id.content, BlankFragment())
                    1 -> transaction.replace(android.R.id.content, BlankFragment2())
                    2 -> transaction.replace(android.R.id.content, BlankFragment3())
                }
                transaction.addToBackStack(null)
                transaction.commit()
            }
        return builder.create()
    }
}