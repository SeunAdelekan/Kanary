package com.iyanuadelekan.kanary.app.framework.resource

import com.iyanuadelekan.kanary.app.resource.Resource
import java.util.*

/**
 * @author Iyanu Adelekan on 16/08/2018.
 */
internal abstract class ResourceRegistry {

    /**
     * @property [resources] - [HashSet] of registered resource types.
     */
    protected val registry: Hashtable<Resource.Type, Resource> = Hashtable()

    /**
     * Retrieves the registered resource of the specified [Resource.Type].
     *
     * @param resourceType - type of resource to be retrieved.
     * @return [Resource] - returns the required resource if registered else null.
     */
    abstract fun getResource(resourceType: Resource.Type): Resource

    /**
     * Registers a resource in the app resource registry.
     *
     * @param resource - resource being registered.
     * @return [Boolean] - true if resource was successfully registered else false.
     */
    abstract fun register(resource: Resource): Boolean

    /**
     * Checks if a resource of the specified type has been registered.
     *
     * @param [resourceType] - type of resource.
     * @return [Boolean] - true if resource has been registered else false.
     */
    abstract fun hasRegisteredResource(resourceType: Resource.Type): Boolean
}