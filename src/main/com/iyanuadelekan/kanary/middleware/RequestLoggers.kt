package com.iyanuadelekan.kanary.middleware

import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author Iyanu Adelekan on 29/05/2017.
 */

/**
 * Logs succinct request details to console
 */
val simpleConsoleRequestLogger: (HttpServletRequest?) -> Unit = {
    if(it != null && it.method != null && it.pathInfo != null) {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        println("Started ${it.method} '${it.pathInfo}' at ${dateFormat.format(Date())}")
        if(it.queryString != null) {
            println("Query string: ${it.queryString}")
        }

    }
}