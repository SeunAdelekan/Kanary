package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.RouteList
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.framework.consumer.MiddlewareConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.exceptions.InvalidRouteException

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

    /**
     * Enum defining types of creatable routes.
     */
    private enum class RouteType {
        POST,
        GET,
        DELETE,
        OPTIONS,
        PUT,
    }

    /**
     * Class managing the creation and addition of routes to respective route trees.
     */
    private class RouteManager {

        private val getRoutes: ArrayList<RouteNode> = ArrayList()
        private val postRoutes: ArrayList<RouteNode> = ArrayList()
        private val putRoutes: ArrayList<RouteNode> = ArrayList()
        private val deleteRoutes: ArrayList<RouteNode> = ArrayList()
        private val optionsRoutes: ArrayList<RouteNode> = ArrayList()

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
        fun addRoute(
                routeType: RouteType,
                path: String,
                action: RouterAction,
                middleware: List<MiddlewareAdapter>? = null): RouteManager {

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
        private fun getMatchingNode(routeList: RouteList, path: String): RouteNode? {
            var node: RouteNode? = null

            routeList.forEach {
                if (it.path == path) {
                    node = it
                }
            }
            return node
        }
    }

    private class RouteNode(val path: String, var action: RouterAction? = null) {

        private lateinit var middleware: ArrayList<MiddlewareAdapter>
        private val children: RouteList = ArrayList()

        fun addChild(routeNode: RouteNode) = this.children.add(routeNode)

        /**
         * Invoked to check if a route node has a specified child node.
         *
         * @param path - path to be matched.
         * @return [Boolean] - true if child exists and false otherwise.
         */
        fun hasChild(path: String): Boolean {
            children.forEach {
                if (it.path == path) {
                    return true
                }
            }
            return false
        }

        /**
         * Gets a child node matching a specific path.
         *
         * @params path - path to be matched.
         * @return [RouteNode] - node if one exists and null otherwise.
         */
        fun getChild(path: String): RouteNode? {
            children.forEach {
                if (it.path == path) {
                    return it
                }
            }
            return null
        }

        fun addMiddleware(middleware: List<MiddlewareAdapter>) {
            this.middleware.addAll(middleware)
        }
    }
}