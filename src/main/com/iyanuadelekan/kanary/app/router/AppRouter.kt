package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.framework.consumer.MiddlewareConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.RouterAction

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

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun get(path: String, routerAction: RouterAction): Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun post(path: String, routerAction: RouterAction): Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun put(path: String, routerAction: RouterAction): Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun delete(path: String, routerAction: RouterAction): Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    override fun options(path: String, routerAction: RouterAction): Router {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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