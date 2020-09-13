package com.example.autosup.utils

import com.example.autosup.Model.CarBrand
import com.example.autosup.Model.SubBrand
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

private fun convertToDoc(str: String?): Document? {
    str?.let {
        return Jsoup.parse(str)
    }
    return null
}

fun getCarsElements(body: String?): Elements? {
    val document = convertToDoc(body) ?: convertToDoc("")
    return document?.getElementById("cars")?.getElementsByAttribute("src")
}

fun convertHtmlElementsToArrayCars(elements: Elements?): ArrayList<CarBrand> {
    val cars = ArrayList<CarBrand>()
    elements?.let {
        for (element in it) {
            cars.add(
                CarBrand(
                    element.attr("src").substringAfterLast("/").substringBefore("."),
                    element.attr("src"),
                    element.parent().attr("href")
                )
            )
        }
    }
    return cars
}

fun getSubCarsElements(body: String?): Elements?{
    val document = convertToDoc(body) ?: convertToDoc("")
    return document?.getElementsByClass("brd")
}

fun convertHtmlElementsToArraySubCars(elements: Elements?): ArrayList<SubBrand> {
    val cars = ArrayList<SubBrand>()
    elements?.let {
        for (element in it) {
            cars.add(
                SubBrand(
                    element.child(0).child(0).text(),
                    element.child(1).text(),
                    element.child(0).child(0).attr("href")
                )
            )
        }
    }
    return cars
}