package com.iyanuadelekan.kanary.app.framework.router

import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Interface defining methods that must be implemented by an application router.
 */
interface Router {

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun get(path: String, routerAction: RouterAction): Router

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun post(path: String, routerAction: RouterAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun put(path: String, routerAction: RouterAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun delete(path: String, routerAction: RouterAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun options(path: String, routerAction: RouterAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router
}