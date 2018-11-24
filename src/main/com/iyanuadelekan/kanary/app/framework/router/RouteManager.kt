package com.iyanuadelekan.kanary.app.framework.router

import com.iyanuadelekan.kanary.app.RouteList
import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.router.RouteNode

/**
 * @author Iyanu Adelekan on 18/11/2018.
 */
internal interface RouteManager {

    /**
     * Invoked to register a new route to the router.
     *
     * @param routeType - type of route to be added. See [RouteType].
     * @param path - URL path.
     * @param action - router action.
     *
     * @return [RouteManager] - current [RouteManager] instance.
     */
    fun addRoute(
            routeType: RouteType,
            path: String,
            action: RouterAction,
            middleware: List<MiddlewareAdapter>? = null
    ): RouteManager

    /**
     * Invoked to resolve a corresponding RouteNode to a given URL target - if any.
     *
     * @param path - URL path (target).
     * @return [RouteNode] - Returns corresponding instance of [RouteNode], if one exists. Else returns null.
     */
    fun getRouteNode(path: String, method: RouteType): RouteNode?

    /**
     * Invoked to get a matching route node - within a given route list - for a given sub path.
     *
     * @param routeList - list of routes.
     * @param path - sub path to match.
     * @return [RouteNode] - returns a [RouteNode] is one exists and null otherwise.
     */
    fun getMatchingNode(routeList: RouteList, path: String): RouteNode?
}