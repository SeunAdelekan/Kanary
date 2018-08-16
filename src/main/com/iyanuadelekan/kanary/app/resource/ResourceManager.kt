package com.iyanuadelekan.kanary.app.resource

import com.iyanuadelekan.kanary.app.framework.resource.ResourceManager as FrameworkResourceManager
import com.iyanuadelekan.kanary.exceptions.ResourceNotFoundException
import com.iyanuadelekan.kanary.exceptions.ResourceOverrideException

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Class in charge of managing application resources. App resources
 * are of varying types.
 * [Resource.Type.DATABASE], [Resource.Type.CACHE] and [Resource.Type.MAILER_SERVICE]
 * are the currently supported application resource types.
 */
object ResourceManager : FrameworkResourceManager() {

    /**
     * Retrieves a resource of the specified resource type.
     *
     * @param [resourceType] - the type of resource to be retrieved.
     * @return [Resource] - the required resource of it exists otherwise null is returned.
     * @throws [ResourceNotFoundException]
     */
    override fun getResource(resourceType: Resource.Type): Resource = ResourceRegistry.getResource(resourceType)

    /**
     * Registers the passed resource to the [ResourceRegistry].
     *
     * @param resource - resource to be registered.
     * @return [ResourceManager] - the resource manager instance.
     * @throws [ResourceOverrideException]
     */
    override fun register(resource: Resource): ResourceManager {
        if (ResourceRegistry.register(resource)) {
            return this
        }
        throw ResourceOverrideException(resource.resourceType)
    }

    /**
     * Checks if a resource of the specified type has been registered in the resource registry.
     *
     * @param resourceType - the type of resource.
     * @return [Boolean] - true if resource has been registered and false otherwise.
     */
    override fun hasRegisteredResource(resourceType: Resource.Type): Boolean
            = ResourceRegistry.hasRegisteredResource(resourceType)
}