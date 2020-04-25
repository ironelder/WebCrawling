package com.ironelder.withinno.domain.repository

import com.ironelder.withinno.domain.model.CrawlingDomainModel
import io.reactivex.Observable

interface WebCrawlingRepository {
    fun getRemoteWebCrawlingData():Observable<List<CrawlingDomainModel>>
}