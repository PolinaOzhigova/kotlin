package com.example.lesson17.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson17.names
import com.example.lesson17.randomDescriptions

class SlideshowViewModel : ViewModel() {
    private val _tabs = MutableLiveData<List<Tab>>()
    val tabs = _tabs

    init {
//        _tabs.value = List(100){
//            Tab("Title: $it", it)
//        }
        _tabs.value = names.mapIndexed{idx, title -> Tab(title, idx)}
    }
}

data class Tab(
    val title: String,
    val contentId: Int,
)