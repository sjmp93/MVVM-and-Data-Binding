package com.example.myapplication.data

class TextData(var text: String = "Disabled", var binary: Boolean = false) {
    fun commute(){
        binary = !binary
        text = if(binary)  "Enabled" else  "Disabled"
    }
}