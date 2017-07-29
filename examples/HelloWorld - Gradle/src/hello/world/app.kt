package hello.world


import com.fasterxml.jackson.databind.ObjectMapper
import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.core.KanaryRouter
import com.iyanuadelekan.kanary.handlers.AppHandler
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.response.sendJson
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import com.iyanuadelekan.kanary.server.Server
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


val requestLogger: (HttpServletRequest?) -> Unit = {
    if(it != null && it.method != null && it.pathInfo != null) {
        println("Started ${it.scheme} ${it.method} request to: '${it.pathInfo}'")
    }
}

class HelloWorldController : KanaryController() {

    fun hello(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()
        with(responseRootNode) {
            put("hello", "world")
        }
        response withStatus 201 sendJson responseRootNode
        baseRequest.done()
    }

}

fun main(args: Array<String>) {

    println("Visit http://localhost:8080/hello/world to see the output")

    val app = KanaryApp()
    val server = Server()
    val helloWorldRouter = KanaryRouter()

    val helloWorldController = HelloWorldController()

    helloWorldRouter on "hello/" use helloWorldController
    helloWorldRouter.get("world/", helloWorldController::hello)

    app.mount(helloWorldRouter)
    app.use(requestLogger)
    server.handler = AppHandler(app)

    //server.listen(Integer.valueOf(System.getenv("PORT"))) // for Heroku deployment
    server.listen(8080) // for local development

}