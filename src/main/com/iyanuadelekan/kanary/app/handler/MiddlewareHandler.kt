package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.consumer.MiddlewareConsumer
import com.iyanuadelekan.kanary.app.framework.lifecycle.Context

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Delegate class for the handling of application middleware.
 */
internal class MiddlewareHandler : MiddlewareConsumer {

    val middleware: ArrayList<MiddlewareAdapter> = ArrayList()
    val iterator: Iterator<MiddlewareAdapter> = middleware.iterator()

    /**
     * Mounts a variable number of middleware.
     *
     * @param middleware - middleware to be added.
     */
    override fun use(vararg middleware: MiddlewareAdapter) {
        this.middleware.addAll(middleware.asList())
    }

    /**
     * Returns the `next` middleware if one exists.
     *
     * @return [MiddlewareAdapter] - the next middleware
     */
    override fun next(): MiddlewareAdapter = iterator.next()

    /**
     * Checks if a `next` middleware exists.
     *
     * @return [Boolean] - true if `next` exists and false otherwise.
     */
    override fun hasNext(): Boolean = iterator.hasNext()

    /**
     * Runs all mounted middleware.
     *
     * @param ctx - server context.
     */
    internal fun run(ctx: Context) {
        while (iterator.hasNext()) {
            iterator.next().run(ctx)
        }
    }
}