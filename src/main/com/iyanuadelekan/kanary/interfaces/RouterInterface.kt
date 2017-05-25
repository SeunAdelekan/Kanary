/**
 * @author Iyanu Adelekan
 */

package com.iyanuadelekan.kanary.interfaces

import com.iyanuadelekan.kanary.core.KanaryRouter
import org.eclipse.jetty.server.Request
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface RouterInterface {

    fun get(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter

    fun post(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter

    fun put(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter

    fun delete(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter

    fun patch(path: String, action: (Request, HttpServletRequest, HttpServletResponse) -> Unit): KanaryRouter

}