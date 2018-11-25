package com.iyanuadelekan.kanary.app.framework.lifecycle

import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Root context class.
 */
abstract class Context {

    var port: Int = 8080
    lateinit var immutableRequest: Request
    lateinit var request: HttpServletRequest
    lateinit var response: HttpServletResponse

    /**
     * Returns server port.
     */
    fun port(): Int = port

    /**
     * Checks if a request is coming from a logged in user.
     *
     * @return [Boolean] - true if logged in and false otherwise.
     */
    abstract fun loggedIn(): Boolean

    /**
     * Returns the credentials of the currently logged in user.
     */
    abstract fun getUser()

    /**
     * Invoked to get a [Map] of request URL parameters.
     *
     * @return [Map] - map containing URL parameters.
     */
    abstract fun getUrlParams(): Map<String, String>

    /**
     * Invoked to get a [Map] of request query parameters.
     *
     * @return [Map] - map containing URL query parameters.
     */
    abstract fun getQueryParams(): Map<String, String>
}