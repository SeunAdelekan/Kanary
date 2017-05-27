package com.iyanuadelekan.kanary.helpers

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

/**
 * @author Iyanu Adelekan on 27/05/2017.
 */

val mapper: ObjectMapper = ObjectMapper()
var root: JsonNode? = null

/**
 * [HttpServletRequest] helper function for the easy retrieval
 * of HTTP request body content.
 *
 * This function is added to the receiver [HttpServletRequest]
 */
fun HttpServletRequest.getBody(): JsonNode? {
    val requestJSONBody = reader.lines().collect(Collectors.joining(System.lineSeparator()))
    root = mapper.readTree(requestJSONBody)
    return root
}

/**
 * [HttpServletRequest] helper function for the easy retrieval
 * of HTTP request body content as a JSON string.
 * @return string containing JSON body
 */
fun HttpServletRequest.getBodyAsJson(): String {
    return reader.lines().collect(Collectors.joining(System.lineSeparator()))
}