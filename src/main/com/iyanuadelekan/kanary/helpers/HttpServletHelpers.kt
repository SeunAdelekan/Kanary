package com.iyanuadelekan.kanary.helpers

import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

/**
 * @author Iyanu Adelekan on 27/05/2017.
 */

/**
 * [HttpServletRequest] helper function for the easy retrieval
 * of HTTP request body content.
 *
 * This function is added to the receiver [HttpServletRequest]
 */
fun HttpServletRequest.getBody() {
    val requestJSONBody = reader.lines().collect(Collectors.joining(System.lineSeparator()))
}

/**
 * [HttpServletRequest] helper function for the easy retrieval
 * of HTTP request body content as a JSON string.
 * @return string containing JSON body
 */
fun HttpServletRequest.getBodyAsJson(): String {
    return reader.lines().collect(Collectors.joining(System.lineSeparator()))
}