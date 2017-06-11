package curious.cwitter

import com.fasterxml.jackson.databind.ObjectMapper
import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.response.sendJson
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.iyanuadelekan.kanary.helpers.http.request.getBody
import com.iyanuadelekan.kanary.helpers.http.response.send


class AuthController : KanaryController() {

    val db = DataHandler()

    override fun beforeAction(request: HttpServletRequest, response: HttpServletResponse?) {
        response?.setHeader("Access-Control-Allow-Origin", getOrigin(request.scheme))
        response?.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE")
        response?.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept, Authorization")
        response?.setHeader("Access-Control-Allow-Credentials", "true")
        db.init()
    }

    fun opt(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response withStatus 200 send "F*ck you CORS!!"
        baseRequest.done()
    }

    fun userSignUp(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        val requestJson = request.getBody()

        val fields = arrayOf("first_name", "last_name", "email", "pword")
        // TODO: Improve the basic request json fields validation, add email regex check and all that
        if (validateJSON(requestJson, fields)){

            val first_name = requestJson?.get("first_name")?.asText()
            val last_name = requestJson?.get("last_name")?.asText()
            val email = requestJson?.get("email")?.asText()
            val pword = requestJson?.get("pword")?.asText()

            if (db.validateEmail(email!!)){
                // Like I said, this is just a proof of concept
                // TODO: Password hashing
                val user: User? = db.registerUser(first_name!!, last_name!!, email, pword!!)

                if (user != null){
                    val dataNode = mapper.createObjectNode()
                    with(dataNode){
                        put("user_id", user.id)
                        put("first_name", user.firstName)
                        put("last_name", user.lastName)
                    }
                    responseRootNode.set("data", dataNode)
                    with(responseRootNode) {
                        put("status", "success")
                        put("message", "registration successful")
                    }
                    val userSessionId = CweetSessionMaker()
                    response.addCookie(userSessionId.cookie(user.id))
                } else {
                    with(responseRootNode) {
                        put("status", "error")
                        put("message", "registration errors")
                    }
                }
            } else {
                with(responseRootNode) {
                    put("status", "error")
                    put("message", "user account with the same email already exists")
                }
            }
        } else {
            with(responseRootNode) {
                put("status", "error")
                put("message", "incomplete or invalid details provided")
            }
        }
        response withStatus 201 sendJson responseRootNode
        baseRequest.done()
    }

    fun userSignIn(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        val requestJson = request.getBody()

        val fields = arrayOf("email", "pword")
        // TODO: Improve the basic request json fields validation, add email regex check and all that
        if (validateJSON(requestJson, fields)){

            val email = requestJson?.get("email")?.asText()
            val pword = requestJson?.get("pword")?.asText()

            val user: User? = db.fetchUser(email!!, pword!!)

            if (user != null){
                val dataNode = mapper.createObjectNode()
                with(dataNode){
                    put("user_id", user.id)
                    put("first_name", user.firstName)
                    put("last_name", user.lastName)
                    put("email", user.email)
                }
                responseRootNode.set("data", dataNode)
                with(responseRootNode) {
                    put("status", "success")
                    put("message", "login successful")
                }

                // create a cookie, which is very basic and crude btw
                val userSessionId = CweetSessionMaker()
                response.addCookie(userSessionId.cookie(user.id))

            } else {
                with(responseRootNode) {
                    put("status", "error")
                    put("message", "invalid email or password provided, pls try again")
                }
            }
        } else {
            with(responseRootNode) {
                put("status", "error")
                put("message", "incomplete or invalid details provided")
            }
        }
        response withStatus 200 sendJson responseRootNode
        baseRequest.done()
    }


    fun userSignOut(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        val requestJson = request.getBody()

        val fields = arrayOf("email", "user_id")
        if (validateJSON(requestJson, fields)) {
            val email = requestJson?.get("email")?.asText()
            val user_id = requestJson?.get("user_id")?.asInt()

            if (!db.validateEmail(email!!)) {
                db.updateSessionId(user_id!!, "")
            }
        }
        with(responseRootNode) {
            put("status", "success")
            put("message", "signout successful")
        }
        response withStatus 200 sendJson responseRootNode
        baseRequest.done()
    }

}