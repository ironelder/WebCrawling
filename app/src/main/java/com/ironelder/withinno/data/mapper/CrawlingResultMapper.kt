package com.ironelder.withinno.data.mapper

import com.ironelder.withinno.data.model.CrawlingDataModel
import com.ironelder.withinno.data.model.CrawlingResult
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

object CrawlingResultMapper {
    fun documentToModel(document: Document): CrawlingDataModel {
        val result: List<Element> = document.select("div.item-wrap")

        return CrawlingDataModel(result.reversed().filter { el ->
            !el.select("div.image-wrap img[src]").attr("src").isNullOrEmpty()
        }.map { el ->
            CrawlingResult(
                imgUrl = el.select("div.image-wrap img[src]").attr("src"),
                title = el.select("div.caption strong").text()
            )
        })
    }
}