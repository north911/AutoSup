package com.example.autosup.utils

import com.example.autosup.model.CarBrand
import com.example.autosup.model.CarPart
import com.example.autosup.model.Engine
import com.example.autosup.model.SubBrand
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
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

fun getSubCarsElements(body: String?): Elements? {
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

fun getEngineElements(body: String?): Elements? {
    val document = convertToDoc(body) ?: convertToDoc("")
    return document?.getElementsByClass("brd")
}

fun convertHtmlElementsToArrayEngines(elements: Elements?): ArrayList<Engine> {
    val engines = ArrayList<Engine>()
    elements?.let {
        for (element in it) {
            engines.add(
                Engine(
                    element.child(0).child(0).text(),
                    element.child(1).text(),
                    element.child(0).child(0).attr("href"),
                    element.child(2).text(),
                    element.child(3).text(),
                    element.child(0).child(0).attr("href")
                )
            )
        }
    }
    return engines
}

fun getCarPartElements(body: String?): Elements? {
    val document = convertToDoc(body) ?: convertToDoc("")
    return document?.getElementsByClass("blacklink12")
}

fun convertHtmlElementsToArrayCarParts(elements: Elements?): ArrayList<CarPart> {
    val carParts = ArrayList<CarPart>()
    elements?.let {
        for (element in it) {
            carParts.add(
                CarPart(
                    element.attr("href"),
                    element.text()
                )
            )
        }
    }
    return carParts
}