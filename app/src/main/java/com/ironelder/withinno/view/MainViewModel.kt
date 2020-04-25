package com.ironelder.withinno.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ironelder.withinno.domain.model.CrawlingDomainModel
import com.ironelder.withinno.domain.repository.WebCrawlingRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val disposeBag = CompositeDisposable()
    val webCrawlingData = MutableLiveData<List<CrawlingDomainModel>>()

    fun startWebCrawling() {
        disposeBag.add(
            WebCrawlingRepositoryImpl.getRemoteWebCrawlingData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    webCrawlingData.postValue(it)
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

}