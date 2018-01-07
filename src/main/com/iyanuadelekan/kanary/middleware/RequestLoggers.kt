package com.iyanuadelekan.kanary.middleware

import org.eclipse.jetty.server.Request
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 29/05/2017.
 */

/**
 * Logs succinct request details to console
 */
val simpleConsoleRequestLogger: (Request?, HttpServletRequest?, HttpServletResponse?) -> Unit = {
    _, req, _ -> run {

    if(req != null && req.method != null && req.pathInfo != null) {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        println("Started ${req.method} '${req.pathInfo}' at ${dateFormat.format(Date())}")
        if(req.queryString != null) {
            println("Query string: ${req.queryString}")
        }

    }
} }