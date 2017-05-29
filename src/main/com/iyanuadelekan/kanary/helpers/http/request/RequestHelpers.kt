package com.iyanuadelekan.kanary.helpers.http.request

import org.eclipse.jetty.server.Request

/**
 * @author Iyanu Adelekan on 29/05/2017.
 */

/**
 * To be called once a request is handled
 */
fun Request.done() {
    isHandled = true
}