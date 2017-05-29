package com.iyanuadelekan.kanary.helpers.http.response

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.eclipse.jetty.server.Request
import java.io.File
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 28/05/2017.
 */


/**
 * Used to send a JSOn response to a client
 * @param responseNode A [JsonNode] containing the specified response
 */
fun HttpServletResponse.sendJson(responseNode: JsonNode) {
    val mapper: ObjectMapper = ObjectMapper()
    writer.print(mapper.writeValueAsString(responseNode))
}

/**
 * Used to end the HTTP response process
 */
fun HttpServletResponse.end() {
    writer.close()
}

/**
 * Used to set the response status code and send it to
 * the client as a response
 * @param status HTTP status code of response
 */
fun HttpServletResponse.sendStatus(status: Int) {
    writer.print(status)
}

/**
 * Used to send a file resource to the client
 * @param file File to be sent back to the client
 */
fun HttpServletResponse.sendFile(file: File) {
    writer.print(file)
}

/**
 * Used to redirect a client HTTP request to a URL
 * @param url URL to redirect request to
 */
fun HttpServletResponse.redirect(url: String) {
    sendRedirect(url)
}

/**
 * To be called once a request is handled
 */
fun Request.done() {
    isHandled = true
}