package com.example.lesson17.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson17.randomDescriptions

class SlideViewModel : ViewModel() {
    private val _currentId = MutableLiveData<Int>()
    val currentId: LiveData<Int> = _currentId

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description


    fun setId(id: Int){
        _currentId.value = id
        _description.value = """
            ${randomDescriptions.get(id)}
        """.trimIndent()
    }
}