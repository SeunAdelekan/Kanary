package com.iyanuadelekan.kanary.middleware

import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Iyanu Adelekan on 29/05/2017.
 */

val consoleRequestLogger: (HttpServletRequest?) -> Unit = {
    if(it != null) {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        println("Started ${it.method} '${it.pathInfo}' at ${dateFormat.format(Date())}")
        println("Query parameters: ${it.queryString}")
    }
}