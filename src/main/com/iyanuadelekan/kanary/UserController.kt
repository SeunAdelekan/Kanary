package com.iyanuadelekan.kanary

import com.iyanuadelekan.kanary.core.KanaryController
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 25/05/2017.
 */
class UserController: KanaryController() {

    fun createUser(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html; charset=utf-8"
        response.status = HttpServletResponse.SC_OK

        val out = response.writer

        out.println("<h1>Hello world!</h1>")

        baseRequest.isHandled = true
    }

}