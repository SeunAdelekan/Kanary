package com.iyanuadelekan.kanary.app

import com.iyanuadelekan.kanary.core.KanaryMiddleware
import com.iyanuadelekan.kanary.core.KanaryRouter
import org.eclipse.jetty.server.handler.ContextHandler


/**
 * @author Iyanu Adelekan
 *
 * @constructor creates a new ContextHandler instance
 */
class KanaryApp : ContextHandler() {

    /**
     * Adds [middleware] to the app
     * @return current KanaryApp instance
     */
    fun use(middleware: KanaryMiddleware): KanaryApp {
        return this
    }

    /**
     * Mounts a [router] on the application queue
     * @return current KanaryApp instance
     */
    fun mount(router: KanaryRouter): KanaryApp {
        return this
    }

}