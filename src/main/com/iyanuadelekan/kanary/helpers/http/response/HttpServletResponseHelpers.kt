package com.iyanuadelekan.kanary.helpers.http.response

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 28/05/2017.
 */


/**
 * Used to send an ordinary text message to the client
 * as a response
 * @param message HTTP status code of response
 */
infix fun HttpServletResponse.send(message: String) {
    contentType = "text/plain"
    writer.print(message)
}

/**
 * Used to send a JSON response to a client
 * @param responseNode A [JsonNode] containing the specified response
 */
infix fun HttpServletResponse.sendJson(responseNode: JsonNode?) {
    contentType = "application/json"

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
infix fun HttpServletResponse.sendStatus(status: Int) {
    contentType = "text/plain"

    this.status = status
    writer.print(status)
}

/**
 * Used to send a file resource to the client
 * @param file File to be sent back to the client
 */
infix fun HttpServletResponse.sendFile(file: File) {
    writer.print(file)
}

/**
 * Used to redirect a client HTTP request to a URL
 * @param url URL to redirect request to
 */
infix fun HttpServletResponse.redirect(url: String) {
    sendRedirect(url)
}

/**
 * Used to send HTML to a client
 * @param html URL to redirect request to
 */
infix fun HttpServletResponse.sendHtml(html: String) {
    contentType = "text/html"
    writer.print(html)
}