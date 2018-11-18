package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.constant.Protocol
import com.iyanuadelekan.kanary.app.framework.annotation.RequestHandler
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 18/08/2018.
 *
 * Handler class in charge of handling of HTTP/HTTPS requests received by
 * the application server.
 */
@RequestHandler(
        protocol = Protocol.HTTP,
        description = "Request handler catering for HTTP and HTTPS requests."
)
internal class AppRequestHandler : AbstractHandler() {

    /**
     * Handles server HTTP requests.
     *
     * @param path - request path.
     * @param immutableRequest - immutable jetty specific request instance.
     * @param request - HTTP request.
     * @param response - HTTP response.
     */
    override fun handle(
            path: String,
            immutableRequest: Request,
            request: HttpServletRequest,
            response: HttpServletResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}