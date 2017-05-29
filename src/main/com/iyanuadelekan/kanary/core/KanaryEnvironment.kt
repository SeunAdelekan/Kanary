package com.iyanuadelekan.kanary.core

/**
 * @author Iyanu Adelekan on 23/05/2017.
 */
class KanaryEnvironment() {

    var kanaryEnvironment: String = ""

    init {
        if(System.getenv("KANARY_ENV") != null) {
            kanaryEnvironment = System.getenv("KANARY_ENV")
        } else {
            kanaryEnvironment = "development"
        }
    }

    fun getEnvironment(): String {
        return kanaryEnvironment
    }
}