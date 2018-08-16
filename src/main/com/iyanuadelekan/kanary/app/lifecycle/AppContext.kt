package com.iyanuadelekan.kanary.app.lifecycle

import com.iyanuadelekan.kanary.app.framework.lifecycle.Context

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Abstract class defines contextual characteristics and
 * behaviours exhibited by the application.
 */
abstract class AppContext : Context() {

    /**
     * Checks if a `next` middleware exists.
     *
     * @return true if `next` middleware exists and false otherwise.
     */
    abstract fun hasNextMiddleware(): Boolean

    /**
     * Checks if a `next` router exists.
     *
     * @return true if `next` router exists and false otherwise.
     */
    abstract fun hasNextRouter(): Boolean
}