package com.iyanuadelekan.kanary.app.framework.router

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.routerAction

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
    fun get(path: String, routerAction: routerAction): Router

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun post(path: String, routerAction: routerAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun put(path: String, routerAction: routerAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun delete(path: String, routerAction: routerAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun options(path: String, routerAction: routerAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction): Router
}