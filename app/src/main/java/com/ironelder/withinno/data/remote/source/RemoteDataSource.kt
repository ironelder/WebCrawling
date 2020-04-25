package com.ironelder.withinno.data.remote.source

import com.ironelder.withinno.data.model.CrawlingDataModel
import io.reactivex.Observable

interface RemoteDataSource {

    fun getRemoteCrawlingData(): Observable<CrawlingDataModel>

}