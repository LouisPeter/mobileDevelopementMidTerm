package com.example.sec2_midterm_peter.ui.display

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is display Fragment"
    }
    val text: LiveData<String> = _text
}