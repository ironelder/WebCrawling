package com.ironelder.withinno.data.remote.source

import com.ironelder.withinno.data.mapper.CrawlingResultMapper.documentToModel
import com.ironelder.withinno.data.model.CrawlingDataModel
import com.ironelder.withinno.data.remote.service.NetworkApiService
import io.reactivex.Observable

object RemoteDataSourceImpl : RemoteDataSource {
    override fun getRemoteCrawlingData(): Observable<CrawlingDataModel> {
        return NetworkApiService.crawlingApi.map(::documentToModel)
    }
}