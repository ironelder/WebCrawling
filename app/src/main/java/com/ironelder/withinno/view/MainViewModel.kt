package com.ironelder.withinno.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironelder.withinno.BuildConfig
import com.ironelder.withinno.data.model.CrawlingResult
import com.ironelder.withinno.domain.repository.WebCrawlingRepositoryImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException

class MainViewModel : ViewModel() {
    private var clickCount: Int = 0
    val countLiveData = MutableLiveData<Int>()
    private val disposeBag = CompositeDisposable()
    open fun getInitialcount() {
        webCrawling()
    }

    private fun getJsoupDocument(): Observable<Document> {
        return Observable.create {
            try {
                val document = Jsoup.connect(BuildConfig.BASE_URL).get()
                it.onNext(document)
            } catch (e: IOException) {
                it.onError(e)
            } finally {
                it.onComplete()
            }
        }
    }

    fun webCrawling() {
        disposeBag.add(
            WebCrawlingRepositoryImpl.getRemoteWebCrawlingData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("ironelderLog", "result yes = ${it}")
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}