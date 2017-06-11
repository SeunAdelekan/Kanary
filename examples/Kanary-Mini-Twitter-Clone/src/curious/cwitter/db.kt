package curious.cwitter

import kotliquery.Row
import kotliquery.queryOf
import kotliquery.sessionOf
import java.time.ZonedDateTime
import java.util.*


data class User(
        val id: Int,
        val firstName: String?,
        val lastName: String?,
        val email: String?,
        val pword: String?,
        val createdAt: String?,
        val sessionId: String?)

data class Cweet(
        val id: Int,
        val text: String?,
        val creatorId: Int,
        val createdAt: String?)

data class TimelineCweet(
        val cweetId: Int,
        val text: String?,
        val creatorId: Int,
        val createdAt: String?,
        val firstName: String?,
        val lastName: String?)


class DataHandler {
    val session = sessionOf("jdbc:sqlite:cweet.db", "", "")

    val toUser: (Row) -> User = { row ->
        User(
                row.int("id"),
                row.stringOrNull("first_name"),
                row.stringOrNull("last_name"),
                row.stringOrNull("email"),
                row.stringOrNull("pword"),
                row.stringOrNull("created_at"),
                row.stringOrNull("session_id")
        )
    }

    val toCweet: (Row) -> Cweet = { row ->
        Cweet(
                row.int("id"),
                row.stringOrNull("text"),
                row.int("creator_id"),
                row.stringOrNull("created_at")
        )
    }

    val toTimelineCweet: (Row) -> TimelineCweet = { row ->
        TimelineCweet(
                row.int("cweet_id"),
                row.stringOrNull("text"),
                row.int("creator_id"),
                row.stringOrNull("created_at"),
                row.stringOrNull("first_name"),
                row.stringOrNull("last_name")
        )
    }


    fun init(): Unit {
        session.run(queryOf("""
              CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first_name CHAR NOT NULL,
                    last_name CHAR NOT NULL,
                    email CHAR NOT NULL,
                    pword CHAR NOT NULL,
                    created_at CHAR NOT NULL,
                    session_id CHAR NULL
                )
            """).asExecute)
        session.run(queryOf("""
              CREATE TABLE IF NOT EXISTS cweets (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    text CHAR NOT NULL,
                    creator_id INTEGER NOT NULL,
                    created_at CHAR NOT NULL
                )
            """).asExecute)
    }

    fun getCweets(): List<TimelineCweet> {
        return session.run(queryOf("select cweets.id as cweet_id, text, creator_id, cweets.created_at, users.first_name, last_name from cweets LEFT JOIN users ON users.id = creator_id").map(toTimelineCweet).asList)
    }

    fun getUserCweets(user_id: Int): List<TimelineCweet> {
        return session.run(queryOf("select cweets.id as cweet_id, text, creator_id, cweets.created_at, users.first_name, last_name from cweets LEFT JOIN users ON users.id = creator_id where users.id = ?", user_id).map(toTimelineCweet).asList)
    }

    fun postCweet(text: String, creator_id: Int): TimelineCweet? {
        var cweet: TimelineCweet? = null
        try {
            val insertQuery: String = "insert into cweets (text, creator_id, created_at) values (?, ?, ?)"
            session.run(queryOf(insertQuery, text, creator_id, java.time.ZonedDateTime.now()).asUpdate)
            val fetchQuery = queryOf("select cweets.id as cweet_id, text, creator_id, cweets.created_at, users.first_name, last_name from cweets LEFT JOIN users ON users.id = creator_id where creator_id = ? ORDER BY cweets.id DESC LIMIT 1", creator_id).map(toTimelineCweet).asSingle
            cweet = session.run(fetchQuery)
        } catch (e: Exception){
            e.printStackTrace()
        }
        return cweet
    }

    fun registerUser(first_name: String, last_name: String, email: String, hpword: String): User? {
        var user: User? = null
        try {
            val insertQuery: String = "insert into users (first_name, last_name, email, pword, created_at) values (?, ?, ?, ?, ?)"
            session.run(queryOf(insertQuery, first_name, last_name, email, hpword, java.time.ZonedDateTime.now()).asUpdate)
            user = fetchUser(email, hpword)
        } catch (e: Exception){
            e.printStackTrace()
        }
        return user
    }

    fun fetchUser(email: String, pword: String): User? {
        var user: User? = null
        try {
            val fetchQuery = queryOf("select id, first_name, last_name, email, pword, created_at, session_id from users where email = ? and pword = ?", email, pword).map(toUser).asSingle
            user = session.run(fetchQuery)
        } catch (e: Exception){
            e.printStackTrace()
        }
        return user
    }

    fun validateEmail(email: String): Boolean {
        val fetchQuery = queryOf("select id, first_name, last_name, email, pword, created_at, session_id from users where email = ?", email).map(toUser).asSingle
        val user: User? = session.run(fetchQuery)
        if (user != null && user.email == email){
            return false
        }
        return true
    }

    fun updateSessionId(user_id: Int, sessionId: String): Int{
        return session.run(queryOf("update users set session_id = ? where id = ?", sessionId, user_id).asUpdate)
    }

    fun fetchUserWithSessionId(sessionId: String): User? {
        val fetchQuery = queryOf("select id, first_name, last_name, email, pword, created_at, session_id from users where session_id = ?", sessionId).map(toUser).asSingle
        val user: User? = session.run(fetchQuery)
        return user
    }

}

