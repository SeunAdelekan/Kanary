package com.iyanuadelekan.kanary.core

import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 26/05/2017.
 */

/**
 * @param path the path of the route
 * @param controller controller that handles requests from the route
 * @param action reference to an action within a KanaryController
 * @constructor initializes route instance
 */
data class Route(val path: String, val controller: KanaryController?, val action: (Request, HttpServletRequest, HttpServletResponse) -> Unit)