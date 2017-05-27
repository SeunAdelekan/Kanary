package com.iyanuadelekan.kanary.core

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
* @author Iyanu Adelekan on 23/05/2017.
*/
open class KanaryController {

    /**
     * This is a stub meant to be overridden by child classes
     * of KanaryController.
     *
     * Before action is the single controller life cycle callback
     * of a KanaryController. It is invoked before any other
     * invokable target action.
     *
     * @param request Instance of [HttpServletRequest]
     * @param response Instancte of [HttpServletResponse]
     */
    fun beforeAction(request: HttpServletRequest, response: HttpServletResponse?) {}

}