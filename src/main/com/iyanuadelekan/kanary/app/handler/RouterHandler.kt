package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.framework.consumer.RouterConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.router.RouteNode

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Delegate class for the handling of router consumption within
 * the framework.
 */
internal class RouterHandler : RouterConsumer {

    private val routers: ArrayList<Router> = ArrayList()
    private val iterator: Iterator<Router> = routers.iterator()

    /**
     * Mounts a variable number of routers to [routers].
     *
     * @param routers - routers to be mounted.
     */
    override fun use(vararg routers: Router) {
        this.routers.addAll(routers.asList())
    }

    /**
     * Returns the `next` router.
     *
     * @return [Router] - `next` router.
     */
    override fun next(): Router = iterator.next()

    /**
     * Checks if a next router exists in [routers]
     *
     * @return [Boolean] - true if `next` router exists and false otherwise.
     */
    override fun hasNext(): Boolean = iterator.hasNext()

    /**
     * Invoked to resolve a route into its corresponding route node - if any.
     *
     * @param path - Target path.
     * @return [Pair] - Returns corresponding Router-RouteNode [Pair] if one exists. null is returned otherwise
     */
    override  fun resolveRoute(path: String, method: RouteType): Pair<Router, RouteNode>? {
        routers.forEach {
            val routeNode = it.routeManager.getRouteNode(path, method)

            if (routeNode != null) {
                return Pair(it, routeNode)
            }
        }
        return null
    }
}