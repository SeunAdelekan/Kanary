package com.iyanuadelekan.kanary.app.security

import com.iyanuadelekan.kanary.app.framework.security.SecurityManager

/**
 * @author Iyanu Adelekan on 17/08/2018.
 */
object SecurityManager : SecurityManager {

    /**
     * Checks if a request is coming from a logged in user.
     *
     * @return [Boolean] - true if logged in and false otherwise.
     */
    override fun loggedIn(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Returns the credentials of the currently logged in user.
     */
    override fun getUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}