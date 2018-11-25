package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.framework.lifecycle.LifeCycle
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.handler.AppRequestHandler
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.handler.RouterHandler
import com.iyanuadelekan.kanary.app.lifecycle.AppContext
import com.iyanuadelekan.kanary.app.lifecycle.LifeCycleManager
import com.iyanuadelekan.kanary.app.resource.Resource
import com.iyanuadelekan.kanary.app.router.RouteNode
import com.iyanuadelekan.kanary.exceptions.ResourceNotFoundException
import org.eclipse.jetty.server.Server
import com.iyanuadelekan.kanary.app.framework.App as AppFramework

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Core application handling app logic and maintaining the lifecycle
 * of the server.
 */
class App : AppFramework, AppContext(), LifeCycle {

    private lateinit var server: Server
    private val routerHandler = RouterHandler()
    private val middlewareHandler = MiddlewareHandler()
    private val requestHandler = AppRequestHandler(this)

    private val lifeCycleManager by lazy {
        LifeCycleManager()
    }

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
    fun nextMiddleware(): MiddlewareAdapter = middlewareHandler.next()

    /**
     * Checks if a `next` middleware exists in the middleware list.
     *
     * @return [Boolean] - true if `next` middleware exists and false otherwise.
     */
    fun hasNextMiddleware(): Boolean = middlewareHandler.hasNext()

    /**
     * Mounts a variable number of routers to the application.
     *
     * @param routers - routers to be mounted.
     */
    override fun mount(vararg routers: Router) = routerHandler.use(*routers)

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
    override fun hasNextRouter(): Boolean = routerHandler.hasNext()

    /**
     * Runs all mounted middleware.
     */
    internal fun runMiddleware() = middlewareHandler.run(this)

    /**
     * Invoked to resolve a route into its corresponding route node - if any.
     *
     * @param path - Target path.
     * @return [RouteNode] - Returns corresponding [RouteNode] if one exists. null is returned otherwise
     *
     */
    internal fun resolveRoute(path: String, method: RouteType): Pair<Router, RouteNode>? =
            this.routerHandler.resolveRoute(path, method)

    /**
     * Starts the server on a specified port.
     *
     * @param port - port to start the server on.
     */
    override fun start(port: Int) {
        lifeCycleManager.onStart()
        this.port = port

        server = Server(port).apply {
            handler = requestHandler

            start()
            join()
        }
    }

    /**
     * Stops the application server.
     */
    override fun stop() {
        lifeCycleManager.onStop()

        if (!server.isStopped) {
            server.stop()
        }
    }

    /**
     * Adds application start event.
     *
     * @param event
     */
    override fun onStart(event: LifeCycleEvent) {
        lifeCycleManager.addStartEvent(event)
    }

    /**
     * Adds application stop event.
     *
     * @param event
     */
    override fun onStop(event: LifeCycleEvent) {
        lifeCycleManager.addStopEvent(event)
    }

    /**
     * Returns an application resource of a specified type.
     *
     * @param resourceType - type of resource to be returned.
     * @throws [ResourceNotFoundException]
     * @see Resource.Type for the types of resources returnable.
     */
    override fun getResource(resourceType: Resource.Type): Resource = resourceManager.getResource(resourceType)

    /**
     * Invoked to get a [Map] of request URL parameters.
     *
     * @return [Map] - map containing URL parameters.
     */
    override fun getUrlParams(): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Invoked to get a [Map] of request query parameters.
     *
     * @return [Map] - map containing URL query parameters.
     */
    override fun getQueryParams(): Map<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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