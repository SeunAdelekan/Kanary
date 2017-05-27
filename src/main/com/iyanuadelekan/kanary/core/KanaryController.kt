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
     * Before action is a controller life cycle callback
     * of a KanaryController. It is invoked before any other
     * invokable target action.
     *
     * @param request Instance of [HttpServletRequest]
     * @param response Instance of [HttpServletResponse]
     */
    open fun beforeAction(request: HttpServletRequest, response: HttpServletResponse?) {}

    /**
     * This is a stub meant to be overridden by child classes
     * of KanaryController.
     *
     * After action is a life cycle callback that is invoked
     * after any invokable target action is completed.
     *
     * @param request Instance of [HttpServletRequest]
     * @param response Instance of [HttpServletResponse]
     */
    open fun afterAction(request: HttpServletRequest, response: HttpServletResponse?) {}

}