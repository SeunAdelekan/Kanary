package com.iyanuadelekan.kanary.handlers

import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.constants.HttpConstants
import com.iyanuadelekan.kanary.core.Route
import com.iyanuadelekan.kanary.helpers.http.response.send
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import com.iyanuadelekan.kanary.utils.RequestUtils
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 25/05/2017.
 * @property app Instance of [KanaryApp]
 */
class AppHandler(val app: KanaryApp): AbstractHandler() {

    private val supportedMethods = arrayOf(HttpConstants.GET.name, HttpConstants.POST.name,
            HttpConstants.PUT.name, HttpConstants.DELETE.name, HttpConstants.PATCH.name, HttpConstants.OPTIONS.name)

    /**
     * Base handler for all incoming HTTP requests
     * @param target Target resource
     * @param baseRequest Mutable HTTP request instance
     * @param request Immutable HTTP request instance
     * @param response Instance used to handle server responses
     */
    override fun handle(target: String?, baseRequest: Request?, request: HttpServletRequest?, response: HttpServletResponse?) {
        runMiddleware(request)

        if (request != null) {
            if(isMethodSupported(request.method)) {
                if(request.method == HttpConstants.OPTIONS.name) {
                    response?.addHeader("Allow", "${HttpConstants.OPTIONS.name}, ${HttpConstants.GET.name}, " +
                            "${HttpConstants.POST.name}, ${HttpConstants.PUT.name}, ${HttpConstants.DELETE.name}, " +
                            HttpConstants.PATCH.name)

                    response?. withStatus(200)?. send("")
                } else {
                    val route: Route? = resolveTargetRoute(request.method, "$target")

                    if (route != null) {
                        val action = route.action
                        executeBeforeAction(route, request, response)

                        if(baseRequest != null && response != null) {
                            action.invoke(baseRequest, request, response)
                        }

                        executeAfterAction(route, request, response)
                    } else {
                        response?. withStatus(404)?. send("Method not found.")
                    }
                }
            } else {
                response?.addHeader("Allow", "${HttpConstants.OPTIONS.name}, ${HttpConstants.GET.name}, " +
                        "${HttpConstants.POST.name}, ${HttpConstants.PUT.name}, ${HttpConstants.DELETE.name}, " +
                        HttpConstants.PATCH.name)

                response?. withStatus(405)?. send("Method not allowed.")
            }
        }
    }

    /**
     * Tests if a request method is supported
     * @param method HTTP method to be tested
     * @return true if supported and false otherwise
     */
    private fun isMethodSupported(method: String): Boolean {
        return supportedMethods.filter({ it == method }).isNotEmpty()
    }

    /**
     * Method used for route resolution
     * @param method HTTP method of the request
     * @param target Target resource
     * @return route required to handle HTTP request
     */
    private fun resolveTargetRoute(method: String?, target: String?): Route? {
        var route: Route? = null
        var matchedRoutes: List<Route>
        val formattedTarget: String? = RequestUtils().formatTarget(target)

        app.routerList.forEach { router -> run {
            when(method) {
                HttpConstants.GET.name -> {
                    matchedRoutes = router.getRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    }
                }
                HttpConstants.POST.name -> {
                    matchedRoutes = router.postRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    }
                }
                HttpConstants.PUT.name -> {
                    matchedRoutes = router.putRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    }
                }
                HttpConstants.DELETE.name -> {
                    matchedRoutes = router.deleteRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    }
                }
                HttpConstants.PATCH.name -> {
                    matchedRoutes = router.patchRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    }
                }
            }
        } }
        return route
    }

    /**
     * Calls beforeAction life cycle callback of route's Associated controller
     * @param route Instance of [Route]
     * @param request Instance of [HttpServletRequest]
     * @param response Instance of [HttpServletResponse]
     */
    private fun executeBeforeAction(route: Route, request: HttpServletRequest, response: HttpServletResponse?) {
        route.controller?.beforeAction(request, response)
    }

    /**
     * Calls afterAction life cycle callback of route's Associated controller
     * @param route Instance of [Route]
     * @param request Instance of [HttpServletRequest]
     * @param response Instance of [HttpServletResponse]
     */
    private fun executeAfterAction(route: Route, request: HttpServletRequest, response: HttpServletResponse?) {
        route.controller?.afterAction(request, response)
    }

    /**
     * This executes all the middleware that have been queued
     * @param request Instance of [HttpServletRequest]
     */
    private fun runMiddleware(request: HttpServletRequest?) {
        app.middlewareList.forEach { middleware -> run {
            Thread(Runnable { middleware.invoke(request) }).start()
        }}
    }

}