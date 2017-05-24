/**
 * @author Iyanu Adelekan on 23/05/2017.
 */

package com.iyanuadelekan.kanary.core

import com.iyanuadelekan.kanary.interfaces.RouterInterface
import com.iyanuadelekan.kanary.libs.RouteList
import com.iyanuadelekan.kanary.constants.HttpConstants

/**
 * @property basePath the root path of mapped HTTP request
 * @constructor Initializes KanaryRouter object
 */
class KanaryRouter(val basePath: String, val controller: KanaryController): RouterInterface {

    val getRouteList: RouteList = RouteList()
    val postRouteList: RouteList = RouteList()
    val putRouteList: RouteList = RouteList()
    val patchRouteList: RouteList = RouteList()
    val deleteRouteList: RouteList = RouteList()

    /**
     * Router function handling GET requests
     * @return the KanaryRouter instance
     */
    override fun get(path: String, action: String): KanaryRouter {
        this.queueRoute(HttpConstants.GET.name, path, action)
        return this
    }

    /**
     * Router function handling POST requests
     * @return the KanaryRouter instance
     */
    override fun post(path: String, action: String): KanaryRouter {
        this.queueRoute(HttpConstants.POST.name, path, action)
        return this
    }

    /**
     * Router function handling DELETE requests
     * @return the KanaryRouter instance
     */
    override fun delete(path: String, action: String): KanaryRouter {
        this.queueRoute(HttpConstants.DELETE.name, path, action)
        return this
    }

    /**
     * Router function handling PUT requests
     * @return the KanaryRouter instance
     */
    override fun put(path: String, action: String): KanaryRouter {
        this.queueRoute(HttpConstants.PUT.name, path, action)
        return this
    }

    /**
     * Router function handling PATCH requests
     * @return the KanaryRouter instance
     */
    override fun patch(path: String, action: String): KanaryRouter {
        this.queueRoute(HttpConstants.PATCH.name, path, action)
        return this
    }

    /**
     * Function used for route queuing
     * Adds a HashMap containing the [path] as key and [action]
     * to the route list
     */
    private fun queueRoute(method: String, path: String, action: String) {
        val routeMap = HashMap<String, String>()
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

    private fun fireAction() {

    }

}