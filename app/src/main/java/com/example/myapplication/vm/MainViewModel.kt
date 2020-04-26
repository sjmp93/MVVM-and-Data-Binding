package com.example.myapplication.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.TextData

class MainViewModel : ViewModel(){
    val data: MutableLiveData<TextData>

    init{
        data = MutableLiveData()
        data.value = TextData()
    }

    fun switch(){
        var _data = data.value ?: TextData()
        _data.commute()
        data.value = _data
    }
}