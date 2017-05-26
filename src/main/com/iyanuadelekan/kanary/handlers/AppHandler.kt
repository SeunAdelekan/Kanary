package com.iyanuadelekan.kanary.handlers

import com.iyanuadelekan.kanary.UserController
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
        val formattedTarget: String? = RequestUtils().formatTarget(target)

        app.routerList.forEach { router -> run {
            when(method) {
                HttpConstants.GET.name -> {
                    route = router.getRouteList.filter { it.path == formattedTarget }[0]
                }
                HttpConstants.POST.name -> {
                    route = router.postRouteList.filter { it.path == formattedTarget }[0]
                }
                HttpConstants.PUT.name -> {
                    route = router.putRouteList.filter { it.path == formattedTarget }[0]
                }
                HttpConstants.DELETE.name -> {
                    route = router.deleteRouteList.filter { it.path == formattedTarget }[0]
                }
                HttpConstants.PATCH.name -> {
                    route = router.patchRouteList.filter { it.path == formattedTarget }[0]
                }
            }
        } }
        return route

    }
}