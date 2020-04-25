package com.ironelder.withinno.domain.repository

import com.ironelder.withinno.data.remote.source.RemoteDataSourceImpl
import com.ironelder.withinno.domain.model.CrawlingDomainModel
import io.reactivex.Observable

object WebCrawlingRepositoryImpl : WebCrawlingRepository {
    override fun getRemoteWebCrawlingData(): Observable<List<CrawlingDomainModel>> {
        return RemoteDataSourceImpl.getRemoteCrawlingData().map {
            it.datas.map { cr ->
                CrawlingDomainModel(title = cr.title, imgUrl = cr.imgUrl)
            }
        }
    }

}