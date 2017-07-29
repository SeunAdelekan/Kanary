package curious.cwitter


import com.iyanuadelekan.kanary.app.KanaryApp
import com.iyanuadelekan.kanary.core.KanaryRouter
import com.iyanuadelekan.kanary.handlers.AppHandler
import com.iyanuadelekan.kanary.server.Server
import javax.servlet.http.HttpServletRequest


val requestLogger: (HttpServletRequest?) -> Unit = {
    if(it != null && it.method != null && it.pathInfo != null) {
        println("Started ${it.scheme} ${it.method} request to: '${it.pathInfo}'")
    }
}



fun main(args: Array<String>) {

    val app = KanaryApp()
    val server = Server()
    val cweetRouter = KanaryRouter()

    val authController = AuthController()
    val timelineController = TimelineController()

    cweetRouter on "auth/" use authController
    cweetRouter.post("signin/", authController::userSignIn)
    cweetRouter.post("signup/", authController::userSignUp)
    cweetRouter.post("signout/", authController::userSignOut)
    // Preflight *mdfkr, this will be refactored later
    cweetRouter.options("signin/", authController::opt)
    cweetRouter.options("signout/", authController::opt)
    cweetRouter.options("signup/", authController::opt)

    cweetRouter on "cweet/" use timelineController
    cweetRouter.post("new/", timelineController::createCweet)
    cweetRouter.post("profile/", timelineController::fetchUserFeed)
    cweetRouter.get("timeline/", timelineController::fetchFeed)
    // Preflight *mdfkr
    cweetRouter.options("new/", timelineController::opt)
    cweetRouter.options("profile/", timelineController::opt)

    // So, the options routes are basically there to respond to Preflight requests
    // which happens when you're making a cross domain XMLHTTPRequest

    app.mount(cweetRouter)
    app.use(requestLogger)
    server.handler = AppHandler(app)

    //server.listen(Integer.valueOf(System.getenv("PORT"))) // for Heroku deployment
    server.listen(8080) // for local development

}