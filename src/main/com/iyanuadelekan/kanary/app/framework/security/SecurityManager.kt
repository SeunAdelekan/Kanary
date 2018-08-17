package com.iyanuadelekan.kanary.app.framework.security

import com.iyanuadelekan.kanary.app.adapter.service.security.SecurityService

/**
 * @author Iyanu Adelekan on 17/08/2018.
 *
 * Interface defining contract for framework's security manager.
 */
interface SecurityManager {

    /**
     * Checks if a request is coming from a logged in user.
     *
     * @return [Boolean] - true if logged in and false otherwise.
     */
    fun loggedIn(): Boolean

    /**
     * Returns the credentials of the currently logged in user.
     */
    fun getUser()

    /**
     * Invoked to register a custom security service to the application.
     *
     * @param service - security service to be registered.
     */
    fun registerService(service: SecurityService)
}