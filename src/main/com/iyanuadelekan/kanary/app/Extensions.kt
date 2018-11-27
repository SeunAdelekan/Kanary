package com.iyanuadelekan.kanary.app

import com.fasterxml.jackson.databind.ObjectMapper
import com.iyanuadelekan.kanary.exceptions.InvalidResponseEntityException
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 24/11/2018.
 */
fun HttpServletResponse.withStatus(statusCode: Int): HttpServletResponse {
    status = statusCode
    return this
}

@Throws(InvalidResponseEntityException::class)
fun <T : Any> HttpServletResponse.send(data: T) {
    val isValidResponseEntity =
            data.javaClass.getAnnotation(ResponseEntity::class.java) != null

    if (isValidResponseEntity) {
        val mapper = ObjectMapper()
        val json = mapper.writeValueAsString(data)

        contentType = "application/json"
        writer.print(json)

        println(json) // TODO remove this line.
        return
    }
    throw InvalidResponseEntityException()
}