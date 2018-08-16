package com.iyanuadelekan.kanary.app.framework.lifecycle

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Root context class.
 */
abstract class Context {

    lateinit var request: HttpServletRequest
    lateinit var response: HttpServletResponse
}