package com.iyanuadelekan.kanary.app.framework.lifecycle

import com.iyanuadelekan.kanary.app.LifeCycleEvent

/**
 * @author Iyanu Adelekan on 25/11/2018.
 */
internal interface LifeCycle {

    /**
     * Adds application start event.
     *
     * @param event
     */
    fun onStart(event: LifeCycleEvent)

    /**
     * Adds application stop event.
     *
     * @param event
     */
    fun onStop(event: LifeCycleEvent)
}