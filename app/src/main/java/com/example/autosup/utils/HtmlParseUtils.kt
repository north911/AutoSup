package com.example.autosup.utils

import com.example.autosup.Model.CarBrand
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

private fun convertToDoc(str: String?): Document? {
    str?.let {
        return Jsoup.parse(str)
    }
    return null
}

fun getElements(body: String?): Elements? {
    val document = convertToDoc(body) ?: convertToDoc("")
    return document?.getElementById("cars")?.select("a")
}

fun convertHtmlElementsToArray(elements: Elements?): ArrayList<CarBrand> {
    val cars = ArrayList<CarBrand>()
    elements?.let {
        for (element in it) {
            cars.add(
                CarBrand(
                    element.attr("href").substringAfterLast("/"),
                    element.getElementsByAttribute("src").attr("src")
                )
            )
        }
    }
    return cars
}