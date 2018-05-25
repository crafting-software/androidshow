package com.craftingsoftware.androidshow.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val count: MutableLiveData<Int> = MutableLiveData()

    fun incrementCount(amount: Int = 1) {
        count.value = amount + (count.value ?: 0)
    }

    fun getCount(): LiveData<Int> = count

    fun sum(a: Int, b: Int): Int = a + b
}
