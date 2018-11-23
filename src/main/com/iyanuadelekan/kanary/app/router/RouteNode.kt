package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.RouteList
import com.iyanuadelekan.kanary.app.RouterAction
import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter

/**
 * @author Iyanu Adelekan on 18/11/2018.
 */
internal class RouteNode(val path: String, var action: RouterAction? = null) {

    private val children = RouteList()
    private var middleware: ArrayList<MiddlewareAdapter> = ArrayList()

    /**
     * Adds a child node to the current node.
     *
     * @param routeNode - node to be added.
     */
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

    /**
     * Invoked to add a collection of middleware to route node.
     *
     * @param middleware - middleware to be added.
     */
    fun addMiddleware(middleware: List<MiddlewareAdapter>) {
        this.middleware.addAll(middleware)
    }
}