package com.iyanuadelekan.kanary.app.framework.lifecycle

/**
 * @author Iyanu Adelekan on 25/11/2018.
 */
internal interface LifeCycle {

    fun onStart(event: () -> Unit)

    fun onStop(event: () -> Unit)
}