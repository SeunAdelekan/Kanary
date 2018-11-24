package com.iyanuadelekan.kanary.app.framework

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.resource.Resource

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
internal interface App {

    /**
     * Mounts a variable number of middleware to the app.
     *
     * @param middleware - middleware to be mounted.
     */
    fun use(vararg middleware: MiddlewareAdapter)

    /**
     * Mounts a variable number of routers to the application.
     *
     * @param routers - routers to be mounted.
     */
    fun mount(vararg routers: Router)

    /**
     * Starts the server on a specified port.
     *
     * @param port - port to start the server on.
     */
    fun start(port: Int)

    /**
     * Registers a resource to the application.
     *
     * @param resource - resource to be registered.
     * @return [App] - current application instance.
     */
    fun registerResource(resource: Resource): App
}