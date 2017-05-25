package com.iyanuadelekan.kanary.app

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.ServerConnector
import org.eclipse.jetty.util.component.LifeCycle

/**
 * @author Iyanu Adelekan on 23/05/2017.
 */
class Server : Server() {

    val httpCable: ServerConnector = ServerConnector(this)

    var port: Int
        get() = httpCable.port
        set(value) {
            httpCable.port = port
        }

    var host: String
        get() = httpCable.host
        set(value) {
            httpCable.host = value
        }

    var timeOut: Long
        get() = httpCable.idleTimeout
        set(value) {
            httpCable.idleTimeout = value
        }

    fun useHttp() {
        this.addConnector(httpCable)
    }

    override fun start(l: LifeCycle?) {
        useHttp()
        super.start(l)
    }

}