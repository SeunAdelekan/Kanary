package com.iyanuadelekan.kanary.helpers.http.response

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.FileInputStream
import javax.servlet.ServletOutputStream
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
    writer.print(ObjectMapper().writeValueAsString(responseNode))
}

infix fun HttpServletResponse.sendJson(obj: Any) {
    val mapper = ObjectMapper()
    val response = mapper.writeValueAsString(obj)
    contentType = "application/json"

    writer.print(response)
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
fun HttpServletResponse.sendFile(file: File, contentType: String="", contentLength: Int=0) {
    if(contentType != "") {
        this.contentType = contentType
    }
    if (contentLength != 0) {
        this.setContentLength(contentLength)
    }

    val fileBytes = ByteArray(file.length().toInt())
    val fis = FileInputStream(file)
    fis.read(fileBytes)

    val servletOutputStream: ServletOutputStream = outputStream
    servletOutputStream.write(fileBytes)
    servletOutputStream.flush()
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

/**
 * Sets the HTTP status code of a [HttpServletResponse] object
 * @param status Required status code
 * @return current instance of [HttpServletResponse]
 */
infix fun HttpServletResponse.withStatus(status: Int): HttpServletResponse {
    this.status = status
    return this
}