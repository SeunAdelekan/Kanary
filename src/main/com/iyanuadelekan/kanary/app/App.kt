package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.handler.RouterHandler
import com.iyanuadelekan.kanary.app.lifecycle.AppContext

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Core application handling app logic and maintaining the lifecycle
 * of the server.
 */
class App : AppContext() {

    private val routerHandler = RouterHandler()
    private val middlewareHandler = MiddlewareHandler()

    /**
     * Mounts a variable number of middleware to the app.
     *
     * @param middleware - middleware to be mounted.
     */
    fun use(vararg middleware: MiddlewareAdapter) {
        this.middlewareHandler.use(*middleware)
    }

    /**
     * Returns the `next` middleware.
     *
     * @return [MiddlewareAdapter] - the `next` middleware.
     */
    internal fun nextMiddleware(): MiddlewareAdapter {
        return middlewareHandler.next()
    }

    override fun hasNextMiddleware(): Boolean {
        return middlewareHandler.hasNext()
    }

    fun mount(vararg router: Router) {
        routerHandler.use(*router)
    }

    internal fun nextRouter(): Router {
        return routerHandler.next()
    }

    override fun hasNextRouter(): Boolean {
        return middlewareHandler.hasNext()
    }

    /**
     * Runs all mounted middleware.
     */
    private fun runMiddleware() {
        middlewareHandler.run(this)
    }
}