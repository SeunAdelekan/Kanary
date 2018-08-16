package com.iyanuadelekan.kanary.app.router

import com.iyanuadelekan.kanary.app.adapter.component.middleware.MiddlewareAdapter
import com.iyanuadelekan.kanary.app.handler.MiddlewareHandler
import com.iyanuadelekan.kanary.app.framework.consumer.MiddlewareConsumer
import com.iyanuadelekan.kanary.app.framework.router.Router
import com.iyanuadelekan.kanary.app.routerAction

class AppRouter : Router, MiddlewareConsumer {

    private val middlewareHandler = MiddlewareHandler()

    override fun get(path: String, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun post(path: String, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun post(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(path: String, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(path: String, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun options(path: String, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun options(path: String, vararg middleware: MiddlewareAdapter, routerAction: routerAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun use(vararg middleware: MiddlewareAdapter) {
        middlewareHandler.use(*middleware)
    }

    override fun next(): MiddlewareAdapter {
        return middlewareHandler.next()
    }

    override fun hasNext(): Boolean {
        return middlewareHandler.hasNext()
    }
}