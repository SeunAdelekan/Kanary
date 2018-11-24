package app.router

import com.iyanuadelekan.kanary.app.router.RouteNode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Iyanu Adelekan on 24/11/2018.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteNodeTests {

    private val node = RouteNode("app").apply {
        addChild(RouteNode("version"))
    }

    @Test
    fun `Adds a child node successfully`() {
        assertEquals(node.getChildCount(), 1)
    }

    @Test
    fun `Return true on presence of child node`() {
        assertTrue(node.hasChild("version"))
    }

    @Test
    fun `Return false on absence of child node`() {
        assertFalse(node.hasChild("revision"))
    }

    @Test
    fun `Successfully gets a child node matching a specified path`() {
        assertTrue(node.getChild("version") is RouteNode)
    }

    @Test
    fun `Returns null when no child node matches a specified path`() {
        assertTrue(node.getChild("revision") == null)
    }

    @Test
    fun `Converts route node into correct string representation`() {
        val routeNode: RouteNode = RouteNode("users").apply {

            addChild(RouteNode("registrations") {
                println("User successfully registered.")
            })

            addChild(RouteNode("login") {
                println("User logged in.")
            })

            addChild(RouteNode("logout") {
                println("User logged out.")
            })
        }
        assertEquals(routeNode.toString(), "users => [registrations => [],login => [],logout => []]")
    }
}