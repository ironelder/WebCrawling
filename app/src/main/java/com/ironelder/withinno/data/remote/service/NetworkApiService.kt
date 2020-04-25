package com.ironelder.withinno.data.remote.service

import com.ironelder.withinno.BuildConfig
import io.reactivex.Observable
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

object NetworkApiService {
    val crawlingApi: Observable<Document> by lazy {
        return@lazy Observable.create<Document> {
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

}


