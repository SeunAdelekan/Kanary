package com.iyanuadelekan.kanary.app.lifecycle

import com.iyanuadelekan.kanary.app.framework.lifecycle.Context
import com.iyanuadelekan.kanary.app.framework.resource.ResourceManager
import com.iyanuadelekan.kanary.app.framework.security.SecurityManager
import com.iyanuadelekan.kanary.app.resource.Resource
import com.iyanuadelekan.kanary.exceptions.ResourceNotFoundException
import com.iyanuadelekan.kanary.app.resource.ResourceManager as AppResourceManager
import com.iyanuadelekan.kanary.app.security.SecurityManager as AppSecurityManager

/**
 * @author Iyanu Adelekan on 16/08/2018.
 *
 * Abstract class enforcing contextual characteristics and
 * behaviours exhibited by the application.
 */
abstract class AppContext : Context() {

    /**
     * @property [resourceManager] - Application's [ResourceManager] instance.
     */
    val resourceManager: ResourceManager = AppResourceManager

    /**
     * @property [resourceManager] - Application's [SecurityManager] instance.
     */
    val securityManager: SecurityManager = AppSecurityManager

    /**
     * Checks if a `next` router exists.
     *
     * @return true if `next` router exists and false otherwise.
     */
    abstract fun hasNextRouter(): Boolean

    /**
     * Returns a specified resource if available.
     *
     * @param resourceType - type or resource to be retrieved.
     * @throws [ResourceNotFoundException]
     */
    abstract fun getResource(resourceType: Resource.Type): Resource
}