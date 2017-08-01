import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.handlers.AppHandler
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.response.send
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import com.iyanuadelekan.kanary.server.Server

/**
 * @author Iyanu Adelekan on 01/08/2017.
 */

fun main(args: Array<String>) {
    val app = KanaryApp()
    val server = Server()

    app.use { base, req, res -> run {
        println(req?.pathInfo)
        res?.withStatus(200)?.send("Hello world")
        base?.done()
    }}

    server.handler = AppHandler(app)
    server.listen(8080)

}