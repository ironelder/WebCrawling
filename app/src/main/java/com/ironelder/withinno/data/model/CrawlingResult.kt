package com.ironelder.withinno.data.model

data class CrawlingDataModel(
    val datas:List<CrawlingResult>
)

data class CrawlingResult(
    val title:String?,
    val imgUrl:String?
)