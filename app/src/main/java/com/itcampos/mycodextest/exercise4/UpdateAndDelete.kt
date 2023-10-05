package com.itcampos.mycodextest.exercise4

interface UpdateAndDelete {
    fun modifyItem(itemUID: String, isDone: Boolean)
    fun onItemDelete(itemUID: String, )
}