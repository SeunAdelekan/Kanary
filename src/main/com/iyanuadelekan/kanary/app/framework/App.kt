package com.iyanuadelekan.kanary.app.framework

import com.iyanuadelekan.kanary.app.resource.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
internal interface App {

    /**
     * Handles HTTP requests to the server.
     *
     * @param request - HTTP request.
     * @param response - HTTP response.
     */
    fun handleRequest(request: HttpServletRequest, response: HttpServletResponse)

    /**
     * Registers a resource to the application.
     *
     * @param resource - resource to be registered.
     * @return [App] - current application instance.
     */
    fun registerResource(resource: Resource): App
}