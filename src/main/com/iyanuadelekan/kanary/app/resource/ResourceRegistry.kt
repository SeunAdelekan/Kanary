package com.iyanuadelekan.kanary.app.resource

import com.iyanuadelekan.kanary.app.framework.resource.ResourceRegistry
import com.iyanuadelekan.kanary.exceptions.ResourceNotFoundException

/**
 * @author Iyanu Adelekan on 16/08/2018.
 */
internal object ResourceRegistry : ResourceRegistry() {

    /**
     * Retrieves the registered resource of the specified [Resource.Type].
     *
     * @param resourceType - type of resource to be retrieved.
     * @return [Resource] - returns the required resource if registered else null.
     * @throws [ResourceNotFoundException]
     */
    @Throws(ResourceNotFoundException::class)
    override fun getResource(resourceType: Resource.Type): Resource {
        if (hasRegisteredResource(resourceType)) {
            return registry[resourceType] as Resource
        }
        throw ResourceNotFoundException(resourceType)
    }

    /**
     * Registers a resource in the app resource registry.
     *
     * @param resource - resource being registered.
     * @return [Boolean] - true if resource was successfully registered else false.
     */
    override fun register(resource: Resource): Boolean {
        if (!hasRegisteredResource(resource.resourceType)) {
            registry[resource.resourceType] = resource
            return true
        }
        return false
    }

    /**
     * Checks if a resource of the specified type has been registered.
     *
     * @param [resourceType] - type of resource.
     * @return [Boolean] - true if resource has been registered else false.
     */
    override fun hasRegisteredResource(resourceType: Resource.Type): Boolean = registry.contains(resourceType)
}