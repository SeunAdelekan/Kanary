package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.core.KanaryRouter
import org.eclipse.jetty.server.handler.ContextHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author Iyanu Adelekan
 *
 * @constructor creates a new ContextHandler instance
 */
class KanaryApp : ContextHandler() {

    val routerList: ArrayList<KanaryRouter> = ArrayList()
    val middlewareList: ArrayList<(HttpServletRequest?) -> Unit> = ArrayList()

    /**
     * Adds [middleware] to the app
     *
     * Middleware takes the simple form of an anonymous function
     * consisting of an instant of [HttpServletRequest] as its first
     * parameter and an instance of [HttpServletResponse] as its second
     * parameter
     *
     * All middleware added to the app will be executed in a non
     * blocking fashion. A separate thread is executed for each
     * middleware added to the app. This ensures no mounted middleware
     * blocks the main application thread.
     *
     * @param middleware Middleware to be added
     * @return current KanaryApp instance
     */
    fun use(middleware: (request: HttpServletRequest?) -> Unit): KanaryApp {
        middlewareList.add(middleware)
        return this
    }

    /**
     * Mounts a [router] on the application queue
     * @return current KanaryApp instance
     */
    fun mount(router: KanaryRouter): KanaryApp {
        routerList.add(router)
        return this
    }

}