package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.handler.RouterHandler
import com.iyanuadelekan.kanary.app.lifecycle.AppContext
import com.iyanuadelekan.kanary.app.resource.Resource
import com.iyanuadelekan.kanary.exceptions.ResourceNotFoundException
import com.iyanuadelekan.kanary.app.framework.App as AppFramework

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Core application handling app logic and maintaining the lifecycle
 * of the server.
 */
class App : AppFramework, AppContext() {

    private val routerHandler = RouterHandler()
    private val middlewareHandler = MiddlewareHandler()

    /**
     * Mounts a variable number of middleware to the app.
     *
     * @param middleware - middleware to be mounted.
     */
    fun use(vararg middleware: MiddlewareAdapter) = this.middlewareHandler.use(*middleware)

    /**
     * Returns the `next` middleware.
     *
     * @return [MiddlewareAdapter] - the `next` middleware.
     */
    internal fun nextMiddleware(): MiddlewareAdapter = middlewareHandler.next()

    /**
     * Checks if a `next` middleware exists in the middleware list.
     *
     * @return [Boolean] - true if `next` middleware exists and false otherwise.
     */
    override fun hasNextMiddleware(): Boolean = middlewareHandler.hasNext()

    /**
     * Mounts a variable number of routers to the application.
     *
     * @param routers - routers to be mounted.
     */
    fun mount(vararg routers: Router) = routerHandler.use(*routers)

    /**
     * Returns the next router in router list maintained by [routerHandler].
     *
     * @return [Router] - `next` router.
     */
    internal fun nextRouter(): Router = routerHandler.next()

    /**
     * Checks if a `next` router exists in the router list.
     *
     * @return [Boolean] - true if `next` router exists and false otherwise.
     */
    override fun hasNextRouter(): Boolean = middlewareHandler.hasNext()

    /**
     * Runs all mounted middleware.
     */
    private fun runMiddleware() = middlewareHandler.run(this)

    /**
     * Returns an application resource of a specified type.
     *
     * @param resourceType - type of resource to be returned.
     * @throws [ResourceNotFoundException]
     * @see Resource.Type for the types of resources returnable.
     */
    override fun getResource(resourceType: Resource.Type): Resource = resourceManager.getResource(resourceType)

    /**
     * Convenience method that checks if a request is coming from a logged in user.
     *
     * @return [Boolean] - true if logged in and false otherwise.
     */
    override fun loggedIn(): Boolean = securityManager.loggedIn()

    /**
     * Convenience method providing access to credentials of the currently logged in user.
     */
    override fun getUser() = securityManager.getUser()

    /**
     * Registers a resource to the application.
     *
     * @param resource - resource to be registered.
     * @return [App] - current application instance.
     */
    override fun registerResource(resource: Resource): App {
        resourceManager.register(resource)
        return this
    }
}