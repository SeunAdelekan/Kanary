package com.iyanuadelekan.kanary.app.framework

import com.iyanuadelekan.kanary.app.resource.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
internal interface App {

    fun handleRequest(request: HttpServletRequest, response: HttpServletResponse)

    fun registerResource(resource: Resource): App
}