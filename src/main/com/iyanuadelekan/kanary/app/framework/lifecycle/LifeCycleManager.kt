package com.iyanuadelekan.kanary.app.framework.lifecycle

import com.iyanuadelekan.kanary.app.LifeCycleEvent

/**
 * @author Iyanu Adelekan on 25/11/2018.
 */
abstract class LifeCycleManager {

    /**
     * Invokes start event.
     */
    abstract fun onStart()

    /**
     * Invokes stop event.
     */
    abstract fun onStop()

    /**
     * Invoked to add an onStart listener.
     *
     * @param listener - start event listener.
     */
    abstract fun addStartEvent(listener: LifeCycleEvent)

    /**
     * Invoked to add an onStop listener.
     *
     * @param listener - stop event listener.
     */
    abstract fun addStopEvent(listener: LifeCycleEvent)
}