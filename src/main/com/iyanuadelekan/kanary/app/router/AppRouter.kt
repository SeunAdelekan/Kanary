package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.consumer.MiddlewareConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.framework.router.RouteManager as FrameworkRouteManager

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Core application router class. Utilize instances of this class
 * to compose server routes. Upon composition of a route it must be
 * mounted to the application before it can be used.
 *
 * Example:
 * val app = App()
 * val router = AppRouter()
 *
 * router.post("/user") { ctx ->
 *      val user = User()
 *
 *      user.name = "Jon Snow"
 *      user.title = "King in the north."
 *
 *      ctx.response.<User>send(user)
 * }
 *
 * app.mount(router)
 * app.start(8080)
 */
class AppRouter : Router, MiddlewareConsumer {

    private val middlewareHandler = MiddlewareHandler()
    private val routeManager: RouteManager = RouteManager()

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun get(path: String, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.GET, path, routerAction)
        return this
    }

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @return [Router] - Current [Router] instance.
     */
    override fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.GET, path, routerAction, middleware.toList())
        return this
    }

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun post(path: String, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.POST, path, routerAction)
        return this
    }

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @return [Router] - Current [Router] instance.
     */
    override fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.POST, path, routerAction, middleware.toList())
        return this
    }

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun put(path: String, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.PUT, path, routerAction)
        return this
    }

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @return [Router] - Current [Router] instance.
     */
    override fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.PUT, path, routerAction, middleware.toList())
        return this
    }

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun delete(path: String, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.DELETE, path, routerAction)
        return this
    }

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @return [Router] - Current [Router] instance.
     */
    override fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.DELETE, path, routerAction, middleware.toList())
        return this
    }

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun options(path: String, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.OPTIONS, path, routerAction)
        return this
    }

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @return [Router] - Current [Router] instance.
     */
    override fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router {
        routeManager.addRoute(RouteType.OPTIONS, path, routerAction, middleware.toList())
        return this
    }

    /**
     * Mounts a variable number of middleware to the application.
     *
     * @param middleware - middleware to be mounted.
     */
    override fun use(vararg middleware: MiddlewareAdapter) {
        middlewareHandler.use(*middleware)
    }

    /**
     * Returns the next middleware in middleware list maintained by [middlewareHandler].
     *
     * @return [MiddlewareAdapter] - `next` middleware.
     */
    override fun next(): MiddlewareAdapter {
        return middlewareHandler.next()
    }

    /**
     * Checks if a `next` middleware exists.
     *
     * @return [Boolean] - true if one exists and false otherwise.
     */
    override fun hasNext(): Boolean {
        return middlewareHandler.hasNext()
    }
}