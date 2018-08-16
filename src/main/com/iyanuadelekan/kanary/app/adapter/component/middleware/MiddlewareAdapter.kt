package com.iyanuadelekan.kanary.app.adapter.component.middleware

import com.iyanuadelekan.kanary.app.framework.lifecycle.Context

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Abstract class defining behaviours that must be exhibited
 * by application compatible middleware components.
 */
abstract class MiddlewareAdapter {

    /**
     * Runs the defined middleware.
     *
     * @param ctx - server context.
     */
    abstract fun run(ctx: Context)
}