package curious.cwitter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.iyanuadelekan.kanary.core.KanaryController
import com.iyanuadelekan.kanary.helpers.http.request.done
import com.iyanuadelekan.kanary.helpers.http.request.getBody
import com.iyanuadelekan.kanary.helpers.http.response.send
import com.iyanuadelekan.kanary.helpers.http.response.sendFile
import com.iyanuadelekan.kanary.helpers.http.response.sendJson
import com.iyanuadelekan.kanary.helpers.http.response.withStatus
import org.eclipse.jetty.server.Request
import java.io.File
import java.nio.file.Files
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TimelineController : KanaryController() {

    var user: User? = null
    val db = DataHandler()

    fun opt(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        response withStatus 200 send "F*ck you CORS!!"
        baseRequest.done()
    }

    override fun beforeAction(request: HttpServletRequest, response: HttpServletResponse?) {
        response?.setHeader("Access-Control-Allow-Origin", getOrigin(request.scheme))
        response?.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE")
        response?.setHeader("Access-Control-Allow-Headers", "X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept, Authorization")
        response?.setHeader("Access-Control-Allow-Credentials", "true")

        // try to find our cookie and check if the session id exists
        try {
            if (request.cookies != null){
                (0..request.cookies.size-1).forEach { i ->
                    if (request.cookies[i].name == "cweet_user"){
                        user = db.fetchUserWithSessionId(request.cookies[i].value)
                    }
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun afterAction(request: HttpServletRequest, response: HttpServletResponse?) {
        user = null
    }

    fun createCweet(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        if (user == null){
            with(responseRootNode) {
                put("status", "error")
                put("message", "unauthorized access")
            }
            response withStatus 401 sendJson responseRootNode
            baseRequest.done()
            return
        }

        val requestJson = request.getBody()
        val fields = arrayOf("text")
        if (validateJSON(requestJson, fields)){
            val text = requestJson?.get("text")?.asText()
            val cweet = db.postCweet(text!!, user!!.id)
            val dataNode = mapper.createObjectNode()
            with(dataNode){
                put("cweet_id", cweet?.cweetId)
                put("user_id", cweet?.creatorId)
                put("text", cweet?.text)
                put("timestamp", cweet?.createdAt)
                put("first_name", cweet?.firstName)
                put("last_name", cweet?.lastName)
            }
            responseRootNode.set("data", dataNode)
            with(responseRootNode) {
                put("status", "success")
                put("message", "new cweet posted successfully")
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

    fun fetchFeed(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        if (user == null){
            with(responseRootNode) {
                put("status", "error")
                put("message", "unauthorized access")
            }
            response withStatus 401 sendJson responseRootNode
            baseRequest.done()
            return
        }

        val cweets = db.getCweets()

        val dataNode = mapper.createArrayNode()
        val userNode = mapper.createObjectNode()

        (0..cweets.size-1).forEach { i ->
            val cweetNode = mapper.createObjectNode()
            with(cweetNode){
                put("cweet_id", cweets[i].cweetId)
                put("user_id", cweets[i].creatorId)
                put("text", cweets[i].text)
                put("timestamp", cweets[i].createdAt)
                put("first_name", cweets[i].firstName)
                put("last_name", cweets[i].lastName)
            }
            dataNode.add(cweetNode)
        }

        responseRootNode.set("data", dataNode)

        with(userNode) {
            put("user_id", user?.id)
            put("first_name", user?.firstName)
            put("last_name", user?.lastName)
            put("email", user?.email)
        }

        responseRootNode.set("user", userNode)

        with(responseRootNode) {
            put("status", "success")
            put("message", "user timeline")
        }

        response withStatus 200 sendJson responseRootNode
        baseRequest.done()
    }

    fun fetchUserFeed(baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
        // Create json object mapper
        val mapper = ObjectMapper()
        val responseRootNode = mapper.createObjectNode()

        if (user == null){
            with(responseRootNode) {
                put("status", "error")
                put("message", "unauthorized access")
            }
            response withStatus 401 sendJson responseRootNode
            baseRequest.done()
            return
        }

        val requestJson = request.getBody()
        val fields = arrayOf("user_id")
        if (validateJSON(requestJson, fields)){

            val user_id = requestJson?.get("user_id")?.asInt()
            val cweets = db.getUserCweets(user_id!!)


            val currentUserNode = mapper.createObjectNode()

            with(currentUserNode) {
                put("user_id", user?.id)
                put("first_name", user?.firstName)
                put("last_name", user?.lastName)
                put("email", user?.email)
            }

            responseRootNode.set("user", currentUserNode)

            if (cweets.isEmpty()){
                with(responseRootNode) {
                    put("status", "error")
                    put("message", "user profile feed not found")
                }
                response withStatus 200 sendJson responseRootNode
                baseRequest.done()
                return
            }

            val dataNode = mapper.createArrayNode()
            val userNode = mapper.createObjectNode()

            (0..cweets.size-1).forEach { i ->
                val cweetNode = mapper.createObjectNode()
                with(cweetNode){
                    put("cweet_id", cweets[i].cweetId)
                    put("user_id", cweets[i].creatorId)
                    put("text", cweets[i].text)
                    put("timestamp", cweets[i].createdAt)
                    put("first_name", cweets[i].firstName)
                    put("last_name", cweets[i].lastName)
                }
                dataNode.add(cweetNode)
            }

            responseRootNode.set("data", dataNode)

            if (cweets.isNotEmpty()){
                with(userNode) {
                    put("user_id", cweets[0].creatorId)
                    put("first_name", cweets[0].firstName)
                    put("last_name", cweets[0].lastName)
                }
                responseRootNode.set("profile_user", userNode)
            }

            with(responseRootNode) {
                put("status", "success")
                put("message", "user profile feed")
            }
        } else {
            with(responseRootNode) {
                put("status", "error")
                put("message", "user id not found in request")
            }
        }

        response withStatus 200 sendJson responseRootNode
        baseRequest.done()
    }

}