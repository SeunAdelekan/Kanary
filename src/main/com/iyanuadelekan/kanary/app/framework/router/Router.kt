package com.iyanuadelekan.kanary.app.framework.router

import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.lifecycle.Context
import com.iyanuadelekan.kanary.app.router.RouteManager

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Interface defining methods that must be implemented by an application router.
 */
abstract class Router {

    internal val routeManager = RouteManager()

    /**
     * Invoked to set 'beforeAction' callback. This action
     * will be executed before individual router actions.
     *
     * @param action
     */
    abstract fun beforeAction(action: RouterAction)

    /**
     * Executes beforeAction callback method.
     *
     * @param ctx - [Context]
     */
    abstract fun executeBeforeAction(ctx: Context)

    /**
     * Invoked to set 'afterAction' callback. This action
     * will be executed after individual router actions.
     *
     * @param action
     */
    abstract fun afterAction(action: RouterAction)

    /**
     * Executes afterAction callback method.
     *
     * @param ctx - [Context]
     */
    abstract fun executeAfterAction(ctx: Context)

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun get(path: String, routerAction: RouterAction): Router

    /**
     * Handles GET requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun post(path: String, routerAction: RouterAction): Router

    /**
     * Handles POST requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun put(path: String, routerAction: RouterAction): Router

    /**
     * Handles PUT requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun delete(path: String, routerAction: RouterAction): Router

    /**
     * Handles DELETE requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun options(path: String, routerAction: RouterAction): Router

    /**
     * Handles OPTIONS requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles HEAD requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun head(path: String, routerAction: RouterAction): Router

    /**
     * Handles HEAD requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun head(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles PATCH requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun patch(path: String, routerAction: RouterAction): Router

    /**
     * Handles PATCH requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun patch(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles LINK requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun link(path: String, routerAction: RouterAction): Router

    /**
     * Handles LINK requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun link(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router

    /**
     * Handles UNLINK requests.
     *
     * @param [path] - request path.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun unlink(path: String, routerAction: RouterAction): Router

    /**
     * Handles UNLINK requests.
     *
     * @param [path] - request path.
     * @param [middleware] - list of [MiddlewareAdapter] instances to be added.
     * @param [routerAction] - router action.
     * @return [Router] - Current [Router] instance.
     */
    abstract fun unlink(path: String, vararg middleware: MiddlewareAdapter, routerAction: RouterAction): Router
}