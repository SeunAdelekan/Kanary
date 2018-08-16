package com.iyanuadelekan.kanary.app.framework.consumer

/**
 * @author Iyanu Adelekan on 16/08/2018
 *
 * Base consumer interface to be extended by framework component
 * consumers.
 *
 * This interface enforces a set of basic behaviours that must be
 * exhibited by component consumers. These behaviours are:
 * - The addition of components to a declared list if the appropriate
 *   component type.
 * - The ability to retrieve components for consumption.
 * - The ability to check if a full iteration has been completed.
 *
 * Some currently available consumers are [MiddlewareConsumer] and [RouterConsumer].
 */
internal interface BaseConsumer<T> {

    /**
     * Adds one or more components to the maintained component list.
     *
     * @param components - components to be added to list.
     */
    fun use(vararg components: T)

    /**
     * Retrieves the next component in the component list
     *
     * @return [T] - The next returnable component.
     */
    fun next(): T

    /**
     * Checks if a next component exists in the component list.
     *
     * @return [Boolean] - true if the component exists and false otherwise.
     */
    fun hasNext(): Boolean
}