package com.ironelder.withinno.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironelder.withinno.BuildConfig
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class MainViewModel:ViewModel() {
    private var clickCount:Int =0
    val countLiveData = MutableLiveData<Int>()
    private val disposBag = CompositeDisposable()
    open fun getInitialcount(){
//        Thread {
//            while (clickCount < 10){
//                clickCount+=1
//                countLiveData.postValue(clickCount)
//                Thread.sleep(1000)
//            }
//        }.start()

        disposBag.add(
            getJsoupDocument()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val element: Elements = it.select("div.image-wrap img[src]")
                    element.forEach {el ->
                        val img = el.attr("src")
                        Log.d("ironelderLog", "img = $img")
                    }
                }
        )
    }

    private fun getJsoupDocument() : Observable<Document> {
        return Observable.create {
            try{
                val document = Jsoup.connect(BuildConfig.BASE_URL).get()
                it.onNext(document)
            }catch (e:IOException){
                it.onError(e)
            }finally {
                it.onComplete()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposBag.dispose()
    }
}