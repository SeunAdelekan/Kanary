package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.framework.consumer.RouterConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router

/**
 * Delegate class for the handling of router consumption within
 * the framework.
 */
class RouterHandler : RouterConsumer {

    private val routers: ArrayList<Router> = ArrayList()
    private val iterator: Iterator<Router> = routers.iterator()

    override fun use(vararg router: Router) {
        this.routers.addAll(router.asList())
    }

    override fun next(): Router {
        return iterator.next()
    }

    override fun hasNext(): Boolean {
        return iterator.hasNext()
    }
}