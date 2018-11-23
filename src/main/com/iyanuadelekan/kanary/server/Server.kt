package com.iyanuadelekan.kanary.server

import com.iyanuadelekan.kanary.core.KanaryEnvironment
import org.eclipse.jetty.server.Server

/**
 * @author Iyanu Adelekan on 23/05/2017.
 */
class Server : Server() {

    /**
     * Used to start the server. The server listens on the port specified
     * @param port Port to start the server on
     */
    fun listen(port: Int) {
        printInitiationMessage()

        val jettyServer = Server(port)
        jettyServer.handler = handler
        jettyServer.start()
        jettyServer.join()
    }

    /**
     * This prints the server setup message to console
     */
    private fun printInitiationMessage() {
        println("=> Initializing Kanary app")
        println("=> Setting handlers...")
        println("=> Application starting in '${KanaryEnvironment().getEnvironment()}'")
        println("=> Starting application server with jetty")
    }
}