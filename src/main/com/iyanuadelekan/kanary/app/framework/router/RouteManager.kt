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

    fun addRoute(routeType: RouteType,
                 path: String,
                 action: RouterAction,
                 middleware: List<MiddlewareAdapter>? = null
    ): RouteManager

    fun getMatchingNode(routeList: RouteList, path: String): RouteNode?
}