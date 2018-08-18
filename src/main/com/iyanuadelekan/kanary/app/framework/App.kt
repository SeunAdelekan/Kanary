package com.iyanuadelekan.kanary.app.framework

import com.iyanuadelekan.kanary.app.resource.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
internal interface App {

    /**
     * Registers a resource to the application.
     *
     * @param resource - resource to be registered.
     * @return [App] - current application instance.
     */
    fun registerResource(resource: Resource): App
}