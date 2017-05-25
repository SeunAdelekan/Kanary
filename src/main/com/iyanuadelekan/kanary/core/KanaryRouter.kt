/**
 * @author Iyanu Adelekan on 23/05/2017.
 */

package com.iyanuadelekan.kanary.core

import com.iyanuadelekan.kanary.interfaces.RouterInterface
import com.iyanuadelekan.kanary.libs.RouteList
import com.iyanuadelekan.kanary.constants.HttpConstants
import com.iyanuadelekan.kanary.exceptions.InvalidRouteException
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @property basePath the root path of mapped HTTP request
 * @constructor Initializes KanaryRouter object
 */
class KanaryRouter(var basePath: String?= null, var routeController: KanaryController?= null): RouterInterface {

    val getRouteList: RouteList = RouteList()
    val postRouteList: RouteList = RouteList()
    val putRouteList: RouteList = RouteList()
    val patchRouteList: RouteList = RouteList()
    val deleteRouteList: RouteList = RouteList()

    /**
     * Router function handling GET requests
     * @return current instance of [KanaryRouter]
     */
    override fun get(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter {
        if (basePath == null) {
            this.queueRoute(HttpConstants.GET.name, path, action)
        } else {
            this.queueRoute(HttpConstants.GET.name, prependBasePath(path), action)
        }
        return this
    }

    /**
     * Router function handling POST requests
     * @return current instance of [KanaryRouter]
     */
    override fun post(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter {
        if (basePath == null) {
            this.queueRoute(HttpConstants.POST.name, path, action)
        } else {
            this.queueRoute(HttpConstants.POST.name, prependBasePath(path), action)
        }
        return this
    }

    /**
     * Router function handling DELETE requests
     * @return current instance of [KanaryRouter]
     */
    override fun delete(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter {
        if (basePath == null) {
            this.queueRoute(HttpConstants.DELETE.name, path, action)
        } else {
            this.queueRoute(HttpConstants.DELETE.name, prependBasePath(path), action)
        }
        return this
    }

    /**
     * Router function handling PUT requests
     * @return current instance of [KanaryRouter]
     */
    override fun put(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter {
        if (basePath == null) {
            this.queueRoute(HttpConstants.PUT.name, path, action)
        } else {
            this.queueRoute(HttpConstants.PUT.name, prependBasePath(path), action)
        }
        return this
    }

    /**
     * Router function handling PATCH requests
     * @return current instance of [KanaryRouter]
     */
    override fun patch(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter {
        if (basePath == null) {
            this.queueRoute(HttpConstants.PATCH.name, path, action)
        } else {
            this.queueRoute(HttpConstants.PATCH.name, prependBasePath(path), action)
        }
        return this
    }

    /**
     * Function used for route queuing
     * Adds a HashMap containing the [path] as key and [action]
     * to the route list
     */
    private fun queueRoute(method: String, path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit) {
        val routeMap = HashMap<String, (Request, HttpServletRequest, HttpServletResponse) -> Unit>()
        routeMap.put(path, action)

        when(method) {
            HttpConstants.GET.name -> getRouteList.add(routeMap)
            HttpConstants.POST.name -> postRouteList.add(routeMap)
            HttpConstants.PUT.name -> putRouteList.add(routeMap)
            HttpConstants.DELETE.name -> deleteRouteList.add(routeMap)
            HttpConstants.PATCH.name -> patchRouteList.add(routeMap)
            else -> {
                println("Unrecognized HTTP method: $method")
            }
        }
    }

    /**
     * Used to set the base path for a set of routes
     * sets [basePath] to [path]
     * @return current instance of [KanaryRouter]
     */
    infix fun on(path: String): KanaryRouter {
        if(isRoutePathValid(path)) {
            basePath = path
            return this
        }
        throw InvalidRouteException("The path $path is an invalid route path")
    }

    /**
     * Used to set the controller for a set of routes
     * sets [routeController] to [controller]
     * @return current instance of [KanaryRouter]
     */
    infix fun use(controller: KanaryController): KanaryRouter  {
        if(basePath != null) {
            this.routeController = controller
            return this
        }
        throw InvalidRouteException("Controller mount attempted without a set base path.")
    }

    /**
     * Tests if a route path is valid
     * @return Boolean indicating if [path] is a valid route path
     */
    private fun isRoutePathValid(path: String): Boolean {
        return Regex("\\w+(/\\w*)*") matches path
    }

    /**
     * Prepends the current [basePath] to a [path]
     * @return prepended path
     */
    private fun prependBasePath(path: String): String {
        return "$basePath/$path"
    }

    private fun fireAction() {

    }

}