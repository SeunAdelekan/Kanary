package com.iyanuadelekan.kanary.app.framework.consumer

import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.router.RouteNode

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Consumer interface for router consumers.
 */
internal interface RouterConsumer : BaseConsumer<Router> {

    /**
     * Invoked to resolve a route into its corresponding route node - if any.
     *
     * @param path - Target path.
     * @return [RouteNode] - Returns corresponding [RouteNode] if one exists. null is returned otherwise
     *
     */
    fun resolveRoute(path: String, method: RouteType): RouteNode?
}