package com.iyanuadelekan.kanary.app.handler

import com.iyanuadelekan.kanary.app.App
import com.iyanuadelekan.kanary.app.constant.Protocol
import com.iyanuadelekan.kanary.app.constant.RouteType
import com.iyanuadelekan.kanary.app.data.GenericResponse
import com.iyanuadelekan.kanary.app.RequestHandler
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
            this.immutableRequest = immutableRequest
        }

        if (RouteType.methodSet.contains(request.method)) {
            val res = app.resolveRoute(path, RouteType.methodSet[request.method] as RouteType)

            if (res != null) {
                val (router, routeNode) = res

                /**
                 * An appropriate route node exists for the HTTP request with the given target.
                 * Hence we firstly run the application level middleware to work on the request.
                 */
                app.runMiddleware()
                /**
                 * Execute beforeAction callback.
                 */
                router.executeBeforeAction(app)
                /**
                 * Next, we run the route specific middleware.
                 */
                routeNode.runMiddleWare(app)
                /**
                 * Execute the corresponding action to the given route.
                 */
                routeNode.executeAction(app)
                /**
                 * Lastly, execute afterAction callback.
                 */
                router.executeAfterAction(app)

                return
            }

            /**
             * No route exists for the requested URL target. Application returns a 404 response to client.
             */
            with(app.response) {
                withStatus(404)
                send(GenericResponse("error", "Resource not found."))
            }
        } else {
            /**
             * The received method is not supported by Kanary at this time. Hence app returns a 405 response
             * to the client.
             */
            with(app.response) {
                addHeader("Allow", RouteType.methodSet.keys.toString())
                withStatus(405)
                send(GenericResponse("error", "Method not allowed."))
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
}