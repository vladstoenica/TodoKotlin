package com.stoe.todokotlin

//data class primary purpose is to hold data - no logic
data class Todo (
    val title: String,
    var isChecked: Boolean = false
)
