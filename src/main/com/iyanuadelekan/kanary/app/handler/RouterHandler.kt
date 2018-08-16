package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.framework.consumer.RouterConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Delegate class for the handling of router consumption within
 * the framework.
 */
class RouterHandler : RouterConsumer {

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
}