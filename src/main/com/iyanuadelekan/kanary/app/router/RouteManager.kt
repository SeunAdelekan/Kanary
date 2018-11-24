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

        val routeList: RouteList = getRouteList(routeType)
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
     * Invoked to resolve a corresponding RouteNode to a given URL target - if any.
     *
     * @param path - URL path (target).
     * @return [RouteNode] - Returns corresponding instance of [RouteNode], if one exists. Else returns null.
     */
    override fun getRouteNode(path: String, method: RouteType): RouteNode? {
        val routeList = getRouteList(method)

        // TODO finish logic.
        return RouteNode("")
    }

    /**
     * Invoked to get a matching route node - within a given route list - for a given sub path.
     *
     * @param routeList - list of routes.
     * @param subPath - sub path to match.
     * @return [RouteNode] - returns a [RouteNode] is one exists and null otherwise.
     */
    override fun getMatchingNode(routeList: RouteList, subPath: String): RouteNode? {
        var node: RouteNode? = null

        routeList.forEach {
            if (it.path == subPath) {
                node = it
            }
        }
        return node
    }

    /**
     * Invoked to resolve the required route list for a given route type.
     *
     * @param method - Specified [RouteType].
     * @return [RouteList] - Corresponding route list.
     */
    private fun getRouteList(method: RouteType): RouteList {
        return when (method) {
            RouteType.GET -> getRoutes
            RouteType.POST -> postRoutes
            RouteType.PUT -> putRoutes
            RouteType.DELETE -> deleteRoutes
            RouteType.OPTIONS -> optionsRoutes
        }
    }
}