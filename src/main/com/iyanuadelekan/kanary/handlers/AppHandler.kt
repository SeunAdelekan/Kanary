package com.iyanuadelekan.kanary.handlers

import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.constants.HttpConstants
import com.iyanuadelekan.kanary.core.Route
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

    /**
     * Base handler for all incoming HTTP requests
     * @param target Target resource
     * @param baseRequest Mutable HTTP request instance
     * @param request Immutable HTTP request instance
     * @param response Instance used to handle server responses
     */
    override fun handle(target: String?, baseRequest: Request?, request: HttpServletRequest?, response: HttpServletResponse?) {
        if (request != null) {
            val route: Route? = resolveTargetRoute(request.method, "$target")

            if (route != null) {
                val action = route.action

                if(baseRequest != null && response != null) {
                    action.invoke(baseRequest, request, response)
                }
            }
        }
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
                    } else {

                    }
                }
                HttpConstants.POST.name -> {
                    matchedRoutes = router.postRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    } else {

                    }
                }
                HttpConstants.PUT.name -> {
                    matchedRoutes = router.putRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    } else {

                    }
                }
                HttpConstants.DELETE.name -> {
                    matchedRoutes = router.deleteRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    } else {

                    }
                }
                HttpConstants.PATCH.name -> {
                    matchedRoutes = router.patchRouteList.filter { it.path == formattedTarget }
                    if(matchedRoutes.isNotEmpty()) {
                        route = matchedRoutes[0]
                    } else {

                    }
                }
            }
        } }
        return route
    }

}