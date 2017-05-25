package com.iyanuadelekan.kanary.libs

import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 24/05/2017.
 */
class RouteList : ArrayList<HashMap<String, (Request, HttpServletRequest, HttpServletResponse) -> Unit>>()