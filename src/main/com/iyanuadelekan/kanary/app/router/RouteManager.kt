package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.RouteList
import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.exceptions.InvalidRouteException

/**
 * @author Iyanu Adelekan on 18/11/2018.
 */
/**
 * Class managing the creation and addition of routes to respective route trees.
 */
internal class RouteManager : com.iyanuadelekan.kanary.app.framework.router.RouteManager {

    private val getRoutes: RouteList = ArrayList()
    private val postRoutes: RouteList = ArrayList()
    private val putRoutes: RouteList = ArrayList()
    private val deleteRoutes: RouteList = ArrayList()
    private val optionsRoutes: RouteList = ArrayList()

    /**
     * Invoked to register a new route to the router.
     *
     * @param routeType - type of route to be added. See [RouteType].
     * @param path - URL path.
     * @param action - router action.
     *
     * @return [RouteManager] - current [RouteManager] instance.
     */
    @Throws(InvalidRouteException::class)
    override fun addRoute(
            routeType: RouteType,
            path: String,
            action: RouterAction,
            middleware: List<MiddlewareAdapter>?): RouteManager {

        val routeList: RouteList = when (routeType) {
            RouteType.GET -> this.getRoutes
            RouteType.POST -> this.postRoutes
            RouteType.PUT -> putRoutes
            RouteType.DELETE -> deleteRoutes
            RouteType.OPTIONS -> optionsRoutes
        }
        val subPaths: List<String> = path.split("/")

        if (!subPaths.isEmpty()) {
            var node: RouteNode? = getMatchingNode(routeList, subPaths[0])

            if (node == null) {
                node = RouteNode(subPaths[0], action)
                routeList.add(node)
            }

            if (subPaths.size > 1) {
                for (i in 1 until subPaths.size) {
                    val pathSegment = subPaths[i]

                    if (!node!!.hasChild(pathSegment)) {
                        val childNode = RouteNode(pathSegment)

                        if (i == subPaths.size - 1) {
                            childNode.action = action
                        }
                        if (middleware != null) {
                            childNode.addMiddleware(middleware)
                        }

                        node.addChild(childNode)
                    } else {
                        if (i == subPaths.size - 1) {
                            throw InvalidRouteException(
                                    "The path '$path' with method '${routeType.name}' has already been defined.")
                        }
                        node = node.getChild(pathSegment)
                    }
                }
            }
            return this
        }
        throw InvalidRouteException("The path '$path' is an invalid route path")
    }

    /**
     * Invoked to get a matching route node for a given sub path.
     *
     * @param routeList - list of routes.
     * @param path - sub path to match.
     * @return [RouteNode] - returns a [RouteNode] is one exists and null otherwise.
     */
    override fun getMatchingNode(routeList: RouteList, path: String): RouteNode? {
        var node: RouteNode? = null

        routeList.forEach {
            if (it.path == path) {
                node = it
            }
        }
        return node
    }
}