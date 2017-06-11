package curious.cwitter

import com.fasterxml.jackson.databind.JsonNode
import java.math.BigInteger
import java.security.SecureRandom
import javax.servlet.http.Cookie


fun getOrigin(scheme: String): String {
    // Not really doing anything right now
    // cos request scheme apparently always seem to be http
    val origins = arrayOf("http://cwitter.curious.com.ng", "https://cwitter.curious.com.ng")
    //val origins = arrayOf("", "http://localhost:8000")
    if (scheme == "https"){
        return origins[1]
    }
    return origins[1]
}

// bare bones request json fields validation
fun validateJSON(data: JsonNode?, fields: Array<String>): Boolean  {
    (0..fields.size-1).forEach { i ->
        if (data?.get(fields[i]) == null || data.get(fields[i]).asText() == ""){
            return false
        }
    }
    return true
}


class CweetSessionMaker {
    private val random = SecureRandom()
    val db = DataHandler()

    fun randomId(): String {
        return BigInteger(130, random).toString(32)
    }

    fun cookie(user_id: Int): Cookie {
        // shhh, don't even think this is cryptographically secure at all, it's not!!
        val sessionId = this.randomId()+this.randomId()
        db.updateSessionId(user_id, sessionId)
        val cookie: Cookie = Cookie("cweet_user", sessionId)
        //cookie.secure = true
        cookie.path = "/"
        cookie.maxAge = 86400 // 60*60*24, let the crude cookie expire after a day
        cookie.isHttpOnly = true
        return cookie
    }
}