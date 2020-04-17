package com.ironelder.withinno.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private var clickCount:Int =0
    val countLiveData = MutableLiveData<Int>()
    open fun getInitialcount(){
        Thread {
            while (true){
                clickCount+=1
                countLiveData.postValue(clickCount)
                Thread.sleep(1000)
            }
        }.start()
    }

}