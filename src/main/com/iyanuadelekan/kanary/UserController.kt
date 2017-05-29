package com.iyanuadelekan.kanary

import com.fasterxml.jackson.databind.ObjectMapper
import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.request.getBody
import com.iyanuadelekan.kanary.helpers.http.request.getBodyAsJson
import com.iyanuadelekan.kanary.helpers.http.response.*
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 25/05/2017.
 */
class UserController: KanaryController() {

    fun createUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response withStatus 200 sendJson request.getBody()
        baseRequest.done()
    }

    fun retrieveUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html; charset=utf-8"
        response.status = HttpServletResponse.SC_OK

        val out = response.writer

        out.println("<h1>User retrieved!</h1>")

        baseRequest.isHandled = true
    }

}