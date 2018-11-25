package com.iyanuadelekan.kanary.app.lifecycle

import com.iyanuadelekan.kanary.app.LifeCycleEvent
import com.iyanuadelekan.kanary.app.framework.lifecycle.LifeCycleManager as FrameworkLifeCycleManager

/**
 * @author Iyanu Adelekan on 25/11/2018.
 */
class LifeCycleManager : FrameworkLifeCycleManager() {

    private lateinit var startEvent: LifeCycleEvent
    private lateinit var stopEvent: LifeCycleEvent

    /**
     * Invokes start event.
     */
    override fun onStart() {
        if (this::startEvent.isInitialized) {
            startEvent()
        }
    }

    /**
     * Invokes stop event.
     */
    override fun onStop() {
        if (this::stopEvent.isInitialized) {
            stopEvent()
        }
    }

    /**
     * Invoked to add an onStart listener.
     *
     * @param listener - start event listener.
     */
    override fun addStartEvent(listener: LifeCycleEvent) {
        startEvent = listener
    }

    /**
     * Invoked to add an onStop listener.
     *
     * @param listener - stop event listener.
     */
    override fun addStopEvent(listener: LifeCycleEvent) {
        stopEvent = listener
    }
}