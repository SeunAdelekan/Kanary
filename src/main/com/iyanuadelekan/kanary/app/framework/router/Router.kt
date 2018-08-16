package com.iyanuadelekan.kanary.app.framework.router

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.routerAction

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Interface defining methods that must be implemented by an application router.
 */
interface Router {

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     */
    fun get(path: String, routerAction: routerAction)

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     */
    fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction)

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     */
    fun post(path: String, routerAction: routerAction)

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     */
    fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction)

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     */
    fun put(path: String, routerAction: routerAction)

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     */
    fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction)

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     */
    fun delete(path: String, routerAction: routerAction)

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     */
    fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction)

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     */
    fun options(path: String, routerAction: routerAction)

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     */
    fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction)
}