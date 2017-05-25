package com.iyanuadelekan.kanary.handlers

import com.iyanuadelekan.kanary.UserController
import com.iyanuadelekan.kanary.app.KanaryApp
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 25/05/2017.
 */
class AppHandler(val app: KanaryApp): AbstractHandler() {
    override fun handle(target: String?, baseRequest: Request?, request: HttpServletRequest?, response: HttpServletResponse?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        println(target)
        UserController::createUser.call(UserController(), baseRequest, request, response)
        //UserController::createUser.call()
    }
}