package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.App
import com.iyanuadelekan.kanary.app.constant.Protocol
import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.framework.annotation.RequestHandler
import com.iyanuadelekan.kanary.app.framework.annotation.ResponseEntity
import com.iyanuadelekan.kanary.app.send
import com.iyanuadelekan.kanary.app.withStatus
import com.iyanuadelekan.kanary.helpers.http.request.done
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
internal class AppRequestHandler(val app: App) : AbstractHandler() {

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
            response: HttpServletResponse
    ) {
        with(app) {
            this.request = request
            this.response = response
        }

        if (RouteType.methodSet.contains(request.method)) {
            /**
             * TODO complete HTTP request routing logic.
             */
            with(app.response) {
                withStatus(200)
                send(ResourceNotFoundVO("success", "Okay"))
            }
        } else {
            with(app.response) {
                addHeader("Allow", RouteType.methodSet.toString())
                withStatus(405)
                send(ResourceNotFoundVO("error", "Method not allowed."))
            }
        }
        completeHttpTransaction(immutableRequest, request, response)
    }

    /**
     * Invoked to complete the HTTP transaction cycle.
     *
     * @param immutableRequest - Immutable [Request] object.
     * @param request - Mutable [HttpServletRequest] object.
     * @param response - Instance of [HttpServletResponse].
     */
    private fun completeHttpTransaction(
            immutableRequest: Request,
            request: HttpServletRequest,
            response: HttpServletResponse
    ) {
        response.addHeader("Powered-By", "Kanary")
        immutableRequest.done()
    }

    @ResponseEntity
    private data class ResourceNotFoundVO(val status: String, val message: String)
}